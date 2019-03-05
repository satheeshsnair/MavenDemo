package regression;

import org.testng.annotations.Test;

import pages.AddNewPosition;
import pages.Headers;
import pages.Login;
import pages.OrderOwner;
import pages.ResourcesPage;
import reusableLibrary.DriverFactory;
import reusableLibrary.WebDriverUtilities;
import utilities.Utilites;

public class TC_OrderOwner_Approval extends DriverFactory 
{
	Login login; //Initialize class
	Headers headers;
	Utilites utilities;
	AddNewPosition newposition;
	WebDriverUtilities sharefunctions;
	ResourcesPage resource;
	OrderOwner orderowner;
	
	@Test(enabled=true,priority=0)
	public void loginasvendor() throws Exception
	{
		initializeExtentReport("Login");
		login = new Login(driver,testInstance); //initialize objectl
		orderowner = new OrderOwner(driver, testInstance);
		login.username("TC_OrderOwner_Approval", "Uname")
		.password("TC_OrderOwner_Approval", "Password")
		.login();
		
	}
	@Test(enabled=true,priority=1)
	public void clickontask() throws Exception
	{
		initializeExtentReport("Click on Task");
		orderowner.clickontask();
		
	}
	@Test(enabled=true,priority = 2)
	public void clickondemand() throws Exception
	{
		initializeExtentReport("Select Demand task");
		orderowner.clickondemand();
	}
	@Test(enabled=true,priority = 3)
	public void clickonpositionid() throws Exception
	{
		initializeExtentReport("Click on Position ID and Approve");
		orderowner.clickpositionid();
	}
	@Test(enabled=true,priority = 4)
	public void logout() throws Exception
	{
		orderowner.logout();
	}
}
