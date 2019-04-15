import java.io.IOException;
import java.io.InputStream;

public class UserCModel {
	
	public static String getUserFirstName(String key, UserList uList) {	
		String firstName = uList.getEntry(key).getFirstName();		
		return firstName;
	}
	
	public static String getUserLastName(String key, UserList uList) {
		String lastName = uList.getEntry(key).getLastName();		
		return lastName;
	}
	
	public static String getUserLanguages(String key, UserList uList) {		
		String languages = uList.getEntry(key).getLanguages();		
		return languages;
	}
	
	public static String getUserDays(String key, UserList uList) {
		String days = uList.getEntry(key).getAvailableDays();		
		return days;
	}
	
	public static String getUserMovie(String key, UserList uList) {		
		String movie = uList.getEntry(key).getFavoriteMovie();		
		return movie;
	}
	
	public static UserList removeUser(String key, UserList uList) {
		uList.removeEntry(key);
		return uList;
	}
	
	public static UserList addUser(String fName, String lName, String langs, String days, String movie, UserList uList) {
		String key = Integer.toString(uList.getSize()+1);
		uList.addEntry(key, fName, lName, days, langs, movie);
		return uList;
	}
	
	public static UserList editUser(String key,String fName, String lName, String langs, String days, String movie, UserList uList) {
		uList.editEntry(key, fName, lName, days, langs, movie);
		return uList;
	}
}

