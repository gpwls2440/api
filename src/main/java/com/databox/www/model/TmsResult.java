package com.databox.www.model;

import lombok.Data;

@Data
public class TmsResult {
	
	private String  output;
	private boolean isSuccess;
	private boolean isZip;
	private String  msgCode;
	private String  msgCtInfo;
	private int     msgSeqNbr;
	private int     msgSeqKey;
	private int     msgDatLen;
	private int     msgZipLen;
	private int     msgRecCnt;
	
}
