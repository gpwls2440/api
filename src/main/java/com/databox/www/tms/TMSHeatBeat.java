package com.databox.www.tms;
/*******************************************************************************
 * File Name   : TMSHeatBeat.java
 * Creation    : 2019.01.01
 * Description : HeatBeat
 *
 * Author      : Logan
 *******************************************************************************
 */

import java.io.*;
import java.lang.*;

public class TMSHeatBeat extends Object implements Serializable
{
	/**
	 *  Defines communication constant
	 */
	public static final int TMSHDR_SIZE     = 100;
	public static final int TMSHDR_SZ_LEN   =  10;


	/**
	 *  Defines communication packet structure
	 */
	public NString m_tmslen         = new NString(10);
	public NString m_tmstr          = new NString( 5);
	public NString m_tmsservice     = new NString( 6);
	public NString m_tmsclid        = new NString(10);
	public NString m_tmscd          = new NString(10);
	public NString m_tmserrcd       = new NString( 5);
	public NString m_tmssreserved   = new NString(44);
	public NString m_tmscreserved   = new NString(10);
	public NString m_data           = new NString( 0);


	public String m_str;

	/**
	 * default constructor
	 */
	TMSHeatBeat()
	{

	}


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
		m_data.Clear();
	}



	/**
	 * MakeHeatBeat
	 *
	 */
	public void MakeHeatBeat()
	{
		Reset();

		m_tmslen.SetValue(TMSHDR_SIZE - TMSHDR_SZ_LEN );
		m_tmstr.SetValue("HBPKT");
		m_tmsservice.SetValue(" ");
		m_tmsclid.SetValue(" ");
		m_tmscd.SetValue(" ");
		m_tmserrcd.SetValue(" ");
		m_tmssreserved.SetValue(" ");
		m_tmscreserved.SetValue(" ");
	}

	public String toString()
	{
		return (m_tmslen.toString()       + m_tmstr.toString()        +
				m_tmsservice.toString()   + m_tmsclid.toString()      +
				m_tmscd.toString()        + m_tmserrcd.toString()     +
				m_tmssreserved.toString() + m_tmscreserved.toString() ) ;
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
				m_tmssreserved.toString() + m_tmscreserved.toString() ;

		return m_str.getBytes();
	}
}


