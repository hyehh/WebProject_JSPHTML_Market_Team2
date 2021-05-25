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
		display: block;
	} */
/* 	#pwUpdate {
 		padding: 5px 20px;
		position: absolute;
		top: 50%;
		left: 50%;
		width: 450px; height: 250px;
		margin-left: -220px;
		margin-top : -250px; 
	} */
	caption {
		font-style: 200px; font-weight: bold; margin: 50px;
		font-size: x-large;
	}
	.table {
		margin: auto;
		border-collapse: collapse;
	}

	.table, th, td {
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
	<%@include file = "LeftMenuBar.jsp" %>
	<div class="container">
		<form action="ReviewRegistration.do" method="post" enctype="multipart/form-data">
			<table border="0">
				<caption>리뷰 등록 - 상품 평가하기</caption>
					<tr>
						<td>이미지 등록</td>
						<td colspan="10"><input type="file" name="uploadFile"></td>
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
						<td colspan="10"><textarea rows="10" cols="50" name="bReviewContent"></textarea></td>
					</tr>
					<tr>
						<td colspan="2"></td>
						<td colspan="2"><input class="buttonJSP" type="submit" value="등록" onclick="reviewRegistration()"></td>
						</form>
						<form action="ReviewRegistrationList_View.do" method="post">
						<td colspan="2"><input class="buttonJSP" type="submit" value="취소"></td>
						</form>
					</tr>
			</table>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>