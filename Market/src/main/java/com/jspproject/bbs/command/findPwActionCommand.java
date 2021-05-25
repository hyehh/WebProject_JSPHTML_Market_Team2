package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.Dao_findPwAction;
import com.jspproject.bbs.util.Share;

public class findPwActionCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

		String inputId = request.getParameter("inputId");
		String inputName = request.getParameter("inputName");
		String inputEmail = request.getParameter("inputEmail");
		String findUserType = request.getParameter("findUserType");
		
		String findmsg = "";
		int findchk = 0;
		
try {
			
			if(findUserType.equals("구매회원")) {
				findUserType = "customer";
				Dao_findPwAction dao = new Dao_findPwAction();
				findchk = dao.findcPw(inputId, inputName, inputEmail, findchk);
				
				session.setAttribute("findchk", findchk);
				session.setAttribute("findUserType", findUserType);
				session.setAttribute("findPw", Share.findPw);
				
				//메세지정하기
				if(findchk == 0 ) {
					findmsg = "패스워드를 찾을 수 없습니다.";
					session.setAttribute("findmsg", findmsg);
				}else {
					findmsg = "회원님의 패스워드는 " + session.getAttribute("findPw") + " 입니다.";
					session.setAttribute("findmsg", findmsg);
				}
				
				System.out.println("findchk =" + findchk);
				System.out.println("findId =" + Share.findId);
				System.out.println("findmsg =" + session.getAttribute(findmsg));
				 
				
			}else if(findUserType.equals("판매회원")) {
				System.out.println(1);
				findUserType = "seller";
				Dao_findPwAction dao = new Dao_findPwAction();
				findchk = dao.findsPw(inputId, inputName, inputEmail, findchk);
				
				session.setAttribute("findchk", findchk);
				session.setAttribute("findId", Share.findId);
				session.setAttribute("findUserType", findUserType);
	
				//메세지정하기
				if(findchk == 0 ) {
					findmsg = "패스워드를 찾을 수 없습니다. "
							+ "\n" + "입력정보를 확인해주세요.";
					session.setAttribute("findmsg", findmsg);
				}else {
					findmsg = "회원님의 패스워드는 " + session.getAttribute("findPw") + " 입니다.";
					session.setAttribute("findmsg", findmsg);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
