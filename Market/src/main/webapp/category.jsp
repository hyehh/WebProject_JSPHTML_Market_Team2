<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String pCategory = request.getParameter("pCategory");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style type="text/css">

	.category_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}

	/* 메인 상품 노출 */
	.category_item {
		width: 1000px;
		margin: auto;
		background-color: #fff;
	}
	.title {
		width: 100%;
		height: 50px;
		margin: auto;
		line-height: 50px;
	}
	.items ul {
		overflow: hidden;
	}
	.items ul li {
		width: 25%;
		height: 325px;
		float: left;
	}
	.item_img {
		margin: 10px;
		height: 225px;
		background-color: #fff;
	}
	.item_img img {
		border: solid 1px;
		width: 100%;
		height: 100%;
	}
	.item_info {
		border: solid 1px;
		margin: 10px;
		height: 70px;
		background-color: #fff;
	}
	.discount {
		color: red;
	}
</style>

<body>
	<!-- header -->
	<%@ include file="header.jsp" %>
	<!-- //header -->
	
	<div class="category_wrap">
		<!-- 상품 리스트 -->
		<div class="category_item">
		
			<div class="title">
				<h2><%=pCategory %></h2>
			</div>
			
			<div class="items">
				<ul>
					<c:forEach items="${category_list }" var="dtoCategory">
					<a href="product.do?pCode=${dtoCategory.pCode }">
						<li>
							<div class="item_img">
								<img alt="${dtoCategory.pName } 메인 이미지" src="${dtoCategory.pFilePath }">
							</div>
							<div class="item_info">
								<p>${dtoCategory.pName }</p>
								<p>유통기한 ${dtoCategory.pExpirationDate }</p>
								<p>${dtoCategory.pPriceDC }원 <strong class="discount">${dtoCategory.pDiscount }%</strong>
								<p>${dtoCategory.star } (${dtoCategory.pCount })</p>
							</div>
						</li>
					</a>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- //상품 리스트 -->
	</div>
	
	<!-- footer -->
	<%@ include file="footer.jsp" %>
	<!-- //footer -->

</body>
</html>