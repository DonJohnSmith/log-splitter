package ee.erkko;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.SystemUtils;

import ee.erkko.constants.PropertiesConstants;

public class ReadConfigProperties {
	
	private String result = "";
	private InputStream inputStream;
	private static String location;
	private static String splitAt;
	private static String suffix;

	public void getPropValues() throws IOException {
 
		Properties prop = new Properties();
		String propFileName = "config.properties";
		try {

			System.out.println(setFixedPropLocation() + propFileName);
			
			inputStream = new FileInputStream(new File(setFixedPropLocation() + propFileName));
		
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" +
						propFileName + "' not found in the classpath");
			}

			Date time = new Date(System.currentTimeMillis());

			suffix = prop.getProperty("suffix");
			splitAt = prop.getProperty(PropertiesConstants.splitAt);
			System.out.println(result + "Program Ran on " + time);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		prop = getProperties(new File(setFixedPropLocation() + propFileName));
		location = prop.getProperty("fileLocation");
	}
	
	public static String setFixedPropLocation(){
		String propLocation="";
		
		CodeSource codeSource = Application.class.getProtectionDomain().getCodeSource();
		File jarFile;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
			String jarDir = jarFile.getParentFile().getPath();
			
			if(getOs() == PropertiesConstants.LINUX){
				propLocation = jarDir + "/";
			}else if(getOs() == PropertiesConstants.WINDOWS){
				propLocation = jarDir + "\\";
			}else {
				propLocation = "";
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return propLocation;
	}
	
	public static String getFileLocation() throws FileNotFoundException{

		if(location != null){
			return location;
		} else {
			throw new FileNotFoundException("File location null");
		}	
	}
	
	public static int getSplitAt(){
		int split = 0;
		
		split = Integer.parseInt(splitAt);
		
		return split;
	}
	
	public static String getSuffix(){
		String suff = "." + suffix;
		return suff;
	}
	
	public static int getOs(){
		int os;
		
		if(SystemUtils.IS_OS_LINUX){
			os = 1;
		}else if(SystemUtils.IS_OS_WINDOWS) {
			os = 2;
		}else{
			os = 0;
		}
		
		return os;
	}
	
	//
	//
	//
	private static Reader preparePropertyFile(File file) throws IOException {

	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    StringBuilder result = new StringBuilder();
	    String line;

	    while ((line = reader.readLine()) != null) {

	        line = line.trim();

	        // if a backslash is found at the end of the line remove it
	        // and decide what to do depending on the next line.
	        if (line.contains("\\")) {
	        	System.out.println(line);
	            //line = line.substring(0, line.length() - 1);
	            result.append(line.replace("\\", "/"));
	            System.out.println("see peaks olema" + result.toString());
	        }
	        //result.append(line.replace("\\", "\\\\"));
	    }
	    reader.close();

	    return new StringReader(result.toString());
	}

	private static Properties getProperties(File file) throws IOException {
	    Properties result = new Properties();
	    result.load(preparePropertyFile(file));
	    return result;
	}
}
