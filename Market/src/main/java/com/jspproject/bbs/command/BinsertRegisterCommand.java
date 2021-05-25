package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoAddRegister;

public class BinsertRegisterCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		int pCode = 0;
		String sId = "admin";
		BDaoAddRegister dao = new BDaoAddRegister();
		
		pCode = dao.getCode();
		dao.addRegister(pCode, sId);
	}

}
