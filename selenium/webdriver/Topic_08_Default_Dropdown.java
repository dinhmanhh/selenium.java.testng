
package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	// Khai báo thư viện Random
	Random rand = new Random();

	// Khai báo thư viện Select
	Select select;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password, day, month, year;
	String countryName, provinceName, cityName, address1, zipCode, phoneNumber;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			// System.setProperty("webdriver.chrome.driver", projectPath +
			// "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			// System.setProperty("webdriver.gecko.driver", projectPath +
			// "/browserDrivers/geckodriver");
		}

		// driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstName = "Dinh";
		lastName = "Manh";
		emailAddress = "dinhmanh" + rand.nextInt(9999) + "@gmail.com";
		password = "123456";
		companyName = "Automation FC";
		day = "18";
		month = "August";
		year = "2000";
		countryName = "Viet Nam";
		provinceName = "Ha Noi";
		cityName = "Ha Noi";
		address1 = "Lanmark 72";
		zipCode = "100000";
		phoneNumber = "0912345678";
	}

	@Test
	public void TC_01_Register_New_Account() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		sleepInSecond(2);
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// Handle DropdownList - Phải đặt ở đây, không đặt ở before class
		// không cần khởi tạo biến, new sử dụng luôn
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		
		// Verify single dropdown 
		Assert.assertFalse(new Select(driver.findElement(By.name("DateOfBirthDay"))).isMultiple());
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		driver.findElement(By.cssSelector("a.ico-login")).click();
		sleepInSecond(2);
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.login-button")).click();
		driver.findElement(By.cssSelector("a.ico-account")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);

		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);

	}

	@Test
	public void TC_02_Add_Address() {
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.cssSelector("button.add-address-button")).click();

		
		sleepInSecond(2);
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAddress);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText("United States");
		new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText("Alaska");
		
		driver.findElement(By.id("Address_City")).sendKeys(cityName);
		driver.findElement(By.id("Address_Address1")).sendKeys(address1);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(zipCode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		
		driver.findElement(By.cssSelector("button.save-address-button")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstName + " " + lastName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(), address1);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.address1")).getText().contains(address1));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(cityName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(zipCode));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains("Alaska"));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.country")).getText().contains("United States"));
		

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