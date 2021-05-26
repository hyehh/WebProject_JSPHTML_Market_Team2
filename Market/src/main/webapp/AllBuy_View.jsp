<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.16 조혜지 주문서 작성/결제 view -->
<title>주문서 작성/결제</title>
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function PostalCode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
				
                
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
<script type="text/javascript">
	function buyInfo() {
		
		var regExpbRecName = /^[가-힣a-zA-z]{1,10}$/;
		var regExpbRecTel = /^[0-9]{11,12}$/;
		var regExpbPayName = /^[가-힣a-zA-z]{1,10}$/;
		var regExpbPayNumber = /^[0-9]*$/;
		var regExpbPayPassword = /^[0-9]{4}$/;
		var form = document.buyForm;
		var bRecName = form.bRecName.value;
		var bRecTel = form.bRecTel.value;
		var bPayName = form.bPayName.value;
		var bPayNumber = form.bPayNumber.value;
		var bPayPassword = form.bPayPassword.value;
		
		if (!regExpbRecName.test(bRecName)) {
			alert("수령인의 이름은 한글,영어 1~10자로 입력해주세요!");
			form.bRecName.select();
			return;
		}
		if(form.bRecPostalCode.value == ""){
			alert("우편번호 찾기를 통해 우편번호를 입력해 주세요!");
			form.bRecPostalCode.focus();
			return;
		}
		if(form.bRecAddress1.value == ""){
			alert("우편번호 찾기를 통해 도로명주소를 입력해 주세요!");
			form.bRecAddress1.focus();
			return;
		}
		if(form.bRecAddress2.value == ""){
			alert("상세 주소를 입력해 주세요!");
			form.bRecAddress2.focus();
			return;
		}
		if (!regExpbRecTel.test(bRecTel)) {
			alert("전화번호는 -없이 숫자 11~12자리로 입력해주세요!");
			form.bRecTel.select();
			return;
		}
		if (!regExpbPayName.test(bPayName)) {
			alert("예금주의 이름은 한글,영어 1~10자로 입력해주세요!");
			form.bPayName.select();
			return;
		}
		if (!regExpbPayNumber.test(bPayNumber)) {
			alert("계좌번호는 -없이 숫자로 입력해주세요!");
			form.bPayNumber.select();
			return;
		}
		if (!regExpbPayPassword.test(bPayPassword)) {
			alert("계좌비밀번호는 숫자 4자리로 입력해주세요!");
			form.bPayPassword.select();
			return;
		}
		form.submit();
		alert("주문이 완료되었습니다!");
	}
</script>
<style>
	.container_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.container {
		width: 1000px;
		margin: auto;
		background-color: #fff;
		text-align: center;
		padding-bottom: 50px;
	} 
	.container h2 {
		padding: 50px 0 20px 0;
	}
	.container form {
		width: 80%;
		margin: auto;
	}
	.container form:nth-child(2) {
		position: relative;
	}
	/* table 디자인 */
	table {
		width: 800px;
		margin: 40px 0;
	}
	caption {
		font-style: 200px;
		font-weight: bold;
		margin: 15px;
		font-size: large;
		caption-side: top;
		text-align: left;
	}

	th, td {
		padding: 10px;
		text-align: left;
	}
	/* 주문상세내역 */
	.title th {
		text-align: center;
	}
	.title th, .buy_product td {
		border-bottom: 1px solid #444444;
		text-align: center;
	}
	.title th:nth-child(1), .buy_product td:nth-child(1) {
		width: 350px;
	}
	.buy_product td:nth-child(1) {
		text-align: left;
	}
	.title th:nth-child(2), .buy_product td:nth-child(2) {
		width: 80px;
	}
	.title th:nth-child(3), .buy_product td:nth-child(3) {
		width: 130px;
	}
	.title th:nth-child(4), .buy_product td:nth-child(4) {
		width: 130px;
	}
	table:nth-child(1) tr:last-child td {
		text-align: right;
		padding-top: 30px;
	}
	
	
	table:nth-child(2) tr td:nth-child(1),
	table:nth-child(3) tr td:nth-child(1),
	table:nth-child(4) tr td:nth-child(1)  {
		width: 100px;
	}
	
	/* input 디자인 */
	.wQuantity {
		color:black;
		background-color: white;
		padding-top: 2px;
		border-width:2px; 
		border-color:gray; 
		border-top-style:none; 
		border-right-style:none; 
		border-left-style:none; 
		border-bottom-style:none;
	}
	.input_info {
		color:black;
		background-color: white;
		padding-top:2px;
		border-width:2px; 
		border-color:gray; 
		border-top-style:none; 
		border-right-style:none; 
		border-left-style:none; 
		border-bottom-style:line;" 
	}
	textarea {
		 color:black; 
		 background-color:white; 
		 padding-top:1px;
		 border-width:1px;
		 border-color:gray; 
		 border-top-style:line; 
		 border-right-style:line; 
		 border-left-style:line; 
		 border-bottom-style:line;
	}
	
	/* 버튼 디자인 */
	.buttonJSP{
		width: 70px;
	    background-color: #646464;
	    border: none;
	    color: #fff;
	    padding: 6px 0;
	    text-decoration: none;
	    font-size: 15px;
	    cursor: pointer;
		border-radius: 10px;
	}
	.buttonAdd{
		width: 90px;
	    background-color: #828282;
	    border: none;
	    color: #fff;
	    padding: 2px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 13px;
	    margin: 10px;
	    cursor: pointer;
		border-radius: 10px;
	}
</style>
<body>
	<%@include file = "header.jsp" %>
	<div class="container_wrap">
		<div class="container" id="allBuy">
			<h2>주문서 작성/결제</h2>
			<form action="AllBuy.do" method="post" name="buyForm">
				<table>
					<caption>주문 상세 내역</caption>
					<tr class="title">
						<th>상품명</th>
						<th>수량</th>
						<th>유통기한</th>
						<th>가격</th>
					</tr>
					<c:set var="SUM" value="0" />
					<c:forEach items="${CART }" var="dto">
					<tr class="buy_product">	
						<td>${dto.pName }</td>
						<td><input class="wQuantity" type="text" name="bQuantity" size="3" readonly="readonly" value="${dto.wQuantity }"></td>
						<td>${dto.pExpirationDate }</td>
						<td>${dto.pPriceDC * dto.wQuantity }</td>
						<td><input type="hidden" name="pCode" value="${dto.pCode }"></td>
					</tr>
					<c:set var="SUM" value="${SUM + (dto.pPriceDC * dto.wQuantity)}" />
					</c:forEach>
					<tr>
						<td colspan="4">총 결제 금액 : ${SUM } 원</td>
					</tr>
				</table>
				<table>
					<caption>주문자 정보</caption>
						<tr>		
							<td>주문자</td>
							<td>${BUYER.cName }</td>
						</tr>
						<tr>		
							<td>전화번호</td>
							<td>${BUYER.cTel }</td>
						</tr>
						<tr>		
							<td>이메일</td>
							<td>${BUYER.cEmail }</td>
						</tr>
				</table>
				<table>
					<caption>배송 정보</caption>
						<tr>		
							<td>수령인</td>
							<td><input class="input_info" type="text" name="bRecName" size="25"placeholder="받는 분의 성함을 입력해주세요."></td>
						</tr>
						<tr>		
							<td>배송지</td>
							<td><input class="input_info" type="text" id="sample4_postcode" name="bRecPostalCode" placeholder="우편번호를 입력해주세요." value="${CUSTOMERINFO.cPostalCode }">
									<input class="buttonAdd" type="button" onclick="PostalCode()" value="우편번호 찾기"><br>
									<input class="input_info" type="text" id="sample4_roadAddress" name="bRecAddress1" placeholder="도로명주소를 입력해주세요." value="${CUSTOMERINFO.cAddress1 }">
									<span id="guide" style="color:#999;display:none"></span>
									<input class="input_info" type="text" id="sample4_detailAddress" name="bRecAddress2" placeholder="상세주소를 입력해주세요." value="${CUSTOMERINFO.cAddress2 }">
						</tr>
						<tr>		
							<td>전화번호</td>
							<td><input class="input_info" type="text" name="bRecTel" size="25" placeholder="-를 제외하고 숫자만 입력해주세요."></td>
						</tr>
						<tr>		
							<td>남기실 말씀</td>
							<td><textarea rows="10" cols="50" name="bRecContent" placeholder="배송 메세지를 입력해주세요."></textarea></td>
						</tr>
				</table>
				<table>			
					<caption>결제 정보</caption>
						<tr>		
							<td>예금주</td>
							<td><input class="input_info" type="text" name="bPayName" size="25" placeholder="예금주 성함을 입력해주세요."></td>
						</tr>
						<tr>		
							<td>은행</td>
							<td>
								<select name="bPayBank">
									<option value="국민은행">국민은행</option>
									<option value="신한은행">신한은행</option>
									<option value="농협은행">농협은행</option>
									<option value="기업은행">기업은행</option>
									<option value="우리은행">우리은행</option>
									<option value="새마을금고">새마을금고</option>
								</select>
							</td>
						</tr>
						<tr>		
							<td>계좌번호</td>
							<td><input class="input_info" type="text" name="bPayNumber" size="25" placeholder="-를 제외하고 숫자만 입력해주세요."></td>
						</tr>
						<tr>		
							<td>비밀번호</td>
							<td><input class="input_info" type="password" name="bPayPassword" size="25" placeholder="숫자 4자리를 입력해주세요."></td>
						</tr>
				</table>
				<input class="buttonJSP" type="button" value="주문하기" style="margin-left: -150px; margin-top: 20px;" onclick="buyInfo()">
			</form>
			<form action="CustomerCart_View.do" method="post">
				<input class="buttonJSP" type="submit" value="취소하기" style="position:absolute; margin-left: 70px; margin-top: -31px;">
			</form>
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>