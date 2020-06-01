package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/loginurl")
public class CustomerController extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,res);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=null;
		String password=null;
		CustomerDAO dao=null;
		PrintWriter pw=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String dbpassword=null;
		RequestDispatcher rd=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		String query="select password from login where username=?";
		String query1="select * from customer_details where username=?";
		String query3="select * from customer_pol_rank ORDER BY pollution_level ASC ";
		String query4="select * from customer_pol_rank";
		int rank=1;
	
		username=req.getParameter("user");
		password=req.getParameter("pass");
		dao=CustomerDAO.getInstance();
		Connection con=dao.getConnection();
		try {
			int x=0;
			ps=con.prepareStatement(query);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()==false)
				x=1;
			rs.previous();
			while(rs.next()){
				dbpassword=rs.getString(1);
			}
			if(dbpassword!=null && dbpassword.equals(password)!=true || x==1){
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('User or password incorrect');");
				pw.println("location='login.html';");
				pw.println("</script>");
			}
			else{
				ps.close();
				rs.close();
				req.setAttribute("username", username);
				req.setAttribute("password",password);
				ps=con.prepareStatement(query1);
				ps.setString(1,username);
				rs=ps.executeQuery();
				while(rs.next()){
					req.setAttribute("city", rs.getString(2));
					req.setAttribute("state", rs.getString(3));
					req.setAttribute("email", rs.getString(4));
				}
//				res.sendRedirect("Dashboard.jsp");
				ps=con.prepareStatement(query3);
				ps.execute();
				ps.close();
				ps=con.prepareStatement(query4);
				rs=ps.executeQuery();
			      while(rs.next()){
					if((username.equals(rs.getString(1)))!=true)
						rank++;
					else
						break;
				}
				//System.out.println(rank);
				req.setAttribute("rank", String.valueOf(rank));
				rd=req.getRequestDispatcher("DashBoard.jsp");
				rd.include(req, res);

			}
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
				try {
					if(rs!=null)
					rs.close();
					if(ps!=null)
						ps.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			dao.closeConnection();
		}
		
		
		
	}

}
