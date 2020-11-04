package com.databox.www.model;

import lombok.Data;

@Data
public class TestInfo {
	
	private String s_dept_cd; // 부점코드
	private String s_dept_nm; // 부점명
	private String s_dept_tp; // 부점구분      1.: 일반(Office)  2 : 총괄(Admin)
	private String s_dept_bsns_tp; // / 부점_업무_구분  1 : 관리(Admin) 2 : 위탁 3 : 상품 4 : 일반(Office)
	
	private String s_brch_cd; //  사업장코드
	private String s_brch_nm; //  사업장명
	private String s_clnt_cd; //  거래처코드
	private String s_budgt_mngr_yn; //  예산관리여부
	
	private String s_budgt_grp_nm_1; //  예산그룹명
	private String s_budgt_grp_nm_2; //  예산그룹명2
	private String s_use_yn; //  사용명
	private String s_upd_wrk_id; //  변경작업ID
	private String s_upd_wrk_dtm; //  변경작업일지
	
}
