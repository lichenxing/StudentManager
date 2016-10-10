package com.student.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.bean.Users;
import com.student.db.DB;

/**
 * Servlet implementation class UsersServlet
 */
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String uri = request.getServletPath();
		
		uri = uri.substring(1, uri.lastIndexOf('.'));
		
		
		if(uri.equals("login")){
			int type = Integer.parseInt(request.getParameter("type"));
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Object obj = null;
			List<Object> list = new ArrayList<Object>();
			list.add(username);
			list.add(password);
			if (type == 1)
            {
				
                obj = new DB().getStudent("SELECT * FROM STUDENT WHERE USERNAME=? AND PASSWORD=?",list);
            }
            else if (type == 2)
            {
            	obj = new DB().getTeacher("SELECT * FROM TEACHER WHERE USERNAME=? AND PASSWORD=?",list);
            }
            else if (type == 3)
            {
            	obj = new DB().getAdmin("SELECT * FROM ADMIN WHERE USERNAME=? AND PASSWORD=?",list);
            }
			
			
			if (obj != null)
            {

                request.getSession().setAttribute("user", obj);
                request.getSession().setAttribute("LoginType", type);
                response.sendRedirect("index.jsp");
                
            }
            else
            {
                request.setAttribute("message", "登陆失败，用户名或密码错误");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
			
		}else if(uri.equals("UpdateInfo")){
			
			int type = Integer.parseInt(request.getSession().getAttribute("LoginType").toString());  
			Users obj = (Users)request.getSession().getAttribute("user");
            String sql = "";
            if (type == 1)
            {
                sql ="UPDATE STUDENT SET PASSWORD=? WHERE ID=?";
            }
            else if (type == 2)
            {
            	sql ="UPDATE TEACHER SET PASSWORD=? WHERE ID=?";
            }
            else if (type == 3)
            {
            	sql ="UPDATE ADMIN SET PASSWORD=? WHERE ID=?";
            }
            
            List<Object> list =new ArrayList<Object>();
            list.add(request.getParameter("password"));
            list.add(obj.getId());
            new DB().ExecuteSql(sql, list);

            
            if (type == 1)
            {
                sql = "SELECT * FROM STUDENT WHERE ID="+obj.getId();
                obj = new DB().getStudent(sql, null);
            }
            else if (type == 2)
            {
            	sql = "SELECT * FROM TEACHER WHERE ID="+obj.getId();
                obj = new DB().getTeacher(sql, null);
            }
            else if (type == 3)
            {
            	sql = "SELECT * FROM ADMIN WHERE ID="+obj.getId();
                obj = new DB().getAdmin(sql, null);
            }

            request.getSession().setAttribute("user", obj);
            request.getSession().setAttribute("LoginType", type);
			response.sendRedirect("UserIndex.jsp");
		}
		
		
		
	}

}
