<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>점수 입력 폼</title>
<script>
	function check(){
		//alert("test");
		let form = document.form1;
		let score = form.score.value;
		
		//값이 입력되지 않았을 때 문자가 입력됏을때 처리
		if(score == "" || isNaN(score)){ //값이 입력 x일때
			alert("숫자를 입력해주세요");
			score.focus();
			return false;
		}else{
			form.submit();
		}
	}
</script>
</head>
<body>
	<h3>점수를 입력해주세요</h3> 
	<!-- 유효성 검사 필요 : 인풋 타입 버튼으로 변경, form의 name 설정-->
	<form action="core02.jsp" method="post" name="form1">
		<p>점수<input type="text" name="score">
			  <input type="button" value="전송" onclick="check()"> <!-- check() 스크립트 사용하여 예외 처리(유효성 검사) -->
	   </p>
	</form>
</body>
</html>