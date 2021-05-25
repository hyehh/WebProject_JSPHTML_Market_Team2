package com.jspproject.bbs.command;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BReviewDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BReviewRegistrationCommand implements BCommand { // 2021.05.17 조혜지 - 리뷰 등록하는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		MultipartRequest multi = null;
		String rFilePath = null;
		String bNumber = null;
		String pCode = null;

		// 3mb로 파일 크기를 제한
		int fileSize = 1024 * 1024 * 3;
		//현재 서비스되는 서버가 사용하는 저장공간의 경로를 불러옵니다.
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/reviewsave");
		
		//해당 디렉토리가 없을경우 디렉토리를 생성합니다.
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
			multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			String bReviewScore = multi.getParameter("bReviewScore");
			String bReviewContent = multi.getParameter("bReviewContent");
			bNumber = (String)session.getAttribute("bNumber");
			pCode = (String)session.getAttribute("pCode");
			
			String uploadFile = multi.getFilesystemName("uploadFile");
			BReviewDao dao = new BReviewDao();
			rFilePath = "reviewsave/" + uploadFile;
			System.out.println(bReviewScore);
			System.out.println(bReviewContent);
			System.out.println(bNumber);
			System.out.println(pCode);
			System.out.println(rFilePath);
			dao.reviewRegistration(Integer.parseInt(bReviewScore), bReviewContent, bNumber, Integer.parseInt(pCode), rFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
