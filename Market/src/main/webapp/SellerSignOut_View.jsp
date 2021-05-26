<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

	function signOut() {
		
		var form = document.sellerSignOut;

		if(!confirm("정말 탈퇴하시겠습니까?")){
			
		}else{
			alert("탈퇴 처리 되었습니다.");
			form.submit();
		}
	}
</script>
<style>
	/* .container_wrap {
		display: block;
	} */
/* 	#signOut {
 		padding: 5px 20px;
		position: absolute;
		top: 50%;
		left: 50%;
		width: 450px; height: 250px;
		margin-left: -220px;
		margin-top : -250px; 
	}  */
	caption {
		font-style: 200px; font-weight: bold; margin: 50px;
		font-size: x-large;
	}
	.table {
		margin: auto;
		border-collapse: collapse;
	}

	.table, th, td {
		padding: 10px;
		text-align: left;
	}
	.buttonJSP{
		width: 70px;
	    background-color: #646464;
	    border: none;
	    color: #fff;
	    padding: 6px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 15px;
	    margin: 10px;
	    cursor: pointer;
		border-radius: 10px;
	}
</style> 
<body>
<!-- 2021.05.19 조혜지 판매자 회원 탈퇴 view -->
	<%@include file = "headerSeller.jsp" %>
	<%@include file = "SellerInfo.jsp" %>
	<div class="container" id="signOut">
		<form action="SellerSignOut.do" name="sellerSignOut" method="post">
			<table>
				<caption>회원 탈퇴</caption>
					<tr>
						<th align="left"><h4>탈퇴 사유 선택<br>
						(기타를 선택하신 경우 '남기실 말씀' 항목에 사유를 남겨주시면 귀담아 듣겠습니다.)</h4><th>
					</tr>
					<tr>
						<td><input type="checkbox" name="sSignOutReason" value="서비스 불만">서비스 불만 
						<input type="checkbox" name="sSignOutReason" value="사이트 신뢰도 불만">사이트 신뢰도 불만 
						<input type="checkbox" name="sSignOutReason" value="개인정보 유출 우려">개인정보 유출 우려 
						<input type="checkbox" name="sSignOutReason" value="사이트 미이용">사이트 미이용 <br>
						<input type="checkbox" name="sSignOutReason" value="판매 감소">판매 감소 
						<input type="checkbox" name="sSignOutReason" value="타사이트 이용">타사이트 이용 
						<input type="checkbox" name="sSignOutReason" value="사업 종료">사업 종료 
						<input type="checkbox" name="sSignOutReason" value="기타">기타 <br><br></td>
					</tr>
					<tr>
						<th align="left"><h4>남기실 말씀</h4></th>
					</tr>
					<tr>
						<td><textarea rows="10" cols="70" name="sSignOutContent" placeholder="사이트를 이용하면서 붎편했던 경험을 자유롭게 기재해주세요. (선택사항입니다.)"></textarea></td>
					</tr>
					<tr>
						<td colspan="1"><input class="buttonJSP" type="button" style="float:right" value="탈퇴" onclick="signOut()"></td>
					</tr>
			</table>
		</form>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>