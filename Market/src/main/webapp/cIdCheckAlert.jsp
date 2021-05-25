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
	var cId = '${cId}'
	var cIdchk = '${cIdchk}';
	var cIdchkMessage = '${cIdchkMessage}';
	var returnUrl = 'cIdCheck.jsp?cId='+ cId + '&idDuplication' + idDuplication;
	
	alert(cIdchkMessage);
	document.location.href =  returnUrl;
	
	
</script>

</body>
</html>