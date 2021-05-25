package com.jspproject.bbs.command;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;
import com.jspproject.bbs.util.Share;

public class BBuyCommand implements BCommand { // 2021.05.19 조혜지 - 선택 상품 주문 선택 시 주문서 작성/결제 창에서 주문 및 결제 정보 insert하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

		// 주문번호 만들기
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String ymd = ym + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		String subNum = "";
		Random rand = new Random();
		for(int i=0; i<6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
		if(!subNum.contains(ran)) {
			subNum += ran;
			}else {
				i-=1;
			}
		}
		int size = (int)session.getAttribute("size");
		System.out.println(size);
		
		String bNumber = ymd + "-" + subNum;
		String bRecName = request.getParameter("bRecName");
		String bRecPostalCode = request.getParameter("bRecPostalCode");
		String bRecAddress1 = request.getParameter("bRecAddress1");
		String bRecAddress2 = request.getParameter("bRecAddress2");
		String bRecTel = request.getParameter("bRecTel");
		String bRecContent = request.getParameter("bRecContent");
		
//		session.setAttribute("cId", "hyeji");
//
//		String cId = (String)session.getAttribute("cId");
////		이건 수훈님과 연동할 때 사용하기 ************************************************************		

		String cId = Share.userId;

		BBuyDao dao = new BBuyDao();		
		dao.insert(cId, bNumber, bRecName, bRecPostalCode, bRecAddress1, bRecAddress2, bRecTel, bRecContent, session);
		
	}

}
