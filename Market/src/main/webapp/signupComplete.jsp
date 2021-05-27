<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.container_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.container {
		width: 1000px;
		margin: auto;
		background-color: #fff;
		padding: 100px 0;
		text-align: center;
	}
	input {
		margin-top: 30px;
		background-color: gray;
		border: none;
		padding: 6px 10px;
		border-radius: 10px;
		color: #fff;
	}
</style>
<body>
	<%@ include file="header.jsp" %>
	<div class="container_wrap">
		<div class="container">
			<form action="Login_View.jsp" method="post">
				<!-- 2021.05.13 수훈 -->
				<h3>회원가입이 완료되었습니다.</h3>
				<input type="submit" value="로그인하기" size="16">	
			</form>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>