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
	.deadline_wrap {
		background-color: #FAFAFA;
		padding-bottom: 30px;
	}
	.img {
		width: 1000px;
		height: 450px;
		margin: auto;
	}
	
	/* 메인 상품 노출 */
	.deadline_item {
		width: 1000px;
		margin: auto;
		margin-top: 30px;
		background-color: #fff;
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
		overflow: hidden;
	}
	.item_img img {
		width: 100%;
	}
	.item_info {
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
	
	<div class="deadline_wrap">
		<!-- 이미지 배너 -->
		<div class="img">
			<img alt="" src="resources/img/banner_deadline.png">
		</div>
		<!-- //이미지 배너 -->
		
		<!-- 마감 임박 상품 리스트 -->
		<div class="deadline_item">
			<div class="items">
				<ul>
					<c:forEach items="${deadline_list }" var="dtoDeadline">
					<li>
						<a href="product.do?pCode=${dtoDeadline.pCode }">
							<div class="item_img">
								<img alt="${dtoDeadline.pName } 메인 이미지" src="${dtoDeadline.pFilePath }">
							</div>
							<div class="item_info">
								<p>${dtoDeadline.pName }</p>
								<p>유통기한 ${dtoDeadline.pExpirationDate }</p>
								<p>${dtoDeadline.pPriceDC }원 <strong class="discount">${dtoDeadline.pDiscount }%</strong>
								<p>${dtoDeadline.star } (${dtoDeadline.pCount })</p>
							</div>
						</a>

					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- //마감 임박 상품 리스트 -->
	</div>
	
	<!-- footer -->
	<%@ include file="footer.jsp" %>
	<!-- //footer -->
	
</body>
</html>