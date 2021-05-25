package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoDelivery;

public class BDeliveryFinishCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		BDaoDelivery dao = new BDaoDelivery();
		String bNumber = request.getParameter("bNumber");
		System.out.println("커맨드" + bNumber);
		dao.deliveryFinish(bNumber);
	}

}
