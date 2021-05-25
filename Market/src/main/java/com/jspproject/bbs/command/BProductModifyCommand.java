package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoProduct;

public class BProductModifyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// pCode, pCategory, pName, pPrice, pExpirationDate, pQuantity, pStatus
		BDaoProduct dao = new BDaoProduct();
		String pCode = request.getParameter("pCode");
		String pCategory = request.getParameter("pCategory");
		String pName = request.getParameter("pName");
		String pPriceDC = request.getParameter("pPriceDC");
		String pProductEA = request.getParameter("pProductEA");
		String pStatus = request.getParameter("pStatus");
		
		System.out.println("수정ㅋ인데 왜 안될까? 코드" + pCode + "/카테고리" + pCategory + "/이름" + pName + "/할인가격" + pPriceDC + "/수량" + pProductEA + "/여부" + pStatus);
		dao.productModify(pCode, pCategory, pName, pPriceDC, pProductEA, pStatus);
//		dao.upDateRegisteㅋㅋㅋㅋr(pCode);
	}

}
