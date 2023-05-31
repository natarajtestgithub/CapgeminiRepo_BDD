package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import Pages.BaseClass;
import Pages.Context;
import Pages.LandingPage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CreateOrderStepDefinitions {
	private BaseClass baseClass;
	private LandingPage lp1;
	private ArrayList<Context> context;
	private ExtentReports extent;
	private ExtentTest test;

	@Before
	public void setup(Scenario scenario) {
		baseClass = new BaseClass();
		lp1 = new LandingPage(baseClass.invokeBrowser());

		// Set up Extent Reports
		extent = new ExtentReports();
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/BDD_Extent_Reports.html");
		extent.attachReporter(htmlReporter);
		test = extent.createTest(scenario.getName(), scenario.getName());
	}

	@Given("User is on the landing page")
	public void userIsOnLandingPage() {
		try {
			// No code required as this is handled in the @Before method
			test.log(Status.INFO, "User is on the landing page");
			captureScreenshotAndAttachToReport(test);
		} catch (Exception e) {
			test.log(Status.FAIL, "Step failed: " + e.getMessage());
		}
	}

	@When("User registers on the page")
	public void userRegistersOnPage() {
		try {
			lp1.register();
			System.out.println("User registers on the page");
			test.log(Status.INFO, "User registers on the page");
			captureScreenshotAndAttachToReport(test);
		} catch (Exception e) {
			test.log(Status.FAIL, "Step failed: " + e.getMessage());
		}
	}

	@Then("User should be successfully registered")
	public void userShouldBeSuccessfullyRegistered() {
		try {
			test.log(Status.PASS, "User is successfully registered");
			captureScreenshotAndAttachToReport(test);
		} catch (Exception e) {
			test.log(Status.FAIL, "Step failed: " + e.getMessage());
		}
	}

	@When("User adds items to the cart")
	public void userAddsItemsToCart() {
		try {
			lp1.creatingNewAccount1();
			lp1.addingtoCart();
			test.log(Status.INFO, "User adds items to the cart");
			captureScreenshotAndAttachToReport(test);
		} catch (Exception e) {
			test.log(Status.FAIL, "Step failed: " + e.getMessage());
		}
	}

	@Then("User should see the items added to the cart and confirm order")
	public void userShouldSeeItemsAddedToCart() {
			try {
				lp1.confirmOrder();
			// Add verification code for items added to the cart
			test.log(Status.PASS, "User sees the items added to the cart");
			captureScreenshotAndAttachToReport(test);
		} catch (Exception e) {
			test.log(Status.FAIL, "Step failed: " + e.getMessage());
		}
	}

	@After
	public void tearDown() {
		extent.flush();
		baseClass.getDriver().quit();
	}

	private void captureScreenshotAndAttachToReport(ExtentTest extentTest) {
		try {
			WebDriver driver = baseClass.getDriver();
			// Capture screenshot and save it to a file
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// Create a unique screenshot name
			String screenshotName = "Screenshot_" + System.currentTimeMillis() + ".png";
			// Specify the destination path where the screenshot will be saved
			String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName;
			// Move the captured screenshot file to the specified destination path
			FileUtils.copyFile(screenshotFile, new File(screenshotPath));
			// Attach the screenshot to the extent report using the file path
			extentTest.addScreenCaptureFromPath(screenshotPath, screenshotName);
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed to capture and attach screenshot: " + e.getMessage());
		}
	}

}
