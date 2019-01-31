package ServicenowDemo;

import org.testng.annotations.Test;

import functionLibrary.Const;
import functionLibrary.sharedFunctions;
import pageFactory.CreateAssetPage;
import pageFactory.ServicenowHomePage;
import pageFactory.ServicenowLoginPage;

public class CreateNewAsset extends sharedFunctions{
	
	ServicenowLoginPage sLoginPage;
	ServicenowHomePage sHomePage;
	CreateAssetPage createAssetPage;

	@Test(priority = 0)
	public void testIncidentCreation() throws InterruptedException {
		initializeExtentReport();
		sLoginPage = new ServicenowLoginPage(webdriver, testInstance);
		sLoginPage.enterCredentialsAndLogin(Const.sUsername, Const.sPassword);
		sHomePage = new ServicenowHomePage(webdriver, testInstance);
		sHomePage.launchCreateAllAssets();
		createAssetPage = new CreateAssetPage(webdriver, testInstance);
		String assetName = createAssetPage.createAsset();
		sHomePage.logoutUser();
	}
}
