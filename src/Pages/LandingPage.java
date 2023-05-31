package Pages;

import org.openqa.selenium.interactions.Actions;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Utilities.ExcelUtils;

import java.util.Random;

public class LandingPage extends BaseClass {
	String product;
	Map<String, String> testData;

	public LandingPage(WebDriver driver) {
		super.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//ul[@id='customer_menu_top']/li/a")
	static WebElement login;

	@FindBy(xpath = "//*[@title='Continue']")
	static WebElement continueButton;

	@FindBy(id = "AccountFrm_firstname")
	static WebElement firstName;

	@FindBy(id = "AccountFrm_lastname")
	static WebElement lastName;

	@FindBy(id = "AccountFrm_agree")
	static WebElement privacyCheckbox;

	@FindBy(id = "AccountFrm_email")
	WebElement email;

	@FindBy(name = "telephone")
	static WebElement telephone;

	@FindBy(name = "fax")
	static WebElement fax;

	@FindBy(name = "address_1")
	static WebElement address1;

	@FindBy(name = "city")
	static WebElement city;

	@FindBy(name = "postcode")
	static WebElement zipcode;

	@FindBy(name = "loginname")
	static WebElement loginname;

	@FindBy(name = "password")
	static WebElement password;

	@FindBy(name = "confirm")
	static WebElement cnfmPwd;

	@FindBy(xpath = "//input[@name='newsletter' and @value='1']")
	static WebElement checkBox;

	@FindBy(xpath = "//span[@class='maintext']")
	static WebElement regeisterPageName;

	@FindBy(xpath = "//input[@name='filter_keyword']")
	static WebElement searchButton;

	@FindBy(xpath = "//div[@class='thumbnails grid row list-inline']/div/div[2]/div[3]/a")
	static List<WebElement> items;

	@FindBy(xpath = "//a[@class='cart']/i")
	static WebElement addToCart;

	@FindBy(xpath = "//a[@title='Checkout']")
	static WebElement Check_out_btn;

	// a[@title='Checkout']
	@FindBy(xpath = "//a[@id='cart_checkout1']")
	static WebElement checkOut;

	@FindBy(xpath = "//a[@class='checkout_heading']")
	static WebElement itemvalidate;

	@FindBy(xpath = "//*[@title='Confirm Order']")
	static WebElement cnfmOrder;

	@FindBy(xpath = "//span[contains(text(),' Your Order Has Been Processed!')]")
	static WebElement validatesuccessmsg;
	
	@FindBy(xpath = "//div[@class='menu_text']")
	static WebElement landingScreeUser;

	public static boolean register() throws InterruptedException {

		// element.sendKeys(context.firstName);;
		Thread.sleep(5000);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		login.click();
		Thread.sleep(2000);
		continueButton.click();

		String accountPageName = regeisterPageName.getText();
		System.out.println("inside register");
		return false;
	}

	public void addingtoCart() throws InterruptedException {
				// adding item to the cart
		searchButton.click();
		searchButton.sendKeys("Men" + Keys.ENTER);
		// searchButton.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[@title='Add to Cart'])[1]")).click();
		Thread.sleep(1000);
		System.out.println("getting the product name");
		//storing the product name into a variable
		 product=driver.findElement(By.xpath("//h1[@class='productname']")).getAttribute("innerText");
          System.out.println("Item in product description page"+product);
       		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll the element into view
		js.executeScript("arguments[0].scrollIntoView(true);", addToCart);
		Thread.sleep(1000);
		addToCart.click();
		Thread.sleep(5000);
		WebElement Hover_button = driver.findElement(By.xpath("//ul[@class='nav topcart pull-left']"));

		Actions actions = new Actions(driver);

		// Perform the mouse hover action on the button
		actions.moveToElement(Hover_button).perform();

		// Find the element to click within the hovered element
		WebElement subButton = driver.findElement(By.xpath("(//a[@title='Checkout'])[last()]"));

		// Click on the sub-button
		subButton.click();
		Thread.sleep(5000);
		//Check_out_btn.click();
		Thread.sleep(8000);
	}

	public void confirmOrder() throws InterruptedException {
		String productcnfm = itemvalidate.getAttribute("innerText");
		System.out.println("Product added to the cart is " + productcnfm);
		Assert.assertEquals(productcnfm, product);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", cnfmOrder);
		Thread.sleep(1000);
		cnfmOrder.click();

	}

	public void creatingNewAccount1() throws InterruptedException {
//	    String pageName = regeisterPageName.getText();
//	    System.out.println(pageName);
		System.out.println(
				"======================Print - Just to check Test Data/Easy debug===========================================");

		Map<String, String> testData = ExcelUtils
				.readTestDataFromExcel(System.getProperty("user.dir") + "/testdata.xlsx", "Sheet1");

		firstName.sendKeys(testData.get("First Name"), Keys.TAB);

		lastName.sendKeys(testData.get("Last Name"), Keys.TAB);
		System.out.println("registering with " + testData.get("First Name") + " " + testData.get("Last Name"));

		Random random = new Random();

		int randomNumber = random.nextInt(90) + 10;
		email.sendKeys(randomNumber + testData.get("Email"), Keys.TAB);

		System.out.println("Dynamic Email " + randomNumber + testData.get("Email"));
		System.out.println("Dynamic UserName " + randomNumber + testData.get("Email"));

		telephone.sendKeys(testData.get("Telephone"), Keys.TAB);
		fax.sendKeys(testData.get("Fax"), Keys.TAB);
		address1.sendKeys(testData.get("Address"), Keys.TAB);
		city.sendKeys(testData.get("City"), Keys.TAB);

		Select drpCountry = new Select(driver.findElement(By.name("country_id")));
		drpCountry.selectByVisibleText(testData.get("Country"));
		Thread.sleep(10000);

		Select state = new Select(driver.findElement(By.name("zone_id")));
		state.selectByVisibleText(testData.get("State"));
		Thread.sleep(2000);

		zipcode.sendKeys(testData.get("Zip Code"), Keys.TAB);
		loginname.sendKeys(testData.get("Login Name") + randomNumber, Keys.TAB);
		// loginname.sendKeys("IndiaUser2", Keys.TAB);

		password.sendKeys(testData.get("Password"), Keys.TAB);
		cnfmPwd.sendKeys(testData.get("Confirm Password"), Keys.TAB);

		privacyCheckbox.click();
		Thread.sleep(1000);

		continueButton.click();
		Thread.sleep(5000);
		continueButton.click();

		Thread.sleep(5000);
		//Validating the landing screen 
		String str =landingScreeUser.getAttribute("outerText");
		Assert.assertTrue(str.contains(testData.get("First Name")), "correct name and surname are displayed in PDP page");
	}

}
