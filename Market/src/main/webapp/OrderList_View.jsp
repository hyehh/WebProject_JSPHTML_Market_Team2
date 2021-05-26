<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.18 조혜지 주문목록/배송조회 view -->
<title>주문목록/배송조회</title>
</head>
<script type="text/javascript">
	function orderCancel() {

			alert("취소 처리 되었습니다.");
		
	}
</script> 
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
		width: 95%;
		margin: auto;
	}
	table {
		width: 100%;
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
	.title th, .order_product td {
		border-bottom: 1px solid #444444;
	}
	/* table 넓이 조정 */
	.title th:nth-child(1), .order_product td:nth-child(1) {
		width: 130px;
	}
	.title th:nth-child(2), .order_product td:nth-child(2) {
		width: 100px;
	}
	.order_product td:nth-child(3) {
		text-align: left;
	}
	.title th:nth-child(4), .order_product td:nth-child(4) {
		width: 35px;
	}
	.title th:nth-child(5), .order_product td:nth-child(5) {
		width: 70px;
	}
	.title th:nth-child(6), .order_product td:nth-child(6) {
		width: 90px;
	}
	
	table tr:last-child td {
		padding-top: 30px;
		padding-bottom: 50px;
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
			<!-- <h3>주문목록/배송조회</h3> -->
			<form method="post">
				<table border="0">
					<c:choose>			
						<c:when test="${empty ORDER}">
						<caption style="width: 950px; text-align: center; margin: 30px 0; padding-top: 80px;">주문목록/배송조회</caption>
						<tr style="width: 950px; text-align: center; margin: 30px 0;">
							<td>과거에 주문한 상품이 존재하지 않습니다.</td>
						</tr>
						</c:when>
						<c:otherwise>
						<caption>주문목록/배송조회</caption>
						<tr class="title">
							<th>주문번호</th>
							<th>주문일</th>
							<th>상품명</th>
							<th>수량</th>
							<th>배송현황</th>
							<th></th>
						</tr>
						<c:forEach items="${ORDER }" var="dto">
						<tr class="order_product">
							<td><input type="text" name="bNumber" readonly="readonly" value="${dto.bNumber }"
							style="color:black; background-color=white; padding-top:2px;
							border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:none;"></td>
							<td>${dto.bBuyDate }</td>
							<td>${dto.pName }</td>
							<td>${dto.bQuantity }</td>
							<td>${dto.dCondition }</td>
	 		 					<c:choose>
									<c:when test ="${dto.datediff == 0 }">
										<td><input class="buttonRev" type="submit" value="취소" formaction="OrderListCancel_View.do?bNumber=${dto.bNumber }" onclick="orderCancel()"></td>
									</c:when>
			  						<c:otherwise>
										<td align="right">취소 불가</td>
									</c:otherwise>  
								</c:choose> 
						</tr>
						</c:forEach>
						<tr>
				         <td align="center" colspan="10">
				         	[<a href="OrderList_View.do"> ◀◀ </a>] 
				         	[<a href="OrderList_View.do?pg=${FROMPAGE -1 }">◀</a>] 
				         	<c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
				         	<a href="OrderList_View.do?pg=${ftp.count}">[ ${ftp.count } ]</a>
			                </c:forEach>
			                [<a href="OrderList_View.do?pg=${TOPAGE }">▶</a>] 
			                [<a href="OrderList_View.do?pg=${ALLPAGE }">▶▶</a>]
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