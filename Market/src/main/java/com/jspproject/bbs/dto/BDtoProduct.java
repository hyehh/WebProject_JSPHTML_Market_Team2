package com.jspproject.bbs.dto;

public class BDtoProduct {
	
	int pCode;
	String pName;
	String pCategory;
	int pPrice;
	int pPriceDC;
	int pDiscount;
	String pExpirationDate;
	int pProductEA;
	String pMainFilePath;
	String pDetailFilePath;
	String pStatus;
	String pDelete;
	String pAddDate;
	
	public BDtoProduct() {
		// TODO Auto-generated constructor stub
	}

	public BDtoProduct(int pCode, String pName, String pCategory, int pPrice, int pPriceDC, int pDiscount,
			String pExpirationDate, int pProductEA) {
		super();
		this.pCode = pCode;
		this.pName = pName;
		this.pCategory = pCategory;
		this.pPrice = pPrice;
		this.pPriceDC = pPriceDC;
		this.pDiscount = pDiscount;
		this.pExpirationDate = pExpirationDate;
		this.pProductEA = pProductEA;
	}

	public BDtoProduct(int pCode, String pName, String pCategory, int pPrice, int pPriceDC, int pDiscount,
			String pExpirationDate, int pProductEA, String pMainFilePath, String pDetailFilePath) {
		super();
		this.pCode = pCode;
		this.pName = pName;
		this.pCategory = pCategory;
		this.pPrice = pPrice;
		this.pPriceDC = pPriceDC;
		this.pDiscount = pDiscount;
		this.pExpirationDate = pExpirationDate;
		this.pProductEA = pProductEA;
		this.pMainFilePath = pMainFilePath;
		this.pDetailFilePath = pDetailFilePath;
	}
	
	public BDtoProduct(int pCode, String pCategory, String pName, int pPrice, String pExpirationDate, int pProductEA) {
		super();
		this.pCode = pCode;
		this.pCategory = pCategory;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pExpirationDate = pExpirationDate;
		this.pProductEA = pProductEA;
	}
	
	public BDtoProduct(int pCode, String pCategory, String pName, int pPrice, String pExpirationDate, int pProductEA,
			String pStatus) {
		super();
		this.pCode = pCode;
		this.pCategory = pCategory;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pExpirationDate = pExpirationDate;
		this.pProductEA = pProductEA;
		this.pStatus = pStatus;
	}
	
	public BDtoProduct(int pCode, String pName, int pPrice, int pPriceDC, String pExpirationDate, String pStatus,
			String pAddDate, String pCategory) {
		super();
		this.pCode = pCode;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pPriceDC = pPriceDC;
		this.pExpirationDate = pExpirationDate;
		this.pStatus = pStatus;
		this.pAddDate = pAddDate;
		this.pCategory = pCategory;
	}

	public BDtoProduct(int pCode, String pCategory, String pName, int pPrice, int pPriceDC, String pExpirationDate,
			int pProductEA, String pStatus, String pAddDate, String pMainFilePath) {
		super();
		this.pCode = pCode;
		this.pCategory = pCategory;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pPriceDC = pPriceDC;
		this.pExpirationDate = pExpirationDate;
		this.pProductEA = pProductEA;
		this.pStatus = pStatus;
		this.pAddDate = pAddDate;
		this.pMainFilePath = pMainFilePath;
	}

	public int getpCode() {
		return pCode;
	}

	public void setpCode(int pCode) {
		this.pCode = pCode;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getpPriceDC() {
		return pPriceDC;
	}

	public void setpPriceDC(int pPriceDC) {
		this.pPriceDC = pPriceDC;
	}

	public int getpDiscount() {
		return pDiscount;
	}

	public void setpDiscount(int pDiscount) {
		this.pDiscount = pDiscount;
	}

	public String getpExpirationDate() {
		return pExpirationDate;
	}

	public void setpExpirationDate(String pExpirationDate) {
		this.pExpirationDate = pExpirationDate;
	}

	public int getpProductEA() {
		return pProductEA;
	}

	public void setpProductEA(int pProductEA) {
		this.pProductEA = pProductEA;
	}

	public String getpMainFilePath() {
		return pMainFilePath;
	}

	public void setpMainFilePath(String pMainFilePath) {
		this.pMainFilePath = pMainFilePath;
	}

	public String getpDetailFilePath() {
		return pDetailFilePath;
	}

	public void setpDetailFilePath(String pDetailFilePath) {
		this.pDetailFilePath = pDetailFilePath;
	}

	public String getpStatus() {
		return pStatus;
	}

	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}

	public String getpDelete() {
		return pDelete;
	}

	public void setpDelete(String pDelete) {
		this.pDelete = pDelete;
	}

	public String getpAddDate() {
		return pAddDate;
	}

	public void setpAddDate(String pAddDate) {
		this.pAddDate = pAddDate;
	}
	
	
}
