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
import com.jspproject.bbs.command.BproductlistnBnsCommand;
import com.jspproject.bbs.command.BCIdCheckCommand;
import com.jspproject.bbs.command.BSIdCheckCommand;
import com.jspproject.bbs.command.BSignUpCustomerCommand;
import com.jspproject.bbs.command.BSignUpSellerCommand;
import com.jspproject.bbs.command.BFindIdActionCommand;
import com.jspproject.bbs.command.BFindPwActionCommand;
import com.jspproject.bbs.command.BLoginActionCommand;
import com.jspproject.bbs.util.Share;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() { // 2021.05.13 ????????? - controller(?????? ???????????? ?????????) ??????
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
	
	// actionDo??????
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
			command = new BSignUpCustomerCommand();
			command.execute(request, response, session);
			viewPage = "signupComplete";
			break;
		case("/signupCustomer.do"):
			command = new BSignUpCustomerCommand();
			command.execute(request, response, session);
			viewPage = "signupComplete.jsp";
			break;
		//????????? ????????????	
		case("/signupSeller.do"):
			command = new BSignUpSellerCommand();
			command.execute(request, response, session);
			viewPage = "signupComplete.jsp";
			break;
		/*
		 * ????????? ????????? ????????????
		 */
		case("/signupsIdCheck.do"):
			command = new BSIdCheckCommand();
			command.execute(request, response, session);
			System.out.println("????????????" + session.getAttribute("sIdchk"));
			System.out.println(session.getAttribute("sHIDDEN"));
			
			String sIdchkMessage;
			if((int)session.getAttribute("sIdchk") == 1) {
				sIdchkMessage = "?????? ???????????? ????????? ?????????. ?????? ???????????? ??????????????????";
				session.setAttribute("sIdchkMessage", sIdchkMessage);
				}else {
				sIdchkMessage = "???????????? ????????? ??? ????????????. ???????????? ????????? ?????????.";
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
		 * ????????? ????????? ????????????
		 */
		case("/signupcIdCheck.do"):
			command = new BCIdCheckCommand();
			command.execute(request, response, session);
			System.out.println(session.getAttribute("cIdchk"));
			
			String cIdchkMessage;
			if((int)session.getAttribute("cIdchk") == 1) {
				 cIdchkMessage = "?????? ???????????? ????????? ?????????. ?????? ???????????? ??????????????????";
				session.setAttribute("cIdchkMessage", cIdchkMessage);
			}else {
				 cIdchkMessage = "???????????? ????????? ??? ????????????. ???????????? ????????? ?????????.";
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
		 * ????????? ?????? ??????
		 */
		case("/loginaction.do"):
			command = new BLoginActionCommand();
			command.execute(request, response, session);
			session.setAttribute("userId", Share.userId);

			System.out.println(session.getAttribute("userId"));
			//Command?????? ????????? ????????? ????????? ?????? viewPage??? ??????
			if ((int)session.getAttribute("loginChkResult") == 0) {
				session.invalidate();
				viewPage = "loginFailMain.jsp";
//				viewPage = "sellerLoginFailMain.jsp";
				// ?????????????????? ???????????? ??? 
				// ????????? ??? ???????????? ???????????? ???????????? ?????????. userType if????????? ????????? ?????? ???.
			}else {
				if(session.getAttribute("USERTYPE").equals("customer")) {
					viewPage = "main.do";
				}else {
					viewPage = "sellerStore.do";
				}
			}
			break;
		case("/logout.do"):
			session.invalidate();
			viewPage = "main.do";
			break;
			/*
			 * ???????????????
			 */
		case("/findId.do"):
			command = new BFindIdActionCommand();
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
			 * ??????????????????
			 */
		case("/findPw.do"):
			command = new BFindPwActionCommand();
			command.execute(request, response, session);
			
			request.setAttribute("findmsg", (String)session.getAttribute("findmsg"));
			
			if((int)session.getAttribute("findchk") == 0) {
				viewPage = "failFidnPw.jsp";
			}else {
				viewPage = "successFindPw.jsp";
			}//
			System.out.println(session.getAttribute("findmsg"));
			break;
		// ???????????? ?????? ?????? ????????? ??? DB??? ?????? ?????? ?????? ????????????
		case("/CustomerInfoUpdate_View.do"):
			command = new BCustomerInfoCommand();
			command.execute(request, response, session);
			viewPage = "CustomerInfoUpdate_View.jsp";
			break;
		// ???????????? ?????? ??????????????? ?????? ????????? ??? DB ????????????
		case("/CustomerInfoUpdate.do"):
			command = new BCustomerInfoUpdateCommand();
			command.execute(request, response, session);
			viewPage = "CustomerInfoUpdate_View.do";
			break;
		// ??????????????? ?????? ?????? ????????? ??? DB??? ?????? ????????? ?????? ????????????
		case("/SellerInfoUpdate_View.do"):
			command = new BSellerInfoCommand();
			command.execute(request, response, session);
			viewPage = "SellerInfoUpdate_View.jsp";
			break;
		// ??????????????? ?????? ??????????????? ?????? ????????? ??? DB ????????????
		case("/SellerInfoUpdate.do"):
			command = new BSellerInfoUpdateCommand();
			command.execute(request, response, session);
			viewPage = "SellerInfoUpdate_View.do";
			break;
		// ?????? ???????????? ????????????
		case("/CustomerPwUpdate_View.do"):
			viewPage = "CustomerPwUpdate_View.jsp";
			break;
		case("/CustomerPwUpdate.do"):
			command = new BCustomerPwUpdateCommand();
			command.execute(request, response, session);
			viewPage = "CustomerPwUpdate_View.do";
			break;
		// ????????? ???????????? ????????????
		case("/SellerPwUpdate_View.do"):
			viewPage = "SellerPwUpdate_View.jsp";
		break;
		case("/SellerPwUpdate.do"):
			command = new BSellerPwUpdateCommand();
			command.execute(request, response, session);
			viewPage = "SellerPwUpdate_View.do";
		break;
		// ????????????
		case("/CustomerSignOut_View.do"):
			viewPage = "CustomerSignOut_View.jsp";
			break;
		case("/CustomerSignOut.do"):
			command = new BCustomerSignOutCommand();
			command.execute(request, response, session);
			// ????????? ???????????? ????????? ??? ?????????????????? ????????? ****************************
			session.invalidate();
			viewPage = "main.do";
			break;
		// ????????????
		case("/SellerSignOut_View.do"):
			viewPage = "SellerSignOut_View.jsp";
		break;
		case("/SellerSignOut.do"):
			command = new BSellerSignOutCommand();
			command.execute(request, response, session);
			// ????????? ???????????? ????????? ??? ?????????????????? ????????? ****************************
			session.invalidate();
			viewPage = "main.do";
		break;
		// ??? ??????????????? ????????? ??????! ???????????? ????????? ??? ?????? ?????????????????? ????????? ****************************
		case("/Product_View.do"):
			viewPage = "Product_View.jsp";
			break;
		// ???????????? ?????????????????? DB??? ????????? ???????????? ?????? insert
		case("/CustomerCartInsert.do"):
			command = new BCartCheckCommand();
			command.execute(request, response, session);
			System.out.println("????????????" + session.getAttribute("check"));
			String checkMessage;
			if((int)session.getAttribute("check") == 1) {
				checkMessage = "?????? ??????????????? ?????? ???????????????. ???????????? ???????????? ???????????? ???????????????.";
				session.setAttribute("checkMessage", checkMessage);
				command = new BCustomerCartUpdateCommand();
				command.execute(request, response, session);
			}else {
				checkMessage = "??????????????? ???????????????.";
				session.setAttribute("checkMessage", checkMessage);
				command = new BCustomerCartInsertCommand();
				command.execute(request, response, session);
			}
			request.setAttribute("check", session.getAttribute("check"));
			request.setAttribute("checkMessage", session.getAttribute("checkMessage"));
			viewPage = "cartCheckAlert.jsp";
		break;
		// ???????????? alert ?????? ?????? pCode ????????????
		case("/productAgain.do"):
			command = new BProductQCommand();
			command.execute(request, response, session);
			viewPage = "product.jsp";
		break;
		// ???????????? DB?????? ????????????
		case("/CustomerCart_View.do"):
			command = new BCustomerCartListCommand();
			command.execute(request, response, session);
			viewPage = "CustomerCart_View.jsp";
			break;
		// ???????????? DB?????? ???????????? ????????? ??????
		case("/CustomerCart_View.do?*"):
			command = new BCustomerCartListCommand();
			command.execute(request, response, session);
			viewPage = "CustomerCart_View.jsp";
		break;
		// ???????????? ?????? ?????? ??????
		case("/CartAllDelete.do"):
			command = new BCartAllDeleteCommand();
			command.execute(request, response, session);
			viewPage = "CustomerCart_View.do";
			break;
		// ???????????? ?????? ?????? ??????
		case("/CartDelete.do"):
			command = new BCartDeleteCommand();
			command.execute(request, response, session);
			viewPage = "CustomerCart_View.do";
		break;
		// ?????? ?????? ????????????
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
		// ?????? ?????? ??? ????????????
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
		// ?????? ?????? ????????????
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
		// ?????? ?????? ??? ????????????
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
		// ?????? ?????? ????????? ?????? ????????????
		case("/Cancel_View.do"):
			command = new BCancelCommand();
			command.execute(request, response, session);
			command = new BBuyConfirmListCommand();
			command.execute(request, response, session);
			viewPage = "Cancel_View.jsp";
		break;
		// ?????? ????????? ?????? ?????? ????????????
		case("/ReviewRegistrationList_View.do"):
			command = new BReviewRegistrationListCommand();
			command.execute(request, response, session);
			viewPage = "ReviewRegistrationList_View.jsp";
			break;
		// ?????? ????????? ?????? ?????? ???????????? ????????? ??????
		case ("/ReviewRegistrationList_View.do?*"):
			command = new BReviewRegistrationListCommand();
			command.execute(request, response, session);
			viewPage = "ReviewRegistrationList_View.jsp";
			break;
		// ?????? ????????????
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
		// ?????? ?????? ?????? ?????? ????????????
		case("/ReviewDelete_View.do"):
			command = new BReviewDeleteListCommand();
			command.execute(request, response, session);
			viewPage = "ReviewDelete_View.jsp";
			break;
		// ?????? ?????? ?????? ?????? ???????????? ????????? ??????
		case ("/ReviewDelete_View.do?*"):
			command = new BReviewDeleteListCommand();
			command.execute(request, response, session);
			viewPage = "ReviewDelete_View.jsp";
			break;
		// ?????? ?????? ?????? ????????????
		case("/ReviewDelete.do"):
			command = new BReviewDeleteCommand();
			command.execute(request, response, session);
			viewPage = "ReviewDelete_View.do";
			break;
		// ?????? ?????? ?????? ????????????
		case("/OrderList_View.do"):
			command = new BOrderListCommand();
			command.execute(request, response, session);
			viewPage = "OrderList_View.jsp";
			break;
		// ?????? ?????? ?????? ???????????? ????????? ??????
		case ("/OrderList_View.do?*"):
			command = new BOrderListCommand();
			command.execute(request, response, session);
			viewPage = "OrderList_View.jsp";
			break;
		// ????????????/?????????????????? ?????? ????????????
		case("/OrderListCancel_View.do"):
			command = new BOrderListCancelCommand();
			command.execute(request, response, session);
			viewPage = "OrderListCancel_View.jsp";
			break;
		// ?????? ?????? ?????? ?????? ????????????
		case("/OrderCancelList_View.do"):
			command = new BCancelListCommand();
			command.execute(request, response, session);
			viewPage = "OrderCancelList_View.jsp";
			break;
		// ?????? ?????? ?????? ?????? ???????????? ????????? ??????
		case ("/OrderCancelList_View.do?*"):
			command = new BCancelListCommand();
			command.execute(request, response, session);
			viewPage = "OrderCancelList_View.jsp";
			break;
		// ?????? ????????? - DH
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
		// ?????????
		case ("/sellerStore.do"):
			command = new BStoreCommand();
			command.execute(request, response, session);
			viewPage = "sellerStore.jsp";
			break;
		// ??????????????? --------------------------------
			// ????????? ????????????
		case ("/productlist.do"):
			command = new BProductListCommand();
			command.execute(request, response, session);
			viewPage = "product_list_management.jsp";
			break;
		case ("/productlist.do?*"):
			System.out.println("?????????????????????????");
			command = new BProductListCommand();
			command.execute(request, response, session);
			viewPage = "product_list_management.jsp";
			break;
			// ---- ???
			// ?????? ?????????
		case ("/searchList.do"):
			command = new BSearchListCommand();
			command.execute(request, response, session);
			viewPage = "ProductSearchList.jsp";
			break;
			// ---- 
			// ???????????? ?????????
		case ("/searchTure.do"):
			command = new BSalesTureCommand();
			command.execute(request, response, session);
			viewPage = "ProductTureList.jsp";
			break;
			// ---- ???
			// ????????? ?????????
		case ("/searchFalse.do"):
			command = new BSalesFalseCommand();
			command.execute(request, response, session);
			viewPage = "ProductFalseList.jsp";
			break;
			// ---- ???
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
		// ??????????????? -------------------------------- ???
		// ???????????? --------------------------------
		// ############### Register ??????????????? ???????????? ??? ?????????@@@@@@@@@@@@@@@@@@@@@@@
		case ("/showFirst.do"):
			viewPage = "firstView.jsp";
			break;
		case ("/insert.do"):
			command = new BAddProductCommand(); // insert register ??????...??????........
			command.execute(request, response, session);
			viewPage = "productlistnBns.do";
			break;
		case ("/productlistnBns.do"):
			command = new BproductlistnBnsCommand(); 
			command.execute(request, response, session);
			viewPage = "productlist.do";
			break;
		case ("/imgUpload.do"):
			command = new BFileUpladCommad();
			command.execute(request, response, session);
//					fileUpload(request, response);
			viewPage = "productlist.do";
			break;
		// ???????????? -------------------------------- ???
		// ???????????? --------------------------------
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
//					command = new BSelectOrderPgListCommand();
			command.execute(request, response, session);
			viewPage = "selectOrderPage_view.jsp";
			break;
			// ??????????????? ??????
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
			// ???????????? -------------------------------- ???
			// ???????????? --------------------------------
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
		// ???????????? -------------------------------- ???
		// ???????????? --------------------------------
		case ("/QnAList.do"):
			command = new BQnAListCommand();
			command.execute(request, response, session);
			viewPage = "QnAList.jsp";
			break;
			// ?????? ??????
		case ("/searchQnAList.do"):
			command = new BSsearchQnAListCommand();
			command.execute(request, response, session);
			viewPage = "searchQnAList.jsp";
			break;
			// ----- ???
			// ????????????
		case ("/QnATure.do"):
			command = new BQnATureCommand();
			command.execute(request, response, session);
			viewPage = "QnATureList.jsp";
			break;
			// ---- ???
			// ?????????
		case ("/QnAFalse.do"):
			command = new BQnAFalseCommand();
			command.execute(request, response, session);
			viewPage = "QnAFalseList.jsp";
			break;
			// ---- ???
		case ("/selectQnA.do"):
			command = new BSelectQnACommand();
			command.execute(request, response, session);
			System.out.println("?????????" + request.getAttribute("qnACode"));
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
			// ???????????? -------------------------------- ???		
		default:
			break;
		}
		
//		session.setAttribute("userId", Share.userId);

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
	
}
