<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.23 장바구니 존재 여부 보여주는 view -->
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	var check = '${check}';
	var checkMessage = '${checkMessage}';
	var returnUrl = 'productAgain.do';
	
	alert(checkMessage);
	document.location.href =  returnUrl;
</script>
</body>
</html>