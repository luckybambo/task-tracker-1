import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {

	public static void main(String[] args) throws JsonProcessingException, IOException{
		
	FileTrack fileT = new FileTrack();
		
		if(args[0].equalsIgnoreCase("add")) {
			fileT.addTask();
		} else if(args[0].equalsIgnoreCase("update")){
			fileT.updateTask(args[1]);
		} else if(args[0].equals("show")){
			fileT.showTask(args[1]);
		} else if(args[0].equalsIgnoreCase("delete")){
			fileT.delTask(args[1]);
		}

	}
}
