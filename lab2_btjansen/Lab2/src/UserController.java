import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController extends HttpServlet {	
	private static String _filename = null;
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		_filename = sc.getInitParameter("userList");
		if (_filename == null || _filename.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param user list with value " + _filename);
	}	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
		UserList uList = null;
		try {
			uList = new UserList(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (req.getParameter("action").contentEquals("list") ) {
				String key = req.getParameter("id");
				req.setAttribute("id", key);
				req.setAttribute("fName", UserCModel.getUserFirstName(key, uList));
				req.setAttribute("lName", UserCModel.getUserLastName(key, uList));
				req.setAttribute("langs", UserCModel.getUserLanguages(key, uList));
				req.setAttribute("days", UserCModel.getUserDays(key, uList));
				req.setAttribute("movie", UserCModel.getUserMovie(key, uList));

				String view = "ShowList";
				req.getRequestDispatcher(view).forward(req, res);
			}
			
			if (req.getParameter("action").contentEquals("delete") ) {
				String key = req.getParameter("id");
				req.setAttribute("id", key);
				String view = "DeleteUser";
				req.getRequestDispatcher(view).forward(req, res);
			}
			
			if (req.getParameter("action").contentEquals("confirm") ) {
				String key = req.getParameter("id");
				uList = UserCModel.removeUser(key, uList);
				uList.saveUserList(_filename);
				//send user back to page 1
				String view = "welcome";
				res.sendRedirect(view);
			}
		} catch (Exception e) {
			String view = "Error2";
			req.getRequestDispatcher(view).forward(req, res);
		}
		
		
	}
}
