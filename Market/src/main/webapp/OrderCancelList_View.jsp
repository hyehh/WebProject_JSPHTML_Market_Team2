<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.23 조혜지 주문취소목록 view -->
<title>주문취소목록</title>
</head>
<style>
	/* .container_wrap {
		display: block;
	} */
	caption {
		font-style: 200px; font-weight: bold; margin: 50px;
		font-size: x-large;
	}
	.table {
		margin: auto;
		border-top: 1px solid #444444;
		border-collapse: collapse;
	}

	.table, th, td {
		border-bottom: 1px solid #444444;
		padding: 10px;
		text-align: center;
	}
</style>
<body>
	<%@include file = "header.jsp" %>
	<%@include file = "LeftMenuBar.jsp" %>
	<div class="container">
		<table border="0">
			<caption>주문취소목록</caption>
				<c:choose>			
				<c:when test="${empty CANCEL}">
					<tr>
						<td>취소하신 상품이 존재하지 않습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<th>주문번호</th>
						<th>주문일</th>
						<th>취소일</th>
						<th>상품명</th>
						<th>수량</th>
					</tr>
				<c:forEach items="${CANCEL }" var="dto">
					<tr>
						<td>${dto.bNumber }</td>
						<td>${dto.bBuyDate }</td>
						<td>${dto.bBuyCancelDate }</td>
						<td>${dto.pName }</td>
						<td>${dto.bQuantity }</td>
					</tr>
				</c:forEach>
					<tr>
			         <td align="center" colspan="10">
			         	[<a href="OrderCancelList_View.do"> ◀◀ </a>] 
			         	[<a href="OrderCancelList_View.do?pg=${FROMPAGE -1 }">◀</a>] 
			         	<c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
			         	<a href="OrderCancelList_View.do?pg=${ftp.count}">[ ${ftp.count } ]</a>
		                </c:forEach>
		                [<a href="OrderCancelList_View.do?pg=${TOPAGE }">▶</a>] 
		                [<a href="OrderCancelList_View.do?pg=${ALLPAGE }">▶▶</a>]
			         </td>
		    		</tr>
				</c:otherwise>
				</c:choose>
		</table>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>