package com.student.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.bean.Classes;
import com.student.bean.Teacher;
import com.student.db.DB;

/**
 * Servlet implementation class ClassesServlet
 */
public class ClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassesServlet() {
        super();
        // TODO Auto-generated constructor stub
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

		if (uri.equals("Classes")) {

			String search = request.getParameter("searchText");
			String sql = "SELECT * FROM Classes";
			if (search != null && !search.equals("")) {
				search = new String(request.getParameter("searchText")
						.getBytes("ISO-8859-1"), "UTF-8");
				sql = "SELECT * FROM Classes WHERE NAME LIKE '%" + search
						+ "%'";

			} else {
				search = "";
			}

			List<Classes> lsc = new DB().getClassesList(sql, null);
			request.setAttribute("lsc", lsc);
			request.setAttribute("searchText", search);
			request.getRequestDispatcher("Classes.jsp").forward(request, response);

		} else if (uri.equals("DeleteClasses")) {
			String id = request.getParameter("id");
			new DB().ExecuteSql("DELETE FROM Classes WHERE ID=" + id, null);
			response.sendRedirect("Classes.classes");
		} else if (uri.equals("UpdateClasses")) {
			
			String id = request.getParameter("id");
			String sql = "SELECT * FROM CLASSES WHERE ID=" + id;
			Classes cls = new DB().getClasses(sql, null);
			request.setAttribute("cls", cls);
			request.getRequestDispatcher("UpdateClasses.jsp").forward(request,
					response);
		} else if(uri.equals("UpdateClassess")){
			String id = request.getParameter("ID");
			List<Object> params = new ArrayList<Object>();
			
			params.add(request.getParameter("Name"));
			
			params.add(id);
			
			String sql = "UPDATE Classes SET Name=? WHERE ID=?";
			
			new DB().ExecuteSql(sql, params);
			response.sendRedirect("Classes.classes");
		} else if(uri.equals("AddClasses")){
			
			request.getRequestDispatcher("AddTeacher.jsp").forward(request, response);
		}else if (uri.equals("AddClassess")) {

			String sql = "INSERT INTO Classes(NAME) VALUES(?)";
			List<Object> params = new ArrayList<Object>();
			
			params.add(request.getParameter("Name"));
			
			new DB().ExecuteSql(sql, params);
			response.sendRedirect("Classes.classes");

		}
	
	}

}
