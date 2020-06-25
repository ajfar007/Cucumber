package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Lenskart {

	public static ChromeDriver driver;
	//public static Actions builder = new Actions(driver);
	
	/*ChromeOptions options;
	WebDriverWait wait;
	JavascriptExecutor js;*/
	
	@Given("Go to https:\\/\\/www.lenskart.com\\/")
	public void goToHttpsWwwLenskartCom() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		 ChromeOptions options= new ChromeOptions();
		 options.addArguments("--disable-notifications");
		 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		 options.addArguments("--incognito");
		 capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	     driver=new ChromeDriver(options);	
	     driver.manage().window().maximize();
	     driver.get("https://www.lenskart.com/");
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
	}

	@Given("Mouseover on Contact Lenses")
	public void mouseoverOnContactLenses() {
		WebElement lens=driver.findElementByXPath("(//a[@href='/contact-lenses.html'])[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(lens).perform();
	}

	@Given("Click on Monthly under Explore By Disposability")
	public void clickOnMonthlyUnderExploreByDisposability() {
		driver.findElementByXPath("//div[@id='cssmenu']/div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/span[1]").click();
	}

	@Given("Select brand as Aqualens")
	public void selectBrandAsAqualens() {
		driver.findElementByXPath("(//span[contains(text(),'Aqualens')])[2]").click();
		
	
	}

	@Given("Click on the first product")
	public void clickOnTheFirstProduct() {
		driver.findElementByXPath("(//img[@class='img-responsive'])[1]").click();
	}

	@Given("Click Buy Now")
	public void clickBuyNow() {
		WebElement buyButton = driver.findElementByXPath("//button[text()='BUY NOW']");
		driver.executeScript("arguments[0].click()",buyButton);
	}

	@Given("Select No of boxes as {int} and Power as {int} for both eyes.")
	public void selectNoOfBoxesAsAndPowerAsForBothEyes(Integer int1, Integer int2) {
	   WebElement box1 = driver.findElementByXPath("(//select[@name='boxes'])[1]");
	   Select box1DropDown = new Select(box1);
	   box1DropDown.selectByValue("2");
	   WebElement box2 = driver.findElementByXPath("(//select[@name='boxes'])[2]");
	   Select box2DropDown = new Select(box2);
	   box2DropDown.selectByValue("2");
	   
	   driver.findElementByXPath("(//span[@class='cl-dd'])[1]").click();
	   driver.findElementByXPath("//li/child::div[text()='-1.00']").click();
	   
	   driver.findElementByXPath("(//span[@class='cl-dd'])[2]").click();
	   driver.findElementByXPath("//li/child::div[text()='-1.00']").click();
	   

	   
	}

	@Given("Type your name in User's name")
	public void typeYourNameInUserSName() {
	    driver.findElementById("example-text-input").sendKeys("FirstNameLastName");
	    
	}

	@When("And click Save and continue")
	public void andClickSaveAndContinue() {
		driver.findElementByXPath("(//button[text()='SAVE & CONTINUE'])[1]").click();
		
	}

	@Then("Print total amount and click Proceed to Checkout")
	public void printTotalAmountAndClickProceedToCheckout() throws InterruptedException {
		Thread.sleep(2000);
		String OrderAmount = driver.findElementByXPath("//span[text()='Order Total :']//following-sibling::span").getText();
		System.out.println("Order Total is: "+OrderAmount);
		
		driver.findElementByXPath("//span[text()='Proceed To Checkout']").click();
		driver.close();
	}
}
