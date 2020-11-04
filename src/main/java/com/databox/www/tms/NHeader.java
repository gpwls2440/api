package com.databox.www.tms;
/*******************************************************************************
 * File Name   : NPacket.java
 * Creation    : 2018.03.19
 * Description : communication packet handling class
 *
 * Author      : Logan Stone Technology Co., Ltd
 *******************************************************************************
 */

public class NHeader
{
	public String tmslen;
	public String tmstr;
	public String tmsservice;
	public String tmsclid;
	public String tmscd;
	public String tmserrcd;
	public String tmssreserved;
	public String tmscreserved;
	public String len;
	public String zip;
	public String uid;
	public String clid;
	public String tr;
	public String ctype;
	public String dtype;
	public String srvtp;
	public String errtp;
	public String errcd;
	public String ctinfo;
	public String svrinfo;
	public String seg_nbr;
	public String rec_cnt;
	public String seg_key;
	public String dat_len;
	public String zip_len;
	public String memb_id;
	public String chnl_cd;
	public String scrn_no;
	public String team_cd;
	public String sid;
	public String reserved2;


	public NHeader()
	{
		tmslen       = "";
		tmstr        = "";
		tmsservice   = "";
		tmsclid      = "";
		tmscd        = "";
		tmserrcd     = "";
		tmssreserved = "";
		tmscreserved = "";
		len          = "";
		zip          = "";
		uid          = "";
		clid         = "";
		tr           = "";
		ctype        = "";
		dtype        = "";
		srvtp        = "";
		errtp        = "";
		errcd        = "";
		ctinfo       = "";
		svrinfo      = "";
		seg_nbr      = "";
		rec_cnt      = "";
		seg_key      = "";
		dat_len      = "";
		zip_len      = "";
		memb_id      = "";
		chnl_cd      = "";
		scrn_no      = "";
		team_cd      = "";
		sid          = "";
		reserved2    = "";
	}
}
