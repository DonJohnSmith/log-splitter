package ee.erkko;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import ee.erkko.constants.PropertiesConstants;

public class WriteToFile {

	public static void writeToFile(String outputLine, String filename){
		
		Charset utf8 = StandardCharsets.UTF_8;
		List<String> lines = new ArrayList<String>();
		lines.add(outputLine);
		
		try {
			
			Path pathToFile = Paths.get(ReadConfigProperties.setFixedPropLocation() + getFolder() 
					+ filename + ReadConfigProperties.getSuffix());
			Files.createDirectories(pathToFile.getParent());
			Files.write(pathToFile, lines, utf8,
			        StandardOpenOption.CREATE, StandardOpenOption.APPEND);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lines = null;
		}
	}
	
	private static String getFolder(){
		String folder = null;
		
		if(ReadConfigProperties.getOs() == PropertiesConstants.LINUX){
			folder = "logs/";
		}else if(ReadConfigProperties.getOs() == PropertiesConstants.WINDOWS){
			folder = "logs\\";
		}
		
		return folder;
	}
}
