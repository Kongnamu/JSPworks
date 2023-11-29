package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.Student;
import student.StudentDAO;

//Tomcat v9.0 의 module 에서 주소 edit 필요함
@WebServlet("*.do") //   "/" : 루트 경로 이하에 do로 끝나는 확장자인 파일이 올수있음
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		StudentDAO sDAO;
       
    public StudentController() {
    	sDAO = new StudentDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("utf-8");
		
		//컨텐츠 유형 응답하기
		response.setContentType("text/html; charset=utf-8");
		
		
		//경로(url) 설정 : 커맨드(command) 패턴
		//http://localhost:8080/student/studentlist.jsp
		//맨 뒤 경로 추출 : lastIndexOf('/'), subString(5)
		//substring(1, 5) : 1번 ~ 4번까지 추출함
		//substring(1) : 1번 인덱스부터 문자열의 끝까지 추출함
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/"));
		System.out.println(uri);
		System.out.println(uri.lastIndexOf("/"));
		System.out.println(command);
		
		//이동할 페이지
		String nextPage = "";
		if(command.equals("/studentlist.do")) {
			List<Student> students =  sDAO.getStudentList();
			
			//모델 생성
			request.setAttribute("students", students);
			//뷰 페이지 이동
			nextPage = "/student/studentlist.jsp";
		}else if(command.equals("/studentform.do")) {
			//학생 등록 폼으로 이동
			nextPage = "/student/studentform.jsp";
		}else if(command.equals("/insertstudent.do")) {
			//폼에 입력된 데이터 받기
			String username = request.getParameter("username");
			String univ = request.getParameter("univ");
			String birth = request.getParameter("birth");
			String email = request.getParameter("email");
			
			//db에 저장(빈 객체에 데이터를 넣어서 db에 보냄)
			Student s = new Student();
			s.setUsername(username);
			s.setUniv(univ);
			s.setBirth(birth);
			s.setEmail(email);
			
			sDAO.insertStudent(s);
			//위 .jsp처럼 하면 데이터가 들어가지 않음
			nextPage = "/studentlist.do";
		}
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
