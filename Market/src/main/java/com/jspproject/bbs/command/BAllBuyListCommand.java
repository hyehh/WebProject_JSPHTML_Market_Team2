package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;
import com.jspproject.bbs.dto.BBuyDto;

public class BAllBuyListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

		session.setAttribute("cId", "hyeji");

		String cId = (String)session.getAttribute("cId");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************		

		BBuyDao dao = new BBuyDao();
		ArrayList<BBuyDto> dtos = dao.allBuyList(cId);
		
		session.setAttribute("CART", dtos);
		
		int asize = dtos.size();
		System.out.println(asize);
		session.setAttribute("asize", asize);

	}

}
