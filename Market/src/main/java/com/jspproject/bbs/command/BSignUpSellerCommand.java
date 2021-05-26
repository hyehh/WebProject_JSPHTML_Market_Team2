package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.Dao_signUpSeller;

public class BSignUpSellerCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String sId = request.getParameter("sId");
		String sPw = request.getParameter("sPw");
		String sName = request.getParameter("sName");
		String sTel = request.getParameter("sTel");
		
		String sBirthY = request.getParameter("sBirthY");
		String sBirthM = request.getParameter("sBirthM");
		String sBirthD = request.getParameter("sBirthD");
		if(Integer.parseInt(request.getParameter("sBirthM"))<10) {
			sBirthM = "0" + sBirthM;
		}
		if(Integer.parseInt(request.getParameter("sBirthD"))<10) {
			sBirthD = "0" + sBirthD;
		}
		
		
		String sBirth = sBirthY + "-" + sBirthM + "-" + sBirthD;
		String sEmail = (request.getParameter("sEmail"));
		String sPostalCode = (request.getParameter("sPostalCode"));
		String sAddress1 = request.getParameter("sAddress1");
		String sAddress2 = request.getParameter("sAddress2");
		String sShopName = request.getParameter("sShopName");
		String sNumber = request.getParameter("sNumber");
		
		Dao_signUpSeller dao = new Dao_signUpSeller();
		dao.writeSeller(sId, sPw, sName, sBirth, sTel, sAddress1, sAddress2, sEmail, sShopName, sNumber, sPostalCode);
		
	}

}
