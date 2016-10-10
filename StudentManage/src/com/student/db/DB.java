package com.student.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.student.bean.*;

public class DB {

	private String driver = "com.mysql.jdbc.Driver";
     
	private String url = "jdbc:mysql://localhost:3306/StudentManager";
	
	private String user = "root";

	private String password = "root";

	private Connection conn = null;

	private PreparedStatement pstmt = null;

	private ResultSet rs = null;

	public DB() {

		try {

			//加载驱动

			Class.forName(driver);

            //获取连接

			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询学生
	public List<Student> getStudentList(String sql, List<Object> params) {

		List<Student> lss = new ArrayList<Student>();
		try {
			if(conn.isClosed())
				conn =  DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			if (params != null)
				for (int i = 0; i < params.size(); i++) {

					pstmt.setObject(i + 1, params.get(i));

				}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setNo(rs.getString("no"));
				student.setPassword(rs.getString("password"));
				student.setUsername(rs.getString("username"));
				lss.add(student);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return lss;
	}


	public Student getStudent(String sql, List<Object> params) {

		Student stu = null;
		List<Student> lss = getStudentList(sql, params);
		if (lss != null && lss.size() > 0)
			stu = lss.get(0);
		return stu;
	}

	public List<Score> getScoreList(String sql, List<Object> params) {
		List<Score> lss = new ArrayList<Score>();
		try {
			if(conn.isClosed())
				conn =  DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			if (params != null)
				for (int i = 0; i < params.size(); i++) {

					pstmt.setObject(i + 1, params.get(i));

				}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Score score = new Score();
				score.setCID(rs.getInt("CID"));
				score.setId(rs.getInt("id"));
				score.setScore(rs.getInt("score"));
				score.setSID(rs.getInt("SID"));

				lss.add(score);
			}

			for(int i=0;i<lss.size();i++){
				lss.get(i).setStudent(this.getStudent("SELECT * FROM STUDENT WHERE ID="+lss.get(i).getSID(),null));
				lss.get(i).setClasses(this.getClasses("SELECT * FROM Classes WHERE ID="+lss.get(i).getCID(),null));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return lss;
	}
	
	public Score getScore(String sql,List<Object> params){
		
		Score score = null;
		
		List<Score> lsc = getScoreList(sql, params);
		if(lsc!=null&&lsc.size()>0)
			score = lsc.get(0);
		return score;
	}


	public List<Classes> getClassesList(String sql, List<Object> params) {
		List<Classes> lsc = new ArrayList<Classes>();
		try {
			if(conn.isClosed())
				conn =  DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			if (params != null)
				for (int i = 0; i < params.size(); i++) {

					pstmt.setObject(i + 1, params.get(i));

				}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Classes cls = new Classes();
				cls.setId(rs.getInt("id"));
				cls.setName(rs.getString("name"));

				lsc.add(cls);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return lsc;
	}


	public Classes getClasses(String sql,List<Object> params) {

		Classes cls = null;
		
		List<Classes> lsc = getClassesList(sql, params);
		if(lsc!=null&&lsc.size()>0)
			cls = lsc.get(0);
		return cls;
	}
	
	
	public List<Teacher> getTeacherList(String sql,List<Object> params){
		List<Teacher> lst = new ArrayList<Teacher>();
		try {
			if(conn.isClosed())
				conn =  DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			if (params != null)
				for (int i = 0; i < params.size(); i++) {

					pstmt.setObject(i + 1, params.get(i));

				}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setPassword(rs.getString("password"));
				teacher.setUsername(rs.getString("username"));
				teacher.setCID(rs.getInt("CID"));
				
				lst.add(teacher);
			}

			for(int i=0;i<lst.size();i++){
				
				lst.get(i).setClasses(this.getClasses("SELECT * FROM Classes WHERE ID="+lst.get(i).getCID(),null));
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return lst;
	}
	
	
	public Teacher getTeacher(String sql,List<Object> params){
		
		Teacher teacher = null;
		List<Teacher> lsc = getTeacherList(sql, params);
		if(lsc!=null&&lsc.size()>0)
			teacher = lsc.get(0);
		return teacher;
		
	}
	
	

	public Admin getAdmin(String sql,List<Object> params){
		Admin admin = null;
		try {
			if(conn.isClosed())
				conn =  DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			if (params != null)
				for (int i = 0; i < params.size(); i++) {

					pstmt.setObject(i + 1, params.get(i));

				}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				admin = new Admin();
				admin.setId(rs.getInt("id"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return admin;
	}
	
	//增删改
	public int ExecuteSql(String sql,List<Object> params){
		int result = 0;
		try {
			if(conn.isClosed())
				conn =  DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			if (params != null)
				for (int i = 0; i < params.size(); i++) {

					pstmt.setObject(i + 1, params.get(i));

				}
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return result;
	}
	
	
    //关闭连接
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
