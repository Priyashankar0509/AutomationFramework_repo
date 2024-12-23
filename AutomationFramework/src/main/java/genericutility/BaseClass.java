package genericutility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import objectrepositry.HomePage;
import objectrepositry.LoginPage;
import objectrepositry.RegisterPage;
import objectrepositry.WelcomePage;

public class BaseClass {
	public static ExtentReports extReport;
	public static WebDriver driver;
	public static ExtentTest test;
	
	public JavaUtility jLib= new JavaUtility();
	public FileUtility fLib = new FileUtility();
	
	public WelcomePage wp;
	public LoginPage lp;
	public HomePage hp;
	public RegisterPage rp;
	
	@BeforeSuite
	public void reportConfig()
	{
		ExtentSparkReporter spark = new ExtentSparkReporter("./HTML_reports/"+jLib.getSystemTime()+".html");
		extReport = new ExtentReports();
		extReport.attachReporter(spark);
	}
	@Parameters("Browser")
	@BeforeClass
	public void lauchBrowser(@Optional("chrome") String browserName) throws IOException
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver= new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get(fLib.getDataFromProperty("url"));
	}
	@BeforeMethod
	public void login() throws IOException
	{
		wp=new WelcomePage(driver);
		wp.getLoginLink().click();
		lp=new LoginPage(driver);
		lp.getEmailTextField().sendKeys(fLib.getDataFromProperty("email"));
		lp.getPasswordTextField().sendKeys(fLib.getDataFromProperty("password"));
		lp.getLoginButton().click();
	}
	
	
	@AfterMethod
	public void logout()
	{
		hp=new HomePage(driver);
		hp.getLogoutLink().click();
	}
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
	}
	@AfterSuite
	public void reportBackup()
	{
		extReport.flush();
	}
}