package ServicenowDemo;

import org.testng.annotations.Test;
import functionLibrary.Const;
import functionLibrary.sharedFunctions;
import pageFactory.CreateIncidentPage;
import pageFactory.ServicenowHomePage;
import pageFactory.ServicenowLoginPage;

public class CreateIncidentTest extends sharedFunctions{
	
	ServicenowLoginPage sLoginPage;
	ServicenowHomePage sHomePage;
	CreateIncidentPage sCreateIncidentPage;

	@Test(priority = 0)
	public void testIncidentCreation() throws InterruptedException {
		initializeExtentReport();
		sLoginPage = new ServicenowLoginPage(webdriver, testInstance);
		sLoginPage.enterCredentialsAndLogin(Const.sUsername, Const.sPassword);
		sHomePage = new ServicenowHomePage(webdriver, testInstance);
		sHomePage.launchCreateIncidentForm();
		sCreateIncidentPage = new CreateIncidentPage(webdriver, testInstance);
		sCreateIncidentPage.submitIncident();
		sHomePage.logoutUser();
	}
}
