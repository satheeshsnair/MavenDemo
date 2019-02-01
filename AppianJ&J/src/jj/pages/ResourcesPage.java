package jj.pages;

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
	public By Firstname = By.xpath("//input[@id='9cc245023d3fa98ccec97fccaa349da4']");
	public By Lastname = By.xpath("//input[@id='0831f58674fb8af8240c9360b3566bd6']");
	public By email = By.xpath("//input[@id='c1d4aebb873ba97cf48f9a9d4a49c294']");
	public By Mobilecountry = By.xpath("//input[@id='4320d6e0780d6501987f45f63f552873']");
	public By Mobilenmbr = By.xpath("//input[@id='d31ff182e6138344bdf50f0d3e500050']");
	public By facility = By.xpath("//span[contains(text(),'Facility')]/../following-sibling::div//div[@role='listbox']");
	public By Addresstype = By.xpath("//span[contains(text(),'Address Type')]/../following-sibling::div//div[@role='listbox']");
	public By Addressline1 = By.xpath("//input[@id='01fd0a3fb0642ebff29c1d6a24e0489b']");
	public By Region = By.xpath("//span[contains(text(),'Region')]/../following-sibling::div//div[@role='listbox']");
	public By country = By.xpath("//span[contains(text(),'Country')]/../following-sibling::div//div[@role='listbox']");
	public By State = By.xpath("//span[contains(text(),'State')]/../following-sibling::div//div[@role='listbox']");
	public By City = By.xpath("//span[contains(text(),'City')]/../following-sibling::div//div[@role='listbox']");
	public By postalcode = By.xpath("//input[@id='ca2c31fcc5e3a13832ad8be80b3963b0']");
	public By citizenship = By.xpath("//input[@id='c3bd3913b7bbbed57b75f08a38dec53e']");
	public By Experiencelevel = By.xpath("//span[contains(text(),'Experience Level')]/..//../following-sibling::div//div[@role='listbox']");
	public By addnewskill = By.xpath("(//a[@class='GridFooter---add_grid_row_link elements---global_a'])[1]");
	public By technology = By.xpath("//div[@id='3cbc273288878c10f186c882b88118ff_value']");
	public By skill = By.xpath("//div[@id='6f2971a3b3ebb8d6c42391f6595a7402_value']");
	public By Experience = By.xpath("//div[@id='51486c18a60c2c2c92ace14603fb3278_value']");
	public By isprimary = By.xpath("(//a[@class='DocumentImage---image_link DocumentImage---inImageGroup elements---global_a']/img)[3]");
	public By upload = By.xpath("(//button[@class='Button---btn Button---default_direction Button---secondary Button---small'])[2]");
	public By empID = By.xpath("//input[@id='1076aa510f3deb941f1156371561054e']");
	public By Addlanguage = By.xpath("(//a[@class='GridFooter---add_grid_row_link elements---global_a'])[2]");
	public By language = By.xpath("(//div[@id='d9f65a3eb0b298bba578f751ec8a8f5d_value']");
	public By isprimarylang = By.xpath("(//a[@class='DocumentImage---image_link DocumentImage---inImageGroup elements---global_a'])[5]/img");
	public By next_Submit = By.xpath("//button[@class='Button---btn Button---default_direction Button---primary appian-context-first-in-list appian-context-last-in-list']");
	
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
	
	public void upload(String path) throws InterruptedException
	{
		Thread.sleep(3000);
		WebElement element = driver.findElement(upload);
		Thread.sleep(3000);
		element.sendKeys(path);
	}
}
