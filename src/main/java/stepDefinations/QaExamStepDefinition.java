package stepDefinations;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QaExamStepDefinition {

	public static WebDriver driver;
	JavascriptExecutor js;
	

	@Given("^user is already on Login Page$")
	public void user_already_on_login_page() {
	
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/netzwelt/Documents/HnH_app/src/test/java/driver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--enable-javascript");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get("https://demo.hnhconsulting.ca/#/login");
		
	}

	@When("^title of login page is HnH app$")
	public void title_of_login_page_is_HnH_app() {
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals("HnH App", title);
	}

	@Then("^user enters \"(.*)\" and \"(.*)\"$")
	public void user_enters_username_and_password(String username, String password) {
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
	}

	@SuppressWarnings("unchecked")
	@Then("^user clicks on login button$")
	public void user_clicks_on_login_button() throws InterruptedException {
		WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		clickElementbyJs(loginBtn);
		waitTillPageLoad();
	}

	@Then("^user is on Qa exam page$")
	public void user_is_Qa_exam_page() {
		String url = driver.getCurrentUrl();
		System.out.println(" Qa exam page url ::" + url);
		Assert.assertEquals("https://demo.hnhconsulting.ca/#/qaexam1", url);

	}
	
	    
	@Then("^user enters details \"(.*)\" and \"(.*)\" and \"(.*)\" and click on add user button and check user got added$")
	public void user_enters_details_and_and_and_click_on_add_user_button_and_check_user_got_added(String name, String email, String role)
			throws InterruptedException {
		System.out.println("*** CREATING USER ***");

		WebElement usernameTxt = driver.findElement(By.id("userName"));
		clickElementbyJs(usernameTxt);
		usernameTxt.clear();
		usernameTxt.sendKeys(name);
		WebElement emailTxt = driver.findElement(By.id("userEmail"));
		emailTxt.clear();
		emailTxt.sendKeys(email);
		WebElement roleTxt = driver.findElement(By.id("userRole"));
		roleTxt.clear();
		roleTxt.sendKeys(role);
		WebElement addUserBtn = driver.findElement(By.xpath("//button[@type='submit' and text()='Add User']"));
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", addUserBtn);
		waitTillPageLoad();
		        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		WebElement  uName=driver.findElement(By.xpath("//tbody/tr[last()]//td[1]"));
	 	    	 String username=uName.getText();
	 	    Assert.assertEquals(name, username);
	 	   js.executeScript("window.scrollBy(0,-3000)", "");
			}


	@When("^user logout$")
	public void user_logout() {
		WebElement profile=driver.findElement(By.xpath("//*[@class='avatar-img']"));
		clickElementbyJs(profile);
		WebElement logout=driver.findElement(By.xpath("//*[text()='Logout']"));
		clickElementbyJs(logout);
		
	}
	@Then("^user is on Login page$")
	public void user_is_Login_page() {
		String url = driver.getCurrentUrl();
		System.out.println(" Login page url ::" + url);
		Assert.assertEquals("https://demo.hnhconsulting.ca/#/login", url);

	}
	@Then("close the browser")
	public void close_the_browser() {
	    driver.close();
	}


	public void clickElementbyJs(WebElement element) {
		JavascriptExecutor ex = (JavascriptExecutor) driver;
		ex.executeScript("arguments[0].click()", element);
	}

	public static void waitTillPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jsLoad = wd -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").toString().equals("complete");
		boolean jsReady = (Boolean) jsExecutor.executeScript("return document.readyState").toString()
				.equals("complete");
		if (!jsReady) {
			System.out.println("JS in NOT Ready!");
			wait.until(jsLoad);
		} else {
			sleep(5);
		}
	}

	public static void sleep(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
