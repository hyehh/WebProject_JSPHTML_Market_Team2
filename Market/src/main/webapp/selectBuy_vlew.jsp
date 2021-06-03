<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세보기</title>
<link rel="stylesheet" href="selectPage.css">
</head>
<style>
.mainTable table:nth-child(2) tr td:nth-child(1),
.mainTable table:nth-child(2) tr td:nth-child(2),
.mainTable table:nth-child(2) tr td:nth-child(3),
.mainTable table:nth-child(2) tr td:nth-child(4)  {
	width: 200px;
}
</style>
<body>
	<%@include file = "headerSeller.jsp" %>
	<br>
	<br>
	<br>
	<div class="mainTable">
	<form method = "post">
		<table style="margin: auto;">
		<tr>
		<th colspan="3">주문 상세보기</th>
		</tr>
			<tr><!-- bNumber, pCategory, pName, bState, bReviewContent, bReviewScore, bDate, bReviewRegistrationDate, bReviewEditDate, bReviewCancelDate, bCancelDate -->
				<td>주문 번호 </td>
				<td><input type = "text" name = "bNumber" value = "${selectBuy.bNumber }" readonly="readonly"></td>
			</tr>
			<tr>
				<td>주문자 아이디</td>
				<td><input type = "text" name = "pCategory" readonly="readonly" value = "${selectBuy.cId }" size = "10"></td>
			</tr>
			<tr>
				<td>주문자명</td>
				<td><input type = "text" name = "pName" readonly="readonly" value = "${selectBuy.cName }"></td>
			</tr>
			<tr>
				<td>주문자 번호</td>
				<td><input type = "text" name = "pName" readonly="readonly" value = "${selectBuy.cTel }"></td>
			</tr>
			</table>
			<table>
			<tr>
				<th colspan="4">구매리스트</th>
			</tr>
			<tr>
				<td>상품 종류</td>
				<td>상품 이름</td>
				<td>구매 수량</td>
				<td>주문 현황</td>
				<%-- <td><input type = "text" name = "buyCancel" readonly="readonly" value = "${selectBuy.buyCancel }"></td> --%>
			</tr>
			<c:forEach items = "${selectBuyList }" var = "list">
			<tr>
				<td>${list.pCategory }</td>
				<td>${list.pName }</td>
				<td>${list.bQuantity }</td>
				<td>${list.buyCancel }</td>
			</tr>
			</c:forEach>
			</table>
			<table style="margin: auto;">
			<tr>
				<td>리뷰</td>
				<td><textarea rows="10" cols="50" readonly="readonly" name = "bReviewContent">${selectBuy.bReviewContent }</textarea>
			</tr>
			<tr>
				<td>별점</td>
				<td><input type = "text" name = "bReviewScore"readonly="readonly"  value = "${selectBuy.bReviewScore }">점</td>
			</tr>
			<tr>
				<td>구매일자</td>
				<td><input type = "text" name = "bBuyDate" readonly="readonly" value = "${selectBuy.bBuyDate }"></td>
			</tr>
			<tr>
				<td>리뷰작성일자</td>
				<td><input type = "text" name = "bReviewWriteDate" readonly="readonly" value = "${selectBuy.bReviewWriteDate }"></td>
			</tr>
			<tr>
				<td>리뷰삭제일자</td>
				<td><input type = "text" name = "bReviewDeleteDate" readonly="readonly" value = "${selectBuy.bReviewDeleteDate }"></td>
			</tr>
			<tr>
				<td>취소일자</td>
				<td><input type = "text" name = "bBuyCancelDate" readonly="readonly" value = "${selectBuy.bBuyCancelDate }"></td>
			</tr>
			<tr>
				<td colspan="2"><a href="buyList.do"><button
								type="button" class="buttonModify buttonModify">목록으로가기</button></a>
				<input type = "submit" value = "배송관리" formaction="buyToDeliver.do" class="buttonModify buttonModify" style="zoom: 1.0;">
			</tr>
		</table>
	</form>
	</div>
	<footer>
		<div class="content">
			<div>주식회사 Team2 | 주소: 서울특별시 서초구 서초4동 강남대로 441 | 대표: 김철수 |
				사업자등록번호: 000-00-00000 | 팩스번호: 00-000-0000 | 메일: team2@gamil.com</div>
		</div>
	</footer>
</body>
</html>