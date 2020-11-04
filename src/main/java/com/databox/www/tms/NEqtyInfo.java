/*******************************************************************************
 * File Name   : NEqtyInfo.java
 * Creation    : 2018.03.19
 * Description : real-time equity
 *
 * Author      : Logan Stone Technology Co., Ltd
 *******************************************************************************
 */
package com.databox.www.tms;

import java.io.*;
import java.lang.*;



public class NEqtyInfo
{
	public NString data_tp           = new NString(  6 ) ;    // 데이터구분
	public NString user_id           = new NString(  8 ) ;    // 사용자ID
	public NString acct_no           = new NString( 15 ) ;    // Account
	public NString crc_cd            = new NString( 10 ) ;    // Currency
	public NString crc_tp            = new NString(  1 ) ;    // 1.Base 2.Currency
	public NString receivables       = new NString( 15 ) ;    // Receivables
	public NString multiplier        = new NString( 15 ) ;    // Multiplier
	public NString balance           = new NString( 15 ) ;    // Balance
	public NString deposit           = new NString( 15 ) ;    // Deposit
	public NString withdraw          = new NString( 15 ) ;    // Withdraw
	public NString profitloss        = new NString( 15 ) ;    // P/L
	public NString commistion        = new NString( 15 ) ;    // Fee
	public NString others            = new NString( 15 ) ;    // Others
	public NString realized          = new NString( 15 ) ;    // Realized P/L
	public NString yield             = new NString( 15 ) ;    // Yield
	public NString buy_amount        = new NString( 15 ) ;    // Buyed amount
	public NString market_value      = new NString( 15 ) ;    // Market Value
	public NString unrealized        = new NString( 15 ) ;    // UnRealized P/L
	public NString total_equity      = new NString( 15 ) ;    // TE
	public NString today_deposit     = new NString( 15 ) ;    // Today deposit
	public NString today_withdraw    = new NString( 15 ) ;    // Today withdraw
	public NString accum_deposit     = new NString( 15 ) ;    // Accumulated deposit
	public NString accum_withdraw    = new NString( 15 ) ;    // Accumulated withdraw
	public NString today_profitloss  = new NString( 15 ) ;    // Today P/L
	public NString today_commistion  = new NString( 15 ) ;    // Today Fee
	public NString today_others      = new NString( 15 ) ;    // Today Others
	public NString accum_profitloss  = new NString( 15 ) ;    // Accumulated P/L
	public NString accum_commistion  = new NString( 15 ) ;    // Accumulated Fee
	public NString accum_others      = new NString( 15 ) ;    // Accumulated Others
	public NString crc_dp            = new NString(  5 ) ;    // Currency Decimal point
	public NString used_margin       = new NString( 15 ) ;    // 사용증거금
	public NString free_margin       = new NString( 15 ) ;    // 여유증거금
	public NString ordr_able_amt     = new NString( 15 ) ;    // 주문가능금액
	public NString wthr_able_amt     = new NString( 15 ) ;    // 출금가능금액


	public String strBuffer;



	/**
	 * default constructor
	 */
	public NEqtyInfo()
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
		data_tp           .Reset() ;
		user_id           .Reset() ;
		acct_no           .Reset() ;
		crc_cd            .Reset() ;
		crc_tp            .Reset() ;
		receivables       .Reset() ;
		multiplier        .Reset() ;
		balance           .Reset() ;
		deposit           .Reset() ;
		withdraw          .Reset() ;
		profitloss        .Reset() ;
		commistion        .Reset() ;
		others            .Reset() ;
		realized          .Reset() ;
		yield             .Reset() ;
		buy_amount        .Reset() ;
		market_value      .Reset() ;
		unrealized        .Reset() ;
		total_equity      .Reset() ;
		today_deposit     .Reset() ;
		today_withdraw    .Reset() ;
		accum_deposit     .Reset() ;
		accum_withdraw    .Reset() ;
		today_profitloss  .Reset() ;
		today_commistion  .Reset() ;
		today_others      .Reset() ;
		accum_profitloss  .Reset() ;
		accum_commistion  .Reset() ;
		accum_others      .Reset() ;
		crc_dp            .Reset() ;
		used_margin       .Reset() ;
		free_margin       .Reset() ;
		ordr_able_amt     .Reset() ;
		wthr_able_amt     .Reset() ;
	}



	public void ParseData ( byte[] bt )
	{
		int msgLen = 0;

		byte[] tempArr = new byte[ data_tp          .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , data_tp          .GetSize() ) ; msgLen += data_tp          .GetSize() ; data_tp          .SetValue( tempArr ) ;
		       tempArr = new byte[ user_id          .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , user_id          .GetSize() ) ; msgLen += user_id          .GetSize() ; user_id          .SetValue( tempArr ) ;
		       tempArr = new byte[ acct_no          .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , acct_no          .GetSize() ) ; msgLen += acct_no          .GetSize() ; acct_no          .SetValue( tempArr ) ;
		       tempArr = new byte[ crc_cd           .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , crc_cd           .GetSize() ) ; msgLen += crc_cd           .GetSize() ; crc_cd           .SetValue( tempArr ) ;
		       tempArr = new byte[ crc_tp           .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , crc_tp           .GetSize() ) ; msgLen += crc_tp           .GetSize() ; crc_tp           .SetValue( tempArr ) ;
		       tempArr = new byte[ receivables      .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , receivables      .GetSize() ) ; msgLen += receivables      .GetSize() ; receivables      .SetValue( tempArr ) ;
		       tempArr = new byte[ multiplier       .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , multiplier       .GetSize() ) ; msgLen += multiplier       .GetSize() ; multiplier       .SetValue( tempArr ) ;
		       tempArr = new byte[ balance          .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , balance          .GetSize() ) ; msgLen += balance          .GetSize() ; balance          .SetValue( tempArr ) ;
		       tempArr = new byte[ deposit          .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , deposit          .GetSize() ) ; msgLen += deposit          .GetSize() ; deposit          .SetValue( tempArr ) ;
		       tempArr = new byte[ withdraw         .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , withdraw         .GetSize() ) ; msgLen += withdraw         .GetSize() ; withdraw         .SetValue( tempArr ) ;
		       tempArr = new byte[ profitloss       .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , profitloss       .GetSize() ) ; msgLen += profitloss       .GetSize() ; profitloss       .SetValue( tempArr ) ;
		       tempArr = new byte[ commistion       .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , commistion       .GetSize() ) ; msgLen += commistion       .GetSize() ; commistion       .SetValue( tempArr ) ;
		       tempArr = new byte[ others           .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , others           .GetSize() ) ; msgLen += others           .GetSize() ; others           .SetValue( tempArr ) ;
		       tempArr = new byte[ realized         .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , realized         .GetSize() ) ; msgLen += realized         .GetSize() ; realized         .SetValue( tempArr ) ;
		       tempArr = new byte[ yield            .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , yield            .GetSize() ) ; msgLen += yield            .GetSize() ; yield            .SetValue( tempArr ) ;
		       tempArr = new byte[ buy_amount       .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , buy_amount       .GetSize() ) ; msgLen += buy_amount       .GetSize() ; buy_amount       .SetValue( tempArr ) ;
		       tempArr = new byte[ market_value     .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , market_value     .GetSize() ) ; msgLen += market_value     .GetSize() ; market_value     .SetValue( tempArr ) ;
		       tempArr = new byte[ unrealized       .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , unrealized       .GetSize() ) ; msgLen += unrealized       .GetSize() ; unrealized       .SetValue( tempArr ) ;
		       tempArr = new byte[ total_equity     .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , total_equity     .GetSize() ) ; msgLen += total_equity     .GetSize() ; total_equity     .SetValue( tempArr ) ;
		       tempArr = new byte[ today_deposit    .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , today_deposit    .GetSize() ) ; msgLen += today_deposit    .GetSize() ; today_deposit    .SetValue( tempArr ) ;
		       tempArr = new byte[ today_withdraw   .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , today_withdraw   .GetSize() ) ; msgLen += today_withdraw   .GetSize() ; today_withdraw   .SetValue( tempArr ) ;
		       tempArr = new byte[ accum_deposit    .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , accum_deposit    .GetSize() ) ; msgLen += accum_deposit    .GetSize() ; accum_deposit    .SetValue( tempArr ) ;
		       tempArr = new byte[ accum_withdraw   .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , accum_withdraw   .GetSize() ) ; msgLen += accum_withdraw   .GetSize() ; accum_withdraw   .SetValue( tempArr ) ;
		       tempArr = new byte[ today_profitloss .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , today_profitloss .GetSize() ) ; msgLen += today_profitloss .GetSize() ; today_profitloss .SetValue( tempArr ) ;
		       tempArr = new byte[ today_commistion .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , today_commistion .GetSize() ) ; msgLen += today_commistion .GetSize() ; today_commistion .SetValue( tempArr ) ;
		       tempArr = new byte[ today_others     .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , today_others     .GetSize() ) ; msgLen += today_others     .GetSize() ; today_others     .SetValue( tempArr ) ;
		       tempArr = new byte[ accum_profitloss .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , accum_profitloss .GetSize() ) ; msgLen += accum_profitloss .GetSize() ; accum_profitloss .SetValue( tempArr ) ;
		       tempArr = new byte[ accum_commistion .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , accum_commistion .GetSize() ) ; msgLen += accum_commistion .GetSize() ; accum_commistion .SetValue( tempArr ) ;
		       tempArr = new byte[ accum_others     .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , accum_others     .GetSize() ) ; msgLen += accum_others     .GetSize() ; accum_others     .SetValue( tempArr ) ;
		       tempArr = new byte[ crc_dp           .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , crc_dp           .GetSize() ) ; msgLen += crc_dp           .GetSize() ; crc_dp           .SetValue( tempArr ) ;
		       tempArr = new byte[ used_margin      .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , used_margin      .GetSize() ) ; msgLen += used_margin      .GetSize() ; used_margin      .SetValue( tempArr ) ;
		       tempArr = new byte[ free_margin      .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , free_margin      .GetSize() ) ; msgLen += free_margin      .GetSize() ; free_margin      .SetValue( tempArr ) ;
		       tempArr = new byte[ ordr_able_amt    .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , ordr_able_amt    .GetSize() ) ; msgLen += ordr_able_amt    .GetSize() ; ordr_able_amt    .SetValue( tempArr ) ;
		       tempArr = new byte[ wthr_able_amt    .GetSize() ] ; System.arraycopy( bt , msgLen , tempArr , 0 , wthr_able_amt    .GetSize() ) ; msgLen += wthr_able_amt    .GetSize() ; wthr_able_amt    .SetValue( tempArr ) ;
	}



	public String toString()
	{
		return ( data_tp          .toString() +
		         user_id          .toString() +
		         acct_no          .toString() +
		         crc_cd           .toString() +
		         crc_tp           .toString() +
		         receivables      .toString() +
		         multiplier       .toString() +
		         balance          .toString() +
		         deposit          .toString() +
		         withdraw         .toString() +
		         profitloss       .toString() +
		         commistion       .toString() +
		         others           .toString() +
		         realized         .toString() +
		         yield            .toString() +
		         buy_amount       .toString() +
		         market_value     .toString() +
		         unrealized       .toString() +
		         total_equity     .toString() +
		         today_deposit    .toString() +
		         today_withdraw   .toString() +
		         accum_deposit    .toString() +
		         accum_withdraw   .toString() +
		         today_profitloss .toString() +
		         today_commistion .toString() +
		         today_others     .toString() +
		         accum_profitloss .toString() +
		         accum_commistion .toString() +
		         accum_others     .toString() +
		         crc_dp           .toString() +
		         used_margin      .toString() +
		         free_margin      .toString() +
		         ordr_able_amt    .toString() +
		         wthr_able_amt    .toString() ) ;
	}



	private void writeObject( ObjectOutputStream out ) throws IOException
	{
		out.writeChars( toString() ) ;
	}



	public byte[] ToBytes()
	{
		strBuffer = data_tp          .toString() +
		            user_id          .toString() +
		            acct_no          .toString() +
		            crc_cd           .toString() +
		            crc_tp           .toString() +
		            receivables      .toString() +
		            multiplier       .toString() +
		            balance          .toString() +
		            deposit          .toString() +
		            withdraw         .toString() +
		            profitloss       .toString() +
		            commistion       .toString() +
		            others           .toString() +
		            realized         .toString() +
		            yield            .toString() +
		            buy_amount       .toString() +
		            market_value     .toString() +
		            unrealized       .toString() +
		            total_equity     .toString() +
		            today_deposit    .toString() +
		            today_withdraw   .toString() +
		            accum_deposit    .toString() +
		            accum_withdraw   .toString() +
		            today_profitloss .toString() +
		            today_commistion .toString() +
		            today_others     .toString() +
		            accum_profitloss .toString() +
		            accum_commistion .toString() +
		            accum_others     .toString() +
		            crc_dp           .toString() +
		            used_margin      .toString() +
		            free_margin      .toString() +
		            ordr_able_amt    .toString() +
		            wthr_able_amt    .toString() ;



		return strBuffer.getBytes() ;
	}



	public String toJSON()
	{
		return ( data_tp          .toString() +
		         user_id          .toString() +
		         acct_no          .toString() +
		         crc_cd           .toString() +
		         crc_tp           .toString() +
		         receivables      .toString() +
		         multiplier       .toString() +
		         balance          .toString() +
		         deposit          .toString() +
		         withdraw         .toString() +
		         profitloss       .toString() +
		         commistion       .toString() +
		         others           .toString() +
		         realized         .toString() +
		         yield            .toString() +
		         buy_amount       .toString() +
		         market_value     .toString() +
		         unrealized       .toString() +
		         total_equity     .toString() +
		         today_deposit    .toString() +
		         today_withdraw   .toString() +
		         accum_deposit    .toString() +
		         accum_withdraw   .toString() +
		         today_profitloss .toString() +
		         today_commistion .toString() +
		         today_others     .toString() +
		         accum_profitloss .toString() +
		         accum_commistion .toString() +
		         accum_others     .toString() +
		         crc_dp           .toString() +
		         used_margin      .toString() +
		         free_margin      .toString() +
		         ordr_able_amt    .toString() +
		         wthr_able_amt    .toString() ) ;
	}
}