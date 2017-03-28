package ee.erkko;

import java.io.IOException;
import java.util.Date;

public class Application {
	
	public static void main(String[] args) throws IOException{
		Date start = new Date(System.currentTimeMillis());
		System.out.println("Program started at: " + start);
		ReadConfigProperties properties = new ReadConfigProperties();
		properties.getPropValues();
		ReadFile.readFile();
		Date stop = new Date(System.currentTimeMillis());
	    System.out.println("Program finished at: " + stop);
		
	}

}
