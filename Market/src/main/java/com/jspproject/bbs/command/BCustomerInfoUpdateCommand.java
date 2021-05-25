package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BCustomerInfoDao;
import com.jspproject.bbs.util.Share;

public class BCustomerInfoUpdateCommand implements BCommand { // 2021.05.14 조혜지 - 회원 정보 수정 버튼 클릭 시 정보 업데이트 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		String cName = request.getParameter("cName");
		String cEmail = request.getParameter("cEmail");
		String cBirthY = request.getParameter("cBirthY");
		String cBirthM = request.getParameter("cBirthM");
		String cBirthD = request.getParameter("cBirthD");
		if(Integer.parseInt(request.getParameter("cBirthM"))<10) {
			cBirthM = "0" + cBirthM;
		}
		if(Integer.parseInt(request.getParameter("cBirthD"))<10) {
			cBirthD = "0" + cBirthD;
		}
		String cBirth = cBirthY + "-" + cBirthM + "-" + cBirthD;
		String cTel = request.getParameter("cTel");
		String cPostalCode = request.getParameter("cPostalCode");
		String cAddress1 = request.getParameter("cAddress1");
		String cAddress2 = request.getParameter("cAddress2");		
		
//		String cId = (String)session.getAttribute("cId");
////		이건 수훈님과 연동할 때 사용하기 ************************************************************		

		String cId = Share.userId;
		
		BCustomerInfoDao dao = new BCustomerInfoDao();
		dao.customerInfoUpdate(cName, cEmail, cBirth, cTel, cPostalCode, cAddress1, cAddress2, cId);

	}

}
