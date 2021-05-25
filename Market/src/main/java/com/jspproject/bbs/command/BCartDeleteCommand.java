package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BCartDao;
import com.jspproject.bbs.dto.BCartDto;

public class BCartDeleteCommand implements BCommand { // 2021.05.22 조혜지 - 장바구니 선택 상품 삭제하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String[] selectedProduct = request.getParameterValues("selectedProduct");
		int wId = 0;
		ArrayList<BCartDto> dtos = new ArrayList<BCartDto>();
		
		for(int i=0; i<selectedProduct.length; i++) {
			wId = Integer.parseInt(selectedProduct[i]);
			
//		이건 수훈님과 연동할 때 삭제하기 ************************************************************		
			session.setAttribute("cId", "hyeji");
			
//		이건 수훈님과 연동할 때 사용하기 ************************************************************		
			String cId = (String)session.getAttribute("cId");
			
			BCartDao dao = new BCartDao();
			dao.cartDelete(cId, wId);
			
		}

	}

}
