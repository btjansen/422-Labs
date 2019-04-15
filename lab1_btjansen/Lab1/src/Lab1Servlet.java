import java.io.*;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class Lab1Servlet extends HttpServlet {
	private static String _filename = null;
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		_filename = sc.getInitParameter("taskList");
		if (_filename == null || _filename.length() == 0) {
			throw new ServletException();
		}
		System.out.println("Loaded init param task list with value " + _filename);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		// process headers
		String browserType = req.getHeader("User-Agent");
		
		
		// process request params
		String name = req.getParameter("Name");
		String description = req.getParameter("Description");
		String time = req.getParameter("Time");
		String priority = req.getParameter("Priority");
		String day = req.getParameter("Week");
		
		
		// perform proccessing
		StringBuilder sb = new StringBuilder("<HTML><HEAD><TITLE>Success!</TITLE></HEAD><BODY");
		if (browserType.contains("Firefox")) {
			sb.append(" bgcolor=\'#ADD8E6\'>\n");
			//System.out.println("BACKGROUND BLUE");
		}
		else {
			sb.append(">\n");
		}
		sb.append("Success! Task was added to the list");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
		TaskList tList = null;
		try {
			tList = new TaskList(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tList.addEntry(name, description, day, time, priority);
		tList.saveTaskList(_filename);
		
		sb.append("<br />Task List length = " + tList.getSize());
		
		sb.append("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab1-btjansen/'>Back to Input Form</a> ");
		//System.out.println("Host = " + req.getServerName());
	    //System.out.println("Port = " + req.getServerPort());
	    //System.out.println("URL = " + req.getRequestURL());
		
		sb.append("</Body></HTML>");
		//Replace same task name
		//return number of tasks
		
		// assemble res payload
		
		
		// assign res headers
		res.setContentType("text/html");
		res.setStatus(res.SC_OK);
		
		// write out response
		PrintWriter out = res.getWriter();
		out.print(sb.toString());
		
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException	{
				
				PrintWriter out= res.getWriter();
				String contentType = "text/html";
				
				//Process headers
				String acceptsHeader = req.getHeader("Accept");
				String[] acceptsFormats = acceptsHeader.split(",");
				for (String s : acceptsFormats) {
					if (s.equals("text/plain")) {
						contentType = "text/plain";
						break;
					}
				}
				
				String browserType = req.getHeader("User-Agent");
				boolean filterParams = false;
				
				InputStream is2 = this.getClass().getClassLoader().getResourceAsStream(_filename);
				TaskList filterList = null;
				try {
					filterList = new TaskList(is2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				String description = req.getParameter("description");
				if (description != null && description.length() != 0) {
					//filterParams += description + ",";
					if (filterParams == false) {
						filterParams = true;
					}					
					filterList.checkDescription(description);				
				}
				
				String day = req.getParameter("days");
				if (day != null && day.length() != 0) {
					//filterParams += day + ",";
					if (filterParams == false) {
						filterParams = true;
					}
					char[] daysFilter = new char[day.length()];
					for (int i = 0; i < day.length(); i++) {
						daysFilter[i] = day.charAt(i);
					}
					filterList.checkDay(daysFilter);
				}
				
				String priority = req.getParameter("custom");
				if (priority != null && priority.length() != 0) {
					//filterParams += priority + ",";
					if (filterParams == false) {
						filterParams = true;
					}
					filterList.checkPriority(priority);
				}
				

				// now get the task list file as an input stream
				// here we use an alternative method that gets it relative to the CLASSPATH
				InputStream is = this.getClass().getClassLoader().getResourceAsStream(_filename);
				TaskList tList = null;
				try {
					tList = new TaskList(is);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				res.setContentType(contentType);
				if (!contentType.equals("text/plain")) {
					out.println("<HTML><HEAD><TITLE>Task List</TITLE></HEAD><BODY");
					if (browserType.contains("Firefox")) {
						out.println(" bgcolor=\'#ADD8E6\'>");
						//System.out.println("BACKGROUND BLUE");
					}
					else {
						out.println(">");
					}
				}
				
				

				try {
					//System.out.println("Working Directory = " + System.getProperty("user.dir"));
					if (filterParams == false) {
						String[] entries = tList.listEntries();
						for (int i = 0; i < entries.length; i++) {
							int place = i+1;
							if (contentType.equals("text/plain")) {
								out.println("" + place + ": " + entries[i] + "\n\n");
							}
							else {
								out.println("<b>" + place + ":</b> " + entries[i] + "<br>");
							}
							
						}
					}
					else {
						String[] entries = filterList.listEntries();
						for (int i = 0; i < entries.length; i++) {
							int place = i+1;
							if (entries[i] != null) {
								if (contentType.equals("text/plain")) {
									out.println("" + place + ": " + entries[i] + "\n\n");
								}
								else {
									out.println("<b>" + place + ":</b> " + entries[i] + "<br>");
								}
							}								
						}						
					}										
				} catch (Exception exc) {					
					if (contentType.equals("text/plain")) {
						out.println("Java exception satisfying request");
					}
					else {
						out.println("<p>Java exception satisfying request</p>");
					}
					exc.printStackTrace();
				}
				
				if (!contentType.equals("text/plain")) {
					out.println("<br /><a href='http://" + req.getServerName() + ":" + req.getServerPort() + "/lab1-btjansen/'>Back to Input Form</a> ");
					out.println("</BODY></HTML>");
				}
				
			}
}