package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDao;
import com.jspproject.bbs.dto.BDtoProductList;

public class BNewCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		BDao dao = new BDao();

		ArrayList<BDtoProductList> dtoNew = dao.newList();
		request.setAttribute("new_list", dtoNew);
	}

}
