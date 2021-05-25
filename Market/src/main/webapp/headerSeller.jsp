<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert tile here</title>
	<link rel="stylesheet" href="css1.css">
</head>

<style type="text/css">
	.search {
		width: 200px;
		height: 46px;
		border: 2px solid red;
		float: left;
	}
</style>

<body>
	<header>
		<!-- 최상단 메뉴 -->
		<div class="top">
			<ul class="top_list">
				<li><a href="">로그아웃</a></li>
				<li><a href="">마이페이지</a></li>
			</ul>
		</div>
		<!-- //최상단 메뉴 -->
		
		<!-- 로고 -->
		<div class="logo">
			<a href="sellerStore.do"><h1>마감임박!!</h1></a>
		</div>
		<!-- //로고 -->
		
		<!-- 카테고리 -->
		<div class="category">
			<nav>
				<ul class="category_list">
					<li><a href="productlist.do">상품관리</a></li>
					<li><a href="insertProduct.jsp">상품등록</a></li>
					<li><a href="buyList.do">주문관리</a></li>
					<li><a href="DeliveryList.do">배송관리</a></li>
					<li><a href="QnAList.do">문의관리</a></li>
				</ul>
			</nav>
			<!-- <div class="search">
				<form action="search.do">
					<input type="text" name="pName">
					<input type="submit" value="검색">
				</form>
				<div>
		            <a class="uk-navbar-toggle" href="#" uk-search-icon></a>
		            <div class="uk-navbar-dropdown" uk-drop="mode: click; cls-drop: uk-navbar-dropdown; boundary: !nav">
		
		                <div class="uk-grid-small uk-flex-middle" uk-grid>
		                    <div class="uk-width-expand">
		                        <form action="search.do" class="uk-search uk-search-navbar uk-width-1-1">
		                            <input class="uk-search-input" type="search" placeholder="Search" autofocus>
		                        </form>
		                    </div>
		                    <div class="uk-width-auto">
		                        <a class="uk-navbar-dropdown-close" href="#" uk-close></a>
		                    </div>
		                </div>
		
		            </div>
		        </div>
			</div> -->
		</div>
		<!-- //카테고리 -->
	</header>
</body>
</html>