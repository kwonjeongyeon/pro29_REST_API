<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script>
	$(function() {
		$("#checkJson").click(function() {
			var article = {
				articleNO : "143",
				writer : "박지성",
				title : "안녕하세요",
				content : "상품 소개 글입니다."
			};

			$.ajax({
				type : "post",
				//type : "get",
				//type:"put",
				url : "${contextPath}/boards",
				//url:"${contextPath}/boards/143",
				contentType : "application/json",
				data : JSON.stringify(article),
				success : function(data, testStatus) {
				},
				error : function(data, testStatus) {
					alert("에러 발생")
				},
				complete : function(data, testStatus) {
				}
			});
		});
	});
</script>
</head>
<body>

	<input type="button" id="checkJson" value="새 글쓰기">

</body>
</html>