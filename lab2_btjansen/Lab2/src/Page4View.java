import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Page4View extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		sb.append("Page 4</TITLE></HEAD><BODY>\n");
		sb.append("<b>Enter Meeting Availability Days</b><br />");
		
		sb.append("<form action='./controller?page=5' METHOD='POST'>");
		sb.append("Days: <input type='text' name='days' value='" + req.getAttribute("days") + "'><br><br>");
		sb.append("<input type='SUBMIT'>");
		sb.append("</form>");
		sb.append("<a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=3'>Previous</a>");
		
		
		sb.append("</Body></HTML>");
		res.setStatus(res.SC_OK);
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		sb.append("Page 4</TITLE></HEAD><BODY>\n");
		sb.append("<b>Enter Meeting Availability Days</b><br />");
		
		sb.append("<form action='./controller?page=5' METHOD='POST'>");
		sb.append("Days: <input type='text' name='days' value='" + req.getAttribute("days") + "'><br><br>");
		sb.append("<input type='SUBMIT'>");
		sb.append("</form>");
		sb.append("<a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=3'>Previous</a>");
		
		
		sb.append("</Body></HTML>");
		res.setStatus(res.SC_OK);
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
}