package com.databox.www.tms;
/**

 * TMSConnection
 * 
 */
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

@Slf4j
public class TMSConnection {
	private HashMap<TMSConnector, Boolean > connectors = new HashMap<TMSConnector, Boolean >();

	private String hostName;
	private String siteIP;
	private int socketPort;
	private int poolSize;


	/**
	 * Create connection thread pool
	 * 
	 */
	public TMSConnection(String hostName, String siteIP, int socketPort, int poolSize )
	{
		this.hostName   = hostName;
		this.siteIP     = siteIP;
		this.socketPort = socketPort;
		this.poolSize   = poolSize;
	}



	/**
	 * getConnection
	 * 
	 */
	public TMSConnector getConnection()
	{
		TMSConnector con = null;
		
		int founded = 0;
		synchronized (connectors) {
			for (Map.Entry me : connectors.entrySet())
			{
				con = (TMSConnector)me.getKey();

				Boolean  b = (Boolean)me.getValue();

				if (b == Boolean.FALSE)
				{
					founded = founded + 1;
					// So we found an unused connection.
					//System.out.println("we found an unused connection:" + con.taskNumber );

					// Update the Hashtable to show this one's taken
					connectors.put(con, Boolean.TRUE);

					// Return the connection
					return con;
				}
			}
		}
		
		if( founded == 0 )
		{
			return null;
		}
		else
			return con;
	}



	/**
	 * return Connection
	 * 
	 */
	public void returnConnection(TMSConnector returned)
	{
		TMSConnector con = null;

		synchronized (connectors) {
			for (Map.Entry me : connectors.entrySet())
			{
				con = (TMSConnector)me.getKey();

				if (con == returned)
				{
					connectors.put(con, Boolean.FALSE);
					break;
				}
			}
		}
	}



	/**
	 * Create connection thread pool
	 * 
	 */
	public void start()
	{
		String clntName = "";

		for (int i=1; i < poolSize + 1; i++ ) {
			clntName = hostName + (1000 + i);

			TMSConnector connector = new TMSConnector(this, clntName, siteIP, socketPort);
			log.info("prams{} {} {} {} {} : ", i, this, clntName, siteIP, socketPort);
			log.info("connector {} : ",connector);
			connectors.put(connector, Boolean.FALSE );
		}
	}



	/**
	 * Destroy connection thread pool
	 * 
	 */
	public void end()
	{
		TMSConnector conn = null;

		synchronized (connectors) {
			for (Map.Entry me : connectors.entrySet())
			{
				conn = (TMSConnector)me.getKey();
				conn.disconnect();
			}
		}
	}
}



