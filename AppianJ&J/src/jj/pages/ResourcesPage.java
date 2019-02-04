package jj.pages;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import utilities.Utilites;

public class ResourcesPage {
	
	public WebDriver driver;
	public Utilites utilites;
	public ExtentTest test;
	public Headers header;
	
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
	
	public ResourcesPage(WebDriver driver)
	{
		this.driver=driver;
		//this.test=test;
	}
	
//	public void sow(int sheetnum, int row, int column) throws Exception
//	{
//		utilites = new Utilites(driver, test);
//		header = new Headers(driver);
//		utilites.readexcel(sheetnum,row,column);
//		driver.findElement(header.provideresource);//.click();
//		test.pass("Entered sow: " + utilites.sow);
//	}
	
//	public void upload(String path) throws InterruptedException
//	{
//		Thread.sleep(3000);
//		WebElement element = driver.findElement(upload);
//		Thread.sleep(3000);
//		element.click();
//		Thread.sleep(3000);
//		element.sendKeys(path);
//		Thread.sleep(3000);
//	}
	
	public void upload(String path) throws InterruptedException, AWTException
	{
		Thread.sleep(3000);
		WebElement element = driver.findElement(upload);
		Thread.sleep(3000);
		element.sendKeys(path);
		Thread.sleep(3000);
		
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
}
