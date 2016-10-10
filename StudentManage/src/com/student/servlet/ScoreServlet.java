package com.student.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.bean.Classes;
import com.student.bean.Score;
import com.student.bean.Student;
import com.student.bean.Teacher;
import com.student.db.DB;

/**
 * Servlet implementation class ScoreServlet
 */
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScoreServlet() {
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

		if (uri.equals("Score")) {

			String search = request.getParameter("searchText");
			String sql = "SELECT * FROM Score";
			if (search != null && !search.equals("")) {
				search = new String(request.getParameter("searchText")
						.getBytes("ISO-8859-1"), "UTF-8");
				sql = "SELECT * FROM Score WHERE SID IN (SELECT ID FROM STUDENT WHERE USERNAME LIKE '%"
						+ search
						+ "%' OR NO LIKE '%"
						+ search
						+ "%') OR CID IN(SELECT ID FROM Classes WHERE Name like '%"
						+ search + "%')";

			} else {
				search = "";
			}

			List<Score> lss = new DB().getScoreList(sql, null);
			request.setAttribute("lss", lss);
			request.setAttribute("searchText", search);
			request.getRequestDispatcher("Score.jsp").forward(request,
					response);

		} else if (uri.equals("DeleteScore")) {
			String id = request.getParameter("id");
			new DB().ExecuteSql("DELETE FROM Score WHERE ID=" + id, null);
			response.sendRedirect("Score.score");
		} else if (uri.equals("UpdateScore")) {
			String id = request.getParameter("id");
			 String sql = "SELECT * FROM Score WHERE ID=" + id;

	            Score score = new DB().getScore(sql,null);

	           request.setAttribute("score", score);


	            sql = "SELECT * FROM CLASSES";
	            List<Classes> lsc = new DB().getClassesList(sql,null);

	            sql = "SELECT * FROM STUDENT";
	            List<Student> lss = new DB().getStudentList(sql,null);

	            request.setAttribute("lsc", lsc);
	            request.setAttribute("lss", lss);
			request.getRequestDispatcher("UpdateScore.jsp").forward(request,
					response);
		} else if (uri.equals("UpdateScores")) {
			String id = request.getParameter("ID");
			List<Object> params = new ArrayList<Object>();

			params.add(request.getParameter("CID"));
			params.add(request.getParameter("SID"));
			params.add(request.getParameter("score"));
			params.add(id);

			
			String sql = "UPDATE SCORE SET CID=?,SID=?,SCORE=? WHERE ID=?";

			new DB().ExecuteSql(sql, params);
			response.sendRedirect("Score.score");
		} else if (uri.equals("AddScore")) {
			List<Classes> lsc = new DB().getClassesList(
					"SELECT * FROM CLASSES", null);
			request.setAttribute("lsc", lsc);

            List<Student> lss = new DB().getStudentList("SELECT * FROM STUDENT",null);
            request.setAttribute("lss", lss);
			request.getRequestDispatcher("AddScore.jsp").forward(request,
					response);
		} else if (uri.equals("AddScores")) {

			String sql = "INSERT INTO Score(CID,SID,SCORE) VALUES(?,?,?)";
			List<Object> params = new ArrayList<Object>();

			params.add(request.getParameter("CID"));
			params.add(request.getParameter("SID"));
			params.add(request.getParameter("score"));
			new DB().ExecuteSql(sql, params);
			response.sendRedirect("Score.score");

		}
	}

}
