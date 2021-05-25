<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="imgUpload.do" method="post"  enctype="multipart/form-data"> 
	<table>
	 <tr>
				<td>대표이미지 : </td>
				<td>
				<input type="file" name="uploadFile"></td>
			</tr>
			<!-- <tr>
				<td>상세이미지 : </td>
				<td>
				<input type="file" name="detail"/></td>
			</tr> -->
	</table>
	<input type="submit" value="등록"/>
	 </form>
</body>
</html>