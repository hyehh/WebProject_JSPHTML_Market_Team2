<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style type="text/css">
	.search_wrap {
		background-color: #FAFAFA;
	}
	.img {
		width: 1000px;
		height: 500px;
		margin: auto;
		background-color: gray;
	}
	
	/* 메인 상품 노출 */
	.search_item {
		width: 1000px;
		height: 1000px;
		margin: auto;
	}
	.items {
		margin: 10px;
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
	
	<div class="search_wrap">
		<!-- 검색 상품 리스트 -->
		<div class="search_item">
			<div class="items">
				<ul>
					<c:forEach items="${search_list }" var="dtoSearch">
					<a href="product.do?pCode=${dtoSearch.pCode }">
						<li>
							<div class="item_img">
								<img alt="${dtoSearch.pName } 메인 이미지" src="resources/img/main_pCode${dtoSearch.pCode }.jpg">
							</div>
							<div class="item_info">
								<p>${dtoSearch.pName }</p>
								<p>유통기한 ${dtoSearch.pExpirationDate }</p>
								<p>${dtoSearch.pPriceDC }원 <strong class="discount">${dtoSearch.pDiscount }%</strong>
								<p>${dtoSearch.star } (${dtoSearch.pCount })</p>
							</div>
						</li>
					</a>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- //검색 임박 상품 리스트 -->
	</div>
	
	<!-- footer -->
	<%@ include file="footer.jsp" %>
	<!-- //footer -->

</body>
</html>