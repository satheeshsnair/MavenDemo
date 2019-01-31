package ServicenowDemo;

import org.testng.annotations.Test;

import functionLibrary.Const;
import functionLibrary.sharedFunctions;
import pageFactory.CreateKnowledgeArticlePage;
import pageFactory.ServicenowHomePage;
import pageFactory.ServicenowLoginPage;

public class CreateKnowledgeArticle extends sharedFunctions{
		
		ServicenowLoginPage sLoginPage;
		ServicenowHomePage sHomePage;
		CreateKnowledgeArticlePage createNewArticlePage;

		@Test(priority = 0)
		public void testChangeRequestCreation() throws InterruptedException {
			initializeExtentReport();
			sLoginPage = new ServicenowLoginPage(webdriver, testInstance);
			sLoginPage.enterCredentialsAndLogin(Const.sUsername, Const.sPassword);
			sHomePage = new ServicenowHomePage(webdriver, testInstance);
			sHomePage.launchCreateKnowledgeForm();
			createNewArticlePage = new CreateKnowledgeArticlePage(webdriver, testInstance);
			String articleNumber =createNewArticlePage.createNewKnowledgeArticle();
			System.out.println("Article Number: "+articleNumber);
			sHomePage.logoutUser();
		}
}
