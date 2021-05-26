<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의내역</title>
<link rel="stylesheet" href="selectPage.css">
</head>
<body>
	<%@include file = "headerSeller.jsp" %>
	<br>
	<br>
	<br>
	<h2 align="center">문의 내역</h2>
	<br>
	<form action="searchQnAList.do" method = "post">
		<table class="table">
			<tr>
				<td>문의현황</td>
				<td>총 문의 <a href = "QnAList.do">${allQnACount }</a>건</td>
				<td>답변완료 <a href = "QnATure.do">${QnATure }</a>건</td>
				<td>미답변 <a href = "QnAFalse.do">${QnAFalse }</a>건</td>
			</tr>
			<tr>
				<td>검색 ${searchtxt }</td>
				<td>주문번호</td>
				<td colspan="2"><input type = "text" name = "searchTxt" size = "30"
				value = "${searchtxt == null ? null : searchtxt }"></td>
				<td><input type = "submit" value = "검색" class="button button5"></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
	<br>
	<h2 align="center">문의 목록</h2>
	<br>
	<table class="table">
		<tr>
			<th>주문번호</th>		
			<th>문의 제목</th>		
			<th>등록일</th>		
			<th>답변상태</th>		
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr class="value" onclick="location.href='selectQnA.do?bNumber=${dto.bNumber }'"><!-- B.bNumber, P.pCategory, p.pName, B.bState -->
			<td>${dto.bNumber }</td>
			<td>${dto.qTitle }</td>
			<td>${dto.qAddDate }</td>
			<td>${dto.qAnswerStatus }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<table class="table">
		<tr>
			<td align="center">
        <a href="searchQnAList.do"><button type="button" class="buttonNum buttonNum">◀◀</button></a>
        <a href="searchQnAList.do?pg=${FROMPAGE }"><button type="button" class="buttonNum buttonNum">◀</button></a>
        	<c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
        		<a href="searchQnAList.do?pg=${FROMPAGE }"><button type="button" class="buttonNum buttonNum">${ftp.count }</button></a>
       		</c:forEach>
        <a href="searchQnAList.do?pg=${TOPAGE }"><button type="button" class="buttonNum buttonNum">▶</button></a>
        <a href="searchQnAList.do?pg=${ALLPAGE }"><button type="button" class="buttonNum buttonNum">▶▶</button></a>

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