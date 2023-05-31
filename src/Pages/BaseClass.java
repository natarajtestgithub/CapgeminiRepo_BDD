package Pages;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public  WebDriver driver;
	public static String reportName;
    protected ExtentReports extent = ExtentReport.ExtentReportGenerator();
    ExtentTest test;
    protected static ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();
    
    //Invoke Browser method
    public WebDriver invokeBrowser()
        
{
	WebDriverManager.chromedriver().setup();
	driver =new ChromeDriver();
	driver.get("https://automationteststore.com/");
	driver.manage().window().maximize();
	return driver;
	}
    
    //Logging the result
  
    public static void logging(String message) {
        extenttest.get().log(Status.PASS, message);
    }

	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		
		return driver;
	}

}
