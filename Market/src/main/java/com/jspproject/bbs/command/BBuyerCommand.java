package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;
import com.jspproject.bbs.dto.BBuyDto;
import com.jspproject.bbs.util.Share;

public class BBuyerCommand implements BCommand { // 2021.05.16 조혜지 - 주문서 작성/결제 창에서 DB에 있는 주문자 정보 불러오는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

//		session.setAttribute("cId", "hyeji");
//
//		String cId = (String)session.getAttribute("cId");
////		이건 수훈님과 연동할 때 사용하기 ************************************************************		

		String cId = Share.userId;

		BBuyDao dao = new BBuyDao();
		BBuyDto dto = dao.buyerInfo(cId);
		
		request.setAttribute("BUYER", dto);
	
	}

}
