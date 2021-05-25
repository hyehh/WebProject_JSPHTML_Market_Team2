package com.jspproject.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.dao.BBuyDao;
import com.jspproject.bbs.dto.BBuyDto;
import com.jspproject.bbs.util.Share;

public class BCancelListCommand implements BCommand { // 2021.05.23 조혜지 - 주문 취소한 상품 리스트 불러오는 command

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// TODO Auto-generated method stub
//		String cId = (String)session.getAttribute("cId");
////	이건 수훈님과 연동할 때 사용하기 ************************************************************		

		String cId = Share.userId;
		System.out.println(cId);
		BBuyDao dao = new BBuyDao();
		
		String strPg = request.getParameter("pg"); //list.jsp?pg=?

		int rowSize = 10; //한페이지에 보여줄 글의 수
		int pg = 1; //페이지 , list.jsp로 넘어온 경우 , 초기값 =1

		if(strPg != null){ //list.jsp?pg=2
		    pg = Integer.parseInt(strPg); //.저장
		}

		System.out.println("jsp" + strPg);  // 확인용


		int total = dao.cancel(cId); //총 게시물 수
		// 여기서 total은 dao에서 뽑아낼 리스트의 개수가 몇개인지 count 확인해야해요
		int allPage = (int) Math.ceil(total/(double)rowSize); //페이지수
//		int totalPage = total/rowSize + (total%rowSize==0?0:1);
		int block = 10; //한페이지에 보여줄  범위 << [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] >>
		
		int from = (pg * rowSize) - (rowSize-1) - 1; //(1*10)-(10-1)=10-9=1 //from
		int to = 10; // 10개씩 자름 // 만일 1~5개씩 보이고싶다면 to 변수에 5라고 적으면 됨.

		// 확인용 콘솔출력
		System.out.println("전체 페이지수 : "+allPage);
		System.out.println("현제 페이지 : "+ strPg);
		System.out.println("ceil:"+Math.ceil(total/rowSize));
		
		int fromPage = ((pg-1)/block*block)+1;  //보여줄 페이지의 시작
		int toPage = ((pg-1)/block*block)+block; //보여줄 페이지의 끝
		if(toPage> allPage){ // 예) 20>17
		    toPage = allPage;
		}

		// 확인용 콘솔출력
		System.out.println("페이지시작 : "+fromPage+ " / 페이지 끝 :"+toPage); 
		
		ArrayList<Integer> pageCount = new ArrayList<Integer>();  // DB에 있는 내용 10단위씩 짤라서 나온 수량 리스트에 저장.
		
		for (int i = fromPage; i <= toPage; i++) {
			pageCount.add(i);
		}

		ArrayList<BBuyDto> dtos = dao.cancelList(cId, from, to);
		
		request.setAttribute("CANCEL", dtos);


		// 페이징 변수들 -> jsp로 넘겨줘야하기에 세션에 저장합니다.
		request.setAttribute("PG", pg); // 페이지넘버
		request.setAttribute("BLOCK", block); // 범위
		request.setAttribute("FROMPAGE", fromPage); // DB리스트 개수의 10개만큼 짤라서 보여줄 숫자. (총 21개면 3페이지 의 첫 페이지)
		request.setAttribute("TOPAGE", toPage); //DB리스트 개수의 10개만큼 짤라서 보여줄 숫자. (총 21개면 3페이지 의 끝 페이지)
		request.setAttribute("pageCount", pageCount); // jps forEach로 넘기기 위한 리스트
		request.setAttribute("ALLPAGE", allPage); // 총 페이지 갯수
	}

}
