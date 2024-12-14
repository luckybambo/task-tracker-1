import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FileTrack {

	Task fileTask = new Task();
	Scanner n = new Scanner(System.in);
	
	// ADD TASK
	public void addTask() throws JsonProcessingException {
		// input task name
		System.out.println("Task Name: ");
		String taskName = n.nextLine();
		fileTask.setTaskName(taskName + ".json");

		// input task description
		System.out.println("What is the task all about?: ");
		String taskdescrip = n.nextLine();
		fileTask.setDescription(taskdescrip);
		
		// set status to "Todo"
		fileTask.setStatus("Todo");
		
		// save current date & time
		LocalDateTime made = LocalDateTime.now();
		String thisTime = made.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:mm:ss"));
		fileTask.setCreatedAt(thisTime);
		
		// set id
		int fileCount = new File("taskfolder/").list().length; 
		int setId=0;
		if(fileCount==0){
			fileTask.setId(fileCount+1); 
		} else if(fileCount!=0){
			File f = new File("taskfolder/");
			ObjectMapper objMapper = new ObjectMapper();
			Task checkTaskId = new Task();
			try{
				for(File fileIdCheck : f.listFiles()) {
					checkTaskId = objMapper.readValue(fileIdCheck,Task.class);
					if(checkTaskId.getId()>=setId){
						setId= checkTaskId.getId()+1;	
					}
				}
				fileTask.setId(setId);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// create instance of task
		Task newTask = new Task(fileTask.getId(), fileTask.getTaskName(), fileTask.getDescription(), fileTask.getStatus(), fileTask.getCreatedAt(), null);		
		
		//Write task to a json string format
		ObjectMapper mapper = new ObjectMapper();
		String jFormatTask = mapper.writeValueAsString(newTask);
				
		//Write task into a json file
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("taskfolder/" + fileTask.getTaskName()));
			writer.write(jFormatTask);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		n.close();
		
		// Confirmation statement
		System.out.println("Task added to folder with details below: ");
		System.out.println(jFormatTask);
	}
	
	// SHOW LIST OF TASKS
	public void showTask() {
		ObjectMapper mapper = new ObjectMapper();
		File f = new File("taskfolder/");
		try{
			for(File files : f.listFiles()) {
				fileTask = mapper.readValue(files,Task.class);
				System.out.println(" "+ "\n"+fileTask + "\n ");
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	// UPDATE TASK
	public void updateTask(String keyId) {
		File f = new File("taskfolder/");

		ObjectMapper mapper = new ObjectMapper();

		int idNum = Integer.parseInt(keyId);

		String choseFilePath=" ";
		try{
			for (File tasks : f.listFiles()) {
				Task tasker = mapper.readValue(tasks,Task.class);
				if(tasker.getId()==idNum){
					choseFilePath = tasks.getAbsolutePath();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		File fileToUpdate = new File(choseFilePath);

		if(fileToUpdate.exists()) {
			try {
				fileTask = mapper.readValue(fileToUpdate, Task.class);
			} catch(Exception e) {
				e.printStackTrace();
			}

			// Ask user to update description
			System.out.println("Enter new description: ");
			String newDescrip = n.nextLine();
			fileTask.setDescription(newDescrip);

			// set updated time
			LocalDateTime timeUpdated = LocalDateTime.now();
			String toUpdatedAt = timeUpdated.format(DateTimeFormatter.ofPattern("yyyy-MM-dd:mm:ss"));
			fileTask.setUpdatedAt(toUpdatedAt);

			boolean progressChoice=false;
			while(progressChoice!=true){
				System.out.println("Mark Task progress (Todo/In-progress/Done)");
				String taskProgress = n.nextLine();
				if(taskProgress.equalsIgnoreCase("Todo")||taskProgress.equalsIgnoreCase("To-do")) {
					fileTask.setStatus("Todo");
					progressChoice=true;
				} else if(taskProgress.equalsIgnoreCase("inprogress")||taskProgress.equalsIgnoreCase("in-progress")) {
					fileTask.setStatus("In-progress");
					progressChoice=true;
				} else if(taskProgress.equalsIgnoreCase("done")){
					fileTask.setStatus("Done");
					progressChoice=true;
				} else {
					System.out.println("Invalid answer");
				}
			}

			String jFormatTaskUpdate = " ";

			try{
				jFormatTaskUpdate = mapper.writeValueAsString(fileTask);
				BufferedWriter bwriter = new BufferedWriter(new FileWriter(choseFilePath));
				bwriter.write(jFormatTaskUpdate);
				bwriter.close();
			} catch(Exception e) {
				e.printStackTrace();
			}

			// Confirmation statement for update
			System.out.println("Task updated to folder with details below: ");
			System.out.println(jFormatTaskUpdate);
			
		} else {
			System.out.println("File does not exist");
		}
		n.close();
	}


	// DELETE TASK
	public void delTask(String keyId) {
		File f = new File("taskfolder/");

		ObjectMapper mapper = new ObjectMapper();

		int idNum = Integer.parseInt(keyId);

		String filePathToDel=" ";
		try{
			for(File tasks : f.listFiles()) {
				Task taskList = mapper.readValue(tasks, Task.class);
				if(taskList.getId()==idNum) {
					filePathToDel = tasks.getAbsolutePath();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		File deleteFile = new File(filePathToDel);
		if(deleteFile.exists()){
			deleteFile.delete();
			System.out.println("File deleted");
		} else {
			System.out.println("File does not exist");
		}

	}

}
