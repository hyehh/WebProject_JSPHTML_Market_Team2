package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoBuy;
import com.jspproject.bbs.dao.BDaoDelivery;
import com.jspproject.bbs.dao.BDaoProduct;
import com.jspproject.bbs.dao.BDaoQnA;

public class BStoreCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		BDaoProduct daoProduct = new BDaoProduct();
		BDaoBuy daoBuy = new BDaoBuy();
		BDaoDelivery daoDelivery = new BDaoDelivery();
		BDaoQnA daoQnA = new BDaoQnA();
		
		// 상품리스트
		int salesCount = daoProduct.sales();
		int salesTure = daoProduct.salesTrue();
		int salesFalse = daoProduct.salesFalse();
		request.setAttribute("SALESCOUNT", salesCount);
		request.setAttribute("SALESTURE", salesTure);
		request.setAttribute("SALESFALSE", salesFalse);
		
		// 주문리스트
		int buyCount = daoBuy.buy();
		int buyTrue = daoBuy.buyTrue();
		int buyCancel = daoBuy.buyCancel();
		request.setAttribute("BUYCOUNT", buyCount);
		request.setAttribute("BUYTRUE", buyTrue);
		request.setAttribute("BUYCANCEL", buyCancel);
		
		
		// 배송리스트
		int deliveryAll = daoDelivery.deliveryAll();
		int deliveryEnd = daoDelivery.deliveryEnd();
		int deliveryING = daoDelivery.deliveryING();
		request.setAttribute("DELIVERYALL", deliveryAll);
		request.setAttribute("DELIVERYEND", deliveryEnd);
		request.setAttribute("DELIVERYING", deliveryING);
		
		// 문의리스트
		int allQnACount = daoQnA.allQnA();
		int QnATure = daoQnA.QnATure();
		int QnAFalse = daoQnA.QnAFalse();
		request.setAttribute("allQnACount", allQnACount);
		request.setAttribute("QnATure",QnATure);
		request.setAttribute("QnAFalse",QnAFalse);
	}

}
