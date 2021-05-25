package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoCategory;
import com.jspproject.bbs.dto.BDtoProductList;

public class BCategoryCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String pCategory = request.getParameter("pCategory");
		
		BDaoCategory dao = new BDaoCategory();
		
		ArrayList<BDtoProductList> dtoCategory = dao.categoryList(pCategory);
		request.setAttribute("category_list", dtoCategory);

	}

}
