package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoQnA;
import com.jspproject.bbs.dto.BDtoQnA;

public class BSelectQnACommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String bNumber = request.getParameter("bNumber");
		BDaoQnA dao = new BDaoQnA();
		BDtoQnA dto = dao.selectQnA(bNumber);
		
		request.setAttribute("selectQnA", dto);
	}

}
