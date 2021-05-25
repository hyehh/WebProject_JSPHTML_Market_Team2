package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;
import com.jspproject.bbs.dao.BCartDao;
import com.jspproject.bbs.dto.BBuyDto;
import com.jspproject.bbs.dto.BCartDto;

public class BBuyConfirmCartDeleteCommand implements BCommand { // 2021.05.24 조혜지 - 장바구니에서 구매한 제품 삭제하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String cId = (String)session.getAttribute("cId");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************		
		System.out.println("여기 마지막" + cId);
		BBuyDao dao = new BBuyDao();
		dao.buyConfirmCartDelete(cId, session);
		
	}

}
