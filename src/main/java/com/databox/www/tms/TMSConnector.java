package com.databox.www.tms;
/**
 * TMS Client TCP Connection Thread
 * 
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class TMSConnector implements Runnable {

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

	private static final String TMS_HEADER_FORMAT      = "%010d%-5s%-6s%-10s%-10s%-5s%-44s%-10s";
	private static final String TMS_SIGNON_FORMAT      = "%010d%-5s%-6s%-10s%-10s%-5s%-44s%-10s%-8s%-8s%-8s%-8s";
	private static final String USR_HEADER_FORMAT      = "%010d%-1s%-8s%-10s%-6s%-1s%-1s%-1s%-1s%-5s%-16s%-9s%05d%010d%012d%010d%010d%-7s%-2s%-4s%-5s%-20s%-56s";

	private static final int    INTPUTSTREAM_READ_RETRY_COUNT = 10;

	static private int count = 0;
	public  int taskNumber;

	public    TMSHeatBeatThread heatbeat;


	public  String service = "";
	public  String sessid  = "";
	public  String uid     = "";
	public  String input   = "";
	public  int    seqNbr  =  1;
	public  int    seqKey  =  0;
	public  String ctInfo  = "";

	public  String result  = "";


	private TMSConnection pools = null;
	private String clntName;
	private String siteIP;
	private int    socketPort;

	public  boolean connected = false;
	public  Socket clientSocket = null;
	public  DataInputStream  br = null;
	public  DataOutputStream bw = null;



	/**
	 * Constructor
	 * 
	 */
	TMSConnector(TMSConnection pools, String clntName, String siteIP, int socketPort)
	{
		this.pools      = pools;
		this.clntName   = clntName;
		this.siteIP     = siteIP;
		this.socketPort = socketPort;

		count++;
		taskNumber = count;

		//System.out.println("taskNumber: " + taskNumber );
		//System.out.println("clntName  : " + clntName   );
		
		if(tpstart() > 0)
		{
			if( tplogin() < 0 )
				connected = false;
			else {
				connected = true;
				heatbeat = new TMSHeatBeatThread(this);
				heatbeat.start();
			}
		}
	}



	/**
	 * Connect to server
	 * 
	 */
	public int tpstart()
	{
		int rc = -1;

		try
		{
			clientSocket = new Socket(siteIP, socketPort);
			System.out.println("clientSocket1 : " + clientSocket);
		}
		catch( IOException e )
		{
			clientSocket = null;
			
			try
			{
				Thread.sleep(1000);
			} catch( InterruptedException ie ) {
				System.out.println(ie.getMessage());
			}
		}

		if( clientSocket != null )
		{ 
			try
			{
				bw = new DataOutputStream(clientSocket.getOutputStream());
				br = new DataInputStream (clientSocket.getInputStream() );
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			rc = 1;
		}
		
		return rc;
	}



	/**
	 * Login to server
	 * 
	 */
	public int tplogin()
	{
		int rc = 0;

		try
		{
			int    len          = TMSHDR_SIZE - TMSHDR_SZ_LEN + 32;
			String tr           = "LOGIN";
			String service_name = " ";
			String clid         = " ";
			String cd           = " ";
			String errcd        = " ";
			String sreserved    = " ";
			String creserved    = " ";
			String userid       = clntName;
			String cltname      = clntName;
			String dompwd       = " ";
			String usrpwd       = " ";
			
			String strBuffer    = String.format(TMS_SIGNON_FORMAT, len, tr, service_name, clid, cd
			                                              , errcd, sreserved, creserved
			                                              , userid, cltname, dompwd, usrpwd );
			
			byte[] byteBuffer   = strBuffer.getBytes();
			
			bw.write(byteBuffer);
			bw.flush();
		
		} catch( Exception e ) {
			System.out.println(e.getMessage());
		}

		return rc;
	}


	/**
	 * Disconnect from server
	 * 
	 */
	public void disconnect()
	{
		connected = false;

		heatbeat.interrupt();

		tpend();
	}


	/**
	 * Disconnect from server
	 * 
	 */
	public void tpend()
	{
		if( clientSocket != null)
		{
			try
			{
				clientSocket.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
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



	/**
	 * Main Loop
	 * 
	 */
	public void run()
	{
		;;;
	}
}




/**
 * Heatbeat Thread
 * 
 */
class TMSHeatBeatThread extends Thread
{
	private int DEFAULT_HEATBEAT_PERIOD = 30;  //seconds
	private TMSConnector owner = null;
	private DataOutputStream writer = null;


	TMSHeatBeatThread(TMSConnector owner)
	{
		this.owner = owner;
	}

	public void run()
	{
		try
		{
			try
			{
				writer = new DataOutputStream(owner.clientSocket.getOutputStream());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}


			TMSHeatBeat pkt = new TMSHeatBeat();
			pkt.MakeHeatBeat();
			String strMsg = pkt.toString();
			byte[] byteBuffer = strMsg.getBytes();

			while(true)
			{
				try
				{
					writer.write(byteBuffer);
					writer.flush();
				} catch (IOException e) {
					System.out.println(e.getMessage());
					interrupt();
				}

				// Let the thread sleep for a while.
				Thread.sleep(DEFAULT_HEATBEAT_PERIOD * 1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread heatbeat agent interrupted.");
		} finally {
			try
			{
				owner.disconnect();
			} catch (Exception ignored) {
				
			}
		}
	}
}

