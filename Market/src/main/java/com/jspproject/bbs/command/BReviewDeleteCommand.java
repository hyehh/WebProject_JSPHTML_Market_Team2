package com.jspproject.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BReviewDao;

public class BReviewDeleteCommand implements BCommand { // 2021.05.18 조혜지 - 리뷰 작성된 상품 삭제하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub

		String bNumber = request.getParameter("bNumber");
		String pCode = request.getParameter("pCode");
		System.out.println(bNumber);
		System.out.println(Integer.parseInt(pCode));
		BReviewDao dao = new BReviewDao();
		dao.reviewDelete(bNumber, Integer.parseInt(pCode));
	}

}
