package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoProduct;
import com.jspproject.bbs.dto.BProductDto;

public class BSelectProductCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String pCode = request.getParameter("pCode");
		System.out.println("커맨드 /" + pCode);
		BDaoProduct dao = new BDaoProduct();
		BProductDto dto = dao.selectProduct(pCode);
		ArrayList<String> category = new ArrayList<String>();
		category.add("세탁용품");
		category.add("청소용품");
		category.add("인테리어소품");
		category.add("통조림");
		category.add("냉동식품");
		category.add("식음료");
		
		
		request.setAttribute("selectProduct", dto);
		request.setAttribute("Category", category);
	}

}
