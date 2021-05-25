<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 스토어</title>
<link rel="stylesheet" href="btnTest.css">
</head>
<style>
</style>
<body>
<header>
		<!-- 최상단 메뉴 -->
		<div class="top">
			<ul class="top_list">
				<!-- <li><a href="">장바구니</a></li> -->
				<!-- 판매자이기때문에 장바구니는 없어도 되니까 숨김 -->
				<li><a href="">로그아웃</a></li> <!-- 로그인창으로 갈 수이 ㅆ게끔 -->
				<li><a href="">마이페이지</a></li>
				<!-- <li><a href="">1:1 문의</a></li> -->
			</ul>
		</div>
		<!-- //최상단 메뉴 -->
		
		<!-- 로고 -->
		<div class="logo">
			<a href=""><h1>마감임박!!</h1></a>
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
					<!-- <li><a href="">청소용품</a></li>
					<li><a href="">인테리터소품</a></li>
					<li><a href="">통조림</a></li>
					<li><a href="">냉동식품</a></li>
					<li><a href="">식음료</a></li> -->
				</ul>
			</nav>
		</div>
		<!-- //카테고리 -->
	</header>
	<br>
	<table>
	<tr>
				<td>첨부파일</td>
				<td>
					<c:set var="requestFilePath" value="${content.filePath }" />
					<c:choose>
					    <c:when test="${!empty requestFilePath}">
					        <a href="${content.filePath }" download>${fileName }</a>
					    </c:when>
					    <c:otherwise>
					        <a>첨부파일 없음</a>
					    </c:otherwise>
					</c:choose>
					<!-- 이미지 수정을 위해서 기존 파일 경로도 전송해야하므로 히든아이템으로 유지한다. -->
					<input type="hidden" name="oldFilePath" value="${content.filePath }">
					<input type="file" name="uploadFile" >
				</td>
			</tr>
			<tr>
				<td>첨부파일 미리보기</td>
				<td><img width="300" src="${content.filePath }"/></td>
			</tr>
	</table>
	
		<footer>
		<div class="content">
			<div>
				주식회사 Team2 | 주소: 서울특별시 서초구 서초4동 강남대로 441 | 대표: 김철수 | 사업자등록번호: 000-00-00000 | 팩스번호: 00-000-0000 | 메일: team2@gamil.com
			</div>
		</div>
	</footer>
</body>
</html>