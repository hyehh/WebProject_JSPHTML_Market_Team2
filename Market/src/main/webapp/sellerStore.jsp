<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 스토어</title>
<link rel="stylesheet" href="sellerStore.css">
</head>
<style>
	.container_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.container {
		width: 1000px;
		margin: auto;
		background-color: none;
	}
</style>
<body>
	<%@include file = "headerSeller.jsp" %>
	<div class="container_wrap">
		<div class="container" style="background-color: none;">
			<div class = "mainTable" style="width: 800px; margin: auto;">
				<table style="float: left">
					<tr>
						<th colspan="2">상품관리</th>
					</tr>
					<tr>
						<td>총 상품</td>
						<td><a href="productlist.do">${SALESCOUNT } </a>건</td>
					</tr>
					<tr>
						<td>판매중</td>
						<td><a href="searchTure.do">${SALESTURE } </a>건</td>
					</tr>
					<tr>
						<td>품절</td>
						<td><a href="searchFalse.do">${SALESFALSE } </a>건
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<th colspan="2">주문관리</th>
					</tr>
					<tr style="background-color: #f2f2f2;">
						<td>총 주문</td>
						<td><a href="buyList.do">${BUYCOUNT } </a>건</td>
					</tr>
					<tr>
						<td>결제완료</td>
						<td><a href="buyTure.do">${BUYTRUE } </a>건</td>
					</tr>
					<tr style="background-color: #f2f2f2;">
						<td>취소</td>
						<td><a href="buyCancel.do">${BUYCANCEL } </a>건
						</td>
					</tr>
				</table>
				<table style="float: left">
					<tr>
						<th colspan="2">배송관리</th>
					</tr>
					<tr style="background-color: #f2f2f2;">
						<td>총 건수</td>
						<td><a href="DeliveryList.do">${DELIVERYALL } </a>건</td>
					</tr>
					<tr>
						<td>배송완료</td>
						<td><a href="DeliveryEnd.do">${DELIVERYEND } </a>건</td>
					</tr>
					<tr style="background-color: #f2f2f2;">
						<td>취소</td>
						<td><a href="DeliveryING.do">${DELIVERYING } </a>건
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<th colspan="2">문의관리</th>
					</tr>
					<tr style="background-color: #f2f2f2;">
						<td>총 건수</td>
						<td><a href="QnAList.do">${allQnACount } </a>건</td>
					</tr>
					<tr>
						<td>배송완료</td>
						<td><a href="QnATure.do">${QnATure } </a>건</td>
					</tr>
					<tr style="background-color: #f2f2f2;">
						<td>취소</td>
						<td><a href="QnAFalse.do">${QnAFalse } </a>건
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="footerSeller.jsp" %>
</body>
</html>