package com.suyasuya.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		System.out.println("id: " + id + ", password: " + password);
		
		AuthLogic logic = new AuthLogic();
		String errorMsg = logic.checkLogin(id, password);
		
		if (errorMsg == null) {
			System.out.println("ログイン成功");
			request.getRequestDispatcher("/WEB-INF/jsp/Main.jsp").forward(request, response);
		} else {			
			System.out.println(errorMsg);
			request.setAttribute("input_id", id);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		
		
		
		
	}

}
