package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoProduct;

public class BProductDeleteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		BDaoProduct dao = new BDaoProduct();
		String pCode = request.getParameter("pCode");
		String pDelete = "1";
		
		dao.deleteList(pCode, pDelete);
		dao.deleteRegister(pCode);
		
	}

}
