package seleniumWebdriver;



import org.openqa.selenium.WebDriver;




public class WebdriverClass {


	public static void main(String[] args) {
		
			WebDriver driver;
			try {
				driver = WebDriverFactory.getDriver("chrome");
				driver.get("https://www.selenium.dev/documentation/en/");
				driver.close();
			} catch (DriverNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
		
	
	}

}
