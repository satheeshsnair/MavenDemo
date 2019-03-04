package pages;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import reusableLibrary.WebDriverUtilities;
import utilities.Utilites;

public class Login extends Utilities {
	
	private WebDriver driver;
	private ExtentTest test;
	private WebDriverUtilities sharedfuntions;
//	@FindBy(xpath = "//input[@id='un']")
//	static
//	WebElement username;
	
//	@FindBy(xpath="(//*[@id='pw'])")
//	static
//	WebElement password;
	
//	@FindBy(xpath="(//*[@class='btn primary'])[2]")
//	static
//	WebElement loginbtn;

	public By username1 = By.xpath("//input[@id='un']");
	public By password = By.xpath("(//*[@id='pw'])");
	public  By loginbtn = By.xpath("(//*[@class='btn primary'])[2]");
	public  By error1 = By.xpath("//*[@id='loginForm']/div[1]");
	String error = "The username/password entered is invalid. Usernames and passwords are case sensitive.";
	Utilites utilites;
	
	public Login(WebDriver driver,ExtentTest test) {
		this.driver = driver;
		this.test = test;
		sharedfuntions = new WebDriverUtilities(driver, test);
		utilites = new Utilites(driver, test);
	}
	
	public Login username(String Testcase, String Method)
	{
		utilites.GetData_Method(Testcase, Method);
		driver.findElement(username1).sendKeys(utilites.GetValue);
		test.pass("Entered username: " + utilites.GetValue);
		return this;
	}
	
	public  Login password(String Testcase, String Method) throws Exception
	{
		utilites.GetData_Method(Testcase, Method);
		driver.findElement(password).sendKeys(utilites.GetValue);
		test.pass("Entered password: " + utilites.GetValue);
		return this;
	}
	
	public Login login() throws InterruptedException
	{
		sharedfuntions.click(loginbtn);
		if(error.equals("The username/password entered is invalid. Usernames and passwords are case sensitive.."))
		{
			Assert.assertTrue(false);
		}
		else 
		{
			Assert.assertTrue(true);

		}
		return this;
	}
	
//	public static void main(String[] args) throws Exception 
//	{
//		Login l = new Login(driver);
//		l.username(0, 1, 0);
//	}
}
