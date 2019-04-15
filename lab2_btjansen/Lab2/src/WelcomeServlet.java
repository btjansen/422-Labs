import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class WelcomeServlet extends HttpServlet {
	private static String _filename = null;
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		_filename = sc.getInitParameter("userList");
		if (_filename == null || _filename.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param user list with value " + _filename);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		// process headers
	
		// process request params
		String fName = req.getParameter("firstName");
		String lName = req.getParameter("lastName");
		String password = req.getParameter("password");
		
		// perform processing
		
		//Check password == ser422
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		
		if (password.equals("ser422")) {
			HttpSession session = req.getSession(true);			
			session.setAttribute("firstName", fName);
			session.setAttribute("lastName", lName);			
			
			Cookie c = new Cookie("firstName", fName);
			Cookie d = new Cookie("lastName", lName);
			c.setMaxAge(604800);
			d.setMaxAge(604800);
			res.addCookie(c);
			res.addCookie(d);
			
			sb.append("Success!</TITLE></HEAD><BODY>\n");
			sb.append("<b>Welcome! " + fName + " " + lName + "</b>");			
			sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/'>Logout</a> ");		
			
			//Create list of entries from data file
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
			UserList uList = null;
			try {
				uList = new UserList(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sb.append("<TABLE>");
			sb.append("<tr>");
			sb.append("<th>Number</th>");
			sb.append("<th>Full Name</th>");
			sb.append("<th>Programming Languages</th>");
			sb.append("<th>Meeting Availability Days</th>");
			sb.append("<th>Favorite Movie</th>");
			sb.append("</tr>");
			for (int i = 1; i < uList.getSize() + 1; i++) {
				sb.append("<tr><br />");
				sb.append("<td>" + i + "</td><br />");
				sb.append("<td><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/usercontroller?id=" + i + "&action=list'>" + uList.getEntry(Integer.toString(i)).getFirstName() + " " + uList.getEntry(Integer.toString(i)).getLastName() + "</a></td>");
				sb.append("<td>" + uList.getEntry(Integer.toString(i)).getLanguages() + "</td>");
				sb.append("<td>" + uList.getEntry(Integer.toString(i)).getAvailableDays() + "</td>");
				sb.append("<td>" + uList.getEntry(Integer.toString(i)).getFavoriteMovie() + "</td>");
				sb.append("</tr>");
			}
			sb.append("</TABLE>");
			
			//If user already exists in the list, save their userID number
			if (uList.getUserNum(fName, lName) != null) {
				String key = uList.getUserNum(fName, lName);
				//Save user info to the session
				session.setAttribute("UserID", key);
				session.setAttribute("langs", uList.getEntry(key).getLanguages());
				session.setAttribute("days", uList.getEntry(key).getAvailableDays());
				session.setAttribute("movie", uList.getEntry(key).getFavoriteMovie());
				//show user info
				sb.append("<br /><br /><b>Your Information:</b>");
				sb.append("<TABLE>");
				sb.append("<tr>");
				sb.append("<th>Number</th>");
				sb.append("<th>Full Name</th>");
				sb.append("<th>Programming Languages</th>");
				sb.append("<th>Meeting Availability Days</th>");
				sb.append("<th>Favorite Movie</th>");
				sb.append("<th>Action</th>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>" + key + "</td>");
				sb.append("<td><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/usercontroller?id=" + key + "&action=list'>" + uList.getEntry(key).getFirstName() + " " + uList.getEntry(key).getLastName() + "</a></td>");
				sb.append("<td>" + uList.getEntry(key).getLanguages() + "</td>");
				sb.append("<td>" + uList.getEntry(key).getAvailableDays() + "</td>");
				sb.append("<td>" + uList.getEntry(key).getFavoriteMovie() + "</td>");
				sb.append("<td><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=2'>EDIT</a></td>");
				sb.append("</tr>");
				//show best matches
			}
			else {
				sb.append("<br /><br /><b>You have not saved any information!</b>");
				sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=2'>Create</a>");
				//create hyperlink
			}
			
			
			
			sb.append("</Body></HTML>");
			res.setStatus(res.SC_OK);
		}
		else {
			sb.append("Incorrect Password</TITLE></HEAD><BODY>\n");
			sb.append("Incorrect Password");			
			sb.append("<br /><br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/'>Return</a> ");		
			sb.append("</Body></HTML>");
			res.setStatus(res.SC_UNAUTHORIZED);
		}
		
		
		// assemble res payload
		
		
		// assign res headers
		res.setContentType("text/html");
		
		
		// write out response
		PrintWriter out = res.getWriter();
		out.print(sb.toString());		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		String fName = session.getAttribute("firstName").toString();
		String lName = session.getAttribute("lastName").toString();
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>");
		sb.append("Welcome!</TITLE></HEAD><BODY>\n");
		sb.append("<b>Welcome! " + fName + " " + lName + "</b>");			
		sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/'>Logout</a> ");		

		InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
		UserList uList = null;
		try {
			uList = new UserList(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sb.append("<TABLE>");
		sb.append("<tr>");
		sb.append("<th>Number</th>");
		sb.append("<th>Full Name</th>");
		sb.append("<th>Programming Languages</th>");
		sb.append("<th>Meeting Availability Days</th>");
		sb.append("<th>Favorite Movie</th>");
		sb.append("</tr>");
		for (int i = 1; i < uList.getSize() + 1; i++) {
			sb.append("<tr>");
			sb.append("<td>" + i + "</td><br />");
			sb.append("<td><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/usercontroller?id=" + i + "&action=list'>" + uList.getEntry(Integer.toString(i)).getFirstName() + " " + uList.getEntry(Integer.toString(i)).getLastName() + "</a></td>");
			sb.append("<td>" + uList.getEntry(Integer.toString(i)).getLanguages() + "</td>");
			sb.append("<td>" + uList.getEntry(Integer.toString(i)).getAvailableDays() + "</td>");
			sb.append("<td>" + uList.getEntry(Integer.toString(i)).getFavoriteMovie() + "</td>");
			sb.append("</tr>");
		}
		sb.append("</TABLE>");
		
		if (uList.getUserNum(fName, lName) != null) {
			String key = uList.getUserNum(fName, lName);
			//Save user info to the session
			session.setAttribute("UserID", key);
			session.setAttribute("Languages", uList.getEntry(key).getLanguages());
			session.setAttribute("Days", uList.getEntry(key).getAvailableDays());
			session.setAttribute("Movie", uList.getEntry(key).getFavoriteMovie());
			//show user info
			sb.append("<br /><br /><b>Your Information:</b>");
			sb.append("<TABLE>");
			sb.append("<tr>");
			sb.append("<th>Number</th>");
			sb.append("<th>Full Name</th>");
			sb.append("<th>Programming Languages</th>");
			sb.append("<th>Meeting Availability Days</th>");
			sb.append("<th>Favorite Movie</th>");
			sb.append("<th>Action</th>");
			sb.append("</tr>");
			sb.append("<tr><br />");
			sb.append("<td>" + key + "</td>");
			sb.append("<td><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/usercontroller?id=" + key + "&action=list'>" + uList.getEntry(key).getFirstName() + " " + uList.getEntry(key).getLastName() + "</a></td>");
			sb.append("<td>" + uList.getEntry(key).getLanguages() + "</td>");
			sb.append("<td>" + uList.getEntry(key).getAvailableDays() + "</td>");
			sb.append("<td>" + uList.getEntry(key).getFavoriteMovie() + "</td>");
			sb.append("<td><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=2'>EDIT</a></td>");
			sb.append("</tr>");
			//show best matches
		}
		else {
			sb.append("<br /><br /><b>You have not saved any information!</b>");
			sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab2_btjansen/controller?page=2'>Create</a>");
			//create hyperlink
		}
		
		sb.append("</Body></HTML>");
		res.setStatus(res.SC_OK);
		
		// assign res headers
		res.setContentType("text/html");
		
		
		// write out response
			PrintWriter out = res.getWriter();
			out.print(sb.toString());
	}
	
	
}
