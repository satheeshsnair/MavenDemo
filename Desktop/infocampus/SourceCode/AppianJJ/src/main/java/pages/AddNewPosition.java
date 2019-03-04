package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import reusableLibrary.DriverFactory;
import reusableLibrary.WebDriverUtilities;
import utilities.Utilites;

public class AddNewPosition extends DriverFactory {
	

	WebDriverUtilities sharefunctions;
	Utilites utilities;
	Login login; //Initialize class
	Headers headers;
	AddNewPosition newposition;
	ExtentTest test; 
	
	public By providedemandtask;
	public By provideresource;
	public By acceptbtn = By.xpath("//button[text()='Accept']");
	public By addnewposition = By.xpath("(//*[@class='LinkedItem---richtext_link elements---global_a'])[4]");
	public By position = By.xpath("//span[contains(text(),'Position Title')]/../following-sibling::div//div[@role='listbox']");
	public By language = By.xpath("//span[contains(text(),'Language')]/../following-sibling::div//div[@role='listbox']");
	public By experience = By.xpath("//span[contains(text(),'Experience')]/../following-sibling::div//div[@role='listbox']");
	public By datefrom = By.xpath("(//input[@type='text'])[1]");
	public By dateto = By.xpath("(//input[@type='text'])[2]");
	public By sponsorwwid = By.xpath("(//input[@type='text'])[3]");
	public By facility = By.xpath("//span[contains(text(),'Facility')]/../following-sibling::div//div[@role='listbox']");
	public By region = By.xpath("//span[contains(text(),'Region')]/../following-sibling::div//div[@role='listbox']");
	public By country = By.xpath("//span[contains(text(),'Country')]/../following-sibling::div//div[@role='listbox']");
	public By state = By.xpath("//span[contains(text(),'State')]/../following-sibling::div//div[@role='listbox']");
	public By city = By.xpath("//span[contains(text(),'City')]/../following-sibling::div//div[@role='listbox']");
	public By role = By.xpath("//span[contains(text(),'Role')]/../following-sibling::div//div[@role='listbox']");
	public By technology = By.xpath("//span[contains(text(),'Technology')]/../following-sibling::div//div[@role='listbox']");
	public By skill = By.xpath("//span[contains(text(),'Skill')]/../following-sibling::div//div[@role='listbox']");
	public By services = By.xpath("//span[contains(text(),'Service')]/../following-sibling::div//div[@role='listbox']");
	public By offering = By.xpath("//span[contains(text(),'Offering')]/../following-sibling::div//div[@role='listbox']");
	public By skilllevel = By.xpath("//span[contains(text(),'Skill Level')]/../following-sibling::div//div[@role='listbox']");
	public By save_complete_submit = By.xpath("//*[@class='Button---btn Button---default_direction Button---primary appian-context-first-in-list appian-context-last-in-list']");
	public By submit_form_yes = By.xpath("(//*[@class='Button---btn Button---default_direction Button---primary appian-context-first-in-list appian-context-last-in-list'])[2]");
	public By searchicon = By.xpath("//*[@class='DocumentImage---image_link elements---global_a']/img");
	public By alert = By.xpath("//div[@class='FieldLayout---field_error']");
	
	public AddNewPosition(WebDriver driver,ExtentTest test) 
	{
		this.driver = driver;
		this.test = test;
		utilities = new Utilites(driver, test);
		sharefunctions = new WebDriverUtilities(driver, test);
		headers = new Headers(driver, test);
		int demandid = utilities.demandid("TC_AddNewPosition", "DemandID");
		providedemandtask = By.xpath("//a[contains(text(),'" + demandid + "')]");
	}
	
	public void Selectsponsor(String wwid) throws InterruptedException 
	{
		//driver.findElement(sponsorwwid).sendKeys(wwid);
		sharefunctions.sendkey(sponsorwwid, wwid);
		sharefunctions.waitTime();
		//driver.findElement(searchicon).click();
		sharefunctions.click(searchicon);
		sharefunctions.waitTime();
	}

	public void checkheadernews() throws Exception
	{
		
		sharefunctions.waitTime();
		String expected = "News";
		sharefunctions.waitTime();
		if(driver.getTitle().equals(expected))
		{
			utilities.passsnaps(driver);
			test.pass("Logged in to application");
			//utilities.WriteExcel(0, 1, 2, "Pass");
		}
		else
		{
			utilities.failsnaps(driver);
			test.fail("Login failed");
			//utilities.WriteExcel(0, 1, 2, "Fail");
			Assert.fail("Login failed");
		}
	}
	public void clickheadertask(By locator) throws Exception
	{
		sharefunctions.click(locator);
		sharefunctions.waitTime();
		String expected = "Tasks";
		if(driver.getTitle().equals(expected))
		{
			utilities.passsnaps(driver);
			test.pass("Clicked on Task Tab");
		}
		else
		{
			utilities.failsnaps(driver);
			test.fail("Task Tab is missing");
			Assert.fail("Task Tab is missing");
			
		}
	}
	public void clickondemand() throws Exception 
	{
		int demandID = utilities.demandid("TC_AddNewPosition", "DemandID");
		String demand = Integer.toString(demandID);		
		if(sharefunctions.isXpathExists(providedemandtask)) {
			String task = driver.findElement(providedemandtask).getText();
			if(task.contains(demand))
			{
				sharefunctions.click(providedemandtask);
				sharefunctions.waitTime();
				String expected = driver.getTitle();
				if(expected.contains("Provide demand"))
				{
					utilities.passsnaps(driver);
					test.pass("Clicked on demand" + demand);
				}
			}
		}else
		{
			utilities.failsnaps(driver);
			test.fail("no such demand");
			Assert.fail("no such demand");
		}
	}
	public void clickaddpositionbtn() throws Exception
	{
		if(sharefunctions.isXpathExists(acceptbtn))
		{
			sharefunctions.click(acceptbtn);
			test.pass("Clicked on Add position button");
			sharefunctions.waitTime();
		}
		try {
			sharefunctions.click(addnewposition);
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Add New position button missing");
			Assert.fail("Add New position button missing");
		}
	}
	public void Selectposition() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(position);
			utilities.passsnaps(driver);
			test.pass("Position selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select position");
			Assert.fail("Not able to select position");
		}
	}
	public void Selectexperience() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(experience);
			utilities.passsnaps(driver);
			test.pass("Experience selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select experience");
			Assert.fail("Not able to select Experience");
		}
	}
	public void Selectlanguage() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(language);
			utilities.passsnaps(driver);
			test.pass("Language selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select language");
			Assert.fail("Not able to select Language");
		}
		
	}
	public void Selectfacility() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(facility);
			utilities.passsnaps(driver);
			test.pass("facility selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select facility");
			Assert.fail("Not able to select facility");
		}
	}
	public void Selectregion() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(region);
			utilities.passsnaps(driver);
			test.pass("region selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select region");
			Assert.fail("Not able to select region");
		}
	}
	public void Selectcountry() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(country);
			utilities.passsnaps(driver);
			test.pass("country selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select country");
			Assert.fail("Not able to select country");
		}
	}
	public void Selectstate() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(state);
			utilities.passsnaps(driver);
			test.pass("state selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select state");
			Assert.fail("Not able to select state");
		}
	}
	public void Selectcity() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(city);
			utilities.passsnaps(driver);
			test.pass("city selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select city");
			Assert.fail("Not able to select city");
		}
	}
	public void Selectrole() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(role);
			utilities.passsnaps(driver);
			test.pass("role selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select role");
			Assert.fail("Not able to select role");
		}
	}
	public void Selecttechnology() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(technology);
			utilities.passsnaps(driver);
			test.pass("technology selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select technology");
			Assert.fail("Not able to select technology");
		}
	}
	public void Selectskill() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(skill);
			utilities.passsnaps(driver);
			test.pass("skill selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select skill");
			Assert.fail("Not able to select skill");
		}
	}
	public void Selectservices() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(services);
			utilities.passsnaps(driver);
			test.pass("services selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select services");
			Assert.fail("Not able to select services");
		}
	}
	public void Selectoffering() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(offering);
			utilities.passsnaps(driver);
			test.pass("offering selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select offering");
			Assert.fail("Not able to select offering");
		}
	}
	public void Selectskilllevel() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(skilllevel);
			utilities.passsnaps(driver);
			test.pass("skilllevel selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select skilllevel");
			Assert.fail("Not able to select skilllevel");
		}
	}
	public void clickonsave() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(save_complete_submit);
			utilities.passsnaps(driver);
			test.pass("Save button selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select Save button");
			Assert.fail("Not able to select Save button");
		}
	}
	public void alert() throws Exception
	{
		if(sharefunctions.isXpathExists(alert))
		{
			utilities.failsnaps(driver);
			test.fail("Missed some values");
			Assert.fail("Missed some values");
		}
		else
		{
			utilities.passsnaps(driver);
			test.pass("Entered all data successfully!!!");
		}
	}
	public void clicksubmitbutton() throws Exception
	{
		try {
			sharefunctions.click(save_complete_submit);
			utilities.passsnaps(driver);
			test.pass("Clicked on Submit button");
			
			}
			catch (Exception e) {
				utilities.failsnaps(driver);
				test.fail("Submit button missing");
				Assert.fail("Submit button missing");
			}
			try {
				sharefunctions.click(submit_form_yes);
				utilities.passsnaps(driver);
				test.pass("Position submiited");
			}catch (Exception e) {
				utilities.failsnaps(driver);
				test.fail("Posiiton not submiited");
				Assert.fail("Position not submiited");
			}
	}
	public void logout() throws InterruptedException
	{
		sharefunctions.waitTime();
		sharefunctions.click(headers.logo);
		sharefunctions.waitTime();
		sharefunctions.click(headers.logout);
	}
}
