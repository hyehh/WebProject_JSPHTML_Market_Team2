package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoAddProduct;
import com.jspproject.bbs.util.Share;

public class BproductlistnBnsCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		BDaoAddProduct dao = new BDaoAddProduct();
		
		int maxCode = dao.getCode();
		String sId = "admin";
		
		System.out.println("맥스코드와 함께라면" + maxCode + sId);
		
		dao.insertBnS(maxCode, sId);
	}

}
