package com.databox.www.tms;
/*******************************************************************************
 * File Name   : NSvcInfo.java
 * Creation    : 2018.03.19
 * Description : communication packet handling class
 *
 * Author      : Logan Stone Technology Co., Ltd
 *******************************************************************************
 */

public class NSvcInfo
{
	public String len;
	public String tr;
	public String service;
	public String clid;
	public String cd;
	public String errcd;
	public String sreserved;
	public String creserved;


	public NSvcInfo()
	{
		len       = "";
		tr        = "";
		service   = "";
		clid      = "";
		cd        = "";
		errcd     = "";
		sreserved = "";
		creserved = "";
	}
}
