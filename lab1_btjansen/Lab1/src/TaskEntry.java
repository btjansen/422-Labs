public class TaskEntry {
    private String taskName;
    private String taskDesc;
    private String taskDay;
    private String taskTime;
    private String taskPrio;
    

    public TaskEntry(String name, String description, String day, String time, String priority) {
		this.taskName  = name;
		this.taskDesc  = description;
		this.taskDay = day;
		this.taskTime = time;
		this.taskPrio = priority;
    }

    public void changeEntry(String description, String day, String time, String priority) {
    	taskDesc = description;
    	taskDay = day;
    	taskTime = time;
    	taskPrio = priority;
    }
    
    public String toString() { 
    	return taskName + "\n" + taskDesc + "\n" + taskDay + "\n" + taskTime + "\n" + taskPrio; 
	}
    
    public String getDesc() {
    	String retVal = this.taskDesc;
    	return retVal;
    }
    
    public String getDay() {
    	String retVal = this.taskDay;
    	return retVal;
    }
    
    public String getPrio() {
    	String retVal = this.taskPrio;
    	return retVal;
    }
    
    public String getName() {
    	String retVal = this.taskName;
    	return retVal;
    }
}