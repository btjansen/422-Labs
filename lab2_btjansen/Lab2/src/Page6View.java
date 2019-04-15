import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Page6View extends HttpServlet {	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		sb.append("Page 6</TITLE></HEAD><BODY>\n");
		sb.append("<b>Review Information</b><br />");
		
		sb.append("User Name: " + req.getAttribute("fName") + " " + req.getAttribute("lName"));
		sb.append("<br />Programming Languages: " + req.getAttribute("langs"));
		sb.append("<br />Available Days: " + req.getAttribute("days"));
		sb.append("<br />Favorite Movie: " + req.getAttribute("movie"));
		
		sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/welcome'>Cancel</a>");
		sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=2'>Edit</a>");
		sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=7'>Confirm</a>");
		
		
		sb.append("</Body></HTML>");
		res.setStatus(res.SC_OK);
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
}