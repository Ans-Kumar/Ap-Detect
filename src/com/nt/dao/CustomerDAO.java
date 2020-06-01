package com.nt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomerDAO {
	Connection con=null;
	private static CustomerDAO dao;

	public static CustomerDAO getInstance() {
		if (dao == null) {
			dao = new CustomerDAO();
			return dao;
		} else {
			return dao;
		}
	}

	public Connection getConnection() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///himanshu1","root", "root");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}


	public void closeConnection() {
		// TODO Auto-generated method stub]
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


}
