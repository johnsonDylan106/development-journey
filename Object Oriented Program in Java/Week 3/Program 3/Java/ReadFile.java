import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class ReadFile {
		
	public ArrayList<String> readFile(String fileName, ArrayList<String> fileOutput) {
		
		//ArrayList<String> fileOutput = new ArrayList<>();
		try {
			
			BufferedReader txtFile = new BufferedReader(new FileReader(fileName + ".txt"));
			Scanner fileReader = new Scanner(txtFile);
			while (fileReader.hasNextLine()) {
				
				fileOutput.add(fileReader.nextLine());
				
			}
			
			fileReader.close();
			return fileOutput;
			
		} catch (FileNotFoundException fnfe) {
			fileOutput.clear();
			fileOutput.add("No Such File");
			System.out.println("No Such File");
			return fileOutput;
		}
		
		
		
	}
	
	
}
