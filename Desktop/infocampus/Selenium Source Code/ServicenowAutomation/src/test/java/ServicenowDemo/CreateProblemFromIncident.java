package ServicenowDemo;

import org.testng.annotations.Test;

import functionLibrary.Const;
import functionLibrary.sharedFunctions;
import pageFactory.CreateProblemPage;
import pageFactory.NewIncidentPage;
import pageFactory.OpenIncidentPage;
import pageFactory.ServicenowHomePage;
import pageFactory.ServicenowLoginPage;

public class CreateProblemFromIncident extends sharedFunctions{
	
	ServicenowLoginPage sLoginPage;
	ServicenowHomePage sHomePage;
	NewIncidentPage sCreateIncidentPage;
	OpenIncidentPage sOpenIncidentPage;
	CreateProblemPage sCreateProblemPage;

	@Test(priority = 0)
	public void testIncidentCreation() throws InterruptedException {
		initializeExtentReport();
		sLoginPage = new ServicenowLoginPage(webdriver, testInstance);
		sLoginPage.enterCredentialsAndLogin(Const.sUsername, Const.sPassword);
		sHomePage = new ServicenowHomePage(webdriver, testInstance);
		sHomePage.launchCreateIncidentForm();
		sCreateIncidentPage = new NewIncidentPage(webdriver, testInstance);
		String incidentNum = sCreateIncidentPage.submitIncident();
		sHomePage.launchOpenIncident();
		sOpenIncidentPage = new OpenIncidentPage(webdriver, testInstance);
		sOpenIncidentPage.resolveIncident(incidentNum);
		sOpenIncidentPage.launchCreateProblem(incidentNum);
		sCreateProblemPage =new CreateProblemPage(webdriver, testInstance);
		sCreateProblemPage.createProblem();
		sHomePage.launchOpenIncident();
		sOpenIncidentPage.getSLAs(incidentNum);
		sHomePage.logoutUser();
	}

}
