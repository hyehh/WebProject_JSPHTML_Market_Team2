package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BCustomerInfoDao;
import com.jspproject.bbs.dto.BCustomerInfoDto;

public class BCustomerInfoCommand implements BCommand { // 2021.05.13 조혜지 - 회원 정보 수정 시 db에 있는 정보 불러오는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub		

//		이건 수훈님과 연동할 때 삭제하기 ************************************************************
		session.setAttribute("cId", "hyeji");

		String cId = (String)session.getAttribute("cId");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************		
		
		BCustomerInfoDao dao = new BCustomerInfoDao();
		BCustomerInfoDto dto = dao.customerinfo(cId);
		
		request.setAttribute("CUSTOMERINFO", dto);
	}

}
