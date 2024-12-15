import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
	public void showTask(String statusSelect) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		File f = new File("taskfolder/");
		
		// if show all tasks
		if(statusSelect.equalsIgnoreCase("all")){
			for(File files : f.listFiles()) {
				fileTask = mapper.readValue(files,Task.class);
				System.out.println(" "+ "\n"+fileTask + "\n ");
			}

		// if show "Todo" tasks only
		}else if(statusSelect.equalsIgnoreCase("todo")||statusSelect.equalsIgnoreCase("to-do")) {
			for(File files : f.listFiles()) {
				fileTask = mapper.readValue(files,Task.class);
				if(fileTask.getStatus().equals("Todo")){
					System.out.println(" "+ "\n"+fileTask + "\n ");
				}
			}

		// if show "In-progress" tasks only
		}else if(statusSelect.equalsIgnoreCase("inprogress")||statusSelect.equalsIgnoreCase("in-progress")) {
			for(File files : f.listFiles()){
				fileTask = mapper.readValue(files,Task.class);
				if(fileTask.getStatus().equals("In-progress")) {
					System.out.println(" "+ "\n"+fileTask + "\n");
				}
			}

		// if show "Done" tasks only	
		}else if(statusSelect.equalsIgnoreCase("done")) {
			for(File files : f.listFiles()){
				fileTask = mapper.readValue(files,Task.class);
				if(fileTask.getStatus().equals("Done")) {
					System.out.println(" "+ "\n"+fileTask + "\n");
				}
			}
		}else {
			System.out.println("Task status can only be one of the following: [\"Todo\", \"In-progress\", \"Done\"]");
		}
		
	}

	// UPDATE TASK
	public void updateTask(String keyId) {
		File f = new File("taskfolder/");

		ObjectMapper mapper = new ObjectMapper();

		int idNum = Integer.parseInt(keyId);

		// Iterately read each file in taskfolder
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

		// code to run if file exists
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

			// Ask user to mark task progress
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

			// write changes to file
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

		//this code snippet functions to delete all tasks
		if(keyId.equalsIgnoreCase("all")){
			for(File tasks : f.listFiles()) {
				tasks.delete();
			}
			System.out.println("All tasks are deleted");
		
		// else if delete only 1 file
		} else {
			int idNum = Integer.parseInt(keyId);

			// read each file in taskfolder
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

			// snippet to delete file
			File deleteFile = new File(filePathToDel);
			if(deleteFile.exists()){
				deleteFile.delete();
				System.out.println("File deleted");	
			} else {
				System.out.println("File does not exist");
			}
		}

	}

}
