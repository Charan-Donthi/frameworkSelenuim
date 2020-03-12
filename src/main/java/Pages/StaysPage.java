package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumWebdriver.InvalidDateException;

public class StaysPage extends BasePage
{
	
	
	@FindBy(xpath="//input[@id='ss']")
	protected WebElement searchField;
	
	@FindBy(xpath="//*[@id='destination__error']/div/text()[2]")
	protected WebElement errorMessage;
	
	@FindBy(xpath="//button[@class='sb-searchbox__button ']")
	protected WebElement searchButton;
	
	@FindBy(xpath="//*[@id='xp__guests__toggle']")
	protected WebElement roomsandOccupancyButton;
	
	@FindBy(xpath="//div[@data-component='search/destination/input']/div[@role='alert']")
	protected WebElement searchAlert;
	
	@FindBy(xpath="//ul[@aria-label=\"List of suggested destinations \"]")
	protected WebElement suggestedDestinationsBox;
	
	@FindBy(xpath="//*[@class='xp__dates xp__group']")
	protected WebElement dateField;
	
	@FindBy(xpath="//*[@class='xp__dates xp__group']//*[@class='bui-calendar']")
	protected WebElement calenderPanel;
	
	@FindBy(xpath="//div[@class='xp__dates xp__group']//td[@class='bui-calendar__date bui-calendar__date--today']")
	protected WebElement highlighted_date;
	
	@FindBy(xpath="//div[@class='xp__dates xp__group']//div[@data-bui-ref='calendar-month']")
	protected List<WebElement> defaultMonthsInCalenderPanel;
	
	@FindBy(xpath="//div[@data-bui-ref='calendar-next']")
	protected WebElement calenderNext;
	
	@FindBy(xpath="//div[@class='xp__input-group xp__guests']")
	protected WebElement noOfPersonsField;
	
	@FindBy(xpath="//div[@class='xp__input-group xp__guests']//*[@class='xp__guests__inputs focussable ']")
	protected WebElement noOfPersonsFieldDropDown;
	
	@FindBy(xpath="//div[@class='sb-group__field sb-group__field-adults']//input[@name='group_adults']")
	protected WebElement adults;
	
	@FindBy(xpath="//div[@class='sb-group__field sb-group-children ']//input[@name='group_children']")
	protected WebElement childrens;
	
	@FindBy(xpath="//div[@class='sb-group__field sb-group__field-rooms']//input[@name='no_rooms']")
	protected WebElement rooms;
	
	



	WebElement checkIndate;
	WebElement checkOutdate;


	public StaysPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		driver.get("http://www.booking.com");
		checkIndate=null;
		checkOutdate=null;
	}
	
	
	public void sendDataToSearchBox(String data) {
		getSearchField().sendKeys(data);
	}
	

	public void search() {
			this.getSearchButton().click();
	}
	
	public boolean roomsandOccupancyButtonisPresent() {
		return roomsandOccupancyButton.isDisplayed();
	}
	
	
	public WebElement getSearchField() {
		return searchField;
	}

	public WebElement getErrorMessageAtSearchBox() {
		return errorMessage;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getRoomsandOccupancyButton() {
		return roomsandOccupancyButton;
	}

	public WebElement getSearchAlert() {
		return searchAlert;
	}
	
	public WebElement getSuggestedDestinationsBox() {
		return suggestedDestinationsBox;
	}
	
	public WebElement getDateField() {
		return dateField;
	}
	
	public WebElement getCalenderPanel() {
		return calenderPanel;
	}

	public WebElement getHighlightedDate() {
		return highlighted_date;
	}
	
	public List<WebElement> getdefaultMonthsInCalenderPanel() {
		return defaultMonthsInCalenderPanel;
	}
	
	
	public void moveToPaticularDateInCalender(String date) throws InvalidDateException {
		
		if(!checkDateFormat(date)) {
			throw new InvalidDateException("Date format Mismatch");
		}
	
		String[] currentDate;
		if(checkIndate==null) {
			System.out.println("abcdefghjhjvv");
			currentDate=getHighlightedDate().getAttribute("data-date").split("-");
		}
		else {
			System.out.println("abcdefgh");
			currentDate=checkIndate.getAttribute("data-date").split("-");
		}
		
		String[] selectedDate=date.split("-");
		int currentYear=Integer.parseInt(currentDate[0]);
		int selectedYear=Integer.parseInt(selectedDate[0]);
		int currentMonth=Integer.parseInt(currentDate[1]);
		int selectedMonth=Integer.parseInt(selectedDate[1]);
		int currentDay=Integer.parseInt(currentDate[2]);
		int selectedDay=Integer.parseInt(selectedDate[2]);
		
		if(selectedYear<currentYear) {
			throw new InvalidDateException("Year is before current Year");
		}
		if(selectedYear==currentYear && selectedMonth<currentMonth) {
			throw new InvalidDateException("Month is before current month");
			
		}
		if(selectedYear==currentYear && selectedMonth==currentMonth && selectedDay<currentDay) {
			throw new InvalidDateException("Day is before current Day");
		}
		
		int noOfClicks=0;
		
		if(selectedYear==currentYear) {
			noOfClicks=selectedMonth-currentMonth-1;
		}else{
			noOfClicks=selectedMonth+12-currentMonth-1;
		}
		
		while((noOfClicks--)>0) {
			getCalenderNext().click();
		}
		
		
	}
	
	public boolean checkDateFormat(String date) {
		boolean checkFormat;

		if (date.matches("([0-9]{4}-[0-9]{2}-[0-9]{2})")) 
		    checkFormat=true;
		else
		   checkFormat=false;
		
		return checkFormat;
	}
	
	public WebElement getCalenderNext() {
		return calenderNext;
	}

	public boolean selectCheckInDate(String date){
		
		try {
			moveToPaticularDateInCalender(date);
			checkIndate=driver.findElement(By.xpath("//div[@class='xp__dates xp__group']//td[@data-date='"+date+"']"));
			checkIndate.click();
			return true;
		} catch (InvalidDateException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean selectCheckOutDate(String date) throws InvalidDateException {
		
		try {
			moveToPaticularDateInCalender(date);
			checkOutdate=driver.findElement(By.xpath("//div[@class='xp__dates xp__group']//td[@data-date='"+date+"']"));
			checkOutdate.click();
			return true;
		} catch (InvalidDateException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	public WebElement getNoOfPersonsField() {
		return noOfPersonsField;
	}
	
	public WebElement getNoOfPersonsFieldDropDown() {
		return noOfPersonsFieldDropDown;
	}
	
	
	public WebElement getAdults() {
		return adults;
	}
	
	
	public WebElement getChildrens() {
		return childrens;
	}
	
	public WebElement getRooms() {
		return rooms;
	}

}
