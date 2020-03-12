package UserStorytestCases;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Pages.StaysPage;
import seleniumWebdriver.DriverNotFoundException;
import seleniumWebdriver.InvalidDateException;
import seleniumWebdriver.WebDriverFactory;

@Listeners(extentReport.ExtentReportListeners.class)
public class SearchingForDifferentBookingOptions {
	
    StaysPage stayspage;
	
	@BeforeClass
	public void setUp() {
		try {
			stayspage=new StaysPage(WebDriverFactory.getDriver("chrome"));
		} catch (DriverNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void verifySearchBox() {
		Assert.assertTrue(stayspage.getSearchField().isDisplayed());
	}
	
	
	@Test
	public void verifyAlertWhenNoDataIsEntered() {
		stayspage.search();
		Assert.assertTrue(stayspage.getSearchAlert().isDisplayed());
	}
	
	@Test
	public void verifySuggestedDestinationsForNoData() {
		stayspage.search();
		Assert.assertTrue(stayspage.getSuggestedDestinationsBox().isDisplayed());
	}
	
	
	@Test
	public void verifyDatesField() {
		stayspage.getDateField().click();
		Assert.assertTrue(stayspage.getCalenderPanel().isDisplayed());
	}
	
	@Test
	public void VerifyMonthsPanelShowsTwoMonths() {
		Assert.assertEquals(stayspage.getdefaultMonthsInCalenderPanel().size(),2);
	}
	
	
	@Test
	public void VerfiyHighlightedDateIsTodayInCalenderPanel() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = new Date();  
	    Assert.assertEquals(stayspage.getHighlightedDate().getAttribute("data-date"),formatter.format(date));
	}
	
	
	@Test
	public void  verifySelectionOfDate(String checkInDate,String checkOutDate) throws InvalidDateException {
		stayspage.getDateField().click();
		Assert.assertTrue(stayspage.selectCheckInDate(checkInDate)  && stayspage.selectCheckOutDate(checkOutDate));
	}
	
	
	@Test
	public void verifyNoOfPersonsField() {
		Assert.assertTrue(stayspage.getNoOfPersonsField().isDisplayed());
	}
	
	
	@Test(dependsOnMethods= {"verifyNoOfPersonsField"})
	public void verifyNoOfPersonsFieldDropDown() {
		stayspage.getNoOfPersonsField().click();
		Assert.assertTrue(stayspage.getNoOfPersonsField().isDisplayed());
	}
	
	@Test(dependsOnMethods= {"verifyNoOfPersonsFieldDropDown"})
	public void VerifydefaultsInPersonsFieldDropDown() {
		String[] actual= {stayspage.getAdults().getAttribute("value"),stayspage.getChildrens().getAttribute("value"),stayspage.getRooms().getAttribute("value")};
		String[] expected= {"2","0","1"};
		Assert.assertEquals(actual, expected);	
	}
	
	
	@Test(dependsOnMethods= {"verifyNoOfPersonsFieldDropDown"})
	public void VerifyMaximumValueForRoomsAndOccupancies() {
		String[] actual= {stayspage.getAdults().getAttribute("max"),stayspage.getChildrens().getAttribute("max"),stayspage.getRooms().getAttribute("max")};
		String[] expected= {"30","10","30"};
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dependsOnMethods= {"verifyNoOfPersonsFieldDropDown"})
	public void VerifyMinimumValueForRoomsAndOccupancies() {
		String[] actual= {stayspage.getAdults().getAttribute("min"),stayspage.getChildrens().getAttribute("min"),stayspage.getRooms().getAttribute("min")};
		String[] expected= {"1","0","1"};
		Assert.assertEquals(actual, expected);
	}
	
	

	
	
	
	
	@AfterClass
	public void CloseDriver() {
		stayspage.closeDriver();
	}
	
	
	 


}
