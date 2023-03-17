package stepDefs;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class blazeappstepDef {
	static WebDriver driver;
	static WebDriverWait wait;
	String search,Price1,Price2;
	WebElement amount;
	List<WebElement> items;
	@BeforeAll
	public static void setup() {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
		wait=new WebDriverWait(driver,Duration.ofMinutes(1));
		driver.get("https://www.demoblaze.com/");
	}
	@Given("User into the login page")
	public void user_into_the_login_page() {
		driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
	}
	@When("User enters login credientials")
	public void user_enters_login_credientials() {
		driver.findElement(By.xpath("//input[@id='loginusername']")).sendKeys("Vasuki5456");
		driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("testing");
		driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
	}
	@Then("Should Display Home Page")
	public void should_display_home_page() {
		Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'Welcome Vasuki5456')]")).getText(), "Welcome Vasuki5456");
	}
	@When("Add an item to cart {string}")
	public void add_an_item_to_cart(String item)
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement home= driver.findElement(By.xpath("//li/a[contains(text(),'Home')]"));
     	  wait.until(ExpectedConditions.elementToBeClickable(home));
		  home.click();
		  driver.findElement(By.linkText(item)).click();
		  driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
		  wait.until(ExpectedConditions.alertIsPresent());
		  Alert alert=driver.switchTo().alert();		  
		  alert.accept();
	}
	
	@Then("Items must be added to cart")
	public void items_must_be_added_to_cart() {
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
		List<WebElement> items=driver.findElements(By.xpath("//tbody/tr"));
		Assert.assertTrue(items.size()!=0);
	}
	@When("List of items should be available in Cart")
	public void list_of_items_should_be_available_in_cart() {
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
		List<WebElement> items=driver.findElements(By.xpath("//tbody/tr"));
		Assert.assertTrue(items.size()!=0);
		amount=driver.findElement(By.id("totalp"));
		Price1=amount.getText();
	}
	@Then("Delete an item from cart")
	public void delete_an_item_from_cart() throws InterruptedException {
		driver.findElement(By.xpath("(//td[4]//a)[1]")).click();
		Thread.sleep(3000);
		amount=driver.findElement(By.id("totalp"));
		Price2=amount.getText();
		Assert.assertNotEquals(Price1, Price2);
	}
	
	@When("Items Should be available in Cart")
	public void items_should_be_available_in_cart() {
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
		items=driver.findElements(By.xpath("//tbody/tr"));
		Assert.assertTrue(items.size()!=0);
	}
	@Then("Place order")
	public void place_order() {
		if(items.size()!=0) {
			driver.findElement(By.xpath("//button[contains(text(),'Place Order' )]")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Purchase')]")));
			driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Vasuki");
			driver.findElement(By.xpath("//input[@id='country']")).sendKeys("India");
			driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Tirunelveli");
			driver.findElement(By.xpath("//input[@id='card']")).sendKeys("1234567890");
			driver.findElement(By.xpath("//input[@id='month']")).sendKeys("09");
			driver.findElement(By.xpath("//input[@id='year']")).sendKeys("2023");
		}
	}
	@Then("Purchase Items")
	public void purchase_items() throws InterruptedException {
		driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
		Assert.assertEquals(driver.findElement(By.xpath("(//h2)[3]")).getText(), "Thank you for your purchase!");
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
	}
	@AfterAll
	public static void finish() {
		driver.close();
	}


}