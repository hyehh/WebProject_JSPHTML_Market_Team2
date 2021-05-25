package com.jspproject.bbs.command;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDao;
import com.jspproject.bbs.util.Share;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BRegister_qCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
//		String strCode = request.getParameter("pCode");
//		int pCode = Integer.parseInt(strCode);
//		String cId = request.getParameter("cId");
//		String qTitle = request.getParameter("qTitle");
//		String qContent = request.getParameter("qContent");
//
//		BDao dao = new BDao();
//		dao.registerQ(pCode, cId, qTitle, qContent);
//		
//		Share.pCode = strCode;

		
		MultipartRequest multi = null;
		String strCode = null;
		String cId = null;
		String qTitle = null;
		String qContent = null;
		String qFilePath = null;


		// 3mb로 파일 크기를 제한
		int fileSize = 1024 * 1024 * 3;
		//현재 서비스되는 서버가 사용하는 저장공간의 경로를 불러옵니다.
		ServletContext context = request.getServletContext();
		String qnaPath = context.getRealPath("/qnasave");
		
		//해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		File folder = new File(qnaPath);
		if (!folder.exists()) {
			try {
				folder.mkdir();
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}

		try {
			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
			multi = new MultipartRequest(request, qnaPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			strCode = multi.getParameter("pCode");
			int pCode = Integer.parseInt(strCode);
			cId = multi.getParameter("cId");
			qTitle = multi.getParameter("qTitle");
			qContent = multi.getParameter("qContent");
		
			String qnaFile = multi.getFilesystemName("qnaFile");
			BDao dao = new BDao();
			qFilePath = "qnasave/" + qnaFile;
			dao.registerQ(pCode, cId, qTitle, qContent, qFilePath);
			
			Share.pCode = strCode;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
