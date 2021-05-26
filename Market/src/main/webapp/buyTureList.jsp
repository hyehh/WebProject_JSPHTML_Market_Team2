<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문관리</title>
<link rel="stylesheet" href="css.css">
</head>
<body>
	<%@include file = "headerSeller.jsp" %>
	<br>
	<br>
	<br>
	<h2 align="center">주문 관리</h2>
	<br>
	<form action="buySearchList.do" method = "post">
		<table class="table">
			<tr>
				<td>주문현황</td>
				<td>총 주문 <a href = "buyList.do">${BUYCOUNT }</a>건</td>
				<td>결제완료 <a href = "buyTure.do">${BUYTRUE }</a>건</td>
				<td>취소 <a href = "buyCancel.do">${BUYCANCEL }</a>건</td>
			</tr>
			<tr>
				<td>검색 필터</td>
				<td><select name = "search">
				<option value = " and P.pName">상품명</option>
				<option value = " and P.pCategory">종류명</option>
				<option value = " and B.bNumber">주문번호</option>
				</select></td>
				<td colspan="2"><input type = "text" name = "searchTxt" size = "30"></td>
				<td><input type = "submit" value = "검색" class="button button5"></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
	<br>
	<h2 align="center">주문 목록</h2>
	<br>
	<table class="table">
		<tr>
			<th>주문번호</th>		
			<th>상품 종류</th>		
			<th>상품명</th>		
			<th>주문일</th>		
			<th>주문현황</th>		
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr class="value" onclick="location.href='selectBuy.do?bNumber=${dto.bNumber }'"><!-- B.bNumber, P.pCategory, p.pName, B.bState -->
			<td>${dto.bNumber }</td>
			<td>${dto.pCategory }</td>
			<td>${dto.pName }</td>
			<td>${dto.bBuyDate }</td>
			<td>${dto.buyCancel }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<table class="table">
		<tr>
			<td align="center">
			
		<a href="buyTure.do"><button type="button" class="buttonNum buttonNum">◀◀</button></a>
        <a href="buyTure.do?pg=${FROMPAGE }"><button type="button" class="buttonNum buttonNum">◀</button></a>
        	<c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
        		<a href="buyTure.do?pg=${ftp.count}"><button type="button" class="buttonNum buttonNum">${ftp.count }</button></a>
       		</c:forEach>
        <a href="buyTure.do?pg=${TOPAGE }"><button type="button" class="buttonNum buttonNum">▶</button></a>
        <a href="buyTuret.do?pg=${ALLPAGE }"><button type="button" class="buttonNum buttonNum">▶▶</button></a>
			

			</td>
		</tr>
	</table>
	<footer>
		<div class="content">
			<div>
				주식회사 Team2 | 주소: 서울특별시 서초구 서초4동 강남대로 441 | 대표: 김철수 | 사업자등록번호: 000-00-00000 | 팩스번호: 00-000-0000 | 메일: team2@gamil.com
			</div>
		</div>
	</footer>
</body>
</html>