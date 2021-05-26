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
	header {
		display: block;
	}
	.container {
		display: inline-block;
		width: 800px;
 		/* padding: 50px 800px; */
 		/* margin: 50px 800px; */
	}
	caption {
		font-style: 200px; font-weight: bold; margin: 15px;
		font-size: large;
		caption-side: top;
		text-align: center;
	}
	.table {
		margin: 100px;
		border-collapse: collapse;
		padding: 50px;
	}

	.table, th, td {
		padding: 10px;
		text-align: left;
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
	<div class="container">
		<h2>결제 완료</h2>
		<p>구매해주셔서 감사합니다.<br>고객님께서 제품을 빠르게 받으실 수 있도록 노력하겠습니다.<br>주문 취소는 구매 당일만 가능하오니, 이 점 양해 부탁드립니다.</p>
		<form action="Cancel_View.do">
			<table border="0">
				<caption>주문 상세 내역</caption>
					<tr>
						<th>주문번호</th>
						<th colspan="6">${bNumber }</th>
					</tr>
					<tr>
						<th>상품명</th>
						<th>수량</th>
						<th>유통기한</th>
						<th>가격</th>
					</tr>
						<c:set var="SUM" value="0" />
						<c:forEach items="${BUYCONFIRM }" var="dto">
					<tr>	
						<td>${dto.pName }</td>
						<td>${dto.bQuantity }</td>
						<td>${dto.pExpirationDate }</td>
						<td>${dto.pPriceDC * dto.bQuantity }</td>
					</tr>
					<c:set var="SUM" value="${SUM + (dto.pPriceDC * dto.bQuantity)}" />
					</c:forEach>
					<tr>
						<td colspan="10">총 결제 금액 : ${SUM } 원</td>
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
					<tr>
						<td align="right"><input class="buttonBuy" type="submit" value="취소하기"></td>
						</form> 
						<form action="main.do" method="post">
						<td align="right"><input class="buttonBuy" type="submit" value="메인으로 이동"></td>
						</form>			
					</tr>
			</table>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>