package computers;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import genericutility.BaseClass;
import objectrepositry.HomePage;

public class TC_DWS_006 extends BaseClass{
	@Test
	public void clickOnComputers()
	{
		test=extReport.createTest("clickOnComputers");
		hp=new HomePage(driver);
		hp.getComputersLink().click();
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Computers", " Computer Page is not displayed");
		test.log(Status.PASS, "Computer Page is Displayed");
	//	test.log(Status.PASS, "Edited GitHub");
	}

}
