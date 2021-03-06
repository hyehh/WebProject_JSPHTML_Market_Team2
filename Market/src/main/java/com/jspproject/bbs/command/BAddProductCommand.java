package com.jspproject.bbs.command;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoAddProduct;
import com.jspproject.bbs.util.Share;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BAddProductCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
////		이건 수훈님과 연동할 때 삭제하기 ************************************************************
//		session.setAttribute("sId", "seller");

//		String sId = (String)session.getAttribute("sId");
//		이건 수훈님과 연동할 때 사용하기 ************************************************************	
		String sId = Share.userId;
		MultipartRequest multiMain = null;


		String filePath = null;

		String pCategory = null;
		String pName = null;
		String pPrice = null;
		String pPriceDC = null;
		String ExpirationDate = null;
		String pProductEA = null;
		String pStatus = null;

		String year = null;
		String month = null;
		String day = null;

		// ---------------------
		// 3mb로 파일 크기를 제한
		int fileSize = 1024 * 1024 * 10;
		// 현재 서비스되는 서버가 사용하는 저장공간의 경로를 불러옵니다.
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/product_save");

		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		File folder = new File(uploadPath);
		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		
		


		try {
			multiMain = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			pCategory = multiMain.getParameter("pCategory");
			pName = multiMain.getParameter("pName");
			pPrice = multiMain.getParameter("pPrice");
			pPriceDC = multiMain.getParameter("pPriceDC");
			pProductEA = multiMain.getParameter("pProductEA");
			pStatus = "판매중";

			String uploadFile = multiMain.getFilesystemName("uploadFile");
			System.out.println("파일은요 - - - - - -" + uploadFile);
			
			filePath = "product_save/" + uploadFile;
			
			System.out.println("파일경로는요 - - - - - -" + filePath);
			
			year = multiMain.getParameter("date1");
			month = multiMain.getParameter("date2");
			day = multiMain.getParameter("date3");

			if (month.length() < 2) {
				month = "0" + month;
			}

			if (day.length() < 2) {
				day = "0" + day;
			}

			ExpirationDate = year + month + day;

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(ExpirationDate);
			String pExpirationDate = sdf.format(cal.getTime());

			BDaoAddProduct dao = new BDaoAddProduct();
			dao.insert(pCategory, pName, pPrice, pExpirationDate, pProductEA, pStatus, pPriceDC, filePath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
