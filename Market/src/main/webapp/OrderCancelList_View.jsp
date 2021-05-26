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
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.container {
		width: 1000px;
		margin: auto;
		background-color: #fff;
	} */
	.table_content {
		min-height: 350px;
		width: 80%;
		margin: auto;
	}
	caption {
		font-style: 200px; font-weight: bold; margin: 50px;
		font-size: x-large;
	}
	th, td {
		padding: 10px;
		text-align: center;
	}
	/* 선 */
	.title th, .cancel_product td {
		border-bottom: 1px solid #444444;
	}
	/* table 넓이 조정 */
	.title th:nth-child(1), .cancel_product td:nth-child(1) {
		width: 130px;
	}
	.title th:nth-child(2), .cancel_product td:nth-child(2) {
		width: 100px;
	}
	.title th:nth-child(2), .cancel_product td:nth-child(2) {
		width: 100px;
	}
	.title th:nth-child(4), .cancel_product td:nth-child(4) {
		width: 338px;
	}
	.cancel_product td:nth-child(4) {
		text-align: left;
	}
	.title th:nth-child(5), .cancel_product td:nth-child(5) {
		width: 35px;
	}
	
	table tr:last-child td {
		padding-top: 30px;
		padding-bottom: 50px;
	}
</style>
<body>
	<%@include file = "header.jsp" %>
	<div class="container_wrap"> 
		<%@include file = "LeftMenuBar.jsp" %>
		<div class="container">
			<div class="table_content">
				<table>
					<c:choose>			
					<c:when test="${empty CANCEL}">
					<caption style="width: 800px; text-align: center; margin: 30px 0; padding-top: 80px;">주문취소목록</caption>
					<tr style="width: 950px; text-align: center; margin: 30px 0;">
						<td>취소하신 상품이 존재하지 않습니다.</td>
					</tr>
					</c:when>
					<c:otherwise>
					<caption>주문취소목록</caption>
					<tr class="title">
						<th>주문번호</th>
						<th>주문일</th>
						<th>취소일</th>
						<th>상품명</th>
						<th>수량</th>
					</tr>
					<c:forEach items="${CANCEL }" var="dto">
					<tr class="cancel_product">
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
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>