package com.nt.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nt.dao.CustomerDAO;

@WebServlet("/custurl")
public class CustomerDetailsServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = null;
		String city = null;
		String state = null;
		String email = null;
		String password = null;
		res.setContentType("text/html");
		username = req.getParameter("username");
		city = req.getParameter("city");
		state = req.getParameter("state");
		email = req.getParameter("email");
		password = req.getParameter("password");
		CustomerDAO dao = CustomerDAO.getInstance();
		Connection con = dao.getConnection();
		PreparedStatement ps = null;
		HttpSession session = null;
		session = req.getSession();
		RequestDispatcher rd = null;
		String query = "insert into customer_details values(?,?,?,?,?)";
		String query1 = "insert into login values(?,?)";
		con = dao.getConnection();
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, email);
			ps.setString(5, password);
			ps.executeUpdate();
			ps.close();
			ps = con.prepareStatement(query1);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
			ps.close();
			session.setAttribute("username", username);
			rd = req.getRequestDispatcher("Default.jsp");
			rd.forward(req, res);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			dao.closeConnection();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
