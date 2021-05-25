package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BCartDao;

public class BCartAllDeleteCommand implements BCommand { // 2021.05.19 조혜지 - 장바구니 전체 상품 삭제하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

		String cId = (String)session.getAttribute("cId");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************
		
		BCartDao dao = new BCartDao();
		dao.cartAllDelete(cId);
	}

}
