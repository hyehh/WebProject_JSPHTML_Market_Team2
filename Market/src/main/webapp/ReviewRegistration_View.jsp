<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.17 조혜지 리뷰 등록 view -->
<title>리뷰 등록</title>
</head>
<script type="text/javascript">
	function reviewRegistration() {

			alert("리뷰가 정상적으로 등록되었습니다.");
		
	}
</script> 
<style>
	/* .container_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.container {
		width: 1000px;
		margin: auto;
		background-color: #fff;
	} */
	.container {
		text-align: center;
		padding-bottom: 50px;
	}
	.container form {
		width: 580px;
		margin: auto;
	}
	.container form:first-child {
		min-height: 350px;
	}
	.container form:last-child {
		position: relative;
	}
	caption {
		font-style: 200px; 
		font-weight: bold; 
		margin: 50px;
		font-size: x-large;
	}
	th, td {
		padding: 10px;
		text-align: left;
	}
	.buttonJSP{
		width: 70px;
	    background-color: #646464;
	    border: none;
	    color: #fff;
	    padding: 6px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 15px;
	    margin: 10px;
	    cursor: pointer;
		border-radius: 10px;
	}
</style>
<body>
	<%@include file = "header.jsp" %>
	<div class="container_wrap">
		<%@include file = "LeftMenuBar.jsp" %>
		<div class="container">
			<form action="ReviewRegistration.do" method="post" enctype="multipart/form-data">
				<table>
					<caption>리뷰 등록 - 상품 평가하기</caption>
					<tr>
						<td>이미지 등록</td>
						<td colspan="5"><input type="file" name="uploadFile"></td>
					</tr>
					<tr>
						<td>상품 만족도</td>
						<td><input type="radio" name="bReviewScore" value="1">★</td>
						<td><input type="radio" name="bReviewScore" value="2">★★</td>
						<td><input type="radio" name="bReviewScore" value="3">★★★</td>
						<td><input type="radio" name="bReviewScore" value="4">★★★★</td>
						<td><input type="radio" name="bReviewScore" value="5" checked="checked">★★★★★</td>
					</tr>
					<tr>
						<td>리뷰 작성하기</td>
						<td colspan="5"><textarea rows="10" cols="50" name="bReviewContent"></textarea></td>
					</tr>
				</table>
				<input class="buttonJSP" type="submit" value="등록" style="margin-left: -150px; margin-top: 50px;" onclick="reviewRegistration()">
			</form>
			<form action="ReviewRegistrationList_View.do" method="post">
				<input class="buttonJSP" type="submit" value="취소" style="position:absolute; margin-left: 70px; margin-top: -41px;">
			</form>
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>