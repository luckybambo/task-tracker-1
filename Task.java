

public class Task {

	private int id;
	private String taskName;
	private String description;
	private String status;
	private String createdAt, updatedAt;
//	private File saveFile;
	
	Task(){
		
	}
	
	Task(int id, String taskName, String description, String status, String createdAt, String updatedAt){
		this.id = id;
		this.taskName = taskName;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	/*
	public File getSaveFile() {
		return saveFile;
	}
	
	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}
	*/
	

	//TEST OVERRIDE
	@Override
	public String toString() {
		return "Task Name: "+ taskName+ "\nID: "+id+"\nDescription: "+description + "\nStatus: "+status + "\nCreated on: "+createdAt+ "\nUpdated on: "+updatedAt;
	}
	
}
