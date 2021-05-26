<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.16 조혜지 장바구니 view -->
<title>장바구니</title>
</head>
<script type="text/javascript">
	function allCancel() {

			alert("장바구니의 전체 상품이 삭제되었습니다.");
		
	}
</script> 
<script type="text/javascript">
	function Cancel() {

			alert("선택하신 상품이 삭제되었습니다.");
		
	}
</script> 
<style>
	/* .container_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.container {
		width: 1000px;
		margin: auto;
		background-color: #fff;
	} */
	.container form {
		min-height: 350px;
		width: 80%;
		margin: auto;
	}
	/* 테이블 디자인 */
	caption {
		font-style: 200px;
		font-weight: bold;
		margin: 50px;
		font-size: x-large;
	}
	th, td {
		padding: 10px;
		text-align: center;
	}
	/* 선 */
	.title th, .cart_product td {
		border-bottom: 1px solid #444444;
	}
	/* table 넓이 조정 */
	.title th:nth-child(1), .cart_product td:nth-child(1) {
		width: 30px;
	}
	.title th:nth-child(2), .cart_product td:nth-child(2) {
		width: 350px;
	}
	.cart_product td:nth-child(2) {
		text-align: left;
	}
	.title th:nth-child(3), .cart_product td:nth-child(3) {
		width: 70px;
	}
	.title th:nth-child(4), .cart_product td:nth-child(4) {
		width: 120px;
	}
	.title th:nth-child(5), .cart_product td:nth-child(5) {
		width: 120px;
	}
	/* 전체가격 */
	.price {
		text-align: right;
		padding: 30px 30px 50px 0;
	}
	/* 버튼 디자인 */
	.buttonCart{
		width: 90px;
	    background-color: #828282;
	    border: none;
	    color: #fff;
	    padding: 2px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 13px;
	    margin: 10px;
	    cursor: pointer;
		border-radius: 10px;
	}
</style>
<body>
	<%@include file = "header.jsp" %>
	<div class="container_wrap">
		<%@include file = "LeftMenuBar.jsp" %>
		<div class="container">
			<form>
				<table>
					<c:choose>
						<c:when test="${empty CART}">
							<caption style="width: 800px; text-align: center; margin: 30px 0; padding-top: 80px;">장바구니</caption>
							<tr style="width: 800px; text-align: center; margin: 30px 0;">
								<td>장바구니 목록이 비었습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<caption>장바구니</caption>
							<tr class="title">
								<th></th>
								<th>상품명</th>
								<th>수량</th>
								<th>유통기한</th>
								<th>가격</th>
							</tr>
						<c:forEach items="${CART }" var="dto">
							<tr class="cart_product">
								<td><input type="checkbox" name="selectedProduct" value = "${dto.wId }" checked="checked">
								<td>${dto.pName }</td>
								<td>${dto.wQuantity }</td>
								<td>${dto.pExpirationDate }</td>
								<td>${dto.pPriceDC * dto.wQuantity }</td>
							</tr>
							<c:set var="SUM" value="${SUM + (dto.pPriceDC * dto.wQuantity)}" />
						</c:forEach>
							<tr>
						        <td align="center" colspan="5">
						         	[<a href="CustomerCart_View.do"> ◀◀ </a>] 
						         	[<a href="CustomerCart_View.do?pg=${FROMPAGE -1 }">◀</a>] 
						         	<c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
					         		<a href="CustomerCart_View.do?pg=${ftp.count}">[ ${ftp.count } ]</a>
					                </c:forEach>
					                [<a href="CustomerCart_View.do?pg=${TOPAGE }">▶</a>] 
					                [<a href="CustomerCart_View.do?pg=${ALLPAGE }">▶▶</a>]
						        </td>
			    			</tr>
							<tr>
								<td colspan="5" class="price">장바구니 총 금액 : ${SUM } 원</td>
							</tr>
							<tr>
								<td colspan="3"></td>
								<td><input type="submit" class="buttonCart" value="선택 상품 주문" formaction="Buy_View.do?selectedProduct=${dto.wId }"></td>
								<td><input type="submit" class="buttonCart" value="선택 상품 삭제" formaction="CartDelete.do?selectedProduct=${dto.wId }" onclick="Cancel()"></td>
								</form>
							</tr>
							<tr>
								<td colspan="3"></td>
								<form action="AllBuy_View.do" method="post">
									<td><input type="submit" class="buttonCart" value="전체 상품 주문"></td>
								</form>	
								<form action="CartAllDelete.do" method="post">
									<td><input type="submit" class="buttonCart" value="전체 상품 삭제" onclick="allCancel()"></td>
								</form>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>