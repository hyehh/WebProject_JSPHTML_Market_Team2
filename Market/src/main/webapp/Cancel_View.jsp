<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.20 조혜지 결제 완료 후 취소버튼 클릭 시 view -->
<title>취소 완료</title>
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
		<h2>취소 완료</h2>
		<p>정상적으로 취소 처리되었습니다.<br>다른 상품들을 구경해보세요.</p>
		<form action="Product_View.do" method="post">
			<table border="0">
				<caption>주문 취소 내역</caption>
					<tr>
						<th>주문번호</th>
						<th>${bNumber }</th>
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
						<td colspan="2"></td>
						<td colspan="10">총 환불 금액 : ${SUM } 원</td>
					</tr>
					<tr>
						<td colspan="3"></td>
						<td align="left"><input class="buttonBuy" type="submit" style="float:left" value="메인으로 이동"></td>
					</tr>
			</table>
		</form>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>