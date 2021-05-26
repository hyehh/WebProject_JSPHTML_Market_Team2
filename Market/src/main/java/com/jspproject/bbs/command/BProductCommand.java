package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BDao;
import com.jspproject.bbs.dto.BDtoProduct;
import com.jspproject.bbs.dto.BDtoQnA;
import com.jspproject.bbs.dto.BDtoReview;

public class BProductCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
		String pCode = request.getParameter("pCode");
		
		BDao dao = new BDao();
		
		BDtoProduct dtoProduct = dao.productView(pCode);
		request.setAttribute("product_view", dtoProduct);
		
//		ArrayList<BDtoReview> dtoReview = dao.reviewList(pCode);
//		request.setAttribute("review_list", dtoReview);
		
		String review_strPg = request.getParameter("review_pg"); //list.jsp?pg=?
		int review_rowSize = 3; //한페이지에 보여줄 글의 수
		int review_pg = 1; //페이지 , list.jsp로 넘어온 경우 , 초기값 =1
		
		if(review_strPg != null){ //list.jsp?pg=2
			review_pg = Integer.parseInt(review_strPg); //.저장
		}
		int review_total = dao.reivewListCount(pCode); //총 게시물 수
		// 여기서 total은 dao에서 뽑아낼 리스트의 개수가 몇개인지 count 확인해야해요
		int review_allPage = (int) Math.ceil(review_total/(double)review_rowSize); //페이지수
		// int totalPage = total/rowSize + (total%rowSize==0?0:1);
		int review_block = 10; //한페이지에 보여줄  범위 << [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] >>
		int review_from = (review_pg * review_rowSize) - (review_rowSize-1) - 1; //(1*10)-(10-1)=10-9=1 //from
		int review_to = 3; // 10개씩 자름 // 만일 1~5개씩 보이고싶다면 to 변수에 5라고 적으면 됨.
		int review_fromPage = ((review_pg-1)/review_block * review_block)+1;		//보여줄 페이지의 시작
		int review_toPage = ((review_pg-1)/review_block * review_block) + review_block;	//보여줄 페이지의 끝
		if(review_toPage> review_allPage){ // 예) 20>17
			review_toPage = review_allPage;
		}
		ArrayList<Integer> review_pageCount = new ArrayList<Integer>();	// DB에 있는 내용 10단위씩 짤라서 나온 수량 리스트에 저장.
		for (int i = review_fromPage; i <= review_toPage; i++) {
			review_pageCount.add(i);
		}
		
		ArrayList<BDtoReview> dtoReview = dao.reviewList(pCode, review_from, review_to);				// 리스트를 구하기 위한메소드
		
		
//		FilePath  cv = new FilePath();
		
		//파일이 업로드되있는 상태라면 (이름+경로) - 경로를 해서 파일 이름만 추출해서 request로 전송
//		if(dtoReview.get(1).getrFilePath() != null) {
//			String filePath = dtoReview.get(1).getrFilePath();
//			String fileName = filePath.substring(cv.review_img.length() + 1);
//			request.setAttribute("fileName", fileName);
//		}
		
		// 페이징 변수들 -> jsp로 넘겨줘야하기에 세션에 저장합니다.
		request.setAttribute("review_PG", review_pg); // 페이지넘버
		request.setAttribute("review_BLOCK", review_block); // 범위
		request.setAttribute("review_FROMPAGE", review_fromPage); // DB리스트 개수의 10개만큼 짤라서 보여줄 숫자. (총 21개면 3페이지 의 첫 페이지)
		request.setAttribute("review_TOPAGE", review_toPage); //DB리스트 개수의 10개만큼 짤라서 보여줄 숫자. (총 21개면 3페이지 의 끝 페이지)
		request.setAttribute("review_pageCount", review_pageCount); // jps forEach로 넘기기 위한 리스트
		request.setAttribute("review_ALLPAGE", review_allPage); // 총 페이지 갯수
		request.setAttribute("review_list", dtoReview);
		
		
		
		
		
		BDtoReview dtoStar = dao.reviewStar(pCode);
		request.setAttribute("review_info", dtoStar);
		
//		ArrayList<BDtoQnA> dtoQnA = dao.qnaList(pCode);
//		request.setAttribute("QnA_list", dtoQnA);
		
		String qna_strPg = request.getParameter("qna_pg"); //list.jsp?pg=?
		int qna_rowSize = 3; //한페이지에 보여줄 글의 수
		int qna_pg = 1; //페이지 , list.jsp로 넘어온 경우 , 초기값 =1
		
		if(qna_strPg != null){ //list.jsp?pg=2
			qna_pg = Integer.parseInt(qna_strPg); //.저장
		}
		
		int qna_total = dao.qnaListCount(pCode); //총 게시물 수
		// 여기서 total은 dao에서 뽑아낼 리스트의 개수가 몇개인지 count 확인해야해요
		int qna_allPage = (int) Math.ceil(qna_total/(double)qna_rowSize); //페이지수
		// int totalPage = total/rowSize + (total%rowSize==0?0:1);
		int qna_block = 10; //한페이지에 보여줄  범위 << [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] >>
		int qna_from = (qna_pg * qna_rowSize) - (qna_rowSize-1) - 1; //(1*10)-(10-1)=10-9=1 //from
		int qna_to = 3; // 10개씩 자름 // 만일 1~5개씩 보이고싶다면 to 변수에 5라고 적으면 됨.
		int qna_fromPage = ((qna_pg-1)/qna_block * qna_block) + 1;		//보여줄 페이지의 시작
		int qna_toPage = ((qna_pg-1)/qna_block * qna_block) + qna_block;	//보여줄 페이지의 끝
		if(qna_toPage> qna_allPage){ // 예) 20>17
			qna_toPage = qna_allPage;
		}
		ArrayList<Integer> qna_pageCount = new ArrayList<Integer>();	// DB에 있는 내용 10단위씩 짤라서 나온 수량 리스트에 저장.
		for (int i = qna_fromPage; i <= qna_toPage; i++) {
			qna_pageCount.add(i);
		}
		
		ArrayList<BDtoQnA> dtoQnA = dao.qnaList(pCode, qna_from, qna_to);				// 리스트를 구하기 위한메소드
		
		// 페이징 변수들 -> jsp로 넘겨줘야하기에 세션에 저장합니다.
		request.setAttribute("QnA_PG", qna_pg); // 페이지넘버
		request.setAttribute("QnA_BLOCK", qna_block); // 범위
		request.setAttribute("QnA_FROMPAGE", qna_fromPage); // DB리스트 개수의 10개만큼 짤라서 보여줄 숫자. (총 21개면 3페이지 의 첫 페이지)
		request.setAttribute("QnA_TOPAGE", qna_toPage); //DB리스트 개수의 10개만큼 짤라서 보여줄 숫자. (총 21개면 3페이지 의 끝 페이지)
		request.setAttribute("QnA_pageCount", qna_pageCount); // jps forEach로 넘기기 위한 리스트
		request.setAttribute("QnA_ALLPAGE", qna_allPage); // 총 페이지 갯수
		request.setAttribute("QnA_list", dtoQnA);
	}

}
