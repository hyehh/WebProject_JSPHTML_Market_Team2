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
	.warp {
		background-color: #FAFAFA;
	}
	.img {
		width: 1000px;
		height: 450px;
		margin: auto;
	}
	.best_wrap {
		width: 1000px;
		height: 2790px;
		margin: auto;
	}
	.best_items {
		background-color: #fff;
		margin-top: 20px;
	}
	.best_title {
		width: 1000px;
		height: 50px;
		padding-top: 10px;
		padding-left: 30px;
		line-height: 50px;
	}
	.items {
		width: 100%;
		height: 325px;
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
		text-align: center;
	}
	.pName {
		font-size: 130%;
	}
	.discount {
		color: red;
	}
</style>

<body>
	<!-- header -->
	<%@ include file="header.jsp" %>
	<!-- //header -->
	
	<div class="warp">
		
		<!-- 베스트 이미지 베너 -->
		<div class="img">
			<img alt="" src="resources/img/banner_best.png">
		</div>
		
		<!-- 베스트 아이템 전체 -->
		<div class="best_wrap">
			<!-- 주방용품 -->
			<div class="best_items">
				<!-- 베스트 타이틀 -->
				<div class="best_title">
					<h2>주방용품</h2>
				</div>
				<!-- //베스트 타이틀 -->
				
				<!-- 베스트 아이템 -->
				<div class="items">
					<ul>
						<c:forEach items="${best_kitchen }" var="dtoKitchen">
						<a href="product.do?pCode=${dtoKitchen.pCode }">
							<li>
								<div class="item_img">
									<img alt="${dtoKitchen.pName } 메인 이미지" src="${dtoKitchen.pFilePath }">
								</div>
								<div class="item_info">
									<p class="pName">${dtoKitchen.pName }</p>
									<p>구매 수 ${dtoKitchen.pCount }</p>
									<p>${dtoKitchen.pPriceDC }원 <strong class="discount">${dtoKitchen.pDiscount }%</strong></p>
								</div>
							</li>
						</a>
						</c:forEach>
					</ul>
				</div>
				<!-- //베스트 아이템 -->
			</div>
			<!-- //주방용품 -->
			
			<!-- 세탁용품 -->
			<div class="best_items">
				<!-- 베스트 타이틀 -->
				<div class="best_title">
					<h2>세탁용품</h2>
				</div>
				<!-- //베스트 타이틀 -->
				
				<!-- 베스트 아이템 -->
				<div class="items">
					<ul>
						<c:forEach items="${best_washing }" var="dtoWashing">
						<a href="product.do?pCode=${dtoWashing.pCode }">
							<li>
								<div class="item_img">
									<img alt="${dtoWashing.pName } 메인 이미지" src="${dtoWashing.pFilePath }">
								</div>
								<div class="item_info">
									<p class="pName">${dtoWashing.pName }</p>
									<p>구매 수 ${dtoWashing.pCount }</p>
									<p>${dtoWashing.pPriceDC }원 <strong class="discount">${dtoWashing.pDiscount }%</strong></p>
								</div>
							</li>
						</a>
						</c:forEach>
					</ul>
				</div>
				<!-- //베스트 아이템 -->
			</div>
			<!-- //세탁용품 -->
			
			<!-- 청소용품 -->
			<div class="best_items">
				<!-- 베스트 타이틀 -->
				<div class="best_title">
					<h2>청소용품</h2>
				</div>
				<!-- //베스트 타이틀 -->
				
				<!-- 베스트 아이템 -->
				<div class="items">
					<ul>
						<c:forEach items="${best_cleaning }" var="dtoCleaning">
						<a href="product.do?pCode=${dtoCleaning.pCode }">
							<li>
								<div class="item_img">
									<img alt="${dtoCleaning.pName } 메인 이미지" src="${dtoCleaning.pFilePath }g">
								</div>
								<div class="item_info">
									<p class="pName">${dtoCleaning.pName }</p>
									<p>구매 수 ${dtoCleaning.pCount }</p>
									<p>${dtoCleaning.pPriceDC }원 <strong class="discount">${dtoCleaning.pDiscount }%</strong></p>
								</div>
							</li>
						</a>
						</c:forEach>
					</ul>
				</div>
				<!-- //베스트 아이템 -->
			</div>
			<!-- //청소용품 -->
			
			<!-- 인테리어소품 -->
			<div class="best_items">
				<!-- 베스트 타이틀 -->
				<div class="best_title">
					<h2>인테리어소품</h2>
				</div>
				<!-- //베스트 타이틀 -->
				
				<!-- 베스트 아이템 -->
				<div class="items">
					<ul>
						<c:forEach items="${best_interior }" var="dtoInterior">
						<a href="product.do?pCode=${dtoInterior.pCode }">
							<li>
								<div class="item_img">
									<img alt="${dtoInterior.pName } 메인 이미지" src="${dtoInterior.pFilePath }">
								</div>
								<div class="item_info">
									<p class="pName">${dtoInterior.pName }</p>
									<p>구매 수 ${dtoInterior.pCount }</p>
									<p>${dtoInterior.pPriceDC }원 <strong class="discount">${dtoInterior.pDiscount }%</strong></p>
								</div>
							</li>
						</a>
						</c:forEach>
					</ul>
				</div>
				<!-- //베스트 아이템 -->
			</div>
			<!-- //인테리어소품 -->
			
			<!-- 통조림 -->
			<div class="best_items">
				<!-- 베스트 타이틀 -->
				<div class="best_title">
					<h2>통조림</h2>
				</div>
				<!-- //베스트 타이틀 -->
				
				<!-- 베스트 아이템 -->
				<div class="items">
					<ul>
						<c:forEach items="${best_can }" var="dtoCan">
						<a href="product.do?pCode=${dtoCan.pCode }">
							<li>
								<div class="item_img">
									<img alt="${dtoCan.pName } 메인 이미지" src="${dtoCan.pFilePath }">
								</div>
								<div class="item_info">
									<p class="pName">${dtoCan.pName }</p>
									<p>구매 수 ${dtoCan.pCount }</p>
									<p>${dtoCan.pPriceDC }원 <strong class="discount">${dtoCan.pDiscount }%</strong></p>
								</div>
							</li>
						</a>
						</c:forEach>
					</ul>
				</div>
				<!-- //베스트 아이템 -->
			</div>
			<!-- //통조림 -->
			
			<!-- 냉동식품 -->
			<div class="best_items">
				<!-- 베스트 타이틀 -->
				<div class="best_title">
					<h2>냉동식품</h2>
				</div>
				<!-- //베스트 타이틀 -->
				
				<!-- 베스트 아이템 -->
				<div class="items">
					<ul>
						<c:forEach items="${best_frozen }" var="dtoFrozen">
						<a href="product.do?pCode=${dtoFrozen.pCode }">
							<li>
								<div class="item_img">
									<img alt="${dtoFrozen.pName } 메인 이미지" src="${dtoFrozen.pFilePath }">
								</div>
								<div class="item_info">
									<p class="pName">${dtoFrozen.pName }</p>
									<p>구매 수 ${dtoFrozen.pCount }</p>
									<p>${dtoFrozen.pPriceDC }원 <strong class="discount">${dtoFrozen.pDiscount }%</strong></p>
								</div>
							</li>
						</a>
						</c:forEach>
					</ul>
				</div>
				<!-- //베스트 아이템 -->
			</div>
			<!-- //냉동식품 -->
			
			<!-- 식음료 -->
			<div class="best_items">
				<!-- 베스트 타이틀 -->
				<div class="best_title">
					<h2>식음료</h2>
				</div>
				<!-- //베스트 타이틀 -->
				
				<!-- 베스트 아이템 -->
				<div class="items">
					<ul>
						<c:forEach items="${best_beverage }" var="dtoBeverage">
						<a href="product.do?pCode=${dtoBeverage.pCode }">
							<li>
								<div class="item_img">
									<img alt="${dtoBeverage.pName } 메인 이미지" src="${dtoBeverage.pFilePath }">
								</div>
								<div class="item_info">
									<p class="pName">${dtoBeverage.pName }</p>
									<p>구매 수 ${dtoBeverage.pCount }</p>
									<p>${dtoBeverage.pPriceDC }원 <strong class="discount">${dtoBeverage.pDiscount }%</strong></p>
								</div>
							</li>
						</a>
						</c:forEach>
					</ul>
				</div>
				<!-- //베스트 아이템 -->
			</div>
			<!-- //식음료 -->
			
		</div>
		<!-- //베스트 아이템 전체 -->
	
	</div>
	
	<!-- footer -->
	<%@ include file="footer.jsp" %>
	<!-- //footer -->
	
</body>
</html>