package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BCartDao;
import com.jspproject.bbs.util.Share;

public class BCustomerCartUpdateCommand implements BCommand { // 2021.05.24 조혜지 - 장바구니 버튼 클릭시 이미 있는 제품인 경우 DB에 수량 update하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

		String wQuantity = request.getParameter("wQuantity");
		
//		session.setAttribute("cId", "hyeji");
//
//		String cId = (String)session.getAttribute("cId");
////		이건 수훈님과 연동할 때 사용하기 ************************************************************		

		String cId = Share.userId;
//		이건 도희님과 연동할 때 삭제하기 ************************************************************
//		session.setAttribute("pCode", "4");
////		이건 도희님과 연동할 때 사용하기 ************************************************************
//		String pCode = (String)session.getAttribute("pCode");
		String pCode = request.getParameter("pCode");
		
		BCartDao dao = new BCartDao();
		
//		System.out.println(dao.cartCheck(cId, Integer.parseInt(pCode)));
		dao.update(Integer.parseInt(wQuantity), cId, Integer.parseInt(pCode));
		
		Share.pCode = pCode;
	}

}
