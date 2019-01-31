package ServicenowDemo;

import org.testng.annotations.Test;

import functionLibrary.Const;
import functionLibrary.sharedFunctions;
import pageFactory.CloudServicesProductionPage;
import pageFactory.MyApprovalPage;
import pageFactory.ServiceCatalogItemsPage;
import pageFactory.ServiceCatalogPage;
import pageFactory.ServicenowHomePage;
import pageFactory.ServicenowLoginPage;
import pageFactory.ServicenowPortalHome;

public class CreateServiceRequest extends sharedFunctions{
	
	ServicenowLoginPage sLoginPage;
	ServicenowHomePage sHomePage;
	ServicenowPortalHome portalHomePage;
	ServiceCatalogPage serviceCatalogPage;
	CloudServicesProductionPage cspPage;
	ServiceCatalogItemsPage serCatItemsPage;
	MyApprovalPage myApprovalPage;
	

	@Test(priority = 0)
	public void testServiceRequestCreation() throws InterruptedException {
		initializeExtentReport();
		sLoginPage = new ServicenowLoginPage(webdriver, testInstance);
		//Logging into service now application
		sLoginPage.enterCredentialsAndLogin(Const.sUsername, Const.sPassword);
		//Launching service now portal application
		webdriver.get(Const.servicenowPortalUrl);
		//Launching service catalog page in portal
		portalHomePage = new ServicenowPortalHome(webdriver, testInstance);
		portalHomePage.launchServiceCatalog();
		//Launching Cloud Services Production form page
		serviceCatalogPage = new ServiceCatalogPage(webdriver, testInstance);
		serviceCatalogPage.launchCloudServiceProductionForm();
		//Submitting the cloud service production request
		cspPage = new CloudServicesProductionPage(webdriver, testInstance);
		String reqNumber = cspPage.submitCloudServicesProductionForm();
		//Launching service now application
		webdriver.get(Const.servicenowUrl);
		//Launching Service Catalog Items page in service now app
		sHomePage = new ServicenowHomePage(webdriver, testInstance);
		boolean approvalFlag =true;
		while(approvalFlag) {
			sHomePage.launchServiceCatalogItems();
			//Searching and previewing request on Service Catalog Items page
			serCatItemsPage = new ServiceCatalogItemsPage(webdriver, testInstance);
			serCatItemsPage.searchAndPreviewRequest(reqNumber);
			//Getting the approval name for the request
			String approvalName = serCatItemsPage.getApprovalName();
			if(approvalName!=null) {
				//Proxying app as a approval user
				sHomePage.impersonateUser(approvalName);
				//Launching my approvals page
				sHomePage.launchMyApprovals();
				//Searching and previewing request on Service Catalog Items page
				myApprovalPage = new MyApprovalPage(webdriver, testInstance);
				myApprovalPage.searchAndPreviewRequest(reqNumber);
				//Approving the request
				myApprovalPage.approveRqst();
				//Proxying app as a default user
				sHomePage.impersonateUser("Manoj Kumar");
			}
			else {
				approvalFlag = false;
			}
		}
		serCatItemsPage.closeOpenTasks();
		sHomePage.logoutUser();
	}
}
