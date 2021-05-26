<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link rel="stylesheet" href="selectPage.css">
</head>
<script type="text/javascript">
function addProduct(){
	var form = document.productInsert;
	var category = form.pCategory.value;
	var name = form.pName.value;
	var price = form.pPrice.value;
	var priceDC = form.pPriceDC.value;
	var year = form.date1.value;
	var month = form.date2.value;
	var day = form.date3.value;
	var prodctEA = form.pProductEA.value;
	/* alert("??????"); */
	
	if (category == ""){
		alert("카테고리를 선택하세요.");
		form.pCategory.focus();
		return false;
	}
	
	if (name == ""){
		alert("이름?");
		form.pName.focus();
		return false;
	}
	if (price == ""){
		alert("이름?");
		form.pName.focus();
		return false;
	}
	if (priceDC == ""){
		alert("이름?");
		form.pName.focus();
		return false;
	}
	if (prodctEA == ""){
		alert("이름?");
		form.pName.focus();
		return false;
	}
	
	form.submit();
	
}
</script>
<style>
	.container_wrap {
		background-color: #FAFAFA;
		padding: 50px 0;
	}
	table tr:last-child {
		height: 50px;
	}
</style>
<body>
	<%@ include file="headerSeller.jsp" %>
	<div class="container_wrap">
		<div class="container">
			<div class="mainTable">	
			<form action="insert.do" name = "productInsert" method="post" enctype="multipart/form-data">
				<table style="margin: auto;">
				<tr>
					<th colspan="3">상품 등록</th>
				</tr>
					<tr><!-- 생활용품 주방용품 세탁용품 청소용품 인테리어소품 통조림 냉동식품 식음료 -->
						<td>종류</td>
						<td>
							<select name = "pCategory">
								<option value = ""> 선택</option>
								<option value = "생활용품"> 생활용품</option>
								<option value = "주방용품"> 주방용품</option>
								<option value = "세탁용품"> 세탁용품</option>
								<option value = "청소용품"> 청소용품</option>
								<option value = "인테리어소품"> 인테리어소품</option>
								<option value = "통조림"> 통조림</option>
								<option value = "냉동식품"> 냉동식품</option>
								<option value = "식음료"> 식음료</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>상품 이름</td>
						<td><input type = "text" name = "pName"></td>
					</tr>
					<tr>
						<td>상품 가격</td>
						<td><input type = "text" name = "pPrice"> 원</td>
					</tr>
					<tr>
						<td>할인 가격</td>
						<td><input type = "text" name = "pPriceDC"> 원</td>
					</tr>
					<tr>
						<td>유통기한</td>
						<td><select name = "date1" onchange="selectYear()">
				<%
						int year = 0;
						for (int i = 2021; i <= 2030; i++){
							year = i;
				%>
						<option value = "<%=year %>"> <%=year %></option>
				<%
						}
				%><!-- onchange="chageLangSelect()" -->
						</select> 년
						<select name = "date2" id="month" onchange="selectMonth()">
				<%
						int month = 0;
						for (int i = 1; i <= 12; i++){
							month = i;
				%>
						<option value = "<%=month %>"> <%=month %></option>
				<%
						}
				%>
						</select> 월
						<select name = "date3" id="day" onchange="selectDay()" >
				<%
						int day = 0;
						for (int i = 1; i <= 31; i++){
							day = i;
				%>
						<option value = "<%=day %>"> <%=day %></option>
				<%
						}
				%>
						</select> 일
						<input type = "button" value="확인"  class="button buttonModify">
						</td>
					</tr>
					<!-- <tr>
						<td><div id = "allDateShow"></div></td>
					</tr> -->
					<tr>
						<td>수량</td>
						<td><input type = "text" name = "pProductEA"> 개</td>
					</tr>
					<tr>
						<td>이미지</td>
						<td><input type = "file" name = "uploadFile" class="button buttonModify"></td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="reset" name="cancel" value="취소" class="button buttonModify"/> 
							<input type="button" name="add" value="등록" class="button buttonModify" onclick="addProduct()"/>
						</td>
					</tr>
				</table>
			 </form>
			 </div>
		 </div>
	</div>
	<%@ include file="footerSeller.jsp" %>
</body>
</html>