package kr.co.ansany.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ansany.cart.model.service.CartService;
import kr.co.ansany.cart.model.vo.Cart;
import kr.co.ansany.member.model.vo.Member;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(name = "CartView", urlPatterns = { "/cartView.do" })
public class CartViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		// 값추출
		HttpSession session = request.getSession(false);
		Member m = (Member) session.getAttribute("m");
		// 비즈니스 로직
		// 결과처리: 로그인하면 장바구니 페이지로 넘어가기, 안 했으면 로그인창으로 넘어가기
		if(m != null) {
			String memberId = m.getMemberId();
			CartService service = new CartService();
			ArrayList<Cart> list = service.selectAllCart(memberId);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/cart/cart.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
		}else {
			response.sendRedirect("/loginFrm.do");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
