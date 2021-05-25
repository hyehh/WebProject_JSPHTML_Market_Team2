package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;
import com.jspproject.bbs.dto.BBuyDto;

public class BOrderListCancelCommand implements BCommand { // 2021.05.20 조혜지 - 구매 취소 버튼 클릭 시 취소일자 업데이트 & 취소 내역 리스트 보여주는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		String cId = (String)session.getAttribute("cId");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************
		String bNumber = request.getParameter("bNumber");
		
		System.out.println(bNumber);

		BBuyDao dao = new BBuyDao();
		dao.cancelDay(bNumber);
		
		request.setAttribute("bNumber", bNumber);
		
		ArrayList<BBuyDto> dtos = dao.buyConfirmList(cId, bNumber);
		
		request.setAttribute("BUYCONFIRM", dtos);
	}

}
