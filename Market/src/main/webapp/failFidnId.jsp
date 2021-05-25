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
	<form action="FindId.jsp" method="post">
	<div>
		<center>
		<p style="font-size: 15pt">${findmsg }</p>
		<br>
		<input type="submit" value="돌아가기" size="16">	
		</center>
	</div>
	</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>