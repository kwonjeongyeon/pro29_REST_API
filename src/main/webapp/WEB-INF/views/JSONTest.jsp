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
$(function(){
	$("#checkJson").click(
			function () {
			let member = {id:"hong", name:"홍", pwd:"1234", email:"hong@test.com"}
			$.ajax({
			type:"post",
			url:"${contextPath}/test/info",
			contentType: "application/json",
			data:JSON.stringify(member),
			success : function (data, testStatus){},
			error:function (data, testStatus){alert("에러 발생")},
			complete: function (data, testStatus){}
			});
			}
	);
});

</script>
</head>
<body>

	<input type="button" id="checkJson" value="나와라">

</body>
</html>