package com.jspproject.bbs.command;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BdaoFileUpload;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BFileUpladCommad implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		MultipartRequest multi = null;
		String filePath = null;
		String writer = null;
		String title = null;
		String content = null;

		// 3mb로 파일 크기를 제한
		int fileSize = 1024 * 1024 * 3;
		//현재 서비스되는 서버가 사용하는 저장공간의 경로를 불러옵니다.
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/save");
		
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
			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
			multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			writer = multi.getParameter("writer");
			title = multi.getParameter("title");
			content = multi.getParameter("content");
			
			String uploadFile = multi.getFilesystemName("uploadFile");
			BdaoFileUpload dao = new BdaoFileUpload();
			filePath = "save/" + uploadFile;
			dao.write(filePath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
