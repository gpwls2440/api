package com.databox.www.tms;
/*******************************************************************************
 * File Name   : NPacket.java
 * Creation    : 2018.03.19
 * Description : communication packet handling class
 *
 * Author      : Logan Stone Technology Co., Ltd
 *******************************************************************************
 */


import java.io.*;
import java.lang.*;


public class NPacket extends Object implements Serializable
{
	/**
	 *  Defines communication constant
	 */

	public static final int TMSHDR_SIZE     = 100;
	public static final int TMSHDR_SZ_LEN   =  10;

	public static final int PKT_LEN_SIZE    =  10;
	public static final int PKT_HEADER_TLEN = 210;
	public static final int PKT_HEADER_DLEN = 200;
	public static final int PKT_MAX_BUF     = 1024 * 64; // 64 K
	public static final int PKT_DATA_LEN    = (PKT_MAX_BUF - PKT_HEADER_TLEN);
	public static final int SND_MAX_LEN     = 1024 * 64; // 64 K


	/**
	 *  Defines communication packet structure
	 */
	public NString m_tmslen         = new NString(10);   // except len
	public NString m_tmstr          = new NString( 5);   // transaction code
	public NString m_tmsservice     = new NString( 6);   // service name
	public NString m_tmsclid        = new NString(10);   // client identifier
	public NString m_tmscd          = new NString(10);   // client info
	public NString m_tmserrcd       = new NString( 5);   // "00000" : complete, 90001" : unknown AP, "99999" : transaction failed
	public NString m_tmssreserved   = new NString(44);   // reserved 5
	public NString m_tmscreserved   = new NString(10);   // client reserved

	public NString m_len            = new NString(10);   // packet length without len field 10 byte
	public NString m_zip            = new NString( 1);   // 'N': no zip
	public NString m_uid            = new NString( 8);   // user id
	public NString m_clid           = new NString(10);   // tms clid
	public NString m_tr             = new NString( 6);   // transation code i.e. service name
	public NString m_ctype          = new NString( 1);   // '1': syncronus '2': asyncronus
	public NString m_dtype          = new NString( 1);   // '1': text '2': binary '3': html '4' xtml
	public NString m_srvtp          = new NString( 1);   // '1': text '2': binary '3': html '4' xtml
	public NString m_errtp          = new NString( 1);   // '0': OK else error '9': system error
	public NString m_errcd          = new NString( 5);   // error code
	public NString m_ctinfo         = new NString(16);   // client information area
	public NString m_svrinfo        = new NString( 9);   // server information area
	public NString m_seg_nbr        = new NString( 5);   // Segment Number
	public NString m_rec_cnt        = new NString(10);   // record count
	public NString m_seg_key        = new NString(12);   // Segment Key
	public NString m_dat_len        = new NString(10);   // uncompress data size
	public NString m_zip_len        = new NString(10);   // compress data size
	public NString m_memb_id        = new NString( 7);   // member id
	public NString m_chnl_cd        = new NString( 2);   // channel cd
	public NString m_scrn_no        = new NString( 4);   // screen no
	public NString m_team_cd        = new NString( 5);   // team cd
	public NString m_sid            = new NString(20);   // session id
	public NString m_reserved2      = new NString(56);   // reserved area : client space padding
	public NString m_data           = new NString( 0);   // data area(variable size)


	public String m_str;

	
	/**
	 * The Reset method initializes the communication packet buffer
	 *
	 * @param      N/A
	 * @return     N/A
	 * @exception  N/A
	 */
	public void Reset()
	{
		m_tmslen.Reset();
		m_tmstr.Reset();
		m_tmsservice.Reset();
		m_tmsclid.Reset();
		m_tmscd.Reset();
		m_tmserrcd.Reset();
		m_tmssreserved.Reset();
		m_tmscreserved.Reset();
		m_len.Reset();
		m_zip.Reset();
		m_uid.Reset();
		m_clid.Reset();
		m_tr.Reset();
		m_ctype.Reset();
		m_dtype.Reset();
		m_srvtp.Reset();
		m_errtp.Reset();
		m_errcd.Reset();
		m_ctinfo.Reset();
		m_svrinfo.Reset();
		m_seg_nbr.Reset();
		m_rec_cnt.Reset();
		m_seg_key.Reset();
		m_dat_len.Reset();
		m_zip_len.Reset();
		m_memb_id.Reset();
		m_chnl_cd.Reset();
		m_scrn_no.Reset();
		m_team_cd.Reset();
		m_sid.Reset();
		m_reserved2.Reset();
		m_data.Clear();
	}



	/**
	 * The MakePacket method makes the communication packet header section
	 *
	 * @param      service    service name
	 * @param      sessid     session id
	 * @param      uid        user id
	 * @param      input      input data
	 * @param      segnbr     segment number
	 * @param      segkey     segment key
	 * @return     N/A
	 * @exception  N/A
	 */
	public void MakePacket(String service, String sessid, String uid, String input, int segnbr, int segkey)
	{
		Reset();

		// size of message
		int msglen = input.getBytes().length;


		// TMS HEADER
		m_tmslen.SetValue(TMSHDR_SIZE - TMSHDR_SZ_LEN + PKT_HEADER_TLEN + msglen);
		m_tmstr.SetValue("SVC_I");
		m_tmsservice.SetValue(service);
		m_tmsclid.SetValue(" ");
		m_tmscd.SetValue(" ");
		m_tmserrcd.SetValue(" ");
		m_tmssreserved.SetValue(" ");
		m_tmscreserved.SetValue(" ");

		// USR HEADER
		m_len.SetValue(PKT_HEADER_TLEN - PKT_LEN_SIZE +  msglen);
		m_zip.SetValue("N");
		m_uid.SetValue(uid);
		m_clid.SetValue(" ");
		m_tr.SetValue(service);
		m_ctype.SetValue(" ");
		m_dtype.SetValue(" ");
		m_srvtp.SetValue(" ");
		m_errtp.SetValue("0");
		m_errcd.SetValue("00000");
		m_ctinfo.SetValue(" ");
		m_svrinfo.SetValue(" ");
		m_seg_nbr.SetValue(segnbr);
		m_rec_cnt.SetValue(0);
		m_seg_key.SetValue(segkey);
		m_dat_len.SetValue(msglen);
		m_zip_len.SetValue(msglen);
		m_memb_id.SetValue(" ");
		m_chnl_cd.SetValue(" ");
		m_scrn_no.SetValue(" ");
		m_team_cd.SetValue(" ");
		m_sid.SetValue(sessid);
		m_reserved2.SetValue(" ");

		m_data = new NString(msglen);
		m_data.SetValue(input);
	}



	public NSvcInfo ParseSvcInfo ( byte[] bt )
	{
		NSvcInfo hdr = new NSvcInfo();
		int hdrLen = 0;

		byte[] tempArr = new byte[m_tmslen.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmslen.GetSize() );
		hdrLen += m_tmslen.GetSize();
		hdr.len = new String(tempArr);

		tempArr = new byte[m_tmstr.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmstr.GetSize()  );
		hdrLen   += m_tmstr.GetSize();
		hdr.tr = new String(tempArr);

		tempArr = new byte[m_tmsservice.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmsservice.GetSize()  );
		hdrLen   += m_tmsservice.GetSize();
		hdr.service = new String(tempArr);

		tempArr = new byte[m_tmsclid.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmsclid.GetSize()  );
		hdrLen   += m_tmsclid.GetSize();
		hdr.clid = new String(tempArr);

		tempArr = new byte[m_tmscd.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmscd.GetSize()  );
		hdrLen   += m_tmscd.GetSize();
		hdr.cd = new String(tempArr);

		tempArr = new byte[m_tmserrcd.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmserrcd.GetSize()  );
		hdrLen   += m_tmserrcd.GetSize();
		hdr.errcd = new String(tempArr);

		tempArr = new byte[m_tmssreserved.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmssreserved.GetSize()  );
		hdrLen   += m_tmssreserved.GetSize();
		hdr.sreserved = new String(tempArr);

		tempArr = new byte[m_tmscreserved.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmscreserved.GetSize()  );
		hdrLen   += m_tmscreserved.GetSize();
		hdr.creserved = new String(tempArr);


		return hdr;
	}




	public NHeader ParseHeader ( byte[] bt )
	{
		NHeader hdr = new NHeader();
		int hdrLen = 0;

		byte[] tempArr = new byte[m_tmslen.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmslen.GetSize() );
		hdrLen += m_tmslen.GetSize();
		hdr.tmslen = new String(tempArr);

		tempArr = new byte[m_tmstr.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmstr.GetSize()  );
		hdrLen   += m_tmstr.GetSize();
		hdr.tmstr = new String(tempArr);

		tempArr = new byte[m_tmsservice.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmsservice.GetSize()  );
		hdrLen   += m_tmsservice.GetSize();
		hdr.tmsservice = new String(tempArr);

		tempArr = new byte[m_tmsclid.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmsclid.GetSize()  );
		hdrLen   += m_tmsclid.GetSize();
		hdr.tmsclid = new String(tempArr);

		tempArr = new byte[m_tmscd.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmscd.GetSize()  );
		hdrLen   += m_tmscd.GetSize();
		hdr.tmscd = new String(tempArr);

		tempArr = new byte[m_tmserrcd.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmserrcd.GetSize()  );
		hdrLen   += m_tmserrcd.GetSize();
		hdr.tmserrcd = new String(tempArr);

		tempArr = new byte[m_tmssreserved.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmssreserved.GetSize()  );
		hdrLen   += m_tmssreserved.GetSize();
		hdr.tmssreserved = new String(tempArr);

		tempArr = new byte[m_tmscreserved.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tmscreserved.GetSize()  );
		hdrLen   += m_tmscreserved.GetSize();
		hdr.tmscreserved = new String(tempArr);

		tempArr = new byte[m_len.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_len.GetSize()  );
		hdrLen   += m_len.GetSize();
		hdr.len = new String(tempArr);

		tempArr = new byte[m_zip.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_zip.GetSize()  );
		hdrLen   += m_zip.GetSize();
		hdr.zip = new String(tempArr);

		tempArr = new byte[m_uid.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_uid.GetSize()  );
		hdrLen   += m_uid.GetSize();
		hdr.uid = new String(tempArr);

		tempArr = new byte[m_clid.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_clid.GetSize()  );
		hdrLen   += m_clid.GetSize();
		hdr.clid = new String(tempArr);

		tempArr = new byte[m_tr.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_tr.GetSize()  );
		hdrLen   += m_tr.GetSize();
		hdr.tr = new String(tempArr);

		tempArr = new byte[m_ctype.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_ctype.GetSize()  );
		hdrLen   += m_ctype.GetSize();
		hdr.ctype = new String(tempArr);

		tempArr = new byte[m_dtype.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_dtype.GetSize()  );
		hdrLen   += m_dtype.GetSize();
		hdr.dtype = new String(tempArr);

		tempArr = new byte[m_srvtp.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_srvtp.GetSize()  );
		hdrLen   += m_srvtp.GetSize();
		hdr.srvtp = new String(tempArr);

		tempArr = new byte[m_errtp.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_errtp.GetSize()  );
		hdrLen   += m_errtp.GetSize();
		hdr.errtp = new String(tempArr);

		tempArr = new byte[m_errcd.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_errcd.GetSize()  );
		hdrLen   += m_errcd.GetSize();
		hdr.errcd = new String(tempArr);

		tempArr = new byte[m_ctinfo.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_ctinfo.GetSize()  );
		hdrLen   += m_ctinfo.GetSize();
		hdr.ctinfo = new String(tempArr);

		tempArr = new byte[m_svrinfo.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_svrinfo.GetSize()  );
		hdrLen   += m_svrinfo.GetSize();
		hdr.svrinfo = new String(tempArr);

		tempArr = new byte[m_seg_nbr.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_seg_nbr.GetSize()  );
		hdrLen   += m_seg_nbr.GetSize();
		hdr.seg_nbr = new String(tempArr);

		tempArr = new byte[m_rec_cnt.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_rec_cnt.GetSize()  );
		hdrLen   += m_rec_cnt.GetSize();
		hdr.rec_cnt = new String(tempArr);

		tempArr = new byte[m_seg_key.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_seg_key.GetSize()  );
		hdrLen   += m_seg_key.GetSize();
		hdr.seg_key = new String(tempArr);

		tempArr = new byte[m_dat_len.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_dat_len.GetSize()  );
		hdrLen   += m_dat_len.GetSize();
		hdr.dat_len = new String(tempArr);

		tempArr = new byte[m_zip_len.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_zip_len.GetSize()  );
		hdrLen   += m_zip_len.GetSize();
		hdr.zip_len = new String(tempArr);

		tempArr = new byte[m_memb_id.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_memb_id.GetSize()  );
		hdrLen   += m_memb_id.GetSize();
		hdr.memb_id = new String(tempArr);

		tempArr = new byte[m_chnl_cd.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_chnl_cd.GetSize()  );
		hdrLen   += m_chnl_cd.GetSize();
		hdr.chnl_cd = new String(tempArr);

		tempArr = new byte[m_scrn_no.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_scrn_no.GetSize()  );
		hdrLen   += m_scrn_no.GetSize();
		hdr.scrn_no = new String(tempArr);

		tempArr = new byte[m_team_cd.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_team_cd.GetSize()  );
		hdrLen   += m_team_cd.GetSize();
		hdr.team_cd = new String(tempArr);

		tempArr = new byte[m_sid.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_sid.GetSize()  );
		hdrLen   += m_sid.GetSize();
		hdr.sid = new String(tempArr);

		tempArr = new byte[m_reserved2.GetSize()];
		System.arraycopy(bt, hdrLen, tempArr, 0, m_reserved2.GetSize()  );
		hdrLen   += m_reserved2.GetSize();
		hdr.reserved2 = new String(tempArr);

		return hdr;
	}




	public String toString()
	{
		return (m_tmslen.toString()       + m_tmstr.toString()        +
				m_tmsservice.toString()   + m_tmsclid.toString()      +
				m_tmscd.toString()        + m_tmserrcd.toString()     +
				m_tmssreserved.toString() + m_tmscreserved.toString() +
				m_len.toString()          + m_zip.toString()          +
				m_uid.toString()          + m_clid.toString()         +
				m_tr.toString()           + m_ctype.toString()        +
				m_dtype.toString()        + m_srvtp.toString()        +
				m_errtp.toString()        + m_errcd.toString()        +
				m_ctinfo.toString()       + m_svrinfo.toString()      +
				m_seg_nbr.toString()      + m_rec_cnt.toString()      +
				m_seg_key.toString()      + m_dat_len.toString()      +
				m_zip_len.toString()      + m_memb_id.toString()      +
				m_chnl_cd.toString()      + m_scrn_no.toString()      +
				m_team_cd.toString()      + m_sid.toString()          +
				m_reserved2.toString()    + m_data.toString() );
	}


	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeChars(toString());
	}


	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{

	}


	public byte[] ToBytes()
	{
		m_str = m_tmslen.toString()       + m_tmstr.toString()        +
				m_tmsservice.toString()   + m_tmsclid.toString()      +
				m_tmscd.toString()        + m_tmserrcd.toString()     +
				m_tmssreserved.toString() + m_tmscreserved.toString() +
				m_len.toString()          + m_zip.toString()          +
				m_uid.toString()          + m_clid.toString()         +
				m_tr.toString()           + m_ctype.toString()        +
				m_dtype.toString()        + m_srvtp.toString()        +
				m_errtp.toString()        + m_errcd.toString()        +
				m_ctinfo.toString()       + m_svrinfo.toString()      +
				m_seg_nbr.toString()      + m_rec_cnt.toString()      +
				m_seg_key.toString()      + m_dat_len.toString()      +
				m_zip_len.toString()      + m_memb_id.toString()      +
				m_chnl_cd.toString()      + m_scrn_no.toString()      +
				m_team_cd.toString()      + m_sid.toString()          +
				m_reserved2.toString()    + m_data.toString();

		return m_str.getBytes();
	}
}


