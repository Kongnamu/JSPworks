<%@ page import="adressbook.AddrBook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 목록</title>
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
	<jsp:useBean id="abDAO" class="adressbook.AddrBookDAO" scope="application"/>
	<%
		//세션이 없는 경우 (로그인 안된 경우) : 로그인 하도록 알림창 띄우기
		//String sessionId = null;
		if(session.getAttribute("sessionId") != null){ //null 이 아닌 경우 : 로그인 한 경우
			//세션 가져와서 유지함
			String sessionId = (String)session.getAttribute("sessionId");
		}else{ //로그인 안됐을때
			out.println("<script>");
			out.println("alert('로그인을 해주세요')");
			out.println("location.href='loginForm.jsp' ");
			out.println("</script>");
		}
	%>
	
	<div id="container">
		<h2>주소 목록</h2>
		<hr>
		<p>
			<a href="logout.jsp">
			<span class="accent">(<%= session.getAttribute("sessionId") %>)님 [로그아웃]</span>
			</a>
		</p>
		<table id="tbl_List">
			<thead>
			<tr>
				<th>번호</th>
				<th>이름</th> 
				<th>전화번호</th>
				<th>이메일</th>
				<th>성별</th>
				<th>등록일</th>
				<th>보기</th>
			</tr>
			</thead>
			<tbody>
			<% 
				/*  */
				for(int i = 0; i < abDAO.getListAll().size(); i++){
					//AddrBook -> import 진행
					AddrBook addrBook = abDAO.getListAll().get(i);
			%>
			<tr>
				<td><%=addrBook.getBnum() %></td>
				<td><%=addrBook.getUsername() %></td>
				<td><%=addrBook.getTel() %></td>
				<td><%=addrBook.getEmail() %></td>
				<td><%=addrBook.getGender() %></td>
				<td><%=addrBook.getRegDate() %></td>
				<td>
					<a href="addrView.jsp?bnum=<%=addrBook.getBnum() %>">
					<button type="button">보기</button>
					</a>
				</td>
			</tr>
			<%
			
				} //for문을 감싸줘야함
			
			%>
			</tbody>	
		</table>
	</div>
</body>
</html>