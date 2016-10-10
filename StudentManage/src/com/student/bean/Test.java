package com.student.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {

	public static void main(String[] args) throws Exception{

		Class.forName("com.mysql.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManager", "root", "root");
		
		PreparedStatement pstm = conn.prepareStatement("select * from Student");
		
		ResultSet rs = pstm.executeQuery();
		
		pstm = conn.prepareStatement("select * from Teacher");

	}

}
