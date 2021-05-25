package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.Dao_cIdCheck;

public class CIdCheckCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String cId = request.getParameter("cId");
		int cIdchk = 0 ;
		String hidden = request.getParameter("idDuplication");
		
		System.out.println(cId);
		Dao_cIdCheck dao = new Dao_cIdCheck();
		
		int cIdchkResult = dao.IdcheckCustomer(cId, cIdchk);
		session.setAttribute("cIdchk", cIdchkResult);
		session.setAttribute("cId", cId);
		session.setAttribute("cHIDDEN", hidden);
	}

}
