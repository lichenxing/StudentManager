package com.student.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.bean.Classes;
import com.student.bean.Student;
import com.student.bean.Teacher;
import com.student.db.DB;

/**
 * Servlet implementation class TeacherServlet
 */
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getServletPath();

		uri = uri.substring(1, uri.lastIndexOf('.'));

		if (uri.equals("Teacher")) {

			String search = request.getParameter("searchText");
			String sql = "SELECT * FROM TEACHER";
			if (search != null && !search.equals("")) {
				search = new String(request.getParameter("searchText")
						.getBytes("ISO-8859-1"), "UTF-8");
				sql = "SELECT * FROM TEACHER WHERE USERNAME LIKE '%" + search
						+ "%' OR CID=(SELECT ID FROM CLASSES WHERE NAME LIKE '%"+search+"%')";

			} else {
				search = "";
			}

			List<Teacher> lst = new DB().getTeacherList(sql, null);
			request.setAttribute("lst", lst);
			request.setAttribute("searchText", search);
			request.getRequestDispatcher("Teacher.jsp").forward(request, response);

		} else if (uri.equals("DeleteTeacher")) {
			String id = request.getParameter("id");
			new DB().ExecuteSql("DELETE FROM TEACHER WHERE ID=" + id, null);
			response.sendRedirect("Teacher.teacher");
		} else if (uri.equals("UpdateTeacher")) {
			String id = request.getParameter("id");
			String sql = "SELECT * FROM TEACHER WHERE ID=" + id;
			Teacher teacher = new DB().getTeacher(sql, null);
			request.setAttribute("teacher", teacher);
			
			List<Classes> cls = new DB().getClassesList("SELECT * FROM CLASSES",null);
			request.setAttribute("cls", cls);
			request.getRequestDispatcher("UpdateTeacher.jsp").forward(request,
					response);
		} else if(uri.equals("UpdateTeachers")){
			String id = request.getParameter("ID");
			List<Object> params = new ArrayList<Object>();
			
			params.add(request.getParameter("Username"));
			params.add(request.getParameter("Password"));
			params.add(request.getParameter("CID"));
			params.add(id);
			
			String sql = "UPDATE TEACHER SET USERNAME=?,PASSWORD=?,CID=? WHERE ID=?";
			
			new DB().ExecuteSql(sql, params);
			response.sendRedirect("Teacher.teacher");
		} else if(uri.equals("AddTeacher")){
			List<Classes> cls = new DB().getClassesList("SELECT * FROM CLASSES",null);
			request.setAttribute("cls", cls);
			request.getRequestDispatcher("AddTeacher.jsp").forward(request, response);
		}else if (uri.equals("AddTeachers")) {

			String sql = "INSERT INTO TEACHER(USERNAME,PASSWORD,CID) VALUES(?,?,?)";
			List<Object> params = new ArrayList<Object>();
			
			params.add(request.getParameter("Username"));
			params.add(request.getParameter("Password"));
			params.add(request.getParameter("CID"));
			new DB().ExecuteSql(sql, params);
			response.sendRedirect("Teacher.teacher");

		}
	}

}
