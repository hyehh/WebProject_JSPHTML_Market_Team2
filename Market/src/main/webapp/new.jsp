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
	.new_wrap {
		background-color: #FAFAFA;
		padding-bottom: 30px;
	}
	.img {
		width: 1000px;
		height: 500px;
		margin: auto;
		background-color: gray;
	}
	
	/* 메인 상품 노출 */
	.new_item {
		background-color: #fff;
		width: 1000px;
		margin: auto;
		margin-top: 30px;
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
	
	<div class="new_wrap">
		<!-- 이미지 배너 -->
		<div class="img">
			new 상품 이미지 배너
		</div>
		<!-- //이미지 배너 -->
		
		<!-- new 상품 리스트 -->
		<div class="new_item">
			<div class="items">
				<ul>
					<c:forEach items="${new_list }" var="dtoNew">
					<li>
						<a href="product.do?pCode=${dtoNew.pCode }">
							<div class="item_img">
								<img alt="${dtoNew.pName } 메인 이미지" src="${dtoNew.pFilePath }">
							</div>
							<div class="item_info">
								<p>${dtoNew.pName }</p>
								<p>유통기한 ${dtoNew.pExpirationDate }</p>
								<p>${dtoNew.pPriceDC }원 <strong class="discount">${dtoNew.pDiscount }%</strong>
								<p>${dtoNew.star } (${dtoNew.pCount })</p>
							</div>
						</a>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- //new 임박 상품 리스트 -->
	</div>
	
	<!-- footer -->
	<%@ include file="footer.jsp" %>
	<!-- //footer -->

</body>
</html>