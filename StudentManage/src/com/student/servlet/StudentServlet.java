package com.student.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.bean.Student;
import com.student.db.DB;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getServletPath();

		uri = uri.substring(1, uri.lastIndexOf('.'));

		if (uri.equals("Main")) {

			String search = request.getParameter("searchText");
			String sql = "SELECT * FROM STUDENT";
			if (search != null && !search.equals("")) {
				search = new String(request.getParameter("searchText")
						.getBytes("ISO-8859-1"), "UTF-8");
				sql = "SELECT * FROM STUDENT WHERE USERNAME LIKE '%" + search
						+ "%' OR NO LIKE '%" + search + "%'";

			} else {
				search = "";
			}

			List<Student> lss = new DB().getStudentList(sql, null);
			request.setAttribute("lss", lss);
			request.setAttribute("searchText", search);
			request.getRequestDispatcher("Main.jsp").forward(request, response);

		} else if (uri.equals("DeleteStudent")) {
			String id = request.getParameter("id");
			new DB().ExecuteSql("DELETE FROM STUDENT WHERE ID=" + id, null);
			response.sendRedirect("Main.student");
		} else if (uri.equals("UpdateStudent")) {
			String id = request.getParameter("id");
			String sql = "SELECT * FROM STUDENT WHERE ID=" + id;
			Student stu = new DB().getStudent(sql, null);
			request.setAttribute("stu", stu);
			request.getRequestDispatcher("UpdateStudent.jsp").forward(request,
					response);
		} else if(uri.equals("UpdateStudents")){
			String id = request.getParameter("ID");
			List<Object> params = new ArrayList<Object>();
			params.add(request.getParameter("No"));
			params.add(request.getParameter("Username"));
			params.add(request.getParameter("Password"));
			params.add(id);
			
			String sql = "UPDATE STUDENT SET NO=?,USERNAME=?,PASSWORD=? WHERE ID=?";
			
			new DB().ExecuteSql(sql, params);
			response.sendRedirect("Main.student");
		} else if (uri.equals("AddStudent")) {

			String sql = "INSERT INTO STUDENT(NO,USERNAME,PASSWORD) VALUES(?,?,?)";
			List<Object> params = new ArrayList<Object>();
			params.add(request.getParameter("No"));
			params.add(request.getParameter("Username"));
			params.add(request.getParameter("Password"));
			new DB().ExecuteSql(sql, params);
			response.sendRedirect("Main.student");

		}

	}

}
