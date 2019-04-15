import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexServlet extends HttpServlet {	
	
	private static String processCookie(HttpServletRequest req, String name) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	      for (int i = 0; i < cookies.length; i++) {
	        if (cookies[i].getName().equals(name)) {
	          return cookies[i].getValue();
	        }
	      }
	    }
	    return null;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		String fName;
		String lName;
		
		/*if (session.getAttribute("firstName") != null) {
			fName = session.getAttribute("firstName").toString();
		}
		else*/ if (processCookie(req, "firstName") != null) {
			fName = processCookie(req, "firstName");
		}
		else {
			fName = "First Name";
		}
		/*if(session.getAttribute("lastName") != null) {
			lName = session.getAttribute("lastName").toString();
		}
		else*/ if (processCookie(req, "lastName") != null) {
			lName = processCookie(req, "lastName");
		}
		else {
			lName = "Last Name";
		}
		
		session.removeAttribute("UserID");
		session.removeAttribute("fName");
		session.removeAttribute("lName");
		session.removeAttribute("langs");
		session.removeAttribute("days");
		session.removeAttribute("movie");
		
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		sb.append("Index</TITLE></HEAD><BODY>\n");
		
		sb.append("<form action='./welcome' METHOD='POST'>");
		sb.append("First Name: <input type='text' name='firstName' value=" + fName + "><br><br>");
		sb.append("Last Name: <input type='text' name='lastName' value=" + lName + "><br><br>");
		sb.append("Password: <input type='text' name='password' value=Password><br><br>");
		sb.append("<input type='SUBMIT'>");
		sb.append("</form>");
		
		sb.append("</Body></HTML>");
		res.setStatus(res.SC_OK);
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
	}
}