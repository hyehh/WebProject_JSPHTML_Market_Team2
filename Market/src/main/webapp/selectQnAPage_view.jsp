<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의-상세보기</title>
<link rel="stylesheet" href="selectPage.css">
</head>
<body>
	<%@include file = "headerSeller.jsp" %>
	<br>
	<br>
	<br>
	<div class="mainTable">
	<form method = "post">
	<table style="margin: auto;">
		<tr>
					<th colspan="3">문의내역</th>
				</tr>
		<tr><!-- B.bNumber, qTitle, C.cName, qAddDate, Q.qContent, Q.aContent  -->
			<td>문의번호</td>
			<td><input type = "text" name = "qnACode" readonly="readonly" value = "${selectQnA.qnACode }"></td>
		</tr>
		<tr><!-- B.bNumber, qTitle, C.cName, qAddDate, Q.qContent, Q.aContent  -->
			<td>주문번호</td>
			<td><input type = "text" name = "bNumber" readonly="readonly" value = "${selectQnA.bNumber }"></td>
		</tr>
		<tr>
			<td>작성자 이름</td>
			<td><input type = "text" name = "cName" readonly="readonly" value = "${selectQnA.cName }"></td>
		</tr>
		<tr>
			<td>작성날짜</td>
			<td><input type = "text" name = "qAddDate" readonly="readonly" value = "${selectQnA.qAddDate }"></td>
		</tr>
		<tr>
			<td>문의 제목</td>
			<td><input type = "text" name = "qTitle" readonly="readonly" value = "${selectQnA.qTitle }"></td>
		</tr>
		<tr>
			<td>문의 내용</td>
			<td><textarea rows="10" cols="50" readonly="readonly" name = "qContent">${selectQnA.qContent }</textarea></td>
		</tr>
		<tr>
			<td>답변 내용</td>
			<td><textarea rows="10" cols="50" name = "aContent">${selectQnA.aContent }</textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type = "text" name = "aAddDate" readonly="readonly" value="${selectQnA.aAddDate }">
			<input type = "text" name = "qAnswerStatus" readonly="readonly" value="${selectQnA.qAnswerStatus }"></td>
		</tr>
		<tr>
				<td colspan="3">
				<input type = "submit" value = "목록으로 가기" formaction="QnAList.do" class="buttonModify buttonModify">
				<input type = "submit" value = "답변등록" formaction="QnAAdd.do" class="buttonModify buttonModify">
				<input type = "submit" value = "답변수정" formaction="QnAEdit.do" class="buttonModify buttonModify">
				<input type = "submit" value = "답변삭제" formaction="QnADelete.do" class="buttonModify buttonModify">
		</tr>
	</table>
</form>
</div>
<footer>
		<div class="content">
			<div>
				주식회사 Team2 | 주소: 서울특별시 서초구 서초4동 강남대로 441 | 대표: 김철수 | 사업자등록번호: 000-00-00000 | 팩스번호: 00-000-0000 | 메일: team2@gamil.com
			</div>
		</div>
	</footer>
</body>
</html>