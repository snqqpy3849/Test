package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderItem;
import bean.Product;
import dao.ProductDAO;

import javax.servlet.ServletException;

public class OderItemAddServlet extends HttpServlet{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		int num = Integer.parseInt(request.getParameter("num"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		Product p = new ProductDAO().getProduct(pid);
		
		OrderItem oi = new OrderItem();
		oi.setNum(num);
		oi.setProduct(p);
		
		List<OrderItem> ois = (List<OrderItem>) request.getSession().getAttribute("ois");
		
		if(ois == null) {
			ois = new ArrayList<>();
			request.getSession().setAttribute("ois", ois);
		}
		
		boolean found = false;
		for(OrderItem orderItem : ois) {
			if(orderItem.getProduct().getId() == oi.getProduct().getId()) {
				orderItem.setNum(orderItem.getNum() + oi.getNum());
				found = true;
				break;
			}
		}
		
		if(!found) {
			ois.add(oi);
		}
		response.sendRedirect("/listOrderItem");
	}
}
