package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BSellerDao;
import com.jspproject.bbs.util.Share;

public class BSellerPwUpdateCommand implements BCommand { // 2021.05.25 조혜지 - 판매자 비밀번호 변경하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String sPw = request.getParameter("sPw");
		System.out.println(sPw);
//		session.setAttribute("sId", "seller");
//		String sId = (String)session.getAttribute("sId");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************
		String sId = Share.userId;
		BSellerDao dao = new BSellerDao();
		dao.sellerPwUpdate(sPw, sId);
	}

}
