<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link rel="stylesheet" href="selectPage.css">
</head><!-- //	pPrice, pCategory, PExpirationDate, pName, pQuantity)pcontent -->
<body>
		<%@ include file="header.jsp" %>
	<br>
	<br>
	<br>
	<h2>상품 등록</h2>
<!-- 	<form action="imgUpload_view.do" method="post"> 
	대표 이미지 및 상세이미지
	<input type="submit" value="등록하러가기"/>
	 </form> -->
	 
			
		<div class="mainTable">	
	<form action="insert.do" method="post" enctype="multipart/form-data">
		<table style="margin: auto;">
			<tr>
				<td>종류</td>
				<td><select name = "pCategory">
				<option value = "생활용품"> 생활용품</option>
				<option value = "식음료"> 식음료</option>
				</select> 
			</tr>
			<tr>
				<td>상품 이름 : </td>
				<td><input type = "text" name = "pName" size = "15"></td>
			</tr>
			<tr>
				<td>상품 가격 : </td>
				<td><input type = "text" name = "pPrice" size = "15"> 원</td>
			</tr>
			<tr>
				<td>할인 가격 : </td>
				<td><input type = "text" name = "pPriceDC" size = "15"> 원</td>
			</tr>
			<tr>
				<td>유통기한 : </td>
				<td><select name = "date1">
		<%
				int year = 0;
				for (int i = 2021; i <= 2030; i++){
					year = i;
		%>
				<option value = "<%=year %>"> <%=year %></option>
		<%
				}
		%>
				</select> 년
				<select name = "date2">
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
				<select name = "date3">
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
				</td>
			</tr>
			<tr>
				<td>수량 : </td>
				<td><input type = "text" name = "pProductEA" size = "15">개</td>
			</tr>
			<tr>
				<td>상품이미지</td>
				<td><input type = "file" name = "uploadFile"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="reset" name="cancel" value="취소"/> 
				<input type="submit" name="submit" value="등록"/></td>
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