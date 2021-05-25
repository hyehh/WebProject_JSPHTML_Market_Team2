package com.jspproject.bbs.homecontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspproject.bbs.command.BAddProductCommand;
import com.jspproject.bbs.command.BAllBuyCommand;
import com.jspproject.bbs.command.BAllBuyListCommand;
import com.jspproject.bbs.command.BBestCommand;
import com.jspproject.bbs.command.BBuyCancelCommand;
import com.jspproject.bbs.command.BBuyCommand;
import com.jspproject.bbs.command.BBuyConfirmCartDeleteCommand;
import com.jspproject.bbs.command.BBuyConfirmListCommand;
import com.jspproject.bbs.command.BBuyListCommand;
import com.jspproject.bbs.command.BBuySearchListCommand;
import com.jspproject.bbs.command.BBuyToDeliverCommand;
import com.jspproject.bbs.command.BBuyTureCommand;
import com.jspproject.bbs.command.BCBuyListCommand;
import com.jspproject.bbs.command.BBuyerCommand;
import com.jspproject.bbs.command.BCancelCommand;
import com.jspproject.bbs.command.BCancelListCommand;
import com.jspproject.bbs.command.BCartAllDeleteCommand;
import com.jspproject.bbs.command.BCartCheckCommand;
import com.jspproject.bbs.command.BCartDeleteCommand;
import com.jspproject.bbs.command.BCategoryCommand;
import com.jspproject.bbs.command.BCommand;
import com.jspproject.bbs.command.BCustomerCartInsertCommand;
import com.jspproject.bbs.command.BCustomerCartListCommand;
import com.jspproject.bbs.command.BCustomerCartUpdateCommand;
import com.jspproject.bbs.command.BCustomerInfoCommand;
import com.jspproject.bbs.command.BCustomerInfoUpdateCommand;
import com.jspproject.bbs.command.BCustomerPwUpdateCommand;
import com.jspproject.bbs.command.BCustomerSignOutCommand;
import com.jspproject.bbs.command.BDeadlineCommand;
import com.jspproject.bbs.command.BDeliveryEndCommand;
import com.jspproject.bbs.command.BDeliveryFinishCommand;
import com.jspproject.bbs.command.BDeliveryForCommand;
import com.jspproject.bbs.command.BDeliveryINGCommand;
import com.jspproject.bbs.command.BDeliveryInfoCommand;
import com.jspproject.bbs.command.BDeliveryListCommand;
import com.jspproject.bbs.command.BDeliverySearchListCommand;
import com.jspproject.bbs.command.BFileUpladCommad;
import com.jspproject.bbs.command.BMainCommand;
import com.jspproject.bbs.command.BNewCommand;
import com.jspproject.bbs.command.BOrderListCancelCommand;
import com.jspproject.bbs.command.BOrderListCommand;
import com.jspproject.bbs.command.BPCodeCommand;
import com.jspproject.bbs.command.BProductCommand;
import com.jspproject.bbs.command.BProductDeleteCommand;
import com.jspproject.bbs.command.BProductListCommand;
import com.jspproject.bbs.command.BProductModifyCommand;
import com.jspproject.bbs.command.BProductQCommand;
import com.jspproject.bbs.command.BQnAAddCommand;
import com.jspproject.bbs.command.BQnADeleteCommand;
import com.jspproject.bbs.command.BQnAEditCommand;
import com.jspproject.bbs.command.BQnAFalseCommand;
import com.jspproject.bbs.command.BQnAListCommand;
import com.jspproject.bbs.command.BQnATureCommand;
import com.jspproject.bbs.command.BRegister_qCommand;
import com.jspproject.bbs.command.BReviewBNumCommand;
import com.jspproject.bbs.command.BReviewDeleteCommand;
import com.jspproject.bbs.command.BReviewDeleteListCommand;
import com.jspproject.bbs.command.BReviewRegistrationCommand;
import com.jspproject.bbs.command.BReviewRegistrationListCommand;
import com.jspproject.bbs.command.BSalesFalseCommand;
import com.jspproject.bbs.command.BSalesTureCommand;
import com.jspproject.bbs.command.BSearchCommand;
import com.jspproject.bbs.command.BSearchListCommand;
import com.jspproject.bbs.command.BSelectBuyCommand;
import com.jspproject.bbs.command.BSelectOrderPageCommand;
import com.jspproject.bbs.command.BSelectProductCommand;
import com.jspproject.bbs.command.BSelectQnACommand;
import com.jspproject.bbs.command.BSellerInfoCommand;
import com.jspproject.bbs.command.BSellerInfoUpdateCommand;
import com.jspproject.bbs.command.BSellerPwUpdateCommand;
import com.jspproject.bbs.command.BSellerSignOutCommand;
import com.jspproject.bbs.command.BSsearchQnAListCommand;
import com.jspproject.bbs.command.BStoreCommand;
import com.jspproject.bbs.command.CIdCheckCommand;
import com.jspproject.bbs.command.SIdCheckCommand;
import com.jspproject.bbs.command.SignUpCustomerCommand;
import com.jspproject.bbs.command.SignUpSellerCommand;
import com.jspproject.bbs.command.findIdActionCommand;
import com.jspproject.bbs.command.findPwActionCommand;
import com.jspproject.bbs.command.loginActionCommand;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() { // 2021.05.13 조혜지 - controller(모든 클라스의 집합소) 추가
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);

	}
	
	String fileNameMain;
	String fileNameDetail;
	public static int from;
	public static int to;
	
	// actionDo함수
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		System.out.println("actionDo()");
		
		request.setCharacterEncoding("utf-8");
				
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		String viewPage = null;
		BCommand command = null;

		switch(com) {
		case("/login.do"):
			viewPage = "Login_View.jsp";
			break;
		
		case("/signup.do"):
			command = new SignUpCustomerCommand();
			command.execute(request, response, session);
			viewPage = "signupComplete";
			break;
		case("/signupCustomer.do"):
			command = new SignUpCustomerCommand();
			command.execute(request, response, session);
			viewPage = "signupComplete.jsp";
			break;
		//판매자 회원가입	
		case("/signupSeller.do"):
			command = new SignUpSellerCommand();
			command.execute(request, response, session);
			viewPage = "signupComplete.jsp";
			break;
		/*
		 * 판매자 아이디 중복체크
		 */
		case("/signupsIdCheck.do"):
			command = new SIdCheckCommand();
			command.execute(request, response, session);
			System.out.println("컨트롤러" + session.getAttribute("sIdchk"));
			System.out.println(session.getAttribute("sHIDDEN"));
			
			String sIdchkMessage;
			if((int)session.getAttribute("sIdchk") == 1) {
				sIdchkMessage = "이미 사용중인 아이디 입니다. 다른 아이디를 입력해주세요";
				session.setAttribute("sIdchkMessage", sIdchkMessage);
				}else {
				sIdchkMessage = "아이디를 사용할 수 있습니다. 계속해서 진행해 주세요.";
				session.setAttribute("sIdchkMessage", sIdchkMessage);
			}
			request.setAttribute("sIdchk", session.getAttribute("sIdkchk"));
			request.setAttribute("sIdchkMessage", session.getAttribute("sIdchkMessage"));
			request.setAttribute("sId", session.getAttribute("sId"));
			request.setAttribute("sHIDDEN", session.getAttribute("sHIDDEN"));
			
//			System.out.println(request.getAttribute("sIdchkMessage"));
//			System.out.print(request.getAttribute("sId"));
			System.out.println(request.getAttribute("sHIDDEN"));
			viewPage= "sIdCheckAlert.jsp";
			break;
		/*
		 * 구매자 아이디 중복체크
		 */
		case("/signupcIdCheck.do"):
			command = new CIdCheckCommand();
			command.execute(request, response, session);
			System.out.println(session.getAttribute("cIdchk"));
			
			String cIdchkMessage;
			if((int)session.getAttribute("cIdchk") == 1) {
				 cIdchkMessage = "이미 사용중인 아이디 입니다. 다른 아이디를 입력해주세요";
				session.setAttribute("cIdchkMessage", cIdchkMessage);
			}else {
				 cIdchkMessage = "아이디를 사용할 수 있습니다. 계속해서 진행해 주세요.";
				session.setAttribute("cIdchkMessage", cIdchkMessage);
			}
			request.setAttribute("cIdchk", session.getAttribute("cIdkchk"));
			request.setAttribute("cIdchkMessage", session.getAttribute("cIdchkMessage"));
			request.setAttribute("cId", session.getAttribute("cId"));
			request.setAttribute("cHIDDEN", session.getAttribute("cHIDDEN"));
			
//			System.out.println(request.getAttribute("cIdchkMessage"));
//			System.out.print(request.getAttribute("cId"));
			System.out.print(request.getAttribute("sHIDDEN"));
			viewPage= "cIdCheckAlert.jsp";
			break;
		/*
		 * 로그인 액션 처리
		 */
		case("/loginaction.do"):
			command = new loginActionCommand();
			command.execute(request, response, session);
			
			//Command에서 처리한 결과에 따라서 다른 viewPage로 연결
			if ((int)session.getAttribute("loginChkResult") == 0) {
				session.invalidate();
				viewPage = "loginFailMain.jsp";
//				viewPage = "sellerLoginFailMain.jsp";
				// 밧데리없어서 여기까지 함 
				// 두개는 다 똑같은데 체크박스 체크값만 다르다. userType if문으로 나눠서 보낼 것.
			}else {
				if(session.getAttribute("USERTYPE").equals("customer")) {
					viewPage = "main.jsp";
				}else {
					viewPage = "sellerStore.jsp";
				}
			}
			break;
			/*
			 * 아이디찾기
			 */
		case("/findId.do"):
			command = new findIdActionCommand();
			command.execute(request, response, session);
			
			request.setAttribute("findmsg", (String)session.getAttribute("findmsg"));
			
			if((int)session.getAttribute("findchk") == 0) {
				viewPage = "failFidnId.jsp";
			}else {
				viewPage = "successFindId.jsp";
			}//
			System.out.println(session.getAttribute("findmsg"));
			break;
			/*
			 * 비밀번호찾기
			 */
		case("/findPw.do"):
			command = new findPwActionCommand();
			command.execute(request, response, session);
			
			request.setAttribute("findmsg", (String)session.getAttribute("findmsg"));
			
			if((int)session.getAttribute("findchk") == 0) {
				viewPage = "failFidnPw.jsp";
			}else {
				viewPage = "successFindPw.jsp";
			}//
			System.out.println(session.getAttribute("findmsg"));
			break;
		// 회원정보 변경 버튼 눌렀을 때 DB에 있는 회원 정보 불러오기
		case("/CustomerInfoUpdate_View.do"):
			command = new BCustomerInfoCommand();
			command.execute(request, response, session);
			viewPage = "CustomerInfoUpdate_View.jsp";
			break;
		// 회원정보 변경 페이지에서 확인 눌렀을 때 DB 수정하기
		case("/CustomerInfoUpdate.do"):
			command = new BCustomerInfoUpdateCommand();
			command.execute(request, response, session);
			viewPage = "CustomerInfoUpdate_View.do";
			break;
		// 판매자정보 변경 버튼 눌렀을 때 DB에 있는 판매자 정보 불러오기
		case("/SellerInfoUpdate_View.do"):
			command = new BSellerInfoCommand();
			command.execute(request, response, session);
			viewPage = "SellerInfoUpdate_View.jsp";
			break;
		// 판매자정보 변경 페이지에서 확인 눌렀을 때 DB 수정하기
		case("/SellerInfoUpdate.do"):
			command = new BSellerInfoUpdateCommand();
			command.execute(request, response, session);
			viewPage = "SellerInfoUpdate_View.do";
			break;
		// 고객 비밀번호 변경하기
		case("/CustomerPwUpdate_View.do"):
			viewPage = "CustomerPwUpdate_View.jsp";
			break;
		case("/CustomerPwUpdate.do"):
			command = new BCustomerPwUpdateCommand();
			command.execute(request, response, session);
			viewPage = "CustomerPwUpdate_View.do";
			break;
		// 판매자 비밀번호 변경하기
		case("/SellerPwUpdate_View.do"):
			viewPage = "SellerPwUpdate_View.jsp";
		break;
		case("/SellerPwUpdate.do"):
			command = new BSellerPwUpdateCommand();
			command.execute(request, response, session);
			viewPage = "SellerPwUpdate_View.do";
		break;
		// 탈퇴하기
		case("/CustomerSignOut_View.do"):
			viewPage = "CustomerSignOut_View.jsp";
			break;
		case("/CustomerSignOut.do"):
			command = new BCustomerSignOutCommand();
			command.execute(request, response, session);
			// 이거는 수훈님과 연동할 때 메인페이지로 바꾸기 ****************************
			viewPage = "CustomerSignOut_View.do";
			break;
		// 탈퇴하기
		case("/SellerSignOut_View.do"):
			viewPage = "SellerSignOut_View.jsp";
		break;
		case("/SellerSignOut.do"):
			command = new BSellerSignOutCommand();
			command.execute(request, response, session);
			// 이거는 예진님과 연동할 때 메인페이지로 바꾸기 ****************************
			viewPage = "SellerSignOut_View.do";
		break;
		// 이 케이스문은 삭제할 것임! 도희님과 연동할 때 상품 상세페이지로 바꾸기 ****************************
		case("/Product_View.do"):
			viewPage = "Product_View.jsp";
			break;
		// 장바구니 중복체크하고 DB에 수량과 장바구니 일자 insert
		case("/CustomerCartInsert.do"):
			command = new BCartCheckCommand();
			command.execute(request, response, session);
			System.out.println("컨트롤러" + session.getAttribute("check"));
			String checkMessage;
			if((int)session.getAttribute("check") == 1) {
				checkMessage = "이미 장바구니에 있는 제품입니다. 선택하신 수량만큼 업데이트 되었습니다.";
				session.setAttribute("checkMessage", checkMessage);
				command = new BCustomerCartUpdateCommand();
				command.execute(request, response, session);
			}else {
				checkMessage = "장바구니에 담았습니다.";
				session.setAttribute("checkMessage", checkMessage);
				command = new BCustomerCartInsertCommand();
				command.execute(request, response, session);
			}
			request.setAttribute("check", session.getAttribute("check"));
			request.setAttribute("checkMessage", session.getAttribute("checkMessage"));
			viewPage = "cartCheckAlert.jsp";
		break;
		// 장바구니 DB에서 불러오기
		case("/CustomerCart_View.do"):
			command = new BCustomerCartListCommand();
			command.execute(request, response, session);
			viewPage = "CustomerCart_View.jsp";
			break;
		// 장바구니 DB에서 불러오기 페이지 분할
		case("/CustomerCart_View.do?*"):
			command = new BCustomerCartListCommand();
			command.execute(request, response, session);
			viewPage = "CustomerCart_View.jsp";
		break;
		// 장바구니 상품 전체 삭제
		case("/CartAllDelete.do"):
			command = new BCartAllDeleteCommand();
			command.execute(request, response, session);
			viewPage = "CustomerCart_View.do";
			break;
			// 장바구니 상품 선택 삭제
		case("/CartDelete.do"):
			command = new BCartDeleteCommand();
			command.execute(request, response, session);
			viewPage = "CustomerCart_View.do";
		break;
		// 선택 상품 구매하기
		case("/Buy_View.do"):
			command = new BCBuyListCommand();
			command.execute(request, response, session);
			command = new BBuyerCommand();
			command.execute(request, response, session);
			viewPage = "Buy_View.jsp";
			break;
		case("/Buy.do"):
			command = new BBuyCommand();
			command.execute(request, response, session);
		viewPage = "BuyConfirm_View.do";
		break;
		// 주문 완료 창 보여주기
		case("/BuyConfirm_View.do"):
			command = new BBuyConfirmListCommand();
			command.execute(request, response, session);
			command = new BDeliveryInfoCommand();
			command.execute(request, response, session);
			command = new BPCodeCommand();
			command.execute(request, response, session);
			command = new BBuyConfirmCartDeleteCommand();
			command.execute(request, response, session);
			viewPage = "BuyConfirm_View.jsp";
		break;
		// 전체 상품 구매하기
		case("/AllBuy_View.do"):
			command = new BAllBuyListCommand();
			command.execute(request, response, session);
			command = new BBuyerCommand();
			command.execute(request, response, session);
			viewPage = "AllBuy_View.jsp";
		break;
		case("/AllBuy.do"):
			command = new BAllBuyCommand();
			command.execute(request, response, session);
			viewPage = "AllBuyConfirm_View.do";
		break;
		// 주문 완료 창 보여주기
		case("/AllBuyConfirm_View.do"):
			command = new BBuyConfirmListCommand();
			command.execute(request, response, session);
			command = new BDeliveryInfoCommand();
			command.execute(request, response, session);
			command = new BPCodeCommand();
			command.execute(request, response, session);
			command = new BBuyConfirmCartDeleteCommand();
			command.execute(request, response, session);
		viewPage = "AllBuyConfirm_View.jsp";
		break;
		// 주문 완료 창에서 주문 취소하기
		case("/Cancel_View.do"):
			command = new BCancelCommand();
			command.execute(request, response, session);
			command = new BBuyConfirmListCommand();
			command.execute(request, response, session);
			viewPage = "Cancel_View.jsp";
		break;
		// 리뷰 미등록 상품 목록 불러오기
		case("/ReviewRegistrationList_View.do"):
			command = new BReviewRegistrationListCommand();
			command.execute(request, response, session);
			viewPage = "ReviewRegistrationList_View.jsp";
			break;
		// 리뷰 미등록 상품 목록 불러오기 페이지 분할
		case ("/ReviewRegistrationList_View.do?*"):
			command = new BReviewRegistrationListCommand();
			command.execute(request, response, session);
			viewPage = "ReviewRegistrationList_View.jsp";
			break;
		// 리뷰 등록하기
		case("/ReviewRegistration_View.do"):
			command = new BReviewBNumCommand();
			command.execute(request, response, session);
			viewPage = "ReviewRegistration_View.jsp";
			break;
		case("/ReviewRegistration.do"):
			command = new BReviewRegistrationCommand();
			command.execute(request, response, session);
			viewPage = "ReviewRegistrationList_View.do";
			break;
		// 리뷰 등록 상품 목록 불러오기
		case("/ReviewDelete_View.do"):
			command = new BReviewDeleteListCommand();
			command.execute(request, response, session);
			viewPage = "ReviewDelete_View.jsp";
			break;
		// 리뷰 등록 상품 목록 불러오기 페이지 분할
		case ("/ReviewDelete_View.do?*"):
			command = new BReviewDeleteListCommand();
			command.execute(request, response, session);
			viewPage = "ReviewDelete_View.jsp";
			break;
		// 리뷰 등록 상품 삭제하기
		case("/ReviewDelete.do"):
			command = new BReviewDeleteCommand();
			command.execute(request, response, session);
			viewPage = "ReviewDelete_View.do";
			break;
		// 주문 상품 목록 불러오기
		case("/OrderList_View.do"):
			command = new BOrderListCommand();
			command.execute(request, response, session);
			viewPage = "OrderList_View.jsp";
			break;
		// 주문 상품 목록 불러오기 페이지 분할
		case ("/OrderList_View.do?*"):
			command = new BOrderListCommand();
			command.execute(request, response, session);
			viewPage = "OrderList_View.jsp";
			break;
		// 주문목록/배송조회에서 주문 취소하기
		case("/OrderListCancel_View.do"):
			command = new BOrderListCancelCommand();
			command.execute(request, response, session);
			viewPage = "OrderListCancel_View.jsp";
			break;
		// 주문 취소 상품 목록 불러오기
		case("/OrderCancelList_View.do"):
			command = new BCancelListCommand();
			command.execute(request, response, session);
			viewPage = "OrderCancelList_View.jsp";
			break;
		// 주문 취소 상품 목록 불러오기 페이지 분할
		case ("/OrderCancelList_View.do?*"):
			command = new BCancelListCommand();
			command.execute(request, response, session);
			viewPage = "OrderCancelList_View.jsp";
			break;
		// 메인 페이지 - DH	
		case("/main.do"):
			command = new BMainCommand();
			command.execute(request, response, session);
			viewPage = "main.jsp";
			break;
		case("/best.do"):
			command = new BBestCommand();
			command.execute(request, response, session);
			viewPage = "best.jsp";
			break;
		case("/new.do"):
			command = new BNewCommand();
			command.execute(request, response, session);
			viewPage = "new.jsp";
			break;
		case("/deadline.do"):
			command = new BDeadlineCommand();
			command.execute(request, response, session);
			viewPage = "deadline.jsp";
			break;
		case("/category.do"):
			command = new BCategoryCommand();
			command.execute(request, response, session);
			viewPage = "category.jsp";
			break;
		case("/search.do"):
			command = new BSearchCommand();
			command.execute(request, response, session);
			viewPage = "search.jsp";
			break;
		case("/product.do"):
			command = new BProductCommand();
			command.execute(request, response, session);
			viewPage = "product.jsp";
			break;
		case("/register_q_view.do"):
			viewPage = "register_q_view.jsp";
			break;
		case("/register_q.do"):
//			String pCode = request.getParameter("pCode");
//			System.out.println(pCode);
			command = new BRegister_qCommand();
			command.execute(request, response, session);
			command = new BProductQCommand();
			command.execute(request, response, session);
			viewPage = "product.jsp";
			break;
		// 첫화면
		case ("/sellerStore.do"):
			command = new BStoreCommand();
			command.execute(request, response, session);
			viewPage = "sellerStore.jsp";
			break;
		// 상품리스트 --------------------------------
			// 리스트 전체보기
		case ("/productlist.do"):
			command = new BProductListCommand();
		command.execute(request, response, session);
			viewPage = "product_list_management.jsp";
			break;
		case ("/productlist.do?*"):
			System.out.println("컨트롤러문제이니?");
			command = new BProductListCommand();
			command.execute(request, response, session);
			viewPage = "product_list_management.jsp";
			break;
			// ---- 끝
			// 검색 리스트
		case ("/searchList.do"):
			command = new BSearchListCommand();
			command.execute(request, response, session);
			viewPage = "ProductSearchList.jsp";
			break;
			// ---- 
			// 판매중인 리스트
		case ("/searchTure.do"):
			command = new BSalesTureCommand();
			command.execute(request, response, session);
			viewPage = "ProductTureList.jsp";
			break;
			// ---- 끝
			// 품절인 리스트
		case ("/searchFalse.do"):
			command = new BSalesFalseCommand();
			command.execute(request, response, session);
			viewPage = "ProductFalseList.jsp";
			break;
			// ---- 끝
		case ("/selectProduct.do"):
			System.out.println(request.getParameter("pStatus"));
			command = new BSelectProductCommand();
			command.execute(request, response, session);
			viewPage = "selectProduct_vlew.jsp";
			break;
		case ("/productModify.do"):
			command = new BProductModifyCommand(); 
			command.execute(request, response, session);
			viewPage = "productlist.do";
			break;
		case ("/productDelete.do"):
			command = new BProductDeleteCommand(); 
			command.execute(request, response, session);
			viewPage = "productlist.do";
			break;
		// 상품리스트 -------------------------------- 끝
		// 상품등록 --------------------------------
		// ############### Register 테이블에도 업로드될 수 있도록@@@@@@@@@@@@@@@@@@@@@@@
		case ("/showFirst.do"):
			viewPage = "firstView.jsp";
			break;
		case ("/insert.do"):
			System.out.println("난다요?");
			command = new BAddProductCommand(); // insert register 안됨...ㅅㅂ........
			System.out.println("난다요?");
			command.execute(request, response, session);
			viewPage = "productlist.do";
			break;
		case ("/imgUpload_view.do"):
			viewPage = "imgUpload_view.jsp";
			break;
		case ("/imgUpload.do"):
			command = new BFileUpladCommad();
			command.execute(request, response, session);
//				fileUpload(request, response);
			viewPage = "productlist.do";
			break;
		// 상품등록 -------------------------------- 끝
		// 배송현황 --------------------------------
		case ("/DeliveryList.do"):
			command = new BDeliveryListCommand();
			command.execute(request, response, session);
			viewPage = "DeliveryList.jsp";
			break;
		case ("/deliverySearchList.do"):
			command = new BDeliverySearchListCommand();
			command.execute(request, response, session);
			viewPage = "DeliveryList.jsp";
			break;
		case ("/DeliveryEnd.do"):
			command = new BDeliveryEndCommand();
			command.execute(request, response, session);
			viewPage = "DeliveryList.jsp";
			break;
		case ("/DeliveryING.do"):
			command = new BDeliveryINGCommand();
			command.execute(request, response, session);
			viewPage = "DeliveryList.jsp";
			break;
		case ("/selectOrderPage.do"):
			command = new BSelectOrderPageCommand();
//				command = new BSelectOrderPgListCommand();
			command.execute(request, response, session);
			viewPage = "selectOrderPage_view.jsp";
			break;
			// 배송중으로 변경
		case ("/deliveryFor.do"):
			command = new BDeliveryForCommand();
			command.execute(request, response, session);
			viewPage = "selectOrderPage.do";
			break;
		case ("/deliveryFinish.do"):
			command = new BDeliveryFinishCommand();
			command.execute(request, response, session);
			viewPage = "selectOrderPage.do";
			break;
			// 배송현황 -------------------------------- 끝
			// 주문관리 --------------------------------
		case ("/buyList.do"):
			command = new BBuyListCommand();
			command.execute(request, response, session);
			viewPage = "buyList.jsp";
			break;
		case ("/buySearchList.do"):
			command = new BBuySearchListCommand();
			command.execute(request, response, session);
			viewPage = "buySearchList.jsp";
			break;
		case ("/buyTure.do"):
			command = new BBuyTureCommand();
			command.execute(request, response, session);
			viewPage = "buyTureList.jsp";
			break;
		case ("/buyCancel.do"):
			command = new BBuyCancelCommand();
			command.execute(request, response, session);
			viewPage = "buyCancelList.jsp";
			break;
		case ("/selectBuy.do"):
			command = new BSelectBuyCommand();
			command.execute(request, response, session);
			viewPage = "selectBuy_vlew.jsp";
			break;
		case ("/buyToDeliver.do"):
			System.out.println(request.getParameter("bNumber"));
			command = new BBuyToDeliverCommand();
			command.execute(request, response, session);
			System.out.println(request.getAttribute("selectOrderPage"));
			viewPage = "selectOrderPage_view.jsp";
			break;
		// 주문관리 -------------------------------- 끈
		// 문의관리 --------------------------------
		case ("/QnAList.do"):
			command = new BQnAListCommand();
			command.execute(request, response, session);
			viewPage = "QnAList.jsp";
			break;
			// 문의 검색
		case ("/searchQnAList.do"):
			command = new BSsearchQnAListCommand();
			command.execute(request, response, session);
			viewPage = "searchQnAList.jsp";
			break;
			// ----- 끝
			// 답변완료
		case ("/QnATure.do"):
			command = new BQnATureCommand();
			command.execute(request, response, session);
			viewPage = "QnATureList.jsp";
			break;
			// ---- 끝
			// 미답변
		case ("/QnAFalse.do"):
			command = new BQnAFalseCommand();
			command.execute(request, response, session);
			viewPage = "QnAFalseList.jsp";
			break;
			// ---- 끝
		case ("/selectQnA.do"):
			command = new BSelectQnACommand();
			command.execute(request, response, session);
			viewPage = "selectQnAPage_view.jsp";
			break;
		case ("/QnAAdd.do"):
			command = new BQnAAddCommand();
			command.execute(request, response, session);
			viewPage = "QnAList.jsp";
			break;
		case ("/QnAEdit.do"):
			command = new BQnAEditCommand();
			command.execute(request, response, session);
			viewPage = "QnAList.jsp";
			break;
		case ("/QnADelete.do"):
			command = new BQnADeleteCommand();
			command.execute(request, response, session);
			viewPage = "QnAList.jsp";
			// 문의관리 -------------------------------- 끝			
		default:
			break;
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
	
}
