<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의내역</title>
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
</style>
<body>
	<%@include file = "headerSeller.jsp" %>
	<div class="container_wrap">
		<div class="container">
			<h2 align="center">문의 내역</h2>
			<form action="searchQnAList.do" method = "post">
				<table class="table">
					<tr>
						<td>문의현황</td>
						<td>총 문의 <a href = "QnAList.do">${allQnACount }</a>건</td>
						<td>답변완료 <a href = "QnATure.do">${QnATure }</a>건</td>
						<td>미답변 <a href = "QnAFalse.do">${QnAFalse }</a>건</td>
						<td></td>
					</tr>
					<tr>
						<td>검색</td>
						<td>주문번호</td>
						<td colspan="2"><input type = "text" name = "searchTxt" size = "30"></td>
						<td><input type = "submit" value = "검색" class="button button5" style="zoom: 0.3;"></td>
					</tr>
				</table>
			</form>
			<br>
			<br>
			<br>
			<br>
			<h2 align="center">문의 목록</h2>
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
				        <a href="QnATure.do"><button type="button" class="buttonNum buttonNum">◀◀</button></a>
				        <a href="QnATure.do?pg=${FROMPAGE }"><button type="button" class="buttonNum buttonNum">◀</button></a>
			        	<c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
		        		<a href="QnATure.do?pg=${ftp.count}"><button type="button" class="buttonNum buttonNum">${ftp.count }</button></a>
			       		</c:forEach>
				        <a href="QnATure.do?pg=${TOPAGE }"><button type="button" class="buttonNum buttonNum">▶</button></a>
				        <a href="QnATure.do?pg=${ALLPAGE }"><button type="button" class="buttonNum buttonNum">▶▶</button></a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<%@ include file="footerSeller.jsp" %>
</body>
</html>