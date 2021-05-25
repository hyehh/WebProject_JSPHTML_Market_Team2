package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;
import com.jspproject.bbs.dto.BBuyDto;
import com.jspproject.bbs.util.Share;

public class BDeliveryInfoCommand implements BCommand { // 2021.05.19 조혜지 - 결제 완료 시 배송 정보 불러오는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
//		String cId = (String)session.getAttribute("cId");
////		이건 수훈님과 연동할 때 사용하기 ************************************************************		

		String cId = Share.userId;	
		String bNumber = (String)session.getAttribute("bNumber");
		
		BBuyDao dao = new BBuyDao();
		BBuyDto dto = dao.deliveryinfo(cId, bNumber);
		
		request.setAttribute("DELIVERY", dto);
	}

}
