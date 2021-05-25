<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.16 조혜지 장바구니 view -->
<title>장바구니</title>
</head>
<script type="text/javascript">
	function allCancel() {

			alert("장바구니의 전체 상품이 삭제되었습니다.");
		
	}
</script> 
<script type="text/javascript">
	function Cancel() {

			alert("선택하신 상품이 삭제되었습니다.");
		
	}
</script> 
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
	
	.buttonCart{
		width: 90px;
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
	<%@include file = "LeftMenuBar.jsp" %>
	<div class="container">
		<form>
			<table border="0">
				<caption>장바구니</caption>
					<c:choose>			
						<c:when test="${empty CART}">
						<tr>
							<td>장바구니 목록이 비었습니다.</td>
						</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th></th>
								<th>상품명</th>
								<th>수량</th>
								<th>유통기한</th>
								<th>가격</th>
							</tr>
			 			<%-- <c:set var="SUM" value="0" />	 --%>	
						<c:forEach items="${CART }" var="dto">
							<tr>
								<td><input type="checkbox" name="selectedProduct" value = "${dto.wId }" checked="checked">
								<td>${dto.pName }</td>
								<td>${dto.wQuantity }</td>
								<td>${dto.pExpirationDate }</td>
								<td>${dto.pPriceDC * dto.wQuantity }</td>
							</tr>
						<c:set var="SUM" value="${SUM + (dto.pPriceDC * dto.wQuantity)}" />
						</c:forEach>
							<tr>
						        <td align="center" colspan="10">
						         	[<a href="CustomerCart_View.do"> ◀◀ </a>] 
						         	[<a href="CustomerCart_View.do?pg=${FROMPAGE -1 }">◀</a>] 
						         	<c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
						         	<a href="CustomerCart_View.do?pg=${ftp.count}">[ ${ftp.count } ]</a>
					                </c:forEach>
					                [<a href="CustomerCart_View.do?pg=${TOPAGE }">▶</a>] 
					                [<a href="CustomerCart_View.do?pg=${ALLPAGE }">▶▶</a>]
						        </td>
			    			</tr>
							<tr>
								<td colspan="2"></td>
								<td colspan="3">장바구니 총 금액 : ${SUM } 원</td>
							</tr>
							<tr>
								<td colspan="3"><input type="submit" class="buttonCart" value="선택 상품 주문" formaction="Buy_View.do?selectedProduct=${dto.wId }"></td>
								<td colspan="3"><input type="submit" class="buttonCart" value="선택 상품 삭제" formaction="CartDelete.do?selectedProduct=${dto.wId }" onclick="Cancel()"></td>
								</form>
							</tr>
							<tr>
								<form action="AllBuy_View.do" method="post">
								<td colspan="3"><input type="submit" class="buttonCart" value="전체 상품 주문"></td>
								</form>	
								<form action="CartAllDelete.do" method="post">
								<td colspan="3"><input type="submit" class="buttonCart" value="전체 상품 삭제" onclick="allCancel()"></td>
								</form>
							</tr>
						</c:otherwise>
					</c:choose>
	</table>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>