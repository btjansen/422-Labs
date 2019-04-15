public class UserEntry {
    private String firstName;
    private String lastName;
    private String availableDays;
    private String languages;
    private String favoriteMovie;
    

    public UserEntry(String fName, String lName, String days, String langs, String fMovie) {
		this.firstName  = fName;
		this.lastName  = lName;
		this.availableDays = days;
		this.languages = langs;
		this.favoriteMovie = fMovie;
    }

    public void changeEntry(String fName, String lName, String days, String langs, String fMovie) {
    	firstName = fName;
    	lastName = lName;
    	availableDays = days;
    	languages = langs;
    	favoriteMovie = fMovie;
    }
    
    public String toString() { 
    	return firstName + "\n" + lastName + "\n" + availableDays + "\n" + languages + "\n" + favoriteMovie; 
	}
    
    public String getLastName() {
    	String retVal = this.lastName;
    	return retVal;
    }
    
    public String getAvailableDays() {
    	String retVal = this.availableDays;
    	return retVal;
    }
    
    public String getFavoriteMovie() {
    	String retVal = this.favoriteMovie;
    	return retVal;
    }
    
    public String getFirstName() {
    	String retVal = this.firstName;
    	return retVal;
    }
    
    public String getLanguages() {
    	String retVal = this.languages;
    	return retVal;
    }
}