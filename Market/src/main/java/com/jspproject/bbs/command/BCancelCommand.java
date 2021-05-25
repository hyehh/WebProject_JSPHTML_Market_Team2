package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;

public class BCancelCommand implements BCommand { // 2021.05.20 조혜지 - 구매 취소 버튼 클릭 시 취소일자 업데이트하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String bNumber = (String)session.getAttribute("bNumber");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************
		System.out.println(bNumber);
		BBuyDao dao = new BBuyDao();
		dao.cancel(bNumber);
	}

}
