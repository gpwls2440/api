package com.databox.www.tms;
/*******************************************************************************
 * File Name   : NTradInfo.java
 * Creation    : 2018.03.19
 * Description : real-time price tick
 *
 * Author      : Logan Stone Technology Co., Ltd
 *******************************************************************************
 */

import java.io.*;
import java.lang.*;


public class NTradInfo
{
	public NString data_tp           = new NString(  6);    // 데이터구분
	public NString user_id           = new NString(  8);    // 사용자ID
	public NString acct_no           = new NString( 15);    // 계좌번호
	public NString ordr_dt           = new NString(  8);    // 주문일자
	public NString ordr_no           = new NString( 20);    // 주문번호
	public NString trad_dt           = new NString(  8);    // 체결일자
	public NString trad_no           = new NString( 20);    // 체결번호
	public NString inst_cd           = new NString( 32);    // 종목코드
	public NString bysl_tp           = new NString(  1);    // 매수매도
	public NString ordr_qty          = new NString( 15);    // 주문수량
	public NString trad_qty          = new NString( 15);    // 체결수량
	public NString trad_prc          = new NString( 15);    // 체결가격
	public NString lqdn_prc          = new NString( 15);    // 진입가격
	public NString commission        = new NString( 15);    // 수수료
	public NString lqdn_pl_amt       = new NString( 15);    // 손익
	public NString exra              = new NString( 15);    // 환율
	public NString ordr_dtm          = new NString( 20);    // 주문시각
	public NString trad_dtm          = new NString( 20);    // 체결시각
	public NString stat_tp           = new NString(  1);    // 상태구분                 1.Pending 2.Confirmed 3.Reject 4.Cancel
	public NString crc_cd            = new NString( 10);    // 통화코드
	public NString ordr_tp           = new NString(  1);    // 주문구분
	public NString prce_tp           = new NString(  1);    // 가격조건
	public NString prc_prsn          = new NString(  5);    // 가격
	public NString qty_prsn          = new NString(  5);    // 수량
	public NString tx_status         = new NString(  1);    // TX_STATUS                1.완료  2.진행  3.대기  9.전송실패
	public NString tx_hash           = new NString(120);    // TX_HASH

	public String strBuffer;


	/**
	 * default constructor
	 */
	public NTradInfo()
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
		data_tp           .Reset();
		user_id           .Reset();
		acct_no           .Reset();
		ordr_dt           .Reset();
		ordr_no           .Reset();
		trad_dt           .Reset();
		trad_no           .Reset();
		inst_cd           .Reset();
		bysl_tp           .Reset();
		ordr_qty          .Reset();
		trad_qty          .Reset();
		trad_prc          .Reset();
		lqdn_prc          .Reset();
		commission        .Reset();
		lqdn_pl_amt       .Reset();
		exra              .Reset();
		ordr_dtm          .Reset();
		trad_dtm          .Reset();
		stat_tp           .Reset();
		crc_cd            .Reset();
		ordr_tp           .Reset();
		prce_tp           .Reset();
		prc_prsn          .Reset();
		qty_prsn          .Reset();
		tx_status         .Reset();
		tx_hash           .Reset();
	}


	public void ParseData ( byte[] bt )
	{
		int msgLen = 0;

		byte[] tempArr = new byte[data_tp.GetSize()]; System.arraycopy(bt, msgLen, tempArr, 0, data_tp.GetSize() ); msgLen += data_tp.GetSize(); data_tp.SetValue(tempArr);

		tempArr = new byte[user_id     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, user_id     .GetSize()  );  msgLen  += user_id     .GetSize(); user_id     .SetValue(tempArr);
		tempArr = new byte[acct_no     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, acct_no     .GetSize()  );  msgLen  += acct_no     .GetSize(); acct_no     .SetValue(tempArr);
		tempArr = new byte[ordr_dt     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_dt     .GetSize()  );  msgLen  += ordr_dt     .GetSize(); ordr_dt     .SetValue(tempArr);
		tempArr = new byte[ordr_no     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_no     .GetSize()  );  msgLen  += ordr_no     .GetSize(); ordr_no     .SetValue(tempArr);
		tempArr = new byte[trad_dt     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, trad_dt     .GetSize()  );  msgLen  += trad_dt     .GetSize(); trad_dt     .SetValue(tempArr);
		tempArr = new byte[trad_no     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, trad_no     .GetSize()  );  msgLen  += trad_no     .GetSize(); trad_no     .SetValue(tempArr);
		tempArr = new byte[inst_cd     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, inst_cd     .GetSize()  );  msgLen  += inst_cd     .GetSize(); inst_cd     .SetValue(tempArr);
		tempArr = new byte[bysl_tp     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, bysl_tp     .GetSize()  );  msgLen  += bysl_tp     .GetSize(); bysl_tp     .SetValue(tempArr);
		tempArr = new byte[ordr_qty    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_qty    .GetSize()  );  msgLen  += ordr_qty    .GetSize(); ordr_qty    .SetValue(tempArr);
		tempArr = new byte[trad_qty    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, trad_qty    .GetSize()  );  msgLen  += trad_qty    .GetSize(); trad_qty    .SetValue(tempArr);
		tempArr = new byte[trad_prc    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, trad_prc    .GetSize()  );  msgLen  += trad_prc    .GetSize(); trad_prc    .SetValue(tempArr);
		tempArr = new byte[lqdn_prc    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, lqdn_prc    .GetSize()  );  msgLen  += lqdn_prc    .GetSize(); lqdn_prc    .SetValue(tempArr);
		tempArr = new byte[commission  .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, commission  .GetSize()  );  msgLen  += commission  .GetSize(); commission  .SetValue(tempArr);
		tempArr = new byte[lqdn_pl_amt .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, lqdn_pl_amt .GetSize()  );  msgLen  += lqdn_pl_amt .GetSize(); lqdn_pl_amt .SetValue(tempArr);
		tempArr = new byte[exra        .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, exra        .GetSize()  );  msgLen  += exra        .GetSize(); exra        .SetValue(tempArr);
		tempArr = new byte[ordr_dtm    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_dtm    .GetSize()  );  msgLen  += ordr_dtm    .GetSize(); ordr_dtm    .SetValue(tempArr);
		tempArr = new byte[trad_dtm    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, trad_dtm    .GetSize()  );  msgLen  += trad_dtm    .GetSize(); trad_dtm    .SetValue(tempArr);
		tempArr = new byte[stat_tp     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, stat_tp     .GetSize()  );  msgLen  += stat_tp     .GetSize(); stat_tp     .SetValue(tempArr);
		tempArr = new byte[crc_cd      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, crc_cd      .GetSize()  );  msgLen  += crc_cd      .GetSize(); crc_cd      .SetValue(tempArr);
		tempArr = new byte[ordr_tp     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_tp     .GetSize()  );  msgLen  += ordr_tp     .GetSize(); ordr_tp     .SetValue(tempArr);
		tempArr = new byte[prce_tp     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, prce_tp     .GetSize()  );  msgLen  += prce_tp     .GetSize(); prce_tp     .SetValue(tempArr);
		tempArr = new byte[prc_prsn    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, prc_prsn    .GetSize()  );  msgLen  += prc_prsn    .GetSize(); prc_prsn    .SetValue(tempArr);
		tempArr = new byte[qty_prsn    .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, qty_prsn    .GetSize()  );  msgLen  += qty_prsn    .GetSize(); qty_prsn    .SetValue(tempArr);
		tempArr = new byte[tx_status   .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, tx_status   .GetSize()  );  msgLen  += tx_status   .GetSize(); tx_status   .SetValue(tempArr);
		tempArr = new byte[tx_hash     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, tx_hash     .GetSize()  );  msgLen  += tx_hash     .GetSize(); tx_hash     .SetValue(tempArr);
	}



	public String toString()
	{
		return ( data_tp     .toString()  + 
		         user_id     .toString()  + 
		         acct_no     .toString()  + 
		         ordr_dt     .toString()  + 
		         ordr_no     .toString()  + 
		         trad_dt     .toString()  + 
		         trad_no     .toString()  + 
		         inst_cd     .toString()  + 
		         bysl_tp     .toString()  + 
		         ordr_qty    .toString()  + 
		         trad_qty    .toString()  + 
		         trad_prc    .toString()  + 
		         lqdn_prc    .toString()  + 
		         commission  .toString()  + 
		         lqdn_pl_amt .toString()  + 
		         exra        .toString()  + 
		         ordr_dtm    .toString()  + 
		         trad_dtm    .toString()  + 
		         stat_tp     .toString()  + 
		         crc_cd      .toString()  + 
		         ordr_tp     .toString()  + 
		         prce_tp     .toString()  + 
		         prc_prsn    .toString()  + 
		         qty_prsn    .toString()  + 
		         tx_status   .toString()  + 
		         tx_hash     .toString()  ); 
	}


	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeChars(toString());
	}


	public byte[] ToBytes()
	{
		strBuffer = data_tp     .toString()  + 
		            user_id     .toString()  + 
		            acct_no     .toString()  + 
		            ordr_dt     .toString()  + 
		            ordr_no     .toString()  + 
		            trad_dt     .toString()  + 
		            trad_no     .toString()  + 
		            inst_cd     .toString()  + 
		            bysl_tp     .toString()  + 
		            ordr_qty    .toString()  + 
		            trad_qty    .toString()  + 
		            trad_prc    .toString()  + 
		            lqdn_prc    .toString()  + 
		            commission  .toString()  + 
		            lqdn_pl_amt .toString()  + 
		            exra        .toString()  + 
		            ordr_dtm    .toString()  + 
		            trad_dtm    .toString()  + 
		            stat_tp     .toString()  + 
		            crc_cd      .toString()  + 
		            ordr_tp     .toString()  + 
		            prce_tp     .toString()  + 
		            prc_prsn    .toString()  + 
		            qty_prsn    .toString()  + 
		            tx_status   .toString()  + 
		            tx_hash     .toString()  ; 

		return strBuffer.getBytes();
	}


	public String toJSON()
	{
		return ( data_tp     .toString()  + 
		         user_id     .toString()  + 
		         acct_no     .toString()  + 
		         ordr_dt     .toString()  + 
		         ordr_no     .toString()  + 
		         trad_dt     .toString()  + 
		         trad_no     .toString()  + 
		         inst_cd     .toString()  + 
		         bysl_tp     .toString()  + 
		         ordr_qty    .toString()  + 
		         trad_qty    .toString()  + 
		         trad_prc    .toString()  + 
		         lqdn_prc    .toString()  + 
		         commission  .toString()  + 
		         lqdn_pl_amt .toString()  + 
		         exra        .toString()  + 
		         ordr_dtm    .toString()  + 
		         trad_dtm    .toString()  + 
		         stat_tp     .toString()  + 
		         crc_cd      .toString()  + 
		         ordr_tp     .toString()  + 
		         prce_tp     .toString()  + 
		         prc_prsn    .toString()  + 
		         qty_prsn    .toString()  + 
		         tx_status   .toString()  + 
		         tx_hash     .toString()  ); 
	}
}
