package seleniumWebdriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private Config() {
		
	}
	
	private static Properties properties;
	
	public static Properties getProperties() throws IOException{
		
			FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\webdriver.properties");
			properties=new Properties();
			properties.load(file);
			return properties;
	}

}
