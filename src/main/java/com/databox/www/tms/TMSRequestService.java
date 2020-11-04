/*
 * TMSRequestService.java
 *
 */
package com.databox.www.tms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.databox.www.model.TmsResult;

import lombok.extern.slf4j.Slf4j;


/**
 * Request Service
 *
 */
@Slf4j
@ConfigurationProperties
public class TMSRequestService
{
	private static final String TPMSG_SUCCESS_TR       = "00000";    // Complete
	private static final String TPMSG_UNKNOWN_TR       = "90001";    // UNKNOWN AP
	private static final String TPMSG_TRXFAIL_TR       = "99999";    // transaction failed

	private static final int    MAX_REQUEST_SIZE       = 1024 * 64;
	private static final int    MAX_RESPONSE_SIZE      = 1024 * 64;
	private static final int    RCV_BUF_LENGTH         = MAX_RESPONSE_SIZE;
	private static final int    ANSWER_BUF_SIZE        = MAX_RESPONSE_SIZE;
	private static final int    BDX_BUF_SIZE           = MAX_RESPONSE_SIZE;
	private static final int    XAPI_LARGE_TX          = RCV_BUF_LENGTH;

	private static final int    TMSHDR_SIZE            = 100;
	private static final int    TMSHDR_SZ_LEN          =  10;
	private static final int    USERHDR_SIZE           = 210;
	private static final int    USERHDR_SZ_LEN         =  10;

	private static final String TMS_HEADER_FORMAT      = "%010d%-5s%-6s%-10s%-10s%-5s%-44s%-10s";
	private static final String USR_HEADER_FORMAT      = "%010d%-1s%-8s%-10s%-6s%-1s%-1s%-1s%-1s%-5s%-16s%-9s%05d%010d%012d%010d%010d%-7s%-2s%-4s%-5s%-20s%-56s";

	private static final int    INTPUTSTREAM_READ_RETRY_COUNT = 10;


	private TMSConnection pools = null;


	public TMSRequestService(TMSConnection pools) {
		this.pools = pools;
	}


	/**
	 * StreamNRead
	 * 
	 */
	public String StreamNRead(InputStream in, int len) throws Exception
	{
		java.io.ByteArrayOutputStream bout = new java.io.ByteArrayOutputStream();
		int bcount = 0;
		byte[] buf = new byte[2048];
		int read_retry_count = 0;

		while( bcount < len )
		{
			int n = in.read(buf, 0, len-bcount < 2048 ? len-bcount : 2048 );
			
			if ( n == -1 )
				throw new java.io.IOException("inputstream has returned an unexpected EOF");
			
			if ( n == 0 && (++read_retry_count == INTPUTSTREAM_READ_RETRY_COUNT) )
				throw new java.io.IOException("inputstream-read-retry-count( " + INTPUTSTREAM_READ_RETRY_COUNT + ") exceed !");
			
			if ( n != 0 )
			{
				bcount += n;
				bout.write(buf,0,n);
			}
		}

		bout.flush();

		byte[] textArray = bout.toByteArray();
		
		return new String(textArray, "euc-kr");
	}



	public TmsResult request(String service, String sessid, String uid, String input, int seqNbr, int seqKey, String ctInfo) throws IOException
	//public synchronized TMSResult request(String service, String sessid, String uid, String input, int seqNbr, int seqKey, String ctInfo) throws IOException
	{
		log.info("======== request start : {} =========", service);
		String  outputStr = null;
		String  outputAll = null;
		boolean isSuccess = false;
		boolean isZip     = false;
		String  msgCode   = null;
		String  msgCtInfo = null;
		int     msgSeqNbr = 0;
		int     msgSeqKey = 0;
		int     msgDatLen = 0;
		int     msgZipLen = 0;
		int     msgRecCnt = 0;

		log.info("::::::::::TMS:::::::::");
		log.info(":::: service : {} :::::::::", service);
		//log.info(":::: sessid  : {} :::::::::", sessid );
		log.info(":::: uid     : {} :::::::::", uid    );
		log.info(":::: input   : {} :::::::::", input  );

		TmsResult tmsResult = new TmsResult();
		//TMSResult tmsResult = new TMSResult();
		//TmsResult tmsResult = null;


		TMSConnector con = null;
		DataInputStream  br = null;
		DataOutputStream bw = null;


		/**
		 * Request some service
		 */
		try
		{
			// get Connection
			con = pools.getConnection();
			log.info("con {} ",con);

			if( con == null )
			{
				outputStr = "";
				msgCode   = "99999";
				msgCtInfo = "";
				msgSeqNbr = 0;
				msgSeqKey = 0;
				msgDatLen = 0;
				msgZipLen = 0;
				msgRecCnt = 0;
				isSuccess = false;
				isZip     = false;

				System.out.println("System is busy!! Retry to after a while");
				
				log.error("System is busy!! Retry to after a while");

				log.info("::::::::::TMS:::::::::");
				//log.info(":::: outputStr : {} :::::::::", outputStr);
				log.info(":::: isSuccess : {} :::::::::", isSuccess);
				log.info(":::: msgCode   : {} :::::::::", msgCode);
				//log.info(":::: msgSeqNbr : {} :::::::::", msgSeqNbr);
		
		
				tmsResult.setOutput(outputStr);
				tmsResult.setSuccess(isSuccess);
				tmsResult.setMsgCode(msgCode);
				tmsResult.setMsgSeqNbr(msgSeqNbr);
				tmsResult.setMsgSeqKey(msgSeqKey);
				tmsResult.setZip(isZip);
				tmsResult.setMsgDatLen(msgDatLen);
				tmsResult.setMsgRecCnt(msgRecCnt);
				tmsResult.setMsgCtInfo(msgCtInfo);
				
				log.info("======== request end : {} =========", service);
				
				return tmsResult;
			}
			

			bw  = new DataOutputStream(con.clientSocket.getOutputStream());
			br  = new DataInputStream (con.clientSocket.getInputStream() );


			// get input msglen
			int    msglen = input.getBytes("euc-kr").length;


			// TMS HEADER
			int    tmslen          = TMSHDR_SIZE - TMSHDR_SZ_LEN + USERHDR_SIZE + msglen;
			String tmstr           = "SVC_I";
			String tmsservice_name = service;
			String tmsclid         = " ";
			String tmscd           = " ";
			String tmserrcd        = " ";
			String tmssreserved    = " ";
			String tmscreserved    = " ";

			String strTmsHeader = String.format(TMS_HEADER_FORMAT, tmslen, tmstr, tmsservice_name, tmsclid, tmscd
			                                              , tmserrcd, tmssreserved, tmscreserved );


			// USR HEADER
			int    usrlen          = USERHDR_SIZE - USERHDR_SZ_LEN + msglen;
			String usrzip          = "N";
			String usruid          = uid;
			String usrclid         = " ";
			String usrtr           = service;
			String usrctype        = " ";
			String usrdtype        = "j";  // json: j
			//String usrdtype        = " ";  // hts
			String usrsrvtp        = " ";
			String usrerrtp        = "0";
			String usrerrcd        = "00000";
			String usrctinfo       = ctInfo;
			String usrsrvinfo      = " ";
			int    usrseg_nbr      = seqNbr;
			int    usrrec_cnt      = 0;
			int    usrseg_key      = seqKey;
			int    usrdat_len      = msglen;
			int    usrzip_len      = msglen;
			String usrmemb_id      = " ";
			String usrchnl_cd      = " ";
			String usrscrn_no      = " ";
			String usrteam_cd      = " ";
			String usrsess_id      = sessid;
			String usrreserved2    = " ";

			String strUsrHeader = String.format(USR_HEADER_FORMAT, usrlen     , usrzip    , usruid    , usrclid,   usrtr
			                                                     , usrctype   , usrdtype  , usrsrvtp  , usrerrtp
			                                                     , usrerrcd   , usrctinfo , usrsrvinfo, usrseg_nbr
			                                                     , usrrec_cnt , usrseg_key, usrdat_len, usrzip_len, usrmemb_id
			                                                     , usrchnl_cd , usrscrn_no, usrteam_cd, usrsess_id, usrreserved2 );


			String strMsg = strTmsHeader + strUsrHeader + input;

			//System.out.println("input:[" + strMsg + "]");
			////log.info(input);

			byte[] byteBuffer   = strMsg.getBytes("euc-kr");

			bw.write(byteBuffer);
			bw.flush();

			////log.info(byteBuffer.toString());
		}
		catch( Exception e )
		{
			////log.info("Service call failed. Please contact technical support.");
		}


		/**
		 * Waiting the reply of answer
		 */
		try
		{
			boolean runFlag = true;

			int pktlen = 0;
			int msglen = 0;

			String strLen = "";
			String strMsg = "";
			String strPkt = "";

			NSvcInfo   tms = new NSvcInfo();
			NHeader    hdr = new NHeader();
			NPacket    pkt = new NPacket();

			while(runFlag)
			{
				pktlen = 0;
				msglen = 0;

				strLen = "";
				strMsg = "";
				strPkt = "";

				// System.out.println(" first reading....");

				// read 10 bytes
				strLen = StreamNRead(br, 10);
				pktlen = Integer.parseInt(strLen.trim());
				//System.out.println( "pktlen = " +  pktlen);

				// System.out.println(" second reading....: " + strLen);

				// read remain  bytes
				strMsg = StreamNRead(br, pktlen);

				strPkt = strLen + strMsg;

				//System.out.println("Output => [" + strPkt +"]");

				byte[] byteBuffer   = strPkt.getBytes("euc-kr");
				//outputStr = new String(byteBuffer, "UTF-8");

				// log.info("byteBuffer ::: {} ", byteBuffer);
				// log.info("outputStr  ::: {} ", outputStr);

				tms = pkt.ParseSvcInfo(byteBuffer);

//				System.out.println("tmslen     =  [" + tms.len      + "]");
//				System.out.println("tmstr      =  [" + tms.tr       + "]");
//				System.out.println("tmsservice =  [" + tms.service  + "]");
//				System.out.println("tmserrcd   =  [" + tms.errcd    + "]");

				if( tms.service.equals("HLGOUT") )
					continue;

				if( tms.tr.equals("HBPKT") )
					continue; //tms.errcd = "00000";

				if( tms.tr.equals("BROAD") )
					continue;


				if( tms.errcd.equals(TPMSG_SUCCESS_TR) )
				{
					if( tms.tr.equals("HBPKT") )
					{
						System.out.println("Heat Beat received!!");
						continue;
					}
					else if( tms.tr.equals("BROAD") )
					{
						System.out.println("Broadcasting msg received!!");
						continue;
					}
					else if( tms.tr.equals("SVC_O") )
					{
						hdr = pkt.ParseHeader (byteBuffer);

//						System.out.println("len        =  [" + hdr.len         + "]");
//						System.out.println("zip        =  [" + hdr.zip         + "]");
//						System.out.println("uid        =  [" + hdr.uid         + "]");
//						System.out.println("tr         =  [" + hdr.tr          + "]");
//						System.out.println("errtp      =  [" + hdr.errtp       + "]");
//						System.out.println("errcd      =  [" + hdr.errcd       + "]");
//						System.out.println("ctinfo     =  [" + hdr.ctinfo      + "]");
//						System.out.println("seg_nbr    =  [" + hdr.seg_nbr     + "]");
//						System.out.println("seg_key    =  [" + hdr.seg_key     + "]");
//						System.out.println("dat_len    =  [" + hdr.dat_len     + "]");
//						System.out.println("zip_len    =  [" + hdr.zip_len     + "]");

						msglen = Integer.parseInt(tms.len.trim());

						//System.out.println("msglen     = " +  msglen );

						byte[] msgArr = new byte[msglen - 300];
						
						System.arraycopy(byteBuffer, TMSHDR_SIZE + USERHDR_SIZE, msgArr, 0, msglen - 300 );
						
						////log.info("byteBuffer ::: {} ", byteBuffer);
						////log.info("msgArr     ::: {} ", msgArr);
						
						outputStr = new String(msgArr, "euc-kr");


						if( hdr.errtp.equals("0") )
							isSuccess = true;
						else
							isSuccess = false;
						
						if( hdr.zip.equals("N") )
							isZip     = false;
						else
							isZip     = true;
						
						
						msgCode   = hdr.errcd;
						msgCtInfo = hdr.ctinfo;
						msgSeqNbr = Integer.parseInt(hdr.seg_nbr.trim());
						msgSeqKey = Integer.parseInt(hdr.seg_key.trim());
						msgDatLen = Integer.parseInt(hdr.dat_len.trim());
						msgZipLen = Integer.parseInt(hdr.zip_len.trim());
						msgRecCnt = Integer.parseInt(hdr.rec_cnt.trim());
						
						runFlag = false;
					}
					else if( tms.tr.equals("RDPKT") )
					{
						System.out.println("real datareceived!!");
						continue;
					}
				}
				else
				{
					if( tms.errcd.equals(TPMSG_UNKNOWN_TR) )
					{
						System.out.println("unknown tr!!");
					}
					else if( tms.errcd.equals(TPMSG_TRXFAIL_TR) )
					{
						System.out.println("transaction failed!!");
					}
					else
					{
						System.out.println("other case error!!");
					}
					
					outputStr = "";
					
					msgCode   = tms.errcd;
					msgCtInfo = "";
					msgSeqNbr = 0;
					msgSeqKey = 0;
					msgDatLen = 0;
					msgZipLen = 0;
					msgRecCnt = 0;
					isSuccess = false;
					isZip     = false;
					
					runFlag = false;
				}
			} // while loop
		}
		catch(Exception e)
		{
			outputStr = "";
			msgCode   = "99999";
			msgCtInfo = "";
			msgSeqNbr = 0;
			msgSeqKey = 0;
			msgDatLen = 0;
			msgZipLen = 0;
			msgRecCnt = 0;
			isSuccess = false;
			isZip     = false;
		}


		/**
		 * Waiting the reply of answer
		 */
		pools.returnConnection(con);


		log.info("::::::::::TMS:::::::::");
		// log.info(":::: outputStr : {} :::::::::", outputStr);
		log.info(":::: isSuccess : {} :::::::::", isSuccess);
		log.info(":::: msgCode   : {} :::::::::", msgCode);
		//log.info(":::: msgSeqNbr : {} :::::::::", msgSeqNbr);


		tmsResult.setOutput(outputStr);
		tmsResult.setSuccess(isSuccess);
		tmsResult.setMsgCode(msgCode);
		tmsResult.setMsgSeqNbr(msgSeqNbr);
		tmsResult.setMsgSeqKey(msgSeqKey);
		tmsResult.setZip(isZip);
		tmsResult.setMsgDatLen(msgDatLen);
		tmsResult.setMsgRecCnt(msgRecCnt);
		tmsResult.setMsgCtInfo(msgCtInfo);
		
		log.info("======== request end : {} =========", service);
		
		return tmsResult;
	}


}