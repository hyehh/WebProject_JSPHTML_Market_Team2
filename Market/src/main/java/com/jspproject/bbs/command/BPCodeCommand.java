package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;
import com.jspproject.bbs.dao.BCartDao;
import com.jspproject.bbs.dto.BBuyDto;

public class BPCodeCommand implements BCommand { // 2021.05.24 조혜지 - 장바구니에서 구매한 제품의 pCode 구하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
//		String cId = (String)session.getAttribute("cId");
////		이건 수훈님과 연동할 때 사용하기 ************************************************************		
		String bNumber = (String)session.getAttribute("bNumber");
		System.out.println(bNumber);
		
		BBuyDao dao = new BBuyDao();
		
		ArrayList<BBuyDto> dtos = dao.pCodeList(bNumber);
		
		session.setAttribute("pCode", dtos);
		
		int psize = dtos.size();
		System.out.println(psize);
		session.setAttribute("psize", psize);
	}

}
