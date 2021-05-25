package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BSellerDao;
import com.jspproject.bbs.util.Share;

public class BSellerInfoUpdateCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String sName = request.getParameter("sName");
		String sEmail = request.getParameter("sEmail");
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
		String sTel = request.getParameter("sTel");
		String sPostalCode = request.getParameter("sPostalCode");
		String sAddress1 = request.getParameter("sAddress1");
		String sAddress2 = request.getParameter("sAddress2");		
		String sShopName = request.getParameter("sShopName");		
		String sNumber = request.getParameter("sNumber");		
		
//		이건 수훈님과 연동할 때 사용하기 ************************************************************
//		String sId = (String)session.getAttribute("sId");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************
		String sId = Share.userId;
		
		BSellerDao dao = new BSellerDao();
		dao.sellerInfoUpdate(sName, sEmail, sBirth, sTel, sPostalCode, sAddress1, sAddress2, sShopName, sNumber, sId);
	}

}
