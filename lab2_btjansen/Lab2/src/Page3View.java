import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Page3View extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		sb.append("Page 3</TITLE></HEAD><BODY>\n");
		sb.append("<b>Enter Programming Languages</b><br />");
		
		sb.append("<form action='./controller?page=4' METHOD='POST'>");
		sb.append("Languages: <input type='text' name='langs' value='" + req.getAttribute("langs") + "'><br><br>");
		sb.append("<input type='SUBMIT'>");
		sb.append("</form>");
		sb.append("<a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=2'>Previous</a>");
		
		
		sb.append("</Body></HTML>");
		res.setStatus(res.SC_OK);
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		sb.append("Page 3</TITLE></HEAD><BODY>\n");
		sb.append("<b>Enter Programming Languages</b><br />");
		
		sb.append("<form action='./controller?page=4' METHOD='POST'>");
		sb.append("Languages: <input type='text' name='langs' value='" + req.getAttribute("langs") + "'><br><br>");
		sb.append("<input type='SUBMIT'>");
		sb.append("</form>");
		sb.append("<a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=2'>Previous</a>");
		
		
		sb.append("</Body></HTML>");
		res.setStatus(res.SC_OK);
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
}