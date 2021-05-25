package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDao;
import com.jspproject.bbs.dto.BDtoProductList;

public class BSearchCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		String pName = request.getParameter("pName");
		
		BDao dao = new BDao();
		
		ArrayList<BDtoProductList> dto = dao.searchList(pName);
		request.setAttribute("search_list", dto);

	}

}
