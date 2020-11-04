package com.databox.www.tms;
/*******************************************************************************
 * File Name   : NOpenInfo.java
 * Creation    : 2018.03.19
 * Description : real-time price tick
 *
 * Author      : Logan Stone Technology Co., Ltd
 *******************************************************************************
 */

import java.io.*;
import java.lang.*;


public class NOpenInfo
{
	public NString data_tp           = new NString(  6);     // 데이터구분
	public NString user_id           = new NString(  8);     // 사용자ID
	public NString acct_no           = new NString( 15);     // 계좌번호
	public NString inst_cd           = new NString( 32);     // 종목코드
	public NString bysl_tp           = new NString(  1);     // 매수매도
	public NString open_qty          = new NString( 15);     // 잔고수량
	public NString lqab_qty          = new NString( 15);     // 청산가능수량
	public NString nmth_qty          = new NString( 15);     // 미체결수량
	public NString over_qty          = new NString( 15);     // 오버나잇
	public NString new_qty           = new NString( 15);     // 신규
	public NString avg_prc           = new NString( 15);     // 평균단가
	public NString last_prc          = new NString( 15);     // 현재가
	public NString open_pl           = new NString( 15);     // 평가손익
	public NString commission        = new NString( 15);     // commsion
	public NString exra              = new NString( 15);     // 환율
	public NString crc_cd            = new NString( 10);     // 통화코드
	public NString prc_prsn          = new NString(  5);     // 가격
	public NString qty_prsn          = new NString(  5);     // 수량


	public String strBuffer;


	/**
	 * default constructor
	 */
	public NOpenInfo()
	{
		
	}


	/**
	 * The Reset method initializes the data buffer
	 *
	 * @param      N/A
	 * @return     N/A
	 * @exception  N/A
	 */
	public void Reset()
	{
		data_tp        .Reset();
		user_id        .Reset();
		acct_no        .Reset();
		inst_cd        .Reset();
		bysl_tp        .Reset();
		open_qty       .Reset();
		lqab_qty       .Reset();
		nmth_qty       .Reset();
		over_qty       .Reset();
		new_qty        .Reset();
		avg_prc        .Reset();
		last_prc       .Reset();
		open_pl        .Reset();
		commission     .Reset();
		exra           .Reset();
		crc_cd         .Reset();
		prc_prsn       .Reset();
		qty_prsn       .Reset();
	}


	public void ParseData ( byte[] bt )
	{
		int msgLen = 0;

		byte[] tempArr = new byte[data_tp.GetSize()]; System.arraycopy(bt, msgLen, tempArr, 0, data_tp.GetSize() ); msgLen += data_tp.GetSize(); data_tp.SetValue(tempArr);

		tempArr = new byte[user_id     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, user_id     .GetSize()  );  msgLen  += user_id      .GetSize(); user_id     .SetValue(tempArr);
		tempArr = new byte[acct_no     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, acct_no     .GetSize()  );  msgLen  += acct_no      .GetSize(); acct_no     .SetValue(tempArr);
		tempArr = new byte[inst_cd     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, inst_cd     .GetSize()  );  msgLen  += inst_cd      .GetSize(); inst_cd     .SetValue(tempArr);
		tempArr = new byte[bysl_tp     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, bysl_tp     .GetSize()  );  msgLen  += bysl_tp      .GetSize(); bysl_tp     .SetValue(tempArr);
		tempArr = new byte[open_qty    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, open_qty    .GetSize()  );  msgLen  += open_qty     .GetSize(); open_qty    .SetValue(tempArr);
		tempArr = new byte[lqab_qty    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, lqab_qty    .GetSize()  );  msgLen  += lqab_qty     .GetSize(); lqab_qty    .SetValue(tempArr);
		tempArr = new byte[nmth_qty    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, nmth_qty    .GetSize()  );  msgLen  += nmth_qty     .GetSize(); nmth_qty    .SetValue(tempArr);
		tempArr = new byte[over_qty    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, over_qty    .GetSize()  );  msgLen  += over_qty     .GetSize(); over_qty    .SetValue(tempArr);
		tempArr = new byte[new_qty     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, new_qty     .GetSize()  );  msgLen  += new_qty      .GetSize(); new_qty     .SetValue(tempArr);
		tempArr = new byte[avg_prc     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, avg_prc     .GetSize()  );  msgLen  += avg_prc      .GetSize(); avg_prc     .SetValue(tempArr);
		tempArr = new byte[last_prc    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, last_prc    .GetSize()  );  msgLen  += last_prc     .GetSize(); last_prc    .SetValue(tempArr);
		tempArr = new byte[open_pl     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, open_pl     .GetSize()  );  msgLen  += open_pl      .GetSize(); open_pl     .SetValue(tempArr);
		tempArr = new byte[commission  .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, commission  .GetSize()  );  msgLen  += commission   .GetSize(); commission  .SetValue(tempArr);
		tempArr = new byte[exra        .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, exra        .GetSize()  );  msgLen  += exra         .GetSize(); exra        .SetValue(tempArr);
		tempArr = new byte[crc_cd      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, crc_cd      .GetSize()  );  msgLen  += crc_cd       .GetSize(); crc_cd      .SetValue(tempArr);
		tempArr = new byte[prc_prsn    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, prc_prsn    .GetSize()  );  msgLen  += prc_prsn     .GetSize(); prc_prsn    .SetValue(tempArr);
		tempArr = new byte[qty_prsn    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, qty_prsn    .GetSize()  );  msgLen  += qty_prsn     .GetSize(); qty_prsn    .SetValue(tempArr);
	}



	public String toString()
	{
		return ( user_id    .toString()  + 
		         acct_no    .toString()  + 
		         inst_cd    .toString()  + 
		         bysl_tp    .toString()  + 
		         open_qty   .toString()  + 
		         lqab_qty   .toString()  + 
		         nmth_qty   .toString()  + 
		         over_qty   .toString()  + 
		         new_qty    .toString()  + 
		         avg_prc    .toString()  + 
		         last_prc   .toString()  + 
		         open_pl    .toString()  + 
		         commission .toString()  + 
		         exra       .toString()  + 
		         crc_cd     .toString()  + 
		         prc_prsn   .toString()  + 
		         qty_prsn   .toString()    ); 
	}


	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeChars(toString());
	}


	public byte[] ToBytes()
	{
		strBuffer = user_id     .toString()  + 
		            acct_no     .toString()  + 
		            inst_cd     .toString()  + 
		            bysl_tp     .toString()  + 
		            open_qty    .toString()  + 
		            lqab_qty    .toString()  + 
		            nmth_qty    .toString()  + 
		            over_qty    .toString()  + 
		            new_qty     .toString()  + 
		            avg_prc     .toString()  + 
		            last_prc    .toString()  + 
		            open_pl     .toString()  + 
		            commission  .toString()  + 
		            exra        .toString()  + 
		            crc_cd      .toString()  + 
		            prc_prsn    .toString()  + 
		            qty_prsn    .toString()  ; 

		return strBuffer.getBytes();
	}


	public String toJSON()
	{
		return ( user_id    .toString()  + 
		         acct_no    .toString()  + 
		         inst_cd    .toString()  + 
		         bysl_tp    .toString()  + 
		         open_qty   .toString()  + 
		         lqab_qty   .toString()  + 
		         nmth_qty   .toString()  + 
		         over_qty   .toString()  + 
		         new_qty    .toString()  + 
		         avg_prc    .toString()  + 
		         last_prc   .toString()  + 
		         open_pl    .toString()  + 
		         commission .toString()  + 
		         exra       .toString()  + 
		         crc_cd     .toString()  + 
		         prc_prsn   .toString()  + 
		         qty_prsn   .toString()    ); 
	}
}
