package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import reusableLibrary.WebDriverUtilities;
import utilities.Utilites;



public class Headers
{
	public WebDriver driver;
	private ExtentTest  test;
	Utilites utilities;
	WebDriverUtilities sharefunctions;
	
	
	/*************Header Locator**************/
	public By Actiontab = By.xpath("(//*[@class='appian-menu-item'])[4]");
	public By Tasktab = By.xpath("(//*[@class='appian-menu-item'])[1]");
	public By Records = By.xpath("(//*[@class='appian-menu-item'])[2]");
	public By Reportstab = By.xpath("(//*[@class='appian-menu-item'])[3]");
	public By logo = By.xpath("//a[@class='gwt-Anchor pull-down-toggle']/span");
	//public By logout = By.xpath("(//a[@class='gwt-Anchor pull-down-menu-item']/span)[6]");
	public By logout = By.xpath("//span[contains(text(),'Sign Out')]");
	/*********Task Locator***************/
	public By firsttask = By.xpath("(//*[@class='gwt-Anchor appian-feed-entry-author'])[1]");
	public By acceptbtn = By.xpath("//button[text()='Accept']");
	
	public By providedemandtask;
	public By provideresource;
	public By addnewposition = By.xpath("(//*[@class='LinkedItem---richtext_link elements---global_a'])[4]");
	
	public Headers(WebDriver driver,ExtentTest test)
	{
		this.driver=driver;
		utilities = new Utilites(driver, test);
		//this.test = test;
		int demandid = utilities.demandid(1, 1, 0);
		String sow = utilities.SOW(1,1,3);
		providedemandtask = By.xpath("//a[contains(text(),'" + demandid + "')]");
		provideresource = By.xpath("//a[contains(text(),'" + sow + "')]");
	}
	public void clickontask() throws Exception
	{
		try
		{
			sharefunctions.click(Tasktab);
			utilities.passsnaps(driver);
			test.pass("Clicked on Task Tab");
		}catch (Exception e) {
			utilities.failsnaps(driver);
			test.fail("Didnt click on Task tab");
			Assert.fail("Didnt click on Task tab");
		}
	}
}	
	