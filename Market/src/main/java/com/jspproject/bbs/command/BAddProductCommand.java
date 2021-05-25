package com.jspproject.bbs.command;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDaoAddProduct;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BAddProductCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		
		MultipartRequest multi = null;
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
		int fileSize = 1024 * 1024 * 3;
		// 현재 서비스되는 서버가 사용하는 저장공간의 경로를 불러옵니다.
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/save");

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
			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
			multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			pCategory = multi.getParameter("pCategory");
			pName = multi.getParameter("pName");
			pPrice = multi.getParameter("pPrice");
			pPriceDC = multi.getParameter("pPriceDC");
			pProductEA = multi.getParameter("pProductEA");
			pStatus = "판매중";
			
			year = multi.getParameter("date1");
			month = multi.getParameter("date2");
			day = multi.getParameter("date3");
			
			if (month.length() < 2) {
				month = "0" + month;
			}

			if (day.length() < 2) {
				day = "0" + day;
			}
			
			System.out.println(year + month + day);

			ExpirationDate = year + month + day;
			

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(ExpirationDate);
			String pExpirationDate = sdf.format(cal.getTime());
			String uploadFile = multi.getFilesystemName("uploadFile");

			BDaoAddProduct dao = new BDaoAddProduct();
			filePath = "product_save/" + uploadFile;
			dao.insert(pCategory, pName, pPrice, pExpirationDate, pProductEA, pStatus, pPriceDC, filePath);
//			dao.write(writer, title, content, filePath);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}

