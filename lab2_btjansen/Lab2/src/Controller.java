import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controller extends HttpServlet {	
	private static String _filename = null;
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		_filename = sc.getInitParameter("userList");
		if (_filename == null || _filename.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param user list with value " + _filename);
	}
	
	public static Cookie getCookie(HttpServletRequest req, String name) {
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		//check the "page" param to see which page is being loaded
		//populate the specific page boxes with info from the session
		
		try {
			HttpSession session = req.getSession(true);	
			String page = req.getParameter("page");
			if (page.equals("2")) {
				String fName;
				String lName;
				if (session.getAttribute("fName") == null) {
					fName = session.getAttribute("firstName").toString();
				}
				else {
					fName = session.getAttribute("fName").toString();
				}
				if (session.getAttribute("lName") == null) {
					lName = session.getAttribute("lastName").toString();
				}
				else {
					lName = session.getAttribute("lName").toString();
				}
				
				req.setAttribute("fName", fName);
				req.setAttribute("lName", lName);
				
				String view = "Page2";
				req.getRequestDispatcher(view).forward(req, res);
			}
			
			else if (page.equals("3")) {
				String langs = session.getAttribute("langs").toString();
				
				req.setAttribute("langs", langs);

				String view = "Page3";
				req.getRequestDispatcher(view).forward(req, res);
			}
			
			else if (page.equals("4")) {
				String days = session.getAttribute("days").toString();
				
				req.setAttribute("days", days);
				
				String view = "Page4";
				req.getRequestDispatcher(view).forward(req, res);
			}
			
			else if (page.equals("5")) {
				String movie = session.getAttribute("movie").toString();
				
				req.setAttribute("movie", movie);
				
				String view = "Page5";
				req.getRequestDispatcher(view).forward(req, res);
			}
			
			else if (page.equals("6")) {
				String movie = session.getAttribute("movie").toString();
				
				req.setAttribute("movie", movie);
				
				String view = "Page6";
				req.getRequestDispatcher(view).forward(req, res);
			}
			
			else if (page.equals("7")) {
				
				
				//add user to the list or edit if user already exists
				InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
				UserList uList = null;
				try {
					uList = new UserList(is);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (session.getAttribute("UserID") == null) {
					UserCModel.addUser(session.getAttribute("fName").toString(), session.getAttribute("lName").toString(), session.getAttribute("langs").toString(), session.getAttribute("days").toString(), session.getAttribute("movie").toString(), uList);
				}
				else {
					UserCModel.editUser(session.getAttribute("UserID").toString(), session.getAttribute("fName").toString(), session.getAttribute("lName").toString(), session.getAttribute("langs").toString(), session.getAttribute("days").toString(), session.getAttribute("movie").toString(), uList);
				}
				
				uList.saveUserList(_filename);
				session.setAttribute("firstName", session.getAttribute("fName").toString());
				session.setAttribute("lastName", session.getAttribute("lName").toString());
				
				//update cookie for names
				Cookie c = getCookie(req, "firstName");
				Cookie d = getCookie(req, "lastName");
				
				if (c != null) {
				    c.setValue(session.getAttribute("fName").toString());
				    c.setMaxAge(604800);
				    res.addCookie(c);
				}
				if (d != null) {
				    d.setValue(session.getAttribute("lName").toString());
				    d.setMaxAge(604800);
				    res.addCookie(d);
				}
				
				
				String view = "welcome";
				res.sendRedirect(view);
			}
			
			else {
				String view = "Error";
				res.sendRedirect(view);			
			}
		} catch (NullPointerException e) {
			String view = "Error";
			req.getRequestDispatcher(view).forward(req, res);
		}
				
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {		
		//check the "page" param to see which page is loaded
		//get the info from the request and save it to the session
		//forward to appropriate new page
		
		try {
			String page = req.getParameter("page");
			HttpSession session = req.getSession(true);	
			
			if (page.equals("3")) {
				String fName = req.getParameter("firstname");
				String lName = req.getParameter("lastname");

				session.setAttribute("fName", fName);
				session.setAttribute("lName", lName);
				
				if(session.getAttribute("langs") == null ) {
					req.setAttribute("langs", "");
				}
				else {
					req.setAttribute("langs", session.getAttribute("langs"));
				}
				
				String view = "Page3";
				req.getRequestDispatcher(view).forward(req, res);			
			}
			
			else if (page.equals("4")) {
				String langs = req.getParameter("langs");
				session.setAttribute("langs", langs);
				
				if(session.getAttribute("days") == null ) {
					req.setAttribute("days", "");
				}
				else {
					req.setAttribute("days", session.getAttribute("days"));
				}
				
				String view = "Page4";
				req.getRequestDispatcher(view).forward(req, res);			
			}
			
			else if (page.equals("5")) {
				String days = req.getParameter("days");
				session.setAttribute("days", days);
				
				if(session.getAttribute("movie") == null ) {
					req.setAttribute("movie", "");
				}
				else {
					req.setAttribute("movie", session.getAttribute("movie"));
				}
				
				String view = "Page5";
				req.getRequestDispatcher(view).forward(req, res);			
			}
			
			else if (page.equals("6")) {
				String movie = req.getParameter("movie");
				session.setAttribute("movie", movie);
				
				req.setAttribute("fName", session.getAttribute("fName"));
				req.setAttribute("lName", session.getAttribute("lName"));
				req.setAttribute("langs", session.getAttribute("langs"));
				req.setAttribute("days", session.getAttribute("days"));
				req.setAttribute("movie", session.getAttribute("movie"));
				
				String view = "Page6";
				req.getRequestDispatcher(view).forward(req, res);			
			}
			
			else {
				String view = "Error";
				res.sendRedirect(view);			
			}
		} catch (NullPointerException e) {
			String view = "Error";
			res.sendRedirect(view);
		}
		
	}
}
