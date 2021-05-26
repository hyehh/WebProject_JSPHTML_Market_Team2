<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송관리</title>
<link rel="stylesheet" href="css.css">
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
		padding: 50px 0;
	}
	h2 {
		margin-bottom: 30px;
	}
	.table tr:nth-child(2) td:nth-child(2) {
		border-bottom: none;
	}
	.table tr:nth-child(2) td:nth-child(2) input {
		margin-left: 10px;
	}
</style>
<body>
	<%@include file = "headerSeller.jsp" %>
	<div class="container_wrap">
		<div class="container">
			<h2 align="center">배송 관리</h2>
			<form action="deliverySearchList.do" method = "post">
				<table class="table">
					<tr>
						<td>배송현황</td>
						<td>총 건수 <a href = "DeliveryList.do">${DELIVERYALL }</a>건</td>
						<td>배송완료 <a href = "DeliveryEnd.do">${DELIVERYEND }</a>건</td>
						<td>배송중 <a href = "DeliveryING.do">${DELIVERYING }</a>건</td>
					</tr>
					<tr>
						<td rowspan="2">검색 필터</td>
						<td colspan="2" style="margin: auto;">
							<input type = "radio" name = "search" value = "allshow" checked="checked"> 전체검색
							<input type = "radio" name = "search" value = " where D.bNumber"> 주문번호
							<input type = "radio" name = "search" value = " where D.dCondition"> 배송현황
						</td>
					</tr>
					<tr>
		 				<td colspan="2"><input type = "text" name = "searchTxt" size = "30"></td>
						<td rowspan="2"><input type = "submit" value = "검색" class="button button5" style="zoom: 0.3;"></td>
					</tr>
				</table>
			</form>
			<br>
			<br>
			<br>
			<br>
			<h2 align="center">배송 목록</h2>
			<table class="table">
				<tr >
					<th>주문번호</th>		
					<th>상품종류</th>		
					<th>상품명</th>		
					<th>배송현황</th>		
				</tr>
				<c:forEach items="${list}" var="dto">
				<tr class="value" onclick="location.href='selectOrderPage.do?bNumber=${dto.bNumber }'">
					<td>${dto.bNumber }</td>
					<td>${dto.pCategory }</td>
					<td>${dto.pName }</td>
					<td>${dto.dCondition }</td>
				</tr>
				</c:forEach>
			</table>
			<br>
			<table class="table">
				<tr>
					<td align="center">
				        [<a href="DeliveryList.do"> ◀◀ </a>] 
				        [<a href="DeliveryList.do?pg=${FROMPAGE }">◀</a>]
				        <c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
				        <a href="DeliveryList.do?pg=${ftp.count}">[ ${ftp.count } ]</a>
				        </c:forEach>
				        [<a href="DeliveryList.do?pg=${TOPAGE }">▶</a>] 
				        [<a href="DeliveryList.do?pg=${ALLPAGE }">▶▶</a>]
					</td>
				</tr>
			</table>
		</div>
	</div>
	<%@ include file="footerSeller.jsp" %>
</body>
</html>