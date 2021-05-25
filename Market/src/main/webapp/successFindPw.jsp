<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 실패</title>
</head>
<%request.setCharacterEncoding("UTF-8"); %>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<form action="Login_View.jsp" method="post">
	<div>
		<center>
		<p style="font-size: 15pt">${findmsg }</p>
		<br>
		<input type="submit" value="로그인하기" size="16">	
		</center>
	</div>
	</form>
<br>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>