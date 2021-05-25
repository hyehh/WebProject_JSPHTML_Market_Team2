<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.17 조혜지 리뷰 삭제 view -->
<title>리뷰 삭제</title>
</head>
<script type="text/javascript">
	function reviewCancel() {

			alert("리뷰가 정상적으로 삭제되었습니다.");
		
	}
</script> 
<style>
	/* .container_wrap {
		display: block;
	} */
	caption {
		font-style: 200px; font-weight: bold; margin: 50px;
		font-size: x-large;
	}
	.table {
		margin: auto;
		border-top: 1px solid #444444;
		border-collapse: collapse;
	}

	.table, th, td {
		border-bottom: 1px solid #444444;
		padding: 10px;
		text-align: center;
	}
	
	.buttonRev{
		width: 70px;
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
	<%@include file = "LeftMenuBar.jsp" %>
	<div class="container">
		<form method="post">
			<table border="0">
				<caption>리뷰 삭제</caption>
					<c:choose>			
						<c:when test="${empty REVIEWDEL}">
						<tr>
							<td>리뷰를 등록하신 제품이 존재하지 않습니다.</td>
						</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>주문번호</td>
								<td>주문일</td>
								<td>상품명</td>
								<td>상품 만족도</td>
								<td>리뷰 내용</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						<c:forEach items="${REVIEWDEL }" var="dto">
							<tr>
								<td><input type="text" name="bNumber" readonly="readonly" value="${dto.bNumber }" 
								style="color:black; background-color=white; padding-top:2px;
								border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:none;"></td>
							 	<td>${dto.bBuyDate }</td>
								<td>${dto.pName }</td>
								<td>${dto.bReviewScore }</td>
								<td>${dto.bReviewContent }</td>
								<td><input type="hidden" name="pCode" value="${dto.pCode }"></td>
								<td><input class="buttonRev" type="submit" value="리뷰 삭제" formaction="ReviewDelete.do?bNumber=${dto.bNumber }&pCode=${dto.pCode }" onclick="reviewCancel()"></td>
							</tr>
						</c:forEach>
							<tr>
					         <td align="center" colspan="10">
					         	[<a href="ReviewDelete_View.do"> ◀◀ </a>] 
					         	[<a href="ReviewDelete_View.do?pg=${FROMPAGE -1 }">◀</a>] 
					         	<c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
					         	<a href="ReviewDelete_View.do?pg=${ftp.count}">[ ${ftp.count } ]</a>
				                </c:forEach>
				                [<a href="ReviewDelete_View.do?pg=${TOPAGE }">▶</a>] 
				                [<a href="ReviewDelete_View.do?pg=${ALLPAGE }">▶▶</a>]
					         </td>
				    		</tr>
						</c:otherwise>
					</c:choose>
			</table>
		</form>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>