<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.16 조혜지 리뷰 미작성 리스트 view -->
<title>리뷰 등록</title>
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
	.container form {
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
	.title th, .review_product td {
		border-bottom: 1px solid #444444;
	}
	/* table 넓이 조정 */
	.title th:nth-child(1), .review_product td:nth-child(1) {
		width: 130px;
	}
	.title th:nth-child(2), .review_product td:nth-child(2) {
		width: 120px;
	}
	.title th:nth-child(3), .review_product td:nth-child(3) {
		width: 170px;
	}
	.review_product td:nth-child(3) {
		text-align: left;
	}
	.title th:nth-child(4), .review_product td:nth-child(4) {
		width: 40px;
	}
	.title th:nth-child(5), .review_product td:nth-child(5) {
		width: 100px;
	}
	.title th:nth-child(6), .review_product td:nth-child(6) {
		width: 90px;
	}
	table tr:last-child td {
		padding-top: 30px;
		padding-bottom: 50px;
	}
	
	
	
	.bNumber {
		color:black;
		background-color:white; 
		padding-top:2px;
		border-width:2px; 
		border-color:gray; 
		border-top-style:none; 
		border-right-style:none; 
		border-left-style:none; 
		border-bottom-style:none;"
	}
	
	.buttonRev{
		width: 70px;
	    background-color: #828282;
	    border: none;
	    color: #fff;
	    padding: 2px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 13px;
	    margin: 10px;
	    cursor: pointer;
		border-radius: 10px;
	}
</style>
<body>
	<%@include file = "header.jsp" %>
	<div class="container_wrap">
		<%@include file = "LeftMenuBar.jsp" %>
		<div class="container">
			<form method="post">
				<table border="0">
					<c:choose>			
						<c:when test="${empty REVIEW}">
						<caption style="width: 800px; text-align: center; margin: 30px 0; padding-top: 80px;">리뷰등록</caption>
						<tr style="width: 800px; text-align: center; margin: 30px 0;">
							<td>리뷰를 등록할 제품이 존재하지 않습니다.</td>
						</tr>
						</c:when>
						<c:otherwise>
						<caption>리뷰등록</caption>
						<tr class="title">
							<th>주문번호</th>
							<th>주문일</th>
							<th>상품명</th>
							<th>수량</th>
							<th>가격</th>		
							<th>&nbsp;</th>
									
						</tr>
						<c:forEach items="${REVIEW }" var="dto">
						<tr class="review_product">
							<td><input class="bNumber" type="text" name="bNumber" readonly="readonly" value="${dto.bNumber }"></td>
							<td>${dto.bBuyDate }</td>
							<td>${dto.pName }</td>
							<td>${dto.bQuantity }</td>
							<td>${dto.pPriceDC * dto.bQuantity }</td>
							<td><input class="buttonRev" type="submit" value="리뷰 등록" formaction="ReviewRegistration_View.do?bNumber=${dto.bNumber }&pCode=${dto.pCode }"></td>
						</tr>
						<input type="hidden" name="pCode" value="${dto.pCode }">
						</c:forEach>
						<tr>
							<td align="center" colspan="6">
								[<a href="ReviewRegistrationList_View.do"> ◀◀ </a>] 
								[<a href="ReviewRegistrationList_View.do?pg=${FROMPAGE -1 }">◀</a>] 
								<c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
								<a href="ReviewRegistrationList_View.do?pg=${ftp.count}">[ ${ftp.count } ]</a>
								</c:forEach>
								[<a href="ReviewRegistrationList_View.do?pg=${TOPAGE }">▶</a>] 
								[<a href="ReviewRegistrationList_View.do?pg=${ALLPAGE }">▶▶</a>]
							</td>
		    		  	</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</form>
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>