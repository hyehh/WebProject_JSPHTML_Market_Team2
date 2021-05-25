<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pCode = request.getParameter("pCode");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<style>
	.register_q {
		border: 1px solid;
		width: 500px;
		margin: auto;
	}
	.register_q img, .register_q h1 {
		display: inline;
	}
	.form {
		margin-top: 30px;
	}
	
	tr {
		min-height: 50px;
	}
	tr td:nth-child(1) {
		padding: 0 30px;
	}
	tr td:nth-child(2) {
		width: 300px;
	}
</style>

<body>
	<!-- header -->
	<%@ include file="header.jsp" %>
	<!-- //header -->
	
	<div class="register_q">
	
		<div class="product_img">
			<img alt="문의 이미지" src="resources/img/main_pCode<%=pCode %>.jpg"  style="width: 200px; height: 200px;">
			<h1>상품명</h1>
		</div>
		
		<div class="form">
			<form action="register_q.do" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
				<input type="hidden" name="pCode" value="<%=pCode %>">
				<table border="1">
					<tr>
						<td>작성자</td>
						<td><input type="text" name="cId"></td>
					</tr>		
					<tr>
						<td>제목</td>
						<td><input type="text" name="qTitle"></td>
					</tr>		
					<tr>
						<td>내용</td>
						<td><input type="text" name="qContent"></td>
					</tr>		
					<tr>
						<td>파일</td>
						<td><input type="file" name="qnaFile" ></td>
					</tr>	
				</table>
				<input type="submit" value="등록">
			</form>
			<form action="product.do" method="post">
				<input type="hidden" name="pCode" value="<%=pCode %>">
				<input type="submit" value="취소">
			</form>
		</div>
	
	</div>
	
</body>
</html>