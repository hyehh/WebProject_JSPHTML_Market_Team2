<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
	var idDuplication = '${sHIDDEN}';
	var sId = '${sId}';
	var sIdchk = '${sIdchk}';
	var sIdchkMessage = '${sIdchkMessage}';
	var returnUrl = 'sIdCheck.jsp?sId='+ sId + '&idDuplication=' + idDuplication;
	
	alert(sIdchkMessage);
	document.location.href =  returnUrl;
	
	
</script>

</body>
</html>