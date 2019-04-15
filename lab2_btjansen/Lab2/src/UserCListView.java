import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCListView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		sb.append("Success!</TITLE></HEAD><BODY>\n");
		sb.append("<b>User Controller Page!</b>");
		sb.append("<br />User Name: " + req.getAttribute("fName") + " " + req.getAttribute("lName"));
		sb.append("<br />Programming Languages: " + req.getAttribute("langs"));
		sb.append("<br />Available Days: " + req.getAttribute("days"));
		sb.append("<br />Favorite Movie: " + req.getAttribute("movie"));
		
		sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/usercontroller?id=" + req.getAttribute("id") + "&action=delete'>Remove User</a>");
		sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/welcome'>Return to Page 1</a>");
		
		sb.append("</Body></HTML>");
		res.setStatus(res.SC_OK);
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
}