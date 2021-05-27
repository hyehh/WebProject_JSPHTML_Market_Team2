<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 실패</title>
</head>
<%request.setCharacterEncoding("UTF-8"); %>
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
<jsp:include page="header.jsp"></jsp:include>
<div class="container_wrap">
	<div class="container">
		<form action="FindPw.jsp" method="post">
			<p style="font-size: 15pt">${findmsg }</p>
			<input type="submit" value="돌아가기" size="16">	
		</form>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>