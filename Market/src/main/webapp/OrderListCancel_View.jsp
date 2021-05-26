<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.20 조혜지 주문목록/배송조회 취소버튼 클릭 시 view -->
<title>취소 완료</title>
</head>
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
	.container {
		text-align: center;
		padding: 50px 0;
	}
	.message {
		padding-bottom: 50px;
	}
	.message h2 {
		padding: 20px 0 30px 0;
	}
	.container form {
		width: 80%;
		margin: auto;
	}
	caption {
		font-style: 200px; font-weight: bold; margin: 15px;
		font-size: large;
		caption-side: top;
		text-align: center;
	}
	th, td {
		padding: 10px;
		text-align: left;
	}
	table tr:first-child th p {
		display: inline-block;
		padding-left: 20px;
	}
	.title th, .cancel_list td {
		text-align: center;
		border-bottom: 1px solid #444444;
	}
	.title th:nth-child(1), .cancel_list td:nth-child(1) {
		width: 350px;
	}
	.cancel_list td:nth-child(1) {
		text-align: left;
	}
	.title th:nth-child(2), .cancel_list td:nth-child(2) {
		width: 80px;
	}
	.title th:nth-child(3), .cancel_list td:nth-child(3) {
		width: 130px;
	}
	.title th:nth-child(4), .cancel_list td:nth-child(4) {
		width: 130px;
	}
	table tr:last-child td {
		text-align: right;
		padding-top: 30px;
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
	    margin: auto;
	    margin-top: 30px;
	    cursor: pointer;
		border-radius: 10px;
	}
</style>
<body>
	<%@include file = "header.jsp" %>
	<div class="container_wrap">
		<%@include file = "LeftMenuBar.jsp" %>
		<div class="container">
			<div class="message">
				<h2>취소 완료</h2>
				<p>정상적으로 취소 처리되었습니다.<br>다른 상품들을 구경해보세요.</p>
			</div>
			<form action="Product_View.do" method="post">
				<table border="0">
					<caption>주문 취소 내역</caption>
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
					<tr class="cancel_list">	
						<td>${dto.pName }</td>
						<td>${dto.bQuantity }</td>
						<td>${dto.pExpirationDate }</td>
						<td>${dto.pPriceDC * dto.bQuantity }</td>
					</tr>
						<c:set var="SUM" value="${SUM + (dto.pPriceDC * dto.bQuantity)}" />
						</c:forEach>
					<tr>
						<td colspan="4">총 환불 금액 : ${SUM } 원</td>
					</tr>
				</table>
				<input class="buttonBuy" type="submit" value="메인으로 이동">
			</form>
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>