package ee.erkko;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Work {
	
	public static List<String> outputList = new ArrayList<String>();
	private static int fileName;
	
	public static void printIntegers(String inputLine){

		Pattern findHost = Pattern.compile("(.*)");
		Matcher m = findHost.matcher(inputLine);
		
		while (m.find()) {
			
			String[] inputArray = inputLine.split("\\s+");
			WriteToFile.writeToFile(m.group(0), inputArray[setUpFileName()]);

		}
	}

	public static List<String> getLines(){
		return outputList;
	}
	
	private static int setUpFileName(){
		
		if(ReadConfigProperties.getSplitAt() == 0){
			fileName=0;
		}else {
			fileName = ReadConfigProperties.getSplitAt() - 1;
		}
		
		return fileName;
	}

}
