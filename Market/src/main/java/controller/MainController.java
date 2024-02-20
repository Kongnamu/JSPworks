package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Product;
import model.ProductDAO;

@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProductDAO pDAO;
	
    public MainController() {
    	pDAO = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩
		request.setCharacterEncoding("utf-8");
		
		//command 패턴 경로 설정
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/"));
		String nextPage = "";
		
		HttpSession session = request.getSession(); // 세션 객체 생성 
		
		if(command.equals("/main.do")) {
			nextPage="/main.jsp";
		}else if(command.equals("/productlist.do")) {
			//목록 보기 메서드 호출
			List<Product> productList = pDAO.getProductList();
			
			//모델 생성해서 보내주기
			request.setAttribute("products", productList);
			nextPage = "/product/list.jsp";
		}else if(command.equals("/productform.do")) {
			nextPage = "/product/pform.jsp";
		}else if(command.equals("/insertproduct.do")) {
			
			String realFolder = "C:\\jspWorks\\Market\\src\\main\\webapp\\upload";
		      int maxSize = 10*1024*1024;   //10MB
		      String encType = "utf-8";   //파일명 한글 인코딩
		      DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		      
		      //5가지 연자
		      MultipartRequest multi = new MultipartRequest (request, realFolder,
		                        maxSize, encType, policy);
			
			//폼에 데이터 받기
			String pid = multi.getParameter("pid");
			String pname = multi.getParameter("pname");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String category = multi.getParameter("category");
			int pstock = Integer.parseInt(multi.getParameter("pstock"));
			String condition = multi.getParameter("condition");
			
			//file 객체 생성 
			Enumeration<?> files = multi.getFileNames();
		    String pimage = "";
		    while(files.hasMoreElements()) { //파일명이 있는 동안 반복
		       String userFilename = (String)files.nextElement();
		         
		       //실제 저장될 이름
		       pimage = multi.getFilesystemName(userFilename);
		    }
			
			//상품 객체 생성
			Product product = new Product();
			product.setPid(pid);
			product.setPname(pname);
			product.setPrice(price);
			product.setDescription(description);
			product.setCategory(category);
			product.setPstock(pstock);
			product.setCondition(condition);
			product.setPimage(pimage);
			
			//db에 등록할 메서드 호출
			pDAO.insertProduct(product);
			
			nextPage = "/productlist.do";
		}else if(command.equals("/productinfo.do")) {
			String pid = request.getParameter("pid");
			
			Product product = pDAO.getProduct(pid);
			
			//모델 생성
			request.setAttribute("product", product);
			nextPage = "/product/pinfo.jsp";
		}else if(command.equals("/editproduct.do")) {
			String edit = request.getParameter("edit");
			List<Product> products = pDAO.getProductList();
			
			request.setAttribute("products", products);
			request.setAttribute("edit", edit);
			nextPage = "/product/edit.jsp";
		}else if(command.equals("/deleteproduct.do")) {
			String pid = request.getParameter("pid");
			
			//상품 삭제
			pDAO.deleteProduct(pid);
			
			nextPage = "/editproduct.do?edit=delete";
		}else if(command.equals("/updateform.do")) {
			String pid = request.getParameter("pid");
			//해당 아이디 상품 가져옴
			Product product = pDAO.getProduct(pid);
			//모델 만들기
			request.setAttribute("product", product);
			
			
			nextPage = "/product/updateform.jsp";
		}else if(command.equals("/updateproduct.do")) {
			String realFolder = "C:\\jspWorks\\Market\\src\\main\\webapp\\upload";
		      int maxSize = 10*1024*1024;   //10MB
		      String encType = "utf-8";   //파일명 한글 인코딩
		      DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		      
		      //5가지 연자
		      MultipartRequest multi = new MultipartRequest (request, realFolder,
		                        maxSize, encType, policy);
			
			//폼에 데이터 받기
			String pid = multi.getParameter("pid");
			String pname = multi.getParameter("pname");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String category = multi.getParameter("category");
			int pstock = Integer.parseInt(multi.getParameter("pstock"));
			String condition = multi.getParameter("condition");
			
			//file 객체 생성 
			Enumeration<?> files = multi.getFileNames();
		    String pimage = "";
		    while(files.hasMoreElements()) { //파일명이 있는 동안 반복
		       String userFilename = (String)files.nextElement();
		         
		       //실제 저장될 이름
		       pimage = multi.getFilesystemName(userFilename);
		    }
			
			//상품 객체 생성
			Product product = new Product();
			product.setPid(pid);
			product.setPname(pname);
			product.setPrice(price);
			product.setDescription(description);
			product.setCategory(category);
			product.setPstock(pstock);
			product.setCondition(condition);
			product.setPimage(pimage);
			
			//db에 등록할 메서드 호출
			if(pimage != null) {
				pDAO.updateProduct(product);
			}else {
				pDAO.updateProductNoImage(product);
			}
			
			nextPage = "/editproduct.do?edit=update";
		}else if(command.equals("/addcart.do")) { //상품 주문하기
			String pid = request.getParameter("pid");

			//상품 목록
			List<Product> goodsList = pDAO.getProductList(); //상품 목록에서 꺼내오기 위함
			Product goods = new Product();
			
			//목록에서 추가한 상품 찾기
			for(int i = 0; i < goodsList.size(); i++) {
				goods = goodsList.get(i);
				if(goods.getPid().equals(pid)) //추가한 상품코드와 일치하면 빠져나온다
					break; //빠져나온다
			}
			
			//상품 세션 발급
			List<Product> list = (ArrayList<Product>)session.getAttribute("cartlist");
			if(list == null) {
				list = new ArrayList<>();
				session.setAttribute("cartlist", list);
			}
			
			//요청 아이디의 상품이 기존 장바구니에 있으면 수량 증가
			int cnt = 0; //장바구니에 추가한 횟수
			Product goodsQnt = new Product();
			
			for(int i = 0; i < list.size(); i++) {
				goodsQnt = list.get(i);
				if(goodsQnt.getPid().equals(pid)) { //기존에 추가한 동일 품목이 있으면
					cnt++;
					//int orderQuantity = goodsQnt.getQuantity() + 1추가 주문된 상품 객체
					goodsQnt.setQuantity(goodsQnt.getQuantity() + 1);
				}
			}
			//장바구니에 해당 물품이 없는 품목은 수량을 1로 정함
			if(cnt == 0) {
				goods.setQuantity(1);
				list.add(goods);
			}
			
			// nextPage = "/productinfo.do?pid=" + pid;
		}else if(command.equals("/cart.do")) {
			//상품 세션 유지
			List<Product> cartlist = (ArrayList<Product>)session.getAttribute("cartlist");
			if(cartlist == null) {
				cartlist = new ArrayList<>();
			}
			
			//합계
			int sum = 0; //총 합계
			int unit_sum = 0; //품목별 합계 = 가격 x 수량
			
			for(int i = 0; i < cartlist.size(); i++) {
				Product product = cartlist.get(i); //장바구니에 담긴 품목
				unit_sum = product.getPrice() * product.getQuantity();
				sum += unit_sum;
			}
			
			//세션 아이디 얻기
			String cartId = session.getId();
			
			request.setAttribute("cartlist", cartlist);
			request.setAttribute("cartId", cartId);
			request.setAttribute("sum", sum);
			
			nextPage = "/product/cart.jsp";
		}else if(command.equals("/deletecart.do")) { //전체 삭제
			//장바구니 품목 전체 삭제 : 세션 삭제
			session.invalidate();
		}else if(command.equals("/removecart.do")) { //개별 품목 삭제
			String pid = request.getParameter("pid");
			//세션 유지
			List<Product> cartlist = (ArrayList<Product>)session.getAttribute("cartlist");
			//장바구니에서 품목을 찾아서 삭제하기
			for(int i = 0; i < cartlist.size(); i++) {
				Product product = cartlist.get(i);
				if(product.getPid().equals(pid)) {//삭제 요청한 아이디와 품목이 일치하면
					cartlist.remove(product); //arraylist의 삭제 메서드 사용
				}
			}
		}else if(command.equals("/shippingform.do")) { //배송 정보
			String cartId = session.getId();
			request.setAttribute("cartId", cartId);
			nextPage = "/product/shippingform.jsp";
		}else if(command.equals("/shippinginfo.do")) {
			//쿠키 사용 : 주문 내역 만들기
			//Cookie("쿠키이름", 객체(쿠키값)) : 한글 인코딩
			Cookie shippingId = new Cookie("Shipping_cartId",
					URLEncoder.encode(request.getParameter("cartId"), "utf-8"));
			Cookie sname = new Cookie("Shipping_name",
					URLEncoder.encode(request.getParameter("sname"), "utf-8"));
			Cookie shippingdate = new Cookie("Shipping_shippingdate",
					URLEncoder.encode(request.getParameter("shippingdate"), "utf-8"));
			Cookie zipcode = new Cookie("Shipping_zipcode",
					URLEncoder.encode(request.getParameter("zipcode"), "utf-8"));
			Cookie address = new Cookie("Shipping_address",
					URLEncoder.encode(request.getParameter("address"), "utf-8"));
			
			//쿠키 유효기간 : 1일로 설정
			shippingId.setMaxAge(24*60*60);
			sname.setMaxAge(24*60*60);
			shippingdate.setMaxAge(24*60*60);
			zipcode.setMaxAge(24*60*60);
			address.setMaxAge(24*60*60);
			
			//클라이언트로 컴으로 쿠키 전송
			response.addCookie(sname);
			response.addCookie(shippingdate);
			response.addCookie(zipcode);
			response.addCookie(address);
			response.addCookie(shippingId);
			
			
			
			//1. 쿠키 정보 가져와서 : 배열 쓰기
			String shipping_cartId = "";
			String shipping_sname = "";
			String shipping_shippingdate = "";
			String shipping_zipcode = "";
			String shipping_address = "";
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i]; //인덱스별로 쿠키를 생성 (0번부터)
					String cname = cookie.getName(); // 쿠키이름 가져옴
					//한글 복호화(디코딩: 문자열로 변환)
					if(cname.equals("Shipping_cartId")) 
						shipping_cartId = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_sname")) 
						shipping_sname = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_shippingdate")) 
						shipping_shippingdate = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_zipcode")) 
						shipping_zipcode = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_address")) 
						shipping_address = URLDecoder.decode(cookie.getValue(), "utf-8");
				}
			}
			
			//합계
			int sum = 0; //총 합계
			int unit_sum = 0; //품목별 합계 = 가격 x 수량
			List<Product> cartlist = (ArrayList<Product>)session.getAttribute("cartlist");
			
			for(int i = 0; i < cartlist.size(); i++) {
				Product product = cartlist.get(i); //장바구니에 담긴 품목
				unit_sum = product.getPrice() * product.getQuantity();
				sum += unit_sum;
			}
			
			//2. 모델 만들기
			request.setAttribute("shipping_cartId", shipping_cartId);
			request.setAttribute("shipping_sname", shipping_sname);
			request.setAttribute("shipping_shippingdate", shipping_shippingdate);
			request.setAttribute("shipping_zipcode", shipping_zipcode);
			request.setAttribute("shipping_address", shipping_address);
			request.setAttribute("sum", sum);
			
			nextPage = "/product/orderconfirm.jsp";
		}else if(command.equals("/thankscustomer.do")) {
			//카트 아이디와 배송일 쿠카정보를 가져와서 모델로보내기
			String shipping_cartId = "";
			String shipping_shippingdate = "";
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i]; //인덱스별로 쿠키를 생성 (0번부터)
					String cname = cookie.getName(); // 쿠키이름 가져옴
					//한글 복호화(디코딩: 문자열로 변환)
					if(cname.equals("Shipping_cartId")) 
						shipping_cartId = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_shippingdate")) 
						shipping_shippingdate = URLDecoder.decode(cookie.getValue(), "utf-8");
				}
			}
			//모델 보내기
			request.setAttribute("shipping_cartId", shipping_cartId);
			request.setAttribute("shipping_shippingdate", shipping_shippingdate);
			
			//주문 완료되면 모든 세션삭제
			session.invalidate();
			//모든 쿠키 삭제 : setMaxAge(0)
			if(cookies != null) {
				for(int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i]; //인덱스별로 쿠키를 생성 (0번부터)
					String cname = cookie.getName(); // 쿠키이름 가져옴
					//한글 복호화(디코딩: 문자열로 변환)
					if(cname.equals("Shipping_cartId")) 
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_sname"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_shippingdate")) 
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_zipcode")) 
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_address")) 
						cookie.setMaxAge(0);
				}
			}
			nextPage = "/product/thankscustomer.jsp";
		}
		
		
		
		//페이지 이동
		//redirect와 forward 구분 필요
		if(command.equals("/insertproduct.do")) {
			response.sendRedirect("/productlist.do");
		}else if(command.equals("/addcart.do")) {
			String pid = request.getParameter("pid");
			response.sendRedirect("/productinfo.do?pid=" + pid);
		}else if(command.equals("/deletecart.do") || command.equals("/removecart.do")) {
			response.sendRedirect("/cart.do");
		}else {
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
	}
}

