import java.io.*;
import java.util.*;

public class TaskList {
	public static final String DEFAULT_FILENAME = "taskList.txt";
	private Formatter format;

	private Map<String, TaskEntry> _tList = new HashMap<String, TaskEntry>();

	public TaskList() throws IOException {
		this(DEFAULT_FILENAME); 
	}
	public TaskList(String fname) throws IOException {
		this(new BufferedReader(new FileReader(fname)));
	}
	public TaskList(InputStream is) throws IOException {
		this(new BufferedReader(new InputStreamReader(is)));
	}
	private TaskList(BufferedReader br) throws IOException {	
		String name = null;
		String description = null;
		String day = null;
		String time = null;
		String priority = null;

		try {
			String nextLine = null;
			while ( (nextLine=br.readLine()) != null) {
				name  = nextLine;
				description = br.readLine();
				day = br.readLine();
				time = br.readLine();
				priority = br.readLine();
				addEntry(name, description, day, time, priority);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error process task list");
			throw new IOException("Could not process task list file");
		}
	}

	public void saveTaskList(String fname) {
		try {
			System.out.println("Opening " + fname);
			//System.out.println("Working Directory = " + System.getProperty("user.dir"));
			PrintWriter pw = new PrintWriter(new File("..\\webapps\\lab1-btjansen\\WEB-INF\\classes\\" + fname));
			//format = new Formatter(fname);
			System.out.println("...done");
			String[] entries = listEntries();
			for (int i = 0; i < entries.length; i++) {
				pw.println(entries[i]);
				//format.format("%s", entries[i]);
				//System.out.println(entries[i]);
			}
			
			//format.close();
			pw.close();
		} catch (Exception exc) { 
			exc.printStackTrace(); 
			System.out.println("Error saving task list");
		}
	}

	public void editEntry(String name, String description, String day, String time, String priority) {
		TaskEntry pentry = _tList.get(name);
		pentry.changeEntry(description, day, time, priority);
	}

	public void addEntry(String name, String description, String day, String time, String priority) { 
		try {
			editEntry(name, description, day, time, priority);
		} catch (Exception exc) {
			addEntry(name, new TaskEntry(name, description, day, time, priority));
		}
		
	}

	public void addEntry(String name, TaskEntry entry) { 
		_tList.put(name, entry); 
	}

	public String[] listEntries() {
		String[] rval = new String[_tList.size()];
		int i = 0;
		TaskEntry nextEntry = null;
		for (Iterator<TaskEntry> iter = _tList.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			rval[i++] = nextEntry.toString();
		}
		return rval;
	}
	
	public int getSize() {
		return _tList.size();
	}
	
	public void removeEntry(String name) {
		TaskEntry pentry = _tList.get(name);
		_tList.remove(name, pentry);
	}
	
	public void checkDescription(String key) {
		TaskEntry nextEntry = null;
		Map<String, TaskEntry> _tempList = new HashMap<String, TaskEntry>(_tList);
		for (Iterator<TaskEntry> iter = _tList.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			if (nextEntry.getDesc().contains(key) == false) {
				//_tempList.put(nextEntry.getName(), nextEntry);
				_tempList.remove(nextEntry.getName(), nextEntry);
				//this.removeEntry(nextEntry.getName());
			}
		}
		_tList = _tempList;
	}
	
	public void checkPriority(String key) {
		TaskEntry nextEntry = null;
		Map<String, TaskEntry> _tempList = new HashMap<String, TaskEntry>(_tList);
		for (Iterator<TaskEntry> iter = _tList.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			if (nextEntry.getPrio().contains(key) == false) {
				//_tempList.put(nextEntry.getName(), nextEntry);
				_tempList.remove(nextEntry.getName(), nextEntry);
				//this.removeEntry(nextEntry.getName());
			}
		}
		_tList = _tempList;
	}
	
	public void checkDay(char[] key) {
		TaskEntry nextEntry = null;
		boolean saveEntry = false;
		Map<String, TaskEntry> _tempList = new HashMap<String, TaskEntry>(_tList);
		for (Iterator<TaskEntry> iter = _tList.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			saveEntry = false;
			for (int i = 0; i < key.length; i++) {
				//System.out.println("Days key is: " + key[i]);
				if (nextEntry.getDay().indexOf(key[i]) >= 0) {
					//_tempList.put(nextEntry.getName(), nextEntry);
					//_tempList.remove(nextEntry.getName(), nextEntry);
					//this.removeEntry(nextEntry.getName());
					//System.out.println("Entry saved");
					saveEntry = true;
				}
			}
			if (saveEntry == false) {
				//System.out.println("saveEntry = " + saveEntry);
				_tempList.remove(nextEntry.getName(), nextEntry);
			}
		}
		_tList = _tempList;
	}
}