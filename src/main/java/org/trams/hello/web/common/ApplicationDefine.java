package org.trams.hello.web.common;

public class ApplicationDefine {
	public static Integer USER_ROLE_USER=1;
	public static Integer USER_ROLE_COUNSELOR_FREELANCE=2;
	public static Integer USER_ROLE_COUNSELOR_IN_CENTER=3;
	public static Integer USER_ROLE_COUNSELING_CENTER=4;
	public static Integer USER_ROLE_MEMBER_BUSSINESS=5;
	public static Integer USER_ROLE_SUB_BUSSINESS=6;
	public static Integer USER_ROLE_BUSSINESS=7;
	public static Integer USER_ROLE_ADMIN=8;
	public static Integer USER_ROLE_MONITERING=9;
	
	public static Short COUNSELOR_LEVEL_SILVER = 1;
	public static Short COUNSELOR_LEVEL_GOLE = 2;
	public static Short COUNSELOR_LEVEL_DIAMOND = 3;
	public static Float COUNSELOR_PROFIT_PERCENT_DEFAULT = 70f;
	public static Float COUNSELOR_CENTER_PROFIT_PERCENT_DEFAULT = 85f;
	
	/*status counselor*/
	public static Integer STATUS_COUNSELOR_WAITING = 0;
	public static Integer STATUS_COUNSELOR_NOMAL_CAN_COUNSELING = 1;
	public static Integer STATUS_COUNSELOR_NOMAL_CANNOT_COUNSELING = 2;
	public static Integer STATUS_COUNSELOR_RESERVE_ACCOUNT = 3;
	public static Integer STATUS_COUNSELOR_WAITING_REQUEST_PROFILE = 4;
	
	/*status active user*/
	public static Integer STATUS_ACTIVE_USER_NORMAL = 0;
	public static Integer STATUS_ACTIVE_USER_BANNED = 1;
	public static Integer STATUS_ACTIVE_USER_DORMAT_ACCOUNT = 2;
	public static Integer STATUS_ACTIVE_USER_WITHDRAWAL = 3;
	
	/*profit counseling*/
	public static Float TAX_PROFIT_COUNSELOR = 0.033F;
	public static Float TAX_VAT = 1/11f;
	public enum EmailSubject {

		AUTHEN_REGISTER_EMAIL("[Hello] 회원가입 이메일 인증")
		, FINDPW_VERYFY_EMAIL("[Hello] 회원가입 이메일 인증")
		, FINDPW_EMAIL("[Hello] 임시 비밀번호 발송")
		, AUTHEN_FINDPWD("[Hello] 비밀번호 찾기 이메일 인증")
		, AUTHEN_TMPPWD("[Hello] 임시 비밀번호 발송")
		, ACCEPT_COUNSELOR("[Hello] 심리상담사 등록 신청 결과 안내")
		, REFUSE_COUNSELOR("[Hello] 심리상담사 등록 신청 결과 안내")
		, ACCEPT_PROMOTION_PAGE("[Hello] 홍보페이지 검토 결과 안내")
		, REFUSE_PROMOTION_PAGE("[Hello] 홍보페이지 검토 결과 안내")
		, COMPLETE_PSYCHOLOGICAL_TEST("[Hello] 심리검사 결제 완료 안내")
		, BAN_ACCOUNT_MEMBER("[Hello] 회원 제재 안내")
		, BAN_ACCOUNT_COUNSELOR("[Hello] 심리상담사 제재 안내")
		, CHANGE_ACCOUNT_TO_DORMANT_ACCOUNT("[Hello] 휴면 전환 예정 안내")
		, REFUSE_COUNSELOR_CHANGE_PROFILE("[Hello] 심리상담사 정보 변경 승인 결과 안내")
		, INIQUIRY("Hello 고객센터 입니다.");
				
		String code;

		EmailSubject(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}
	
	public enum ExcelTemplate {
		MEMBER_STATISTIC("excel-template/MemberStatistics.xls", "회원 통계"),
		COUNSELOR_STATISTIC("excel-template/CounselorStatistics.xls", "가입-탈퇴 통계 "),
		COUNSELING_STATISTIC("excel-template/CounselingStatistics.xls", "상담 통계 "),
		COUNSELING_MEMBER_STATISTIC("excel-template/CounselingStatistics.xls", "상담 통계 "),
		MINDCHECK_STATISTIC("excel-template/MindCheckStatistics.xls", "마인드체크 통계  "),
		PSYCHOLOGICAL_STATISTIC("excel-template/PsychologicalStatistics.xls", "심리 검사 통계 "),
		SERVICE_STATISTIC("excel-template/ServiceStatistics.xls", "서비스 활동 통계 "),
		COUNSELOR_RANKING("excel-template/CounselorRanking.xls", "상담사 순위"),
		PROFIT_CENTER("excel-template/ProfitCenter.xls", "정산 내역"),
		LOGIN_STATISTIC("excel-template/LoginStatistics.xls", "로그인 통계 "),;
		String url;
		
		String name;
		

		ExcelTemplate(String url, String name) {
			this.url = url;
			this.name = name;
		}

		public String getUrl() {
			return url;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public enum Notification_Type {

		Reservation((int)1), Message((int)2), Inquiry((int)3), NoticeOrEvent((int)4), Satisfaction((int)5), PsychologicalTest((int)6)
				, PsychologicalResult((int)7), CancelRequestPsychologicalTest((int)8), Home ((int)9), CounselorDetail((int)10);
		
		int code;

		Notification_Type(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	public enum Heart_Number {

		CompleteCounseling((int)5), Shared((int)1), SatisfactionComplete((int)3);
		
		int code;

		Heart_Number(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	public enum Voucher_CounselingTimeType {

		Minutes30((short)1), Minutes50((short)2);
		
		short code;

		Voucher_CounselingTimeType(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Push_Type {

		SendAnyUser((short)1), SendAll((short)2);
		
		short code;

		Push_Type(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Voucher_Type {

		BusinessVoucher((short)1), HeartVoucher((short)2);
		
		short code;

		Voucher_Type(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Push_Status {

		Waiting((short)0), Success((short)1), Cancel((short)2);
		
		short code;

		Push_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum VoucherUser_Status {

		CanUse((short)0), Used((short)1), Expired((short)2), Reserved((short)2);
		
		short code;

		VoucherUser_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Questionnaire_Type {

		SatisfactionEvaluation ((short)1), MindCheck((short)2), SelfDiagnosis((short)2);
		
		short code;

		Questionnaire_Type(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum RechargeHeart_Status {

		HelloCharge ((int)47), CounselingComplete((int)48), Satisfaction((int)49),AppShare((int)50),ManualRechargeByAdmin((int)51);
		int code;
		RechargeHeart_Status(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	public enum PaymentHeart_Status {

		Exchange_Voucher ((int)52), RemoveHeart((int)53),WithdrawAccount((int)54),ManualPaymentByAdmin((int)55);
		int code;
		PaymentHeart_Status(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	
	public enum PaymentHistory_PaymentMethod {

		Card ((int)26), CellPhone((int)27), Kakao((int)28);
		int code;
		PaymentHistory_PaymentMethod(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	public enum Recharge_Status {

		Watting ((short)0), PaymentSuccess((short)1),Cancel((short)2);
		short code;
		Recharge_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	
	public enum Payment_TypePaymentStatus {

		Recharge ((int)1), Payment((int)2);
		int code;
		Payment_TypePaymentStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	
	public enum Payment_TypeCoin {

		Coin ((short)1), Heart((short)2), Voucher((short)3);
		short code;
		Payment_TypeCoin(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Payment_Status {

		Watting ((short)0), PaymentSuccess((short)1), NeedTanferMoney((short)2), Cancel((short)3);
		short code;
		Payment_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	
	public enum Payment_Reson {

		Reverstation("상담 예약"), Counseling("실시간 상담"),PaymentPsychologicalTest("심리 검사"), Manual_deduction("Admin deduction");
		String code;
		Payment_Reson(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}
	public enum PaymentTypeUse {

		Reservation((int)35), Consultation((int)36), Psychological((int)37),Membership_withdrawal((int)38), Manual_deduction((int)39);
		
		int code;

		PaymentTypeUse(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	public enum Recharge_Reson {
		NormalCharging("정상 충전"), CancelReservation("상담 예약 취소"), Manual("Admin send coin");
		String code;
		Recharge_Reson(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}
	
	
	public enum Recharge_TypeUse {

		NormalCharging((short)40), CancelReservation((short)41), ManualPayments((short)42);
		
		short code;

		Recharge_TypeUse(short code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	public enum Counselor_Status {
		Waiting((short)0), NormalDisplay((short)1),  NormalNotDisplay((short)2), Reserve((short)3), WaitingRequestProfile((short) 4);
		short code;
		Counselor_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Counselor_Actived {
		OFF((short)0), ON((short)1);
		short code;
		Counselor_Actived(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum CounselingSession_Status {

		RESERVATION_CANCEL((short)0), RESERVATION_COMPLETE((short)1), COUNSELED_ADMIN_NOT_REFUND((short)2),
		COUNSELED_ADMIN_REFUND((short)3);
		
		short code;

		CounselingSession_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}

	public enum UserStatusActive {

		NORMAL((short)0), BANNED((short)1), DORMAT((short)2), WITHDRAWAL((short)3) ;

		short code;

		UserStatusActive(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}

	public enum CounselingSessionStatus {

		CANCEL((short)0), NOT_COUNSELED((short)1), COUNSELD((short)2), COMPLETED((short)3);

		short code;

		CounselingSessionStatus(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum UserFile_Type {

		Certificate ((short)1);
		short code;
		UserFile_Type(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}

	public enum PaymentProfitStatus {
		PENDING((short) 0), PAID((short) 1);

		short code;

		PaymentProfitStatus(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum RequestPsychological_Status {
		WatitngPayment((short) 0), CompletePayment((short) 1), WaiverPayment((short) 2), CancelPayment((short) 3), CancelRequest((short) 4);

		short code;

		RequestPsychological_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}

}
