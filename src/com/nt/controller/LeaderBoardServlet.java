package com.nt.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nt.dao.CustomerDAO;

/**
 * Servlet implementation class LeaderBoardServlet
 */
@WebServlet("/LeaderBoardServlet")
public class LeaderBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaderBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html");
		RequestDispatcher rd=null;
		CustomerDAO dao=CustomerDAO.getInstance();
		Connection con=null;
		PreparedStatement ps=null;
		HttpSession session=null;
		ResultSet rs=null;
		String query="select * from customer_pol_rank ORDER BY pollution_level ASC ";
		String query1="select * from customer_pol_rank";
		con=dao.getConnection();
		try {
			ps=con.prepareStatement(query);
			ps.execute();
			session=req.getSession();
			String username=(String)session.getAttribute("username");
			ps.close();
			ps=con.prepareStatement(query1);
			rs=ps.executeQuery();
			int rank=1;
			while(rs.next()){
				if(username.equals(rs.getString(2))!=true)
					rank++;
				else
					break;
			}
			session.setAttribute("rank", String.valueOf(rank));
			rd=req.getRequestDispatcher("new1.html");
			rd.forward(req, res);
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
