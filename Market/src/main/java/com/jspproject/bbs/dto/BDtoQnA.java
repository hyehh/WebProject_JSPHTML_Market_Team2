package com.jspproject.bbs.dto;

public class BDtoQnA {
	
	int qnaCode;
	String customer_cId;
	String qTitle;
	String qContent;
	String qAddDate;
	String aAddDate;
	String aContent;
	String answer;
	String qFilePath;
	
	
	
	public BDtoQnA() {
		// TODO Auto-generated constructor stub
	}

	public BDtoQnA(int qnaCode, String customer_cId, String qTitle, String qContent, String qAddDate, String aAddDate,
			String aContent, String answer, String qFilePath) {
		super();
		this.qnaCode = qnaCode;
		this.customer_cId = customer_cId;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qAddDate = qAddDate;
		this.aAddDate = aAddDate;
		this.aContent = aContent;
		this.answer = answer;
		this.qFilePath = qFilePath;
	}
	
	

	public int getQnaCode() {
		return qnaCode;
	}

	public void setQnaCode(int qnaCode) {
		this.qnaCode = qnaCode;
	}

	public String getCustomer_cId() {
		return customer_cId;
	}

	public void setCustomer_cId(String customer_cId) {
		this.customer_cId = customer_cId;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public String getqAddDate() {
		return qAddDate;
	}

	public void setqAddDate(String qAddDate) {
		this.qAddDate = qAddDate;
	}

	public String getaAddDate() {
		return aAddDate;
	}

	public void setaAddDate(String aAddDate) {
		this.aAddDate = aAddDate;
	}

	public String getaContent() {
		return aContent;
	}

	public void setaContent(String aContent) {
		this.aContent = aContent;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getqFilePath() {
		return qFilePath;
	}

	public void setqFilePath(String qFilePath) {
		this.qFilePath = qFilePath;
	}
	
}
