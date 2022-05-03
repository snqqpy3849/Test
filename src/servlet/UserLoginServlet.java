package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import bean.User;
import dao.UserDAO;

public class UserLoginServlet extends HttpServlet{
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User user = new UserDAO().getUser(name, password);
		if(user != null) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/listProduct");
		}
		else {
			response.sendRedirect("/login");
		}
	}
}
