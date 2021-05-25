package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BSelectOrderPgListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
//		String cName = request.getParameter("cName");
//		BDaoDelivery dao = new BDaoDelivery();
//		ArrayList<BDtoDelivery> dtos = dao.selectOrderPageList(cName);
//		
//		request.setAttribute("OrderPageList", dtos);
	}

}
