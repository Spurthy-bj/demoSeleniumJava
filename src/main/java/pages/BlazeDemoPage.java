package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;

public class BlazeDemoPage extends BasePage{

	public BlazeDemoPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(name="fromPort")
	private WebElement departureDropdown;
	
	@FindBy(name = "toPort")
	private WebElement destinationDropdown;
	
	@FindBy(xpath = "//input[@value='Find Flights']")
	private WebElement findFlights;


	public void selectDestination(String from,String to)
	{
		Select departure = new Select(departureDropdown);
		departure.selectByVisibleText(from);

		
		Select destination = new Select(destinationDropdown);
		destination.selectByVisibleText(to);
		
		clickElement(findFlights);
	}
}
