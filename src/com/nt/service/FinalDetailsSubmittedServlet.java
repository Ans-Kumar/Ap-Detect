package com.nt.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nt.dao.CustomerDAO;

/**
 * Servlet implementation class FinalDetailsSubmittedServlet
 */

@WebServlet("/FinalDetailsSubmittedServlet")

public class FinalDetailsSubmittedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalDetailsSubmittedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		PrintWriter pw=null;
		String username=null;
		Double d=null;
		HttpSession session=null;
		session=req.getSession();
		res.setContentType("text/html");
		CustomerDAO dao=CustomerDAO.getInstance();
		Connection con=dao.getConnection();
		String query2="insert into customer_pol_rank values(?,?)";
		try{
			pw=res.getWriter();
		username=(String) session.getAttribute("username");
		d=Double.parseDouble((String) req.getAttribute("poll"));
		System.out.println(username+" "+d);
		ps=con.prepareStatement(query2);
		ps.setString(2, username);
		ps.setDouble(1,d);
		if(ps.executeUpdate()>0){
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Details Submitted');");
			pw.println("location='login.html';");
			pw.println("</script>");
		}
		ps.close();			
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		if(ps!=null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		dao.closeConnection();
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
