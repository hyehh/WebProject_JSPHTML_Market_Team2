<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>임시 주문 상세 페이지</title>
</head>
<body>
	<form action="CustomerCartInsert.do" method="post">

				상품ID : <input type="text" name="pCode"> <br>
				상품 수량 : <select name="wQuantity">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				</select> <br>
				<input type="submit" value="장바구니">
	</form>
	
	<form action="SellerSignOut_View.do" method="post">
		<input type="submit" value="회원 탈퇴">
	</form>
</body>
</html>