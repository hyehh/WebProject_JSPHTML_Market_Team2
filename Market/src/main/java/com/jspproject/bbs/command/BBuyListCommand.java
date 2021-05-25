package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;
import com.jspproject.bbs.dto.BBuyDto;

public class BBuyListCommand implements BCommand { // 2021.05.17 조혜지 - 장바구니에서 선택한 상품한 가져오는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		String[] selectedProduct = request.getParameterValues("selectedProduct");
		int wId = 0;
		ArrayList<BBuyDto> dtos = new ArrayList<BBuyDto>();
		
		for(int i=0; i<selectedProduct.length; i++) {
			wId = Integer.parseInt(selectedProduct[i]);
			
			session.setAttribute("cId", "hyeji");
			
//		이건 수훈님과 연동할 때 사용하기 ************************************************************		
			String cId = (String)session.getAttribute("cId");
			
			BBuyDao dao = new BBuyDao();
			BBuyDto dto = dao.customerBuyList(cId, wId);
			dtos.add(dto);
			
			session.setAttribute("BUY", dtos);
			System.out.println(wId);
			
		}
		
		int size = dtos.size();
		System.out.println(size);
		session.setAttribute("size", size);

		
	}

}
