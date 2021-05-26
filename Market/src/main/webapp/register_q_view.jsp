<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pCode = request.getParameter("pCode");
	String img = request.getParameter("img");
%>
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
		padding: 50px 0;
	}
	
	.register_q {
		text-align: center;
		width: 435px;
		margin: auto;
	}
	.product_img {
		text-align: left;
		padding-left: 20px;
	}
	.product_img h1 {
		margin-left: 20px;
	}
	.product_img img, .product_img h1 {
		display: inline;
		
	}
	.form {
		margin-top: 30px;
	}
	
	table {
		margin-bottom: 30px;
		text-align: left;
	}
	tr {
		min-height: 50px;
	}
	tr td {
		padding: 10px;
	}
	tr td:nth-child(1) {
		padding: 0 30px;
		text-align: center;
	}
	tr td:nth-child(2) {
		width: 300px;
	}
	
	input[type=text] {
		border: none;
		border-bottom: 1px solid gray;
	}
	input[type=submit] {
		border: none;
		background-color: gray;
		padding: 6px 20px;
		border-radius: 10px;
		color: #fff;
	}
</style>

<body>
	<!-- header -->
	<%@ include file="header.jsp" %>
	<!-- //header -->
	<div class="container_wrap">
		<div class="container">
		
			<div class="register_q">
			
				<div class="product_img">
					<img alt="문의 이미지" src="<%=img %>"  style="width: 200px; height: 200px;">
					<h1>상품명</h1>
				</div>
				
				<div class="form">
					<form action="register_q.do" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
						<input type="hidden" name="pCode" value="<%=pCode %>">
						<table>
							<tr>
								<td>작성자</td>
								<td><input type="text" name="cId" placeholder="아이디를 입력해주세요"></td>
							</tr>		
							<tr>
								<td>제목</td>
								<td><input type="text" name="qTitle" placeholder="문의제목을 입력해주세요"></td>
							</tr>		
							<tr>
								<td>내용</td>
								<td><textarea rows="10" cols="30" name="qContent" placeholder="문의내용을 입력해주세요"></textarea></td>
							</tr>		
							<tr>
								<td>파일</td>
								<td><input type="file" name="qnaFile" ></td>
							</tr>	
						</table>
						<input type="submit" value="등록" style="margin-left: -130px;">
					</form>
					<form action="product.do" method="post">
						<input type="hidden" name="pCode" value="<%=pCode %>">
						<input type="submit" value="취소" style="position:absolute; margin-left: 40px; margin-top: -28px;">
					</form>
				</div>
			
			</div>
		
		</div>
	</div>
	<%@ include file="footer.jsp" %>
	
</body>
</html>