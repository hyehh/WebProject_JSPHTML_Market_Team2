package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoDelivery;
import com.jspproject.bbs.dto.BDtoDelivery;

public class BSelectOrderPageCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String bNumber = request.getParameter("bNumber");
		BDaoDelivery dao = new BDaoDelivery();
		
		
		BDtoDelivery dto = dao.selectOrderPage(bNumber);
		
		
		
//		String cName = dao.setCName(bNumber); 
		ArrayList<BDtoDelivery> dtos = dao.selectDPageList(bNumber);
		
		request.setAttribute("selectOrderPage", dto);
		request.setAttribute("OrderPageList", dtos);
	}

}
