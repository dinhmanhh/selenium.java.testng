
package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_Textarea {
	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	

	
	String employeeID = String.valueOf(rand.nextInt(99999)); // Convert kiểu số nguyên sang kiểu chuỗi
	String userName = "dinhmanh" + employeeID;
	String passportNumber = "111222333444";
	String comment = "Day la Dai tieng noi Viet Nam\nPhat thanh tu Ha Noi\nThu do nuoc CHXHCN Viet Nam";
	
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

	@Test
	public void TC_01_Create_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		
		sleepInSecond(3);
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		driver.findElement(By.name("firstName")).sendKeys("Dinh");
		driver.findElement(By.name("lastName")).sendKeys("Manh");
		
		WebElement element = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
		sleepInSecond(1);
		element.sendKeys(Keys.DELETE);
		sleepInSecond(1);
		element.sendKeys(employeeID);
		
		
		// Click element bị lỗi is not clickable at point (x,y) because another element obscures it
		// Actions action = new Actions(driver);
		// WebElement createLogin = driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//input"));
		// action.moveToElement(createLogin).click().perform();
		
		sleepInSecond(2);
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("123Password@");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("123Password@");
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		
		sleepInSecond(8);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Dinh");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Manh");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div//i")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
		driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).sendKeys(comment);
		
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		
		sleepInSecond(6);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).getAttribute("value"), comment);
		
		driver.findElement(By.cssSelector("i.oxd-userdropdown-icon")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys(userName);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("123Password@");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		
		sleepInSecond(4);
		
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		
		sleepInSecond(8);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Dinh");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Manh");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).getAttribute("value"), comment);
	}

	@Test
	public void TC_02_Logo() {
		
	}

	@Test
	public void TC_03_Form() {
		
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