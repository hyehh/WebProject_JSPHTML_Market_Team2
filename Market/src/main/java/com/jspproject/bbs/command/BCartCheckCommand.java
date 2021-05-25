package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BCartDao;

public class BCartCheckCommand implements BCommand { // 2021.05.23 조혜지 - 장바구니에 같은 제품이 존재하는지 여부 체크하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
//		이건 수훈님과 연동할 때 삭제하기 ************************************************************
		session.setAttribute("cId", "hyeji");

//		이건 수훈님과 연동할 때 사용하기 ************************************************************
		String cId = (String)session.getAttribute("cId");
//		이건 도희님과 연동할 때 삭제하기 ************************************************************
		session.setAttribute("pCode", "4");
//		이건 도희님과 연동할 때 사용하기 ************************************************************
		String pCode = (String)session.getAttribute("pCode");
		
		int cart = 0;
		
		BCartDao dao = new BCartDao();
		
		int check = dao.cartCheck(cId, Integer.parseInt(pCode), cart);
		System.out.println(check);
		session.setAttribute("check", check);

	}

}
