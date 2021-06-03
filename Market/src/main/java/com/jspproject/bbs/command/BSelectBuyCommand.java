package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoBuy;
import com.jspproject.bbs.dto.BDtoBuy;


public class BSelectBuyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String bNumber = request.getParameter("bNumber");
		BDaoBuy dao = new BDaoBuy();
		BDtoBuy dto = dao.selectBuy(bNumber);
		
		ArrayList<BDtoBuy> dtos = dao.selectBuyPageList(bNumber);
		
		request.setAttribute("selectBuy", dto);
		request.setAttribute("selectBuyList", dtos);
	}

}
