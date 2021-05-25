package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BReviewBNumCommand implements BCommand { // 2021.05.19 조혜지 - 리뷰 등록 시 주문번호 session에 저장하기 위한 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		
		String bNumber = request.getParameter("bNumber");
		String pCode = request.getParameter("pCode");

		session.setAttribute("bNumber", bNumber);
		session.setAttribute("pCode", pCode);
	}

}
