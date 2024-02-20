<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KH Market</title>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="container my-5 " align="center">
			<div class="col-4">
			<h3>로그인</h3>
			 <form action="/j_security_check" method="post">
			 	<div class="form-group my-3">
		            <input type="text" id="id" name="j_username"
		            	   class="form-control" placeholder="ID" autofocus required>
		        </div>
		        <div class="form-group my-3"> 
		            <input type="password" id="passwd" name="j_password"
		            	   class="form-control" placeholder="Password" required>
		       </div>
		       
		       <%
		      	String error = request.getParameter("error");
		        if(error != null){
		        	out.println("<div class='alert alert-danger'>");
		        	out.println("관리자 전용 메뉴입니다.<tr> 일반 로그인 해주세요.");
		        	out.println("</div>");
		        }
		      %>
		       	
		      <div class="d-gird my-3">
		        <button type="submit" class="btn btn-success p-2">로그인</button>
		      </div>
		      
		    </form>
		  </div>
		</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>