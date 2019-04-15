import java.io.*;
import java.util.*;

public class UserList {
	public static final String DEFAULT_FILENAME = "users.txt";
	//private Formatter format;

	private Map<String, UserEntry> _uList = new HashMap<String, UserEntry>();

	public UserList() throws IOException {
		this(DEFAULT_FILENAME); 
	}
	public UserList(String fname) throws IOException {
		this(new BufferedReader(new FileReader(fname)));
	}
	public UserList(InputStream is) throws IOException {
		this(new BufferedReader(new InputStreamReader(is)));
	}
	private UserList(BufferedReader br) throws IOException {	
		String fName = null;
		String lName = null;
		String day = null;
		String languages = null;
		String favMovie = null;

		try {
			String nextLine = null;
			int userCount = 0;
			while ( (nextLine=br.readLine()) != null) {
				userCount++;
				String userNum = Integer.toString(userCount);
				fName  = nextLine;
				lName = br.readLine();
				day = br.readLine();
				languages = br.readLine();
				favMovie = br.readLine();
				addEntry(userNum, fName, lName, day, languages, favMovie);
				//System.out.println("userNum: " + userNum + "   Name: " + fName + " " + lName);
			}
			br.close();
			//System.out.println("Successfully loaded user list");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error process user list");
			throw new IOException("Could not process user list file");
		}
	}

	public void saveUserList(String fileName) {
		try {
			System.out.println("Opening " + fileName);
			PrintWriter pw = new PrintWriter(new File("..\\webapps\\lab2_btjansen\\WEB-INF\\classes\\" + fileName));
			System.out.println("...done");
			String[] entries = listEntries();
			for (int i = 0; i < entries.length; i++) {
				pw.println(entries[i]);
			}
			pw.close();
		} catch (Exception exc) { 
			exc.printStackTrace(); 
			System.out.println("Error saving user list");
		}
	}

	public void editEntry(String userNum, String firstName, String lastName, String day, String language, String movie) {
		UserEntry pentry = _uList.get(userNum);
		pentry.changeEntry(firstName, lastName, day, language, movie);
		//_uList.remove(oldKey, pentry);
		//addEntry(firstName, lastName, day, language, movie);
	}

	public void addEntry(String userNum, String fName, String lName, String day, String language, String movie) { 
		addEntry(userNum, new UserEntry(fName, lName, day, language, movie));		
	}

	public void addEntry(String key, UserEntry entry) { 
		_uList.put(key, entry); 
	}
	
	public UserEntry getEntry(String key) {		
		UserEntry pentry = _uList.get(key);
		return pentry;
	}

	public String[] listEntries() {
		String[] rval = new String[_uList.size()];
		int i = 0;
		UserEntry nextEntry = null;
		for (Iterator<UserEntry> iter = _uList.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			rval[i++] = nextEntry.toString();
		}
		return rval;
	}
	
	public int getSize() {
		return _uList.size();
	}
	
	public void removeEntry(String userNum) {
		UserEntry pentry = _uList.get(userNum);
		_uList.remove(userNum, pentry);
	}
	
	public String getUserNum(String firstName, String lastName) {
		String retVal = null;
		UserEntry nextEntry = null;
		int i = 0;
		for (Iterator<UserEntry> iter = _uList.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			i++;
			if (nextEntry.getFirstName().equals(firstName) && nextEntry.getLastName().equals(lastName)) {
				retVal = Integer.toString(i);
				return retVal;
			}
		}		
		return retVal;
	}
	
	/*
	public void checkDescription(String key) {
		UserEntry nextEntry = null;
		Map<String, UserEntry> _tempList = new HashMap<String, UserEntry>(_uList);
		for (Iterator<UserEntry> iter = _uList.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			if (nextEntry.getLastName().contains(key) == false) {
				//_tempList.put(nextEntry.getName(), nextEntry);
				_tempList.remove(nextEntry.getFirstName(), nextEntry);
				//this.removeEntry(nextEntry.getName());
			}
		}
		_uList = _tempList;
	}
	
	public void checkPriority(String key) {
		UserEntry nextEntry = null;
		Map<String, UserEntry> _tempList = new HashMap<String, UserEntry>(_uList);
		for (Iterator<UserEntry> iter = _uList.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			if (nextEntry.getFavoriteMovie().contains(key) == false) {
				//_tempList.put(nextEntry.getName(), nextEntry);
				_tempList.remove(nextEntry.getFirstName(), nextEntry);
				//this.removeEntry(nextEntry.getName());
			}
		}
		_uList = _tempList;
	}
	
	public void checkDay(char[] key) {
		UserEntry nextEntry = null;
		boolean saveEntry = false;
		Map<String, UserEntry> _tempList = new HashMap<String, UserEntry>(_uList);
		for (Iterator<UserEntry> iter = _uList.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			saveEntry = false;
			for (int i = 0; i < key.length; i++) {
				//System.out.println("Days key is: " + key[i]);
				if (nextEntry.getAvailableDays().indexOf(key[i]) >= 0) {
					//_tempList.put(nextEntry.getName(), nextEntry);
					//_tempList.remove(nextEntry.getName(), nextEntry);
					//this.removeEntry(nextEntry.getName());
					//System.out.println("Entry saved");
					saveEntry = true;
				}
			}
			if (saveEntry == false) {
				//System.out.println("saveEntry = " + saveEntry);
				_tempList.remove(nextEntry.getFirstName(), nextEntry);
			}
		}
		_uList = _tempList;
	}
	*/
}