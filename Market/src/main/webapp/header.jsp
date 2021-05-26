<%@page import="com.jspproject.bbs.util.Share"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
			<c:choose>
				<c:when test="${empty userId}">
					<li><a href="login.do">로그인</a></li>
					<li><a href="signup.do">회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="CustomerCart_View.do">장바구니</a></li>
					<li><a href="OrderList_View.do">마이페이지</a></li>
					<li><a href="logout.do">로그아웃</a></li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
		<!-- //최상단 메뉴 -->
		
		<!-- 로고 -->
		<div class="logo">
			<a href="main.do"><h1>마감임박!!</h1></a>
		</div>
		<!-- //로고 -->
		
		<!-- 카테고리 -->
		<div class="category">
			<nav>
				<ul class="category_list">
					<li><a href="best.do">Best</a></li>
					<li><a href="new.do">New</a></li>
					<li><a href="deadline.do">마감임박</a></li>
					<li><a href="category.do?pCategory=주방용품">주방용품</a></li>
					<li><a href="category.do?pCategory=세탁용품">세탁용품</a></li>
					<li><a href="category.do?pCategory=청소용품">청소용품</a></li>
					<li><a href="category.do?pCategory=인테리어소품">인테리어소품</a></li>
					<li><a href="category.do?pCategory=통조림">통조림</a></li>
					<li><a href="category.do?pCategory=냉동식품">냉동식품</a></li>
					<li><a href="category.do?pCategory=식음료">식음료</a></li>
				</ul>
			</nav>
			<div class="search">
				<form action="search.do">
					<input type="text" name="pName">
					<input type="submit" value="검색">
				</form>
				<!-- <div>
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
		        </div> -->
			</div>
		</div>
		<!-- //카테고리 -->
	</header>
</body>
</html>