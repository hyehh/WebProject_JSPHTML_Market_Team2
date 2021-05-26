<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.20 조혜지 장바구니에 있는 전체 상품 결제 완료 view -->
<title>결제 완료</title>
</head>
<style>
	.container_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.container {
		width: 1000px;
		margin: auto;
		background-color: #fff;
		text-align: center;
		padding: 50px 0;
	} 
	.message {
		padding-bottom: 30px;
	}
	.message h2 {
		padding: 20px 0 30px 0;
	}
	.container form {
		width: 80%;
		margin: auto;
	}
	caption {
		font-style: 200px; 
		font-weight: bold; 
		margin: 30px;
		font-size: large;
		caption-side: top;
		text-align: center;
		
	}
	table {
		width: 100%;
		margin-top: 50px;
	}
	th, td {
		padding: 10px;
		text-align: left;
		dispaly: block;
	}
	table:nth-child(1) tr:first-child th p {
		display: inline-block;
		padding-left: 20px;
	}
	.title th, .order_list td {
		text-align: center;
		border-bottom: 1px solid #444444;
	}
	.title th:nth-child(1), .order_list td:nth-child(1) {
		width: 55%;
	}
	.order_list td:nth-child(1) {
		text-align: left;
	}
	.title th:nth-child(2), .order_list td:nth-child(2) {
		width: 10%;
	}
	.title th:nth-child(3), .order_list td:nth-child(3) {
		width: 15%;
	}
	.title th:nth-child(3), .order_list td:nth-child(3) {
		width: 20%;
	}
	table:nth-child(1) tr:last-child td {
		text-align: right;
		padding-right: 20px;
		padding-top: 20px;
	}
	
	table:nth-child(2) tr td:first-child {
		width: 15%;
	}
	
	.buttonBuy{
		width: 90px;
	    background-color: #646464;
	    border: none;
	    color: #fff;
	    padding: 6px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 15px;
	    margin: 10px;
	    cursor: pointer;
		border-radius: 10px;
	}
</style>
<body>
	<%@include file = "header.jsp" %>
	<div class="container_wrap">
		<div class="container">
			<div class="message">
				<h2>결제 완료</h2>
				<p>
					구매해주셔서 감사합니다.<br>
					고객님께서 제품을 빠르게 받으실 수 있도록 노력하겠습니다.<br>
					주문 취소는 구매 당일만 가능하오니, 이 점 양해 부탁드립니다.
				</p>
			</div>
			<form action="Cancel_View.do">
				<table border="0">
					<caption>주문 상세 내역</caption>
					<tr>
						<th colspan="4">주문번호 <p>${bNumber }</p></th>
					</tr>
					<tr class="title">
						<th>상품명</th>
						<th>수량</th>
						<th>유통기한</th>
						<th>가격</th>
					</tr>
					<c:set var="SUM" value="0" />
					<c:forEach items="${BUYCONFIRM }" var="dto">
					<tr class="order_list">	
						<td>${dto.pName }</td>
						<td>${dto.bQuantity }</td>
						<td>${dto.pExpirationDate }</td>
						<td>${dto.pPriceDC * dto.bQuantity }</td>
					</tr>
					<c:set var="SUM" value="${SUM + (dto.pPriceDC * dto.bQuantity)}" />
					</c:forEach>
					<tr>
						<td colspan="4">총 결제 금액 : ${SUM } 원</td>
					</tr>
				</table>
				<table border="0">
					<caption>배송 정보</caption>
					<tr>		
						<td>수령인</td>
						<td>${DELIVERY.bRecName }</td>
					</tr>
					<tr>		
						<td>배송지</td>
						<td>${DELIVERY.bRecPostalCode }<br>
							${DELIVERY.bRecAddress1 } ${DELIVERY.bRecAddress2 }
					</tr>
					<tr>		
						<td>전화번호</td>
						<td>${DELIVERY.bRecTel }</td>
					</tr>
					<tr>		
						<td>남기실 말씀</td>
						<td>${DELIVERY.bRecContent }</td>
					</tr>
				</table>
				<input class="buttonBuy" type="submit" value="취소하기" style="margin-left: -150px; margin-top: 50px;">
			</form> 
			<form action="main.do" method="post">
				<input class="buttonBuy" type="submit" value="메인으로 이동" style="position:absolute; margin-left: 70px; margin-top: -41px;">
			</form>			
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>