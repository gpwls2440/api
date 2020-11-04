package com.databox.www.tms;
/*******************************************************************************
 * File Name   : NNtrdInfo.java
 * Creation    : 2018.03.19
 * Description : real-time price tick
 *
 * Author      : Logan Stone Technology Co., Ltd
 *******************************************************************************
 */

import java.io.*;
import java.lang.*;


public class NNtrdInfo
{
	public NString data_tp           = new NString(  6);    // 데이터구분
	public NString user_id           = new NString(  8);    // 사용자ID
	public NString acct_no           = new NString( 15);    // 계좌번호
	public NString ordr_dt           = new NString(  8);    // 주문일자
	public NString ordr_no           = new NString( 20);    // 주문번호
	public NString orig_ordr_no      = new NString( 20);    // 원주문번호
	public NString mthr_ordr_no      = new NString( 20);    // 모주문번호
	public NString inst_cd           = new NString( 32);    // 종목코드
	public NString mrkt_cd           = new NString( 10);    // 거래소코드
	public NString bysl_tp           = new NString(  1);    // 매수매도
	public NString ordr_qty          = new NString( 15);    // 주문수량
	public NString mdfy_qty          = new NString( 15);    // 정정수량
	public NString cncl_qty          = new NString( 15);    // 취소수량
	public NString trad_qty          = new NString( 15);    // 체결수량
	public NString remn_qty          = new NString( 15);    // 주문잔량
	public NString ordr_prc          = new NString( 15);    // 주문가격
	public NString tick_cnt          = new NString(  5);    // 틱수
	public NString cond_prc          = new NString( 15);    // 조건가격
	public NString akprc_tp          = new NString(  1);    // 호가구분
	public NString ordr_tp           = new NString(  1);    // 주문구분
	public NString trad_cond         = new NString(  1);    // 체결조건
	public NString prce_tp           = new NString(  1);    // 가격조건(1.시장가 2.지정가 3.STOP 4.STOP LIMIT)
	public NString aval_tp           = new NString(  1);    // GTC/DAY
	public NString acpt_tp           = new NString(  1);    // 접수구분
	public NString ordr_dtm          = new NString( 20);    // 주문시간
	public NString crc_cd            = new NString( 10);    // 통화코드
	public NString prc_prsn          = new NString(  5);    // 가격
	public NString qty_prsn          = new NString(  5);    // 수량

	public String strBuffer;


	/**
	 * default constructor
	 */
	public NNtrdInfo()
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
		data_tp          .Reset();
		user_id          .Reset();
		acct_no          .Reset();
		ordr_dt          .Reset();
		ordr_no          .Reset();
		orig_ordr_no     .Reset();
		mthr_ordr_no     .Reset();
		inst_cd          .Reset();
		mrkt_cd          .Reset();
		bysl_tp          .Reset();
		ordr_qty         .Reset();
		mdfy_qty         .Reset();
		cncl_qty         .Reset();
		trad_qty         .Reset();
		remn_qty         .Reset();
		ordr_prc         .Reset();
		tick_cnt         .Reset();
		cond_prc         .Reset();
		akprc_tp         .Reset();
		ordr_tp          .Reset();
		trad_cond        .Reset();
		prce_tp          .Reset();
		aval_tp          .Reset();
		acpt_tp          .Reset();
		ordr_dtm         .Reset();
		crc_cd           .Reset();
		prc_prsn         .Reset();
		qty_prsn         .Reset();
	}


	public void ParseData ( byte[] bt )
	{
		int msgLen = 0;

		byte[] tempArr = new byte[data_tp.GetSize()]; System.arraycopy(bt, msgLen, tempArr, 0, data_tp.GetSize() ); msgLen += data_tp.GetSize(); data_tp.SetValue(tempArr);

		tempArr = new byte[user_id       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, user_id       .GetSize()  );  msgLen  += user_id       .GetSize(); user_id       .SetValue(tempArr);
		tempArr = new byte[acct_no       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, acct_no       .GetSize()  );  msgLen  += acct_no       .GetSize(); acct_no       .SetValue(tempArr);
		tempArr = new byte[ordr_dt       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_dt       .GetSize()  );  msgLen  += ordr_dt       .GetSize(); ordr_dt       .SetValue(tempArr);
		tempArr = new byte[ordr_no       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_no       .GetSize()  );  msgLen  += ordr_no       .GetSize(); ordr_no       .SetValue(tempArr);
		tempArr = new byte[orig_ordr_no  .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, orig_ordr_no  .GetSize()  );  msgLen  += orig_ordr_no  .GetSize(); orig_ordr_no  .SetValue(tempArr);
		tempArr = new byte[mthr_ordr_no  .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, mthr_ordr_no  .GetSize()  );  msgLen  += mthr_ordr_no  .GetSize(); mthr_ordr_no  .SetValue(tempArr);
		tempArr = new byte[inst_cd       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, inst_cd       .GetSize()  );  msgLen  += inst_cd       .GetSize(); inst_cd       .SetValue(tempArr);
		tempArr = new byte[mrkt_cd       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, mrkt_cd       .GetSize()  );  msgLen  += mrkt_cd       .GetSize(); mrkt_cd       .SetValue(tempArr);
		tempArr = new byte[bysl_tp       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, bysl_tp       .GetSize()  );  msgLen  += bysl_tp       .GetSize(); bysl_tp       .SetValue(tempArr);
		tempArr = new byte[ordr_qty      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_qty      .GetSize()  );  msgLen  += ordr_qty      .GetSize(); ordr_qty      .SetValue(tempArr);
		tempArr = new byte[mdfy_qty      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, mdfy_qty      .GetSize()  );  msgLen  += mdfy_qty      .GetSize(); mdfy_qty      .SetValue(tempArr);
		tempArr = new byte[cncl_qty      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, cncl_qty      .GetSize()  );  msgLen  += cncl_qty      .GetSize(); cncl_qty      .SetValue(tempArr);
		tempArr = new byte[trad_qty      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, trad_qty      .GetSize()  );  msgLen  += trad_qty      .GetSize(); trad_qty      .SetValue(tempArr);
		tempArr = new byte[remn_qty      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, remn_qty      .GetSize()  );  msgLen  += remn_qty      .GetSize(); remn_qty      .SetValue(tempArr);
		tempArr = new byte[ordr_prc      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_prc      .GetSize()  );  msgLen  += ordr_prc      .GetSize(); ordr_prc      .SetValue(tempArr);
		tempArr = new byte[tick_cnt      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, tick_cnt      .GetSize()  );  msgLen  += tick_cnt      .GetSize(); tick_cnt      .SetValue(tempArr);
		tempArr = new byte[cond_prc      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, cond_prc      .GetSize()  );  msgLen  += cond_prc      .GetSize(); cond_prc      .SetValue(tempArr);
		tempArr = new byte[akprc_tp      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, akprc_tp      .GetSize()  );  msgLen  += akprc_tp      .GetSize(); akprc_tp      .SetValue(tempArr);
		tempArr = new byte[ordr_tp       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_tp       .GetSize()  );  msgLen  += ordr_tp       .GetSize(); ordr_tp       .SetValue(tempArr);
		tempArr = new byte[trad_cond     .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, trad_cond     .GetSize()  );  msgLen  += trad_cond     .GetSize(); trad_cond     .SetValue(tempArr);
		tempArr = new byte[prce_tp       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, prce_tp       .GetSize()  );  msgLen  += prce_tp       .GetSize(); prce_tp       .SetValue(tempArr);
		tempArr = new byte[aval_tp       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, aval_tp       .GetSize()  );  msgLen  += aval_tp       .GetSize(); aval_tp       .SetValue(tempArr);
		tempArr = new byte[acpt_tp       .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, acpt_tp       .GetSize()  );  msgLen  += acpt_tp       .GetSize(); acpt_tp       .SetValue(tempArr);
		tempArr = new byte[ordr_dtm      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, ordr_dtm      .GetSize()  );  msgLen  += ordr_dtm      .GetSize(); ordr_dtm      .SetValue(tempArr);
		tempArr = new byte[crc_cd        .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, crc_cd        .GetSize()  );  msgLen  += crc_cd        .GetSize(); crc_cd        .SetValue(tempArr);
		tempArr = new byte[prc_prsn      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, prc_prsn      .GetSize()  );  msgLen  += prc_prsn      .GetSize(); prc_prsn      .SetValue(tempArr);
		tempArr = new byte[qty_prsn      .GetSize()];  System.arraycopy(bt, msgLen, tempArr, 0, qty_prsn      .GetSize()  );  msgLen  += qty_prsn      .GetSize(); qty_prsn      .SetValue(tempArr);
	}



	public String toString()
	{
		return ( data_tp       .toString()  + 
		         user_id       .toString()  + 
		         acct_no       .toString()  + 
		         ordr_dt       .toString()  + 
		         ordr_no       .toString()  + 
		         orig_ordr_no  .toString()  + 
		         mthr_ordr_no  .toString()  + 
		         inst_cd       .toString()  + 
		         mrkt_cd       .toString()  + 
		         bysl_tp       .toString()  + 
		         ordr_qty      .toString()  + 
		         mdfy_qty      .toString()  + 
		         cncl_qty      .toString()  + 
		         trad_qty      .toString()  + 
		         remn_qty      .toString()  + 
		         ordr_prc      .toString()  + 
		         tick_cnt      .toString()  + 
		         cond_prc      .toString()  + 
		         akprc_tp      .toString()  + 
		         ordr_tp       .toString()  + 
		         trad_cond     .toString()  + 
		         prce_tp       .toString()  + 
		         aval_tp       .toString()  + 
		         acpt_tp       .toString()  + 
		         ordr_dtm      .toString()  + 
		         crc_cd        .toString()  + 
		         prc_prsn      .toString()  + 
		         qty_prsn      .toString()  ); 
	}


	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeChars(toString());
	}


	public byte[] ToBytes()
	{
		strBuffer = data_tp       .toString()  + 
		            user_id       .toString()  + 
		            acct_no       .toString()  + 
		            ordr_dt       .toString()  + 
		            ordr_no       .toString()  + 
		            orig_ordr_no  .toString()  + 
		            mthr_ordr_no  .toString()  + 
		            inst_cd       .toString()  + 
		            mrkt_cd       .toString()  + 
		            bysl_tp       .toString()  + 
		            ordr_qty      .toString()  + 
		            mdfy_qty      .toString()  + 
		            cncl_qty      .toString()  + 
		            trad_qty      .toString()  + 
		            remn_qty      .toString()  + 
		            ordr_prc      .toString()  + 
		            tick_cnt      .toString()  + 
		            cond_prc      .toString()  + 
		            akprc_tp      .toString()  + 
		            ordr_tp       .toString()  + 
		            trad_cond     .toString()  + 
		            prce_tp       .toString()  + 
		            aval_tp       .toString()  + 
		            acpt_tp       .toString()  + 
		            ordr_dtm      .toString()  + 
		            crc_cd        .toString()  + 
		            prc_prsn      .toString()  + 
		            qty_prsn      .toString()  ; 

		return strBuffer.getBytes();
	}


	public String toJSON()
	{
		return ( data_tp       .toString()  + 
		         user_id       .toString()  + 
		         acct_no       .toString()  + 
		         ordr_dt       .toString()  + 
		         ordr_no       .toString()  + 
		         orig_ordr_no  .toString()  + 
		         mthr_ordr_no  .toString()  + 
		         inst_cd       .toString()  + 
		         mrkt_cd       .toString()  + 
		         bysl_tp       .toString()  + 
		         ordr_qty      .toString()  + 
		         mdfy_qty      .toString()  + 
		         cncl_qty      .toString()  + 
		         trad_qty      .toString()  + 
		         remn_qty      .toString()  + 
		         ordr_prc      .toString()  + 
		         tick_cnt      .toString()  + 
		         cond_prc      .toString()  + 
		         akprc_tp      .toString()  + 
		         ordr_tp       .toString()  + 
		         trad_cond     .toString()  + 
		         prce_tp       .toString()  + 
		         aval_tp       .toString()  + 
		         acpt_tp       .toString()  + 
		         ordr_dtm      .toString()  + 
		         crc_cd        .toString()  + 
		         prc_prsn      .toString()  + 
		         qty_prsn      .toString()  ); 
	}
}
