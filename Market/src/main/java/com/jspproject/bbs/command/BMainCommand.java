package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoMain;
import com.jspproject.bbs.dto.BDtoBest;
import com.jspproject.bbs.dto.BDtoProductList;

public class BMainCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		BDaoMain dao = new BDaoMain();

		ArrayList<BDtoBest> dtoBest = dao.mainBest();
		request.setAttribute("main_best", dtoBest);
		
		ArrayList<BDtoProductList> dtoNew = dao.mainNew();
		request.setAttribute("main_new", dtoNew);
		
		ArrayList<BDtoProductList> dtoRand = dao.mainRand();
		request.setAttribute("main_rand", dtoRand);
		
	}

}
