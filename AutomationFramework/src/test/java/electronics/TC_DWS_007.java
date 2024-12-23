package electronics;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericutility.BaseClass;
import objectrepositry.HomePage;

public class TC_DWS_007 extends BaseClass{
	@Test
	public void clickOnElectronics()
	{
		test=extReport.createTest("clickOnElectronics");
		hp=new HomePage(driver);
		hp.getElectronicsLink().click();
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Electronics", " Electronics Page is not displayed");
		test.log(Status.PASS, "Electrnoics Page is Displayed");
	}
}
