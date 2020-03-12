package extentReport;
import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportListeners implements ITestListener{
	
	ExtentReports extent;
	ExtentTest test;
	
	public void onStart(ITestContext context) {
		 extent=new ExtentReports(System.getProperty("user.dir")+"/test-output/extentreports.html",true);
		  extent.loadConfig(new File(System.getProperty("user.dir")+"/src/main/resources/extent-config.xml"));
	}
	
	
	public void onFinish(ITestContext context) {
		extent.flush();
		extent.close();
	}

	public  void onTestStart(ITestResult result) {
		test=extent.startTest(result.getName());
	  }


	 public  void onTestSuccess(ITestResult result) {
		 test.log(LogStatus.PASS,"");
		 extent.endTest(test);
	  }

	 public  void onTestFailure(ITestResult result) {
		 test.log(LogStatus.FAIL,result.getThrowable());
		 extent.endTest(test);
	  }

	 
	  public  void onTestSkipped(ITestResult result) {
		  extent.endTest(test);
	  }
}
