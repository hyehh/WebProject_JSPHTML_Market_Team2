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
<style>
	header {
		display: block;
	}
	.container {
		display: inline-block;
		width: 800px;
 		/* padding: 50px 800px; */
 		/* margin: 50px 800px; */
	}
	caption {
		font-style: 200px; font-weight: bold; margin: 15px;
		font-size: large;
		caption-side: top;
		text-align: left;
	}
	.table {
		margin: 100px;
		border-collapse: collapse;
		padding: 50px;
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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function infoUpdate() {
		
		var regExpbRecName = /^[가-힣a-zA-z]{1,30}$/;
		var regExpbRecTel = /^[0-9]{11,12}$/;
		var regExpcEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		var form = document.customerInfoUpdateForm;
		var cName = form.cName.value;
		var cTel = form.cTel.value;
		var cEmail = form.cEmail.value;
		var regExpbPayName = /^[가-힣a-zA-z]{1,30}$/;
		
		if (!regExpbRecName.test(bRecName)) {
			alert("수령인의 이름은 한글,영어 1~30자로 입력해주세요!");
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
			alert("전화번호는 -없이 숫자11~12자리로 입력해주세요!");
			form.bRecTel.select();
			return;
		}

		form.submit();
		alert("회원정보가 수정되었습니다!");
	}
</script>
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
<body>
	<%@include file = "header.jsp" %>
	<div class="container">
		<h2>주문서 작성/결제</h2>
		<form action="Buy.do" method="post">
			<table border="0">
				<caption>주문 상세 내역</caption>
					<tr>
						<th>상품명</th>
						<th>수량</th>
						<th>유통기한</th>
						<th>가격</th>
					</tr>
					<c:set var="SUM" value="0" />
					<c:forEach items="${BUY }" var="dto">
					<tr>	
						<td>${dto.pName }</td>
						<td><input type="text" name="bQuantity" size="3" readonly="readonly" value="${dto.wQuantity }" 
						style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:none;"></td>
						<td>${dto.pExpirationDate }</td>
						<td>${dto.pPriceDC * dto.wQuantity }</td>
						<td><input type="hidden" name="pCode" value="${dto.pCode }"></td>
					</tr>
					<c:set var="SUM" value="${SUM + (dto.pPriceDC * dto.wQuantity)}" />
					</c:forEach>
					<tr>
						<td colspan="10" align="left">총 결제 금액 : ${SUM } 원</td>
					</tr>
			</table>
			<table border="0">
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
			<table border="0">
				<caption>배송 정보</caption>
					<tr>		
						<td>수령인</td>
						<td><input type="text" name="bRecName" size="25" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" placeholder="받는 분의 성함을 입력해주세요."></td>
					</tr>
					<tr>		
						<td>배송지</td>
						<td><input type="text" id="sample4_postcode" name="bRecPostalCode" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="우편번호를 입력해주세요." value="${CUSTOMERINFO.cPostalCode }">
								<input class="buttonAdd" type="button" onclick="PostalCode()" value="우편번호 찾기"><br>
								<input type="text" id="sample4_roadAddress" name="bRecAddress1" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="도로명주소를 입력해주세요." value="${CUSTOMERINFO.cAddress1 }">
								<span id="guide" style="color:#999;display:none"></span>
								<input type="text" id="sample4_detailAddress" name="bRecAddress2" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="상세주소를 입력해주세요." value="${CUSTOMERINFO.cAddress2 }">
					</tr>
					<tr>		
						<td>전화번호</td>
						<td><input type="text" name="bRecTel" size="25" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="-를 제외하고 숫자만 입력해주세요."></td>
					</tr>
					<tr>		
						<td>남기실 말씀</td>
						<td><textarea rows="10" cols="50" name="bRecContent" style="color:black; background-color=white; padding-top:1px;
						border-width:1px; border-color:gray; border-top-style:line; border-right-style:line; border-left-style:line; border-bottom-style:line;" 
						placeholder="배송 메세지를 입력해주세요."></textarea></td>
					</tr>
			</table>
			<table border="0">			
				<caption>결제 정보</caption>
					<tr>		
						<td>예금주</td>
						<td><input type="text" name="bPayName" size="25" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="예금주 성함을 입력해주세요."></td>
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
						<td><input type="text" name="bPayNumber" size="25" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="-를 제외하고 숫자만 입력해주세요."></td>
					</tr>
					<tr>		
						<td>비밀번호</td>
						<td><input type="password" name="bPayPassword" size="25" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="숫자 4자리를 입력해주세요."></td>
					</tr>
					<tr>
			 			<td align="right"><input class="buttonJSP" type="submit" value="주문하기"></td>
			 			</form>
						<form action="CustomerCart_View.do" method="post">
						<td align="right"><input class="buttonJSP" type="submit" value="취소하기"></td>
						</form>
					</tr>
			</table>
		</div>
		<%@include file = "footer.jsp" %>
</body>
</html>