package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.Dao_loginAction;
import com.jspproject.bbs.util.Share;

public class BLoginActionCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String userType = request.getParameter("userType");
		int loginChk = 0;	//DB에서 로그인 결과 체크시 사용할 변수 선언
		int loginChkResult = 0; // 로그인 DB확인 후 결과 값 선언
		
		System.out.println(userType);
		
		try {
			if(userType.equals("구매회원")) {
				userType = "customer";
				Dao_loginAction dao = new Dao_loginAction();
				loginChkResult = dao.customerLoginAction(loginId, loginPw, loginChk);
				
				System.out.println(loginChkResult);
				System.out.println(Share.userId + "\n" + Share.userPw);
				
				session.setAttribute("loginChkResult", loginChkResult); // 1 = 해당 아이디 존재 ,0 = 존재하지 않음
				session.setAttribute("USERTYPE", userType);
				
			}else if(userType.equals("판매회원")) {
				userType = "seller";
				Dao_loginAction dao = new Dao_loginAction();
				loginChkResult = dao.sellerLoginAction(loginId, loginPw, loginChk);
				
				System.out.println(loginChkResult);
				System.out.println(Share.userId + "\n" + Share.userPw);
				
				session.setAttribute("loginChkResult", loginChkResult);
				session.setAttribute("USERTYPE", userType);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
