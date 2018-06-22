package fr.pizzeria.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;
	
	public static void loadConfig() throws IOException {

		InputStream inputStream = null;

		try {
			prop = new Properties();
			String propFileName = "jdbc.properties";

			inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}

	}
	
	public static String getPropertyFromConfig(String propertyName){
		return prop.getProperty(propertyName);
	}
	
}
