<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세보기</title>
<link rel="stylesheet" href="selectPage.css">
</head>
<body>
	<%@ include file="header.jsp" %>
	<br>
	<br>
	<br>
	<div class="mainTable">
		<form method="post">
			<table style="margin: auto;">
				<tr>
					<th colspan="3">상품 상세보기</th>
				</tr>
				<tr>
					<!-- pCode, pCategory, pName, pPrice, pExpirationDate, pQuantity, pStatus -->
					<td>상품 번호<input type = "hidden" name = "pCode" value="${selectProduct.pCode }"></td>
					<td><input style="background-color: #f2f2f2" type="text" name="pCode" value = "${selectProduct.pCode }"
						 readonly="readonly"></td>

				</tr>
				<tr>
					<td>상품 종류 <input type="hidden"
						value="${selectProduct.pCategory }"></td>
					<td style="align: center;"><select id="test" name="pCategory"
						onchange="">
							<option value="">전체</option>
							<c:forEach items="${Category}" var="Category">
								<option value="${selectProduct.pCategory }"
									${selectProduct.pCategory == Category ? 'selected="selected"' : ''}>${Category }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>상품명</td>
					<td><input type="text" name="pName"
						value="${selectProduct.pName }"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td><input style="background-color: #f2f2f2" type="text" name="pPrice"
						value="${selectProduct.pPrice }" readonly="readonly">원</td>
				</tr>
				<tr>
					<td>할인 가격</td>
					<td><input type="text" name="pPriceDC"
						value="${selectProduct.pPriceDC }">원</td>
				</tr>
				<tr>
					<td>유통기한</td>
					<td><input style="text-align: center; background-color: #f2f2f2" type="text"
						name="pExpirationDate"
						value="${selectProduct.pExpirationDate }"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td>상품 수량</td>
					<td><input type="text" name="pProductEA"
						value="${selectProduct.pProductEA }"></td>
				</tr>
				<tr>
					<td>품절 여부<input type="hidden"
						value="${selectProduct.pStatus }"></td>
					<td><input type="radio" name="pStatus"
						value="판매중"${selectProduct.pStatus == '판매중' ? 'checked="checked"' : '' }">
						판매중 <input type="radio" name="pStatus"
						value="품절"${selectProduct.pStatus == '품절' ? 'checked="checked"' : '' }">
						품절</td>
				</tr>
				<tr>
					<td>등록일</td>
					<td><input type="text" name="pQuantity"
						value="${selectProduct.pAddDate }"></td>
				</tr>
				<tr>
					<td colspan="3">
					<input type = "submit" formaction="productlist.do" class="button buttonModify" 
					value = "목록으로가기">
					<input type = "submit" formaction="productModify.do" class="buttonModify buttonModify" 
					value = "수정">
					<input type = "submit" formaction="productDelete.do" class="buttonModify buttonModify" 
					value = "삭제"></td>

				</tr>
				<tr>
				<td>첨부파일</td>
				<td>
					<c:set var="requestFilePath" value="${selectProduct.pMainFilePath }" />
					<c:choose>
					    <c:when test="${!empty requestFilePath}">
					        <a href="${selectProduct.pMainFilePath }" download>${fileName }</a>
					    </c:when>
					    <c:otherwise>
					        <a>첨부파일 없음</a>
					    </c:otherwise>
					</c:choose>
					<!-- 이미지 수정을 위해서 기존 파일 경로도 전송해야하므로 히든아이템으로 유지한다. -->
					<input type="hidden" name="oldFilePath" value="${selectProduct.pMainFilePath }">
					<input type="file" name="uploadFile" >
				</td>
			</tr>
			<tr>
				<td>첨부파일 미리보기</td>
				<td><img width="100" src="${selectProduct.pMainFilePath }" alt=""/></td>
			</tr>
			</table>
		</form>
	</div>
	<footer>
		<div class="content">
			<div>주식회사 Team2 | 주소: 서울특별시 서초구 서초4동 강남대로 441 | 대표: 김철수 |
				사업자등록번호: 000-00-00000 | 팩스번호: 00-000-0000 | 메일: team2@gamil.com</div>
		</div>
	</footer>
</body>
</html>