package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoBest;
import com.jspproject.bbs.dto.BDtoBest;

public class BBestCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		BDaoBest dao = new BDaoBest();
		
		ArrayList<BDtoBest> dtoKitchen = dao.kitchen();
		request.setAttribute("best_kitchen", dtoKitchen);
		
		ArrayList<BDtoBest> dtoWashing = dao.washing();
		request.setAttribute("best_washing", dtoWashing);
		
		ArrayList<BDtoBest> dtoCleaning = dao.cleaning();
		request.setAttribute("best_cleaning", dtoCleaning);
		
		ArrayList<BDtoBest> dtoInterior = dao.interior();
		request.setAttribute("best_interior", dtoInterior);
		
		ArrayList<BDtoBest> dtoCan = dao.can();
		request.setAttribute("best_can", dtoCan);
		
		ArrayList<BDtoBest> dtoFrozen = dao.frozen();
		request.setAttribute("best_frozen", dtoFrozen);
		
		ArrayList<BDtoBest> dtoBeverage = dao.beverage();
		request.setAttribute("best_beverage", dtoBeverage);

	}

}
