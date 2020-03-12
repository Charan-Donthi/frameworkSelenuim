package seleniumWebdriver;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

	private static WebDriver driver;
	
	public static WebDriver getDriver(String driverName) throws DriverNotFoundException {
		
		try {
			if(driverName.equalsIgnoreCase(Config.getProperties().getProperty("driver.chrome.name"))) {
				System.setProperty(Config.getProperties().getProperty("driver.chrome"), System.getProperty("user.dir")+Config.getProperties().getProperty("driver.chrome.path"));
				driver=new ChromeDriver();
			}
			else if(driverName.equalsIgnoreCase(Config.getProperties().getProperty("driver.firefox.name"))) {
				System.setProperty(Config.getProperties().getProperty("driver.gecko"), System.getProperty("user.dir")+Config.getProperties().getProperty("driver.firefox.path"));
				driver=new FirefoxDriver();
			}
			
		} catch (IOException e) {
			throw new DriverNotFoundException(e.getMessage());
		}
		
		if(driver==null) {
			throw new DriverNotFoundException("Driver Name Mismatch");
		}
		
		return driver;
	}
	
}
