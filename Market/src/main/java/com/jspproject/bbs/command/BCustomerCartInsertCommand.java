package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BCartDao;

public class BCustomerCartInsertCommand implements BCommand { // 2021.05.16 조혜지 - 장바구니 추가하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

		String wQuantity = request.getParameter("wQuantity");
		
//		이건 수훈님과 연동할 때 삭제하기 ************************************************************
		session.setAttribute("cId", "hyeji");

//		이건 수훈님과 연동할 때 사용하기 ************************************************************
		String cId = (String)session.getAttribute("cId");
//		이건 도희님과 연동할 때 삭제하기 ************************************************************
		session.setAttribute("pCode", "4");
//		이건 도희님과 연동할 때 사용하기 ************************************************************
		String pCode = (String)session.getAttribute("pCode");
		
		BCartDao dao = new BCartDao();
		
//		System.out.println(dao.cartCheck(cId, Integer.parseInt(pCode)));
		dao.insert(Integer.parseInt(wQuantity), cId, Integer.parseInt(pCode));
		
	}

}
