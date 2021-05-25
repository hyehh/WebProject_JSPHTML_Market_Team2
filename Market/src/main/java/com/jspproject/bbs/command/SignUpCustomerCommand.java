package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.Dao_signUpCustomer;

public class SignUpCustomerCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String cId = request.getParameter("cId");
		String cPw = request.getParameter("cPw");
		String cName = request.getParameter("cName");
		String cTel = request.getParameter("cTel");
		
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
		String cEmail = (request.getParameter("cEmail"));
		String cPostalCode = (request.getParameter("cPostalCode"));
		String cAddress1 = request.getParameter("cAddress1");
		String cAddress2 = request.getParameter("cAddress2");
		
		Dao_signUpCustomer dao = new Dao_signUpCustomer();
		dao.writeCustomer(cId, cPw, cName, cBirth, cTel, cAddress1, cAddress2, cEmail, cPostalCode);
	}

}
