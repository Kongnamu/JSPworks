<%@page import="java.util.regex.Pattern"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<% 
    // 입력된 숫자를 가져옴
    String num = request.getParameter("num");
	String regex = "[0-9]+"; //숫
	String result = ""; // 문자열 변수를 전역변수로 선언
	/* Pattren.matches(정규표현식, 문자열) */
	if(num == "" || !Pattern.matches(regex, num)){ // 빈 문자처리, 문자 입력시 오류처리
 		out.println("<script>");
 		out.println("alert('숫자를 입력해주세요')");
 		out.println("history.back()"); //이전 페이지로 이동
 		out.println("</script>");
	}else{
		int num2 = Integer.parseInt(num); //문자를 숫자로 변환
			if(num2 % 3 == 0){
				result = "홀수";
			}else{
				result = "짝수";
			}
	}
%>
	<p>결과는 <%=result %> 입니다.</p>
			
			
      <%-- if (numStr != null && !numStr.isEmpty()) {
      try {
        // 숫자로 변환
        int num = Integer.parseInt(numStr);

        // 홀수/짝수 판별
        String result = (num % 2 == 0) ? "짝수" : "홀수";
  %>
        <p><%= num %>은(는) <%= result %>입니다.</p>
  <%
      } catch (NumberFormatException e) {
  %>
        <p>올바른 숫자를 입력해주세요.</p>
  <%
      }
    }
  %> --%>

  <p><a href="even_odd.jsp">다시 입력하기</a></p>