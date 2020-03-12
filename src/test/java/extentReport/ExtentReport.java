package extentReport;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



import org.testng.Assert;


@Listeners(ExtentReportListeners.class)
public class ExtentReport {
	
	
	
	@Test
	  public void f4() {
		  
		  Assert.assertTrue(true);
		 
	  }
	
  @Test
  public void f2() {
	  
	  Assert.assertTrue(false);
	 
  }
  
  @Test
  public void f1() {
	  
	  Assert.assertTrue(true);
	  
  }
  
  
  @Test
  public void f3() {
	  
	  Assert.assertTrue(false);
	  
  }
  
  
}
