import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		sb.append("Error!</TITLE></HEAD><BODY>\n");
		sb.append("<b>Error with request page parameter!</b>");
		
		sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=2'>Restart Entry</a>");
		
		sb.append("</Body></HTML>");
		res.setStatus(res.SC_BAD_REQUEST);
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
}