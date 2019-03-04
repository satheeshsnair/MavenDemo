package pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import reusableLibrary.WebDriverUtilities;
import utilities.Utilites;

public class ResourcesPage {
	
	public WebDriver driver;
	WebDriverUtilities sharefunctions;
	Utilites utilities;
	Login login; //Initialize class
	Headers headers;
	AddNewPosition newposition;
	ExtentTest test;
	
	public By provideresource;
	public By position = By.xpath("//p[@class='LinkGroup---link_group LinkGroup---align_start elements---global_p']/a");
	public By Firstname = By.xpath("//span[contains(text(),'First Name')]/../input");
	public By Lastname = By.xpath("//span[contains(text(),'Last Name')]/../input");
	public By email = By.xpath("//span[contains(text(),'Email Address')]/../input");
	public By Mobilecountry = By.xpath("//span[contains(text(),'Business Mobile Country Code')]/../input");
	public By Mobilenmbr = By.xpath("//span[contains(text(),'Mobile Local Number')]/../input");
	public By facility = By.xpath("//span[contains(text(),'Facility')]/../following-sibling::div//div[@role='listbox']");
	public By Addresstype = By.xpath("//span[contains(text(),'Address Type')]/../following-sibling::div//div[@role='listbox']");
	public By Addressline1 = By.xpath("(//span[contains(text(),'Address')]/../input)");
	public By Region = By.xpath("//span[contains(text(),'Region')]/../following-sibling::div//div[@role='listbox']");
	public By country = By.xpath("//span[contains(text(),'Country')]/../following-sibling::div//div[@role='listbox']");
	public By State = By.xpath("//span[contains(text(),'State')]/../following-sibling::div//div[@role='listbox']");
	public By City = By.xpath("//span[contains(text(),'City')]/../following-sibling::div//div[@role='listbox']");
	public By postalcode = By.xpath("//span[contains(text(),'Postal Code')]/../input");
	public By citizenship = By.xpath("(//div[@class='FieldLayout---input_below']/div/input)[17]");
	public By Experiencelevel = By.xpath("//span[contains(text(),'Experience Level')]/..//../following-sibling::div//div[@role='listbox']");
	public By addnewskill = By.xpath("(//a[@class='GridFooter---add_grid_row_link elements---global_a'])[1]");
	public By technology = By.xpath("//table[@class='EditableGridLayout---table EditableGridLayout---striped EditableGridLayout---distribute EditableGridLayout---scrollable']//td[1]/div/div");
	public By skill = By.xpath("//table[@class='EditableGridLayout---table EditableGridLayout---striped EditableGridLayout---distribute EditableGridLayout---scrollable']//td[2]/div/div");
	public By Experience = By.xpath("//table[@class='EditableGridLayout---table EditableGridLayout---striped EditableGridLayout---distribute EditableGridLayout---scrollable']//td[3]/div/div");
	public By isprimary = By.xpath("(//a[@class='DocumentImage---image_link DocumentImage---inImageGroup elements---global_a']/img)[3]");
	public By upload = By.xpath("(//input[@type='file'])[last()]");
	public By empID = By.xpath("//label[contains(text(),'ID')]/../following-sibling::div/div/input[@type='text']");
	public By Addlanguage = By.xpath("(//a[@class='GridFooter---add_grid_row_link elements---global_a'])[2]");
	public By language = By.xpath("(//td[@class='EditableGridLayout---editable'])[4]/div/div");
	public By isprimarylang = By.xpath("(//a[@class='DocumentImage---image_link DocumentImage---inImageGroup elements---global_a'])[5]/img");
	public By next_Submit = By.xpath("//button[@class='Button---btn Button---default_direction Button---primary appian-context-first-in-list appian-context-last-in-list']");
	public By alert_yes = By.xpath("(//button[@class='Button---btn Button---default_direction Button---primary appian-context-first-in-list appian-context-last-in-list'])[last()]");
	
	public ResourcesPage(WebDriver driver, ExtentTest test )
	{
		this.driver=driver;
		this.test=test;
		headers = new Headers(driver, test);
		utilities = new Utilites(driver,test);
		sharefunctions = new WebDriverUtilities(driver,test);
		String sow = utilities.GetData_Method("TC_AddNewResource", "SOW");
		provideresource = By.xpath("//a[contains(text(),'" + sow + "')]");
	}
	
	
	public void upload(String path) throws InterruptedException, AWTException
	{
		WebElement element = driver.findElement(upload);
		sharefunctions.waitTime();
		element.sendKeys(path);
		sharefunctions.waitTime();
		
//		StringSelection ss = new StringSelection(path);
//		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
//		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_V);
//		robot.keyRelease(KeyEvent.VK_V);
//		robot.keyRelease(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_ENTER);
//		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public void clickontasktab(By locator) throws Exception
	{
		sharefunctions.click(locator);
		String expected = "Tasks";
		sharefunctions.waitTime();
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
	public void clickonresource() throws Exception 
	{
		String sow = utilities.GetData_Method("TC_AddNewResource", "SOW");
		System.out.println(sow+" "+"sow");
		String sownmbr = utilities.GetValue;
		System.out.println(sownmbr + " " + "Sownmbr");
		//String sow = utilities.readexcel(sheetnum, row, column);
		if(sharefunctions.isXpathExists(provideresource)) {
			String task = driver.findElement(provideresource).getText();
			System.out.println(task + " " + "task");
			if(task.contains(sownmbr))
			{
				sharefunctions.click(provideresource);
				sharefunctions.waitTime();
				test.pass("Clicked on Resource" + sownmbr);
				String expected = driver.getTitle();
				sharefunctions.waitTime();
				if(expected.contains("Provide Resource"))
				{
					utilities.passsnaps(driver);
					test.pass("Resource page selected");
				}
			}
		}else
		{
			test.fail("no such demand");
			utilities.failsnaps(driver);
			Assert.fail("no such demand");
		}
	}
	public void clickaddresourcebtn() throws Exception

	{
		if(sharefunctions.isXpathExists(headers.acceptbtn))
		{
			sharefunctions.click(headers.acceptbtn);
			sharefunctions.waitTime();
		}
		try {
			sharefunctions.click(position);
			sharefunctions.waitTime();
			String expected = driver.getTitle();
			if(expected.contains("Provide Resource"))
			{
				utilities.passsnaps(driver);
				test.pass("Clicked on Add position button");
			}
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Failed to Click on Add position button");
			Assert.fail("Failed to Click on Add position button");
		}
	}
	
	public void firstname() throws Exception
	{
		try {
			sharefunctions.sendkey(Firstname, "Test first name");
			utilities.passsnaps(driver);
			test.pass("Fristname entered");
		}catch (Exception e) {
			test.fail("Firstname not entered");
			utilities.failsnaps(driver);
			Assert.fail("Firstname not entered");
		}
	}
	public void lastname() throws Exception
	{
		try {
			sharefunctions.sendkey(Lastname, "Test last name");
			utilities.passsnaps(driver);
			test.pass("last name entered");
		}catch (Exception e) {
			test.fail("last name not entered");
			utilities.failsnaps(driver);
			Assert.fail("last name not entered");
		}
	}
	public void email() throws Exception
	{
		try {
			sharefunctions.sendkey(email, "t@t.com");
			utilities.passsnaps(driver);
			test.pass("email entered");
		}catch (Exception e) {
			test.fail("email not entered");
			utilities.failsnaps(driver);
			Assert.fail("email not entered");
		}
	}	
	public void mobilecode() throws Exception
	{
		try {
			sharefunctions.sendkey(Mobilecountry, "123");
			utilities.passsnaps(driver);
			test.pass("Mobilecountry code entered");
		}catch (Exception e) {
			test.fail("Mobilecountry code not entered");
			utilities.failsnaps(driver);
			Assert.fail("Mobilecountry code not entered");
		}
	}
	public void mobilenmbr() throws Exception
	{
		try {
			sharefunctions.sendkey(Mobilenmbr, "0987654321");
			utilities.passsnaps(driver);
			test.pass("Mobilenmbr entered");
		}catch (Exception e) {
			test.fail("Mobilenmbr entered");
			utilities.failsnaps(driver);
			Assert.fail("Mobilenmbr entered");
		}
	}
	public void facility() throws Exception
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
	public void addresstype() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(Addresstype);
			utilities.passsnaps(driver);
			test.pass("Addresstype selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select Addresstype");
			Assert.fail("Not able to select Addresstype");
		}
	}
	public void addressline() throws Exception
	{
		try {
			sharefunctions.sendkey(Addressline1, "Address Line 1");
			utilities.passsnaps(driver);
			test.pass("Address line 1 entered");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Address line 1 not entered");
			Assert.fail("Address line 1 not entered");
		}
	}
	public void region() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(Region);
			utilities.passsnaps(driver);
			test.pass("Region selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select Region");
			Assert.fail("Not able to select Region");
		}
	}
	public void country() throws Exception
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
	public void state() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(State);
			utilities.passsnaps(driver);
			test.pass("State selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select State");
			Assert.fail("Not able to select State");
		}
	}
	public void city() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(City);
			utilities.passsnaps(driver);
			test.pass("City selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select City");
			Assert.fail("Not able to select City");
		}
	}
	public void postalcode() throws Exception
	{
		try {
			sharefunctions.sendkey(postalcode, "123456");
			test.pass("Postal code entered");
			utilities.passsnaps(driver);
		}catch (Exception e) {
			test.fail("Postal code missed");
			utilities.failsnaps(driver);
			Assert.fail("Postal code missed");
		}
	}
	public void citizenship() throws Exception
	{
		try {
			sharefunctions.sendkey(citizenship, "Indian");
			test.pass("citizenship entered");
			utilities.passsnaps(driver);
		}catch (Exception e) {
			test.fail("citizenship missed");
			utilities.failsnaps(driver);
			Assert.fail("citizenship missed");
		}
	}
	public void explevel() throws Exception
	{
		try {
			sharefunctions.dropdown(Experiencelevel);
			test.pass("Experience level entered");
			utilities.passsnaps(driver);
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Expereience level missed");
			Assert.fail("Expereience level missed");
		}
	}
	public void addnewskill() throws Exception
	{
		try {
			sharefunctions.click(addnewskill);
			utilities.passsnaps(driver);
			test.pass("Clicked on Add new skill");
		}catch (Exception e) {
			test.fail("Missed to click Add New Skill");
			utilities.failsnaps(driver);
			Assert.fail("Missed to click Add New Skill");
		}
	}
	public void Technology() throws Exception
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
	public void skill() throws Exception
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
	public void experience() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(Experience);
			utilities.passsnaps(driver);
			test.pass("experience selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select experience");
			Assert.fail("Not able to select experience");
		}
	}
	public void isprimary() throws Exception
	{
		try 
		{
			sharefunctions.click(isprimary);
			utilities.passsnaps(driver);
			test.pass("skill selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select skill");
			Assert.fail("Not able to select skill");
		}
	}
	public void empID() throws Exception
	{
		try 
		{
			sharefunctions.sendkey(empID, "0000081");
			utilities.passsnaps(driver);
			test.pass("empID selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select empID");
			Assert.fail("Not able to select empID");
		}
	}
	public void addlang() throws Exception
	{
		try 
		{
			sharefunctions.click(Addlanguage);
			utilities.passsnaps(driver);
			test.pass("Clicked on language");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to click on  language");
			Assert.fail("Not able to click on language");
		}
	}
	public void lang() throws Exception
	{
		try 
		{
			sharefunctions.dropdown(language);
			utilities.passsnaps(driver);
			test.pass("language selected");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to select language");
			Assert.fail("Not able to select language");
		}
	}
	public void isprimarylang() throws Exception
	{
		try 
		{
			sharefunctions.click(isprimarylang);
			utilities.passsnaps(driver);
			test.pass("Clicked on isprimarylanguage");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to click on  isprimarylanguage");
			Assert.fail("Not able to click on isprimarylanguage");
		}
	}
	public void next() throws Exception
	{
		try 
		{
			sharefunctions.click(next_Submit);
			utilities.passsnaps(driver);
			test.pass("Clicked on next");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to click on  next");
			Assert.fail("Not able to click on next");
		}
	}
	public void submit() throws Exception
	{
		try 
		{
			sharefunctions.click(next_Submit);
			utilities.passsnaps(driver);
			test.pass("Clicked on next");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to click on  next");
			Assert.fail("Not able to click on next");
		}
	}
	public void alertyes() throws Exception
	{
		try 
		{
			sharefunctions.click(alert_yes);
			utilities.passsnaps(driver);
			test.pass("Resource details submitted");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Not able to click on  next");
			Assert.fail("Not able to click on next");
		}
	}
}
		

