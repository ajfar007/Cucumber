package steps;

import java.util.List;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Carwale {

	public ChromeDriver driver;
	ChromeOptions options;
	WebDriverWait wait;
	JavascriptExecutor js;
	List<Integer> kmListSorted;
	
	@Given("Launch https:\\/\\/www.carwale.com\\/")
	public void launchHttpsWwwCarwaleCom() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		//Disable notifications and launch the browser
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		
		//Launch URL. maximize window and declare wait time
		driver.get("https://www.carwale.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Given("Click on Used")
	public void clickOnUsed() {
	    
		driver.findElement(By.xpath("//li[@data-tabs='usedCars']")).click();
	}

	@Given("Select the City as Chennai")
	public void selectTheCityAsChennai() {
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("usedCarsList")));
		driver.findElement(By.id("usedCarsList")).sendKeys("Chennai");
		driver.findElement(By.xpath("//a[@cityname='chennai,tamilnadu']")).click();
	}

	@Given("Select budget min 8L and max 12L and Click Search")
	public void selectBudgetMinLAndMaxLAndClickSearch() {
		driver.findElement(By.id("minInput")).sendKeys("8",Keys.TAB);
		driver.findElement(By.id("maxInput")).sendKeys("12",Keys.TAB);
		driver.findElement(By.id("btnFindCar")).click();
	}

	@Given("Select Cars with Photos under Only Show Cars With")
	public void selectCarsWithPhotosUnderOnlyShowCarsWith() throws InterruptedException {
		Thread.sleep(3000);
		WebElement photos = driver.findElement(By.xpath("//span[text()='Cars with Photos']"));
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", photos);
	}

	@Given("Select Manufacturer as Hyundai --> Creta")
	public void selectManufacturerAsCreta() throws InterruptedException {
		WebElement element = driver.findElementByXPath("//li[@data-manufacture-en='Hyundai']");
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		WebElement subElement = driver.findElementByXPath("//span[text()='Creta']/parent::li");
		js.executeScript("arguments[0].click();", subElement);
		Thread.sleep(2000);
	}
	   
	

	@Given("Select Fuel Type as Petrol")
	public void selectFuelTypeAsPetrol() {
		WebElement fuel = driver.findElement(By.xpath("(//span[text()='Petrol'])[1]"));
		js.executeScript("arguments[0].click()", fuel);
		
		
	}

	@When("Select Best Match as 'KM: Low to High'")
	public void selectBestMatchAs() throws InterruptedException {
		WebElement sortElement = driver.findElementById("sort");
		Select sortDropdown = new Select(sortElement);
		sortDropdown.selectByVisibleText("KM: Low to High");
		Thread.sleep(2000);
	   
	}

	@Then("Print the first resulting car")
	public void printTheFirstResultingCar() {
	    System.out.println(driver.findElementByXPath("(//span[@data-carname-id='carname'])[1]").getText());
	    driver.close();
	}
}
