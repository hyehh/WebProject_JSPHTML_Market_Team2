package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BCustomerInfoDao;

public class BCustomerSignOutCommand implements BCommand { // 2021.05.15 조혜지 - 회원 탈퇴 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String[] cSignOutReason = request.getParameterValues("cSignOutReason");
		String cSignOutContent =  request.getParameter("cSignOutContent");

		String cId = (String)session.getAttribute("cId");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************
		
		BCustomerInfoDao dao = new BCustomerInfoDao();
		dao.customerSignOut(cSignOutReason, cSignOutContent, cId);
	}

}
