import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Page2View extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		sb.append("Page 2</TITLE></HEAD><BODY>\n");		
		sb.append("<b>Enter first and last name</b><br />");
		
		sb.append("<form action='./controller?page=3' METHOD='POST'>");
		sb.append("First Name: <input type='text' name='firstname' value='" + req.getAttribute("fName") + "'><br><br>");
		sb.append("Last Name: <input type='text' name='lastname' value='" + req.getAttribute("lName") + "'><br><br>");
		sb.append("<input type='SUBMIT'>");
		sb.append("</form>");
		
		
		sb.append("</Body></HTML>");
		res.setStatus(res.SC_OK);
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
}