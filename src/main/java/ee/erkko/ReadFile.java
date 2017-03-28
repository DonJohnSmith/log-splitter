package ee.erkko;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static void readFile(){
		//File file = new File("../../file.txt");
		File file = null;
		try {
			file = new File(ReadConfigProperties.getFileLocation());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = reader.readLine()) != null; ) {
		    	Work.printIntegers(line);
		    }

		    // line is not visible here.
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

