package marvin_challenges;

import static org.testng.Assert.assertEquals;
import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class first_challenge {
	
	public String url = "https://amazon.in";
	public String login_mob = "9059539045";
	public String login_pwd = "Qa@Marvin";
	public static WebDriver driver = new EdgeDriver();
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));

	@SuppressWarnings("deprecation")
@Test(priority = 0)
  public void openbrowser() throws InterruptedException {
	  
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 driver.get(url);
	 Thread.sleep(2000);
  }
  @Test(priority = 1)
  public void serach() {
	  //typing keyword into the search box 
	  WebElement serach = driver.findElement(By.id("twotabsearchtextbox"));
	  serach.sendKeys("dress");
	  //clicking on the search button
	  driver.findElement(By.id("nav-search-submit-button")).click();
  }
  @Test(priority = 2)
  public void productdetail() throws InterruptedException {
	  //selecting a product from the results page
	  WebElement firstproduct = driver.findElement(By.xpath("//body/div[@id='a-page']/div[@id='search']/div[1]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/a[1]/span[1]/span[2]"));
	  firstproduct.click();
	  Thread.sleep(5000);
	  
	  //selecting the size
	  WebElement size = driver.findElement(By.name("dropdown_selected_size_name"));;
	  wait.until(ExpectedConditions.visibilityOf(size)).click();
	  Select selectsize = new Select(size);
	  selectsize.selectByVisibleText("M");
	  Thread.sleep(5000);
  }
  @Test(priority = 3)
  public void addtocart() throws InterruptedException {
	  //Adding the product to cart
	  WebElement cartbutton = driver.findElement(By.id("add-to-cart-button"));
	  cartbutton.click();
	  Thread.sleep(2000);
  }
  @Test(priority = 4)
  public void gotocart() throws InterruptedException {
	  //USer goes to the product to cart
	  WebElement cart = driver.findElement(By.id("nav-cart"));
	  cart.click();
  }
  @Test(priority = 5)
  public void OrderSummary() throws InterruptedException {
	  // Validating Quantity
	String Act_quantity = driver.findElement(By.xpath("//body/div[@id='a-page']/div[2]/div[3]/div[4]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[3]/div[4]/div[1]/div[2]/ul[1]/li[7]/span[1]/span[2]")).getText();
	String Exp_quantity = "2";
	assertEquals(Act_quantity, Exp_quantity);
	System.out.println("Order Quantity assertion passed");
	System.out.println("Number of selected items"+ Act_quantity);
	// Validating Price
	String Act_price = driver.findElement(By.xpath("//body/div[@id='a-page']/div[2]/div[3]/div[4]/div[1]/div[2]/div[1]/div[1]/form[1]/div[3]/span[2]/span[1]")).getText();
	String Exp_price = "  1,698.00";
	assertEquals(Act_price, Exp_price);
	System.out.println("Order price assertion passed");
	System.out.println("Total price of selected items in INR"+ Act_price);
	
  }
  @Test(priority = 6)
  public void Buy() throws InterruptedException {
	  // Proceeding to buy
	  WebElement buy_button = driver.findElement(By.name("proceedToRetailCheckout"));
	  wait.until(ExpectedConditions.visibilityOf(buy_button)).click();
	  //page redirects to login page 
	  //verifying login page redirection
	  String Act_Title = driver.getTitle();
	  String Exp_Title = "Amazon Sign In";
	  assertequals(Act_Title, Exp_Title);
	  System.out.println("Title of the redirected page" + Act_Title);  
	  
  }
  @Test(priority = 7)
  public void loginpage() throws Exception {
	  
	  // Taking screenshot of the login page
	  File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  //Storing the screenshot in local directory
	  FileUtils.copyFile(screenshotFile, new File("C:\\Users\\screenshot.png"));
	  
	  System.out.println("User can now view the loginpage screenshot at C:\\Users\\screenshot.png location");
  }

  
  
  private void assertequals(String act_Title, String exp_Title) {
	// TODO Auto-generated method stub
	
}
@BeforeTest
  public void beforeTest() {
	  
	  WebDriverManager.edgedriver().setup();
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}
