<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		
    	<!-- UIkit CSS -->
	    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.7/css/uikit.min.css" /> -->
	    <link rel="stylesheet" href="img_slide.css">
	
	    <!-- UIkit JS -->
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.7/js/uikit.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.7/js/uikit-icons.min.js"></script>
	    
	    <link rel="stylesheet" href="css1.css" />
	</head>
	
	<style type="text/css">
		/* 이미지 슬라이드 */
		.img_slide {
			width: 100%;
			height: 500px;
		}
		.my-slider {
            width: 100%;
            height: 500px;
            overflow: hidden;
        }
        .my-slider ul li img {
        	width: 100%;
        	height: 100%;
        }
        .my-slider a {
        	width: 14px;
        	height: 24px;
        	padding: 5px 10px;
        	margin: 15px;
        }
   
		/* 메인 상품 노출 */
		.main_wrap {
			background-color: #FAFAFA;
		}
		.best_items, .new_items, .rand_items{
			width: 1000px;
			margin: auto;
			margin-top: 30px;
			background-color: #fff;
		}
		.title {
			width: 100%;
			height: 50px;
			margin: auto;
			line-height: 50px;
		}
		.items {
			width: 100%;
			height: 325px;
		}
		.rand_items .items {
			height: 1625px;
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
		
		<div class="main_wrap">
			<!-- 메인 이미지 슬라이드 -->
			<div class="img_slide">
				<div class="my-slider uk-position-relative uk-visible-toggle uk-light" tabindex="-1" uk-slideshow="autoplay:true; autoplay-interval=1000;">
				    <ul class="uk-slideshow-items">
				        <li>
				            <img src="resources/img/photo4.jpg" alt="">
				        </li>
				        <li>
				            <img src="resources/img/photo5.jpg" alt="">
				        </li>
				        <li>
				            <img src="resources/img/photo6.jpg" alt="">
				        </li>
				        <li>
				            <img src="resources/img/photo7.jpg" alt="">
				        </li>
				        <li>
				            <img src="resources/img/photo8.jpg" alt="">
				        </li>
				        <li>
				            <img src="resources/img/photo9.jpg" alt="">
				        </li>
				    </ul>
				
				    <a class="btn uk-position-center-left uk-position-small uk-hidden-hover" href="#" uk-slidenav-previous uk-slideshow-item="previous"></a>
				    <a class="btn uk-position-center-right uk-position-small uk-hidden-hover" href="#" uk-slidenav-next uk-slideshow-item="next"></a>
				
				</div>
			</div>
			<!-- //메인 이미지 슬라이드 -->
			
			<!-- 메인 상품 진열 -->
			<div class="main_items">
				<!-- 베스트 상품 진열 -->
				<div class="best_items">
					<!-- 베스트 상품 타이틀 -->
					<div class="title">
						<h2>Best</h2>
					</div>
					<!-- //베스트 상품 타이틀 -->
					
					<!-- 베스트 상품 노출 -->
					<div class="items">
						<ul>
							<c:forEach items="${main_best }" var="dtoBest">
							<a href="product.do?pCode=${dtoBest.pCode }">
								<li>
									<div class="item_img">
										<img alt="${dtoBest.pName } 메인 이미지" src="resources/img/main_pCode${dtoBest.pCode }.jpg">
									</div>
									<div class="item_info">
										<p class="pName">${dtoBest.pName }</p>
										<p>구매 수 ${dtoBest.pCount }</p>
										<p>${dtoBest.pPriceDC }원 <strong class="discount">${dtoBest.pDiscount }%</strong></p>
									</div>
								</li>
							</a>
							</c:forEach>
						</ul>
					</div>
					<!-- //베스트 상품 노출 -->
				</div>
				<!-- //베스트 상품 진열 -->
				
				<!-- new 상품 리스트 -->
				<div class="new_items">
					<!-- new 상품 타이틀 -->
					<div class="title">
						<h2>New</h2>
					</div>
					<!-- //new 상품 타이틀 -->
					
					<div class="items">
						<ul>
							<c:forEach items="${main_new }" var="dtoNew">
							<a href="product.do?pCode=${dtoNew.pCode }">
								<li>
									<div class="item_img">
										<img alt="${dtoNew.pName } 메인 이미지" src="resource/img/main_pCode${dtoNew.pCode }.jpg">
									</div>
									<div class="item_info">
										<p>${dtoNew.pName }</p>
										<p>유통기한 ${dtoNew.pExpirationDate }</p>
										<p>${dtoNew.pPriceDC }원 <strong class="discount">${dtoNew.pDiscount }%</strong>
										<p>${dtoNew.star } (${dtoNew.pCount })</p>
									</div>
								</li>
							</a>
							</c:forEach>
						</ul>
					</div>
				</div>
				<!-- //new 상품 리스트 -->
				
				<!-- 랜덤 상품 리스트 -->
				<div class="rand_items">
					<!-- new 상품 타이틀 -->
					<div class="title">
						<h2>MD추천</h2>
					</div>
					<!-- //new 상품 타이틀 -->
					
					<div class="items">
						<ul>
							<c:forEach items="${main_rand }" var="dtoRand">
							<a href="product.do?pCode=${dtoRand.pCode }">
								<li>
									<div class="item_img">
										<img alt="${dtoRand.pName } 메인 이미지" src="resource/img/main_pCode${dtoRand.pCode }.jpg">
									</div>
									<div class="item_info">
										<p>${dtoRand.pName }</p>
										<p>유통기한 ${dtoRand.pExpirationDate }</p>
										<p>${dtoRand.pPriceDC }원 <strong class="discount">${dtoRand.pDiscount }%</strong>
										<p>${dtoRand.star } (${dtoRand.pCount })</p>
									</div>
								</li>
							</a>
							</c:forEach>
						</ul>
					</div>
				</div>
				<!-- //랜덤 상품 리스트 -->
				
			</div>
			<!-- //메인 상품 진열 -->
		</div>
		<!-- footer -->
		<%@ include file="footer.jsp" %>
		<!-- //footer -->
	</body>
</html>