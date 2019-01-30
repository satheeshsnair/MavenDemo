package jj.pages;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import utilities.Utilites;

public class Login extends Utilities {
	
	private WebDriver driver;
	private ExtentTest test;
	
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
	}
	
	public void username(int sheetnum, int row, int column) throws Exception
	{
		utilites = new Utilites(driver);
		utilites.readexcel(sheetnum,row,column);
		driver.findElement(username1).sendKeys(utilites.uname);
		test.pass("Entered username: " + utilites.uname);
	}
	
	public  void password(int sheetnum, int row, int column) throws Exception
	{
		utilites = new Utilites(driver);
		utilites.readexcel(sheetnum,row,column);
		driver.findElement(password).sendKeys(utilites.pwd);
		test.pass("Entered password: " + utilites.pwd);
	}
	
	public boolean login() throws InterruptedException
	{
		driver.findElement(loginbtn).click();
		if(error.equals("The username/password entered is invalid. Usernames and passwords are case sensitive."))
		{
			//Assert.assertTrue(false);
			return false;
		}
		else 
		{
			return true;
		}
	}
	
//	public static void main(String[] args) throws Exception 
//	{
//		Login l = new Login(driver);
//		l.username(0, 1, 0);
//	}
}
