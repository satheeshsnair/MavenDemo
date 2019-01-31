package ServicenowDemo;

import org.testng.annotations.Test;

import functionLibrary.Const;
import functionLibrary.sharedFunctions;
import pageFactory.CreateChangeRequestPage;
import pageFactory.MyApprovalPage;
import pageFactory.ServiceCatalogItemsPage;
import pageFactory.ServicenowHomePage;
import pageFactory.ServicenowLoginPage;

public class CreateChangeRequest extends sharedFunctions{
	
	ServicenowLoginPage sLoginPage;
	ServicenowHomePage sHomePage;
	CreateChangeRequestPage createChangeReqPage;
	MyApprovalPage myApprovalPage;

	@Test(priority = 0)
	public void testChangeRequestCreation() throws InterruptedException {
		initializeExtentReport();
		sLoginPage = new ServicenowLoginPage(webdriver, testInstance);
		sLoginPage.enterCredentialsAndLogin(Const.sUsername, Const.sPassword);
		sHomePage = new ServicenowHomePage(webdriver, testInstance);
		sHomePage.launchCreateChangeForm();
		createChangeReqPage = new CreateChangeRequestPage(webdriver, testInstance);
		String reqNumber =createChangeReqPage.createChangeRequest();
		System.out.println("Req# :"+reqNumber);
		boolean approvalFlag =true;
		while(approvalFlag) {
			sHomePage.launchOpenChangeReq();
			//Searching and previewing request on Service Catalog Items page
			createChangeReqPage.searchAndPreviewRequest(reqNumber);
			//Getting the approval name for the request
			String approvalName = createChangeReqPage.getApprovalName();
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
				sHomePage.impersonateUser(Const.defUser);
			}
			else {
				approvalFlag = false;
			}
		}
		String assignedUser = createChangeReqPage.getAssignedUser();
		System.out.println(assignedUser);
		sHomePage.impersonateUser(assignedUser);
		sHomePage.launchOpenChangeReq();
		createChangeReqPage.searchAndPreviewRequest(reqNumber);
		String reqState = createChangeReqPage.reviewChangeRequest();
		System.out.println("Req status after review: "+reqState);
		sHomePage.impersonateUser(Const.defUser);
		if(!reqState.equalsIgnoreCase("Closed")) {
			createChangeReqPage.closeChangeRequest();
		}
		sHomePage.logoutUser();
	}

}
