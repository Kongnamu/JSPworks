package servlet.forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/for/second")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SecondServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩 요청
		request.setCharacterEncoding("utf-8"); //first서블릿에서 name을 한글로 입력해서 가져올 경우
		
		//응답할 컨텐츠 유형
		response.setContentType("text/html; charset=utf-8");
		
		//first에서 name 속성값 받음
		String name = request.getParameter("name");
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h3>이름: " + name + "<br>");
		out.println("<h3>dispatcher를 이용한 포워딩 실습입니다.</h3>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
