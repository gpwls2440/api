package com.databox.www.tms;
/*******************************************************************************
 * File Name   : NString.java
 * Creation    : 2018.03.19
 * Description : string handling class for tcp communication
 *
 * Author      : Logan Stone Technology Co., Ltd
 *******************************************************************************
 */


public class NString extends Object
{
	int m_size;
	StringBuffer m_buf= null;

	NString()
	{
		m_size = 0;
	}

	public NString(int size)
	{
		int i;
		m_size = size;
		m_buf  = new StringBuffer(size);
		for(i=0; i < m_size; i++) m_buf.insert(i, ' ');
	}


	public String toString()
	{
		return m_buf.toString();
	}

	public void SetValue(String str, char ch)
	{
		int i, j;
		int strLen;
		int spaceSize;

		strLen = str.length();

		if(m_size == 0)
		{
			m_size = strLen;
			m_buf = new StringBuffer(m_size);
		}

		spaceSize = m_size - strLen;

		for(i=0; i < spaceSize; i++) m_buf.setCharAt(i, ch);

		for(i=spaceSize, j=0; i < m_size; i++, j++)
		{
			m_buf.setCharAt(i, str.charAt(j));
		}
	}

	public void SetValue(String str)
	{
		this.SetValue(str, ' ');
	}

	public void SetValue(StringBuffer str)
	{
		this.SetValue(str.toString());
	}

	public void SetValue(int value)
	{
		this.SetValue(Integer.toString(value), '0');
	}

	public void SetValue(double value)
	{
		this.SetValue(Double.toString(value), '0');
	}

	public void SetValue(byte[] bytearray)
	{
		String str = new String(bytearray);

		this.SetValue(str, ' ');
	}


	public void Reset()
	{
		int i;
		for(i=0; i < m_size; i++) m_buf.setCharAt(i, ' ');
	}

	public void Clear()
	{
		if(m_size == 0 ) return;
		m_buf.delete(0, m_size);
	}

	public int GetSize()
	{
		return m_size;
	}
}

