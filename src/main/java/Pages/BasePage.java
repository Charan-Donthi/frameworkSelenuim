package Pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
	
	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public void closeDriver() {
		driver.close();
	}
}