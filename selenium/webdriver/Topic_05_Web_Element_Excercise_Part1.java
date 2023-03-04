
package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Excercise_Part1 {
	WebDriver driver;
	
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By education = By.id("edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTextbox = By.id("disable_password");
	By developmentCheckbox = By.cssSelector("#development");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			// System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			// System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		// driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_check_displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Textbox nếu có hiển thị thì nhập text vào và in ra console 
		if (driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Selenium WebDriver");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		// Textarea
		if (driver.findElement(education).isDisplayed()) {
			driver.findElement(education).sendKeys("Become a automation tester");
			System.out.println("Education text area is displayed");
		} else {
			System.out.println("Education is not displayed");
		}
		
		// Radio button
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age under 18 radio is displayed");
		} else {
			System.out.println("Age under 18 radio is not displayed");
		}
		
		// 
		if (driver.findElement(nameUser5Text).isDisplayed()) {
			System.out.println("Name user 5 text is displayed");
		} else {
			System.out.println("Name user 5 is not displayed");
		}
	}

	// @Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Email
		if (driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Email textbox is enable");
		} else {
			System.out.println("Email textbox is disable");
		}
		
		//Age under 18
		if (driver.findElement(ageUnder18Radio).isEnabled()) {
			System.out.println("Age under 18 is enable");
		} else {
			System.out.println("Age under 18 is disable");
		}
		
		// Education
		if (driver.findElement(education).isEnabled()) {
			System.out.println("Education is enable");
		} else {
			System.out.println("Education 18 is disable");
		}
		
		// Password
		if (driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("Password is enable");
		} else {
			System.out.println("Password 18 is disable");
		}
	}

	// @Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Verify checkbox/radio button are not selected
		
		// Vì ageUnder18 chưa được chọn nên kết quả mong muốn trả về là False -> dùng assertFalse
		Assert.assertFalse(driver.findElement(ageUnder18Radio).isSelected());
		// Vì developmentCheckbox chưa được chọn nên kết quả mong muốn trả về là False -> dùng assertFalse
		Assert.assertFalse(driver.findElement(developmentCheckbox).isSelected());
		
		//click chọn vào ageUnder18 và developmentCheckbox
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(developmentCheckbox).click();
		
		sleepInSecond(3);
		
		//Verify checkbox/radio button are selected
		
		// Vì ageUnder18 lúc này đã được chọn nên kết quả mong muốn trả về là True -> dùng assertTrue
		Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
		// Vì developmentCheckbox lúc này đã được chọn nên kết quả mong muốn trả về là True -> dùng assertTrue
		Assert.assertTrue(driver.findElement(developmentCheckbox).isSelected());	
	}
	@Test
	public void TC_04_combine_Mailchimpsite() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("dinhmanh.dsnnn@gmail.com");
		By passwordTextbox = By.id("new_password");
		By signupButton = By.id("create-account-enabled");
		
		// Actions action = new Actions(driver);
		// WebElement signUp = driver.findElement(By.id("create-account-enabled"));
		// action.moveToElement(signUp).click().perform();
		
		// case1: nhap abc - only lower case
		driver.findElement(By.cssSelector("button.onetrust-close-btn-handler.ot-close-icon")).click();
		driver.findElement(passwordTextbox).sendKeys("abc");
		driver.findElement(signupButton).click();
		
		// JavascriptExecutor js = (JavascriptExecutor) driver;  
		// js.executeScript("document.getElementById('create-account-enabled').click()");
		
		sleepInSecond(3);
		//verify case 1 - only lower case
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// case2: nhap ABC - only upper case
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
		// action.moveToElement(signUp).click().perform();
		driver.findElement(signupButton).click();
		// js.executeScript("document.getElementById('create-account-enabled').click()");
		sleepInSecond(3);
		//verify case 2 - only upper case
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// case3: nhap 123 - only number
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
		// action.moveToElement(signUp).click().perform();
		driver.findElement(signupButton).click();
		// js.executeScript("document.getElementById('create-account-enabled').click()");
		sleepInSecond(3);
		//verify case 3 - only number
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// case4: nhap @#$%^ - only special character
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("@#$%^");
		// action.moveToElement(signUp).click().perform();
		driver.findElement(signupButton).click();
		// js.executeScript("document.getElementById('create-account-enabled').click()");
		sleepInSecond(3);
		//verify case 4 - only special character
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// case5: nhap !@#a1A - combine but not >= 8-char
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@#a1A");
		// action.moveToElement(signUp).click().perform();
		driver.findElement(signupButton).click();
		// js.executeScript("document.getElementById('create-account-enabled').click()");
		sleepInSecond(3);
		//verify case 5 - combine but not >= 8-char
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// case6: nhap ABCDEFGHJHK >= 8-char
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABCDEFGHJHK");
		// action.moveToElement(signUp).click().perform();
		driver.findElement(signupButton).click();
		// js.executeScript("document.getElementById('create-account-enabled').click()");
		sleepInSecond(3);
		//verify case 6 - nhap ABCDEFGHJHK >= 8-char
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		// case7: nhap 123RHH@#$%aad 
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123RHH@#$%aad");
		// driver.findElement(signupButton).click();
		// js.executeScript("document.getElementById('create-account-enabled').click()");
		sleepInSecond(3);
		//verify case 6 - nhap 123RHH@#$%aad
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
	
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}