package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;
import com.jspproject.bbs.dao.BReviewDao;
import com.jspproject.bbs.dto.BBuyDto;
import com.jspproject.bbs.dto.BReviewDto;

public class BBuyConfirmListCommand implements BCommand { // 2021.05.19 조혜지 - 결제 완료 시 주문한 상품 리스트 불러오는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		String cId = (String)session.getAttribute("cId");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************		

		BBuyDao dao = new BBuyDao();
		String bNumber = dao.bNumber(cId);
		System.out.println(cId);
		System.out.println(bNumber);
		System.out.println("여기 confirm");
		session.setAttribute("bNumber", bNumber);
		
		ArrayList<BBuyDto> dtos = dao.buyConfirmList(cId, bNumber);
		
		request.setAttribute("BUYCONFIRM", dtos);
	}

}
