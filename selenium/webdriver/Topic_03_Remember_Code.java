
package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Remember_Code {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			// System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			// System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	// Tương tác với Browser thì sẽ thông qua biến WebDriver driver
	// Tương tác với Element thì sẽ thông qua biến WebElement element 
	
	@Test
	public void TC_01_Web_Driver() {
		// Tạo biến: 
		WebElement tenbien = driver.findElement(By.id("email"));
		
		// Dùng biến: 
		tenbien.clear(); tenbien.sendKeys("123abc");
		
		//go to URL
		driver.get("https://www.youtube.com/");
		
		// Find 1 Element
		driver.findElement(By.xpath("//input[@id='email']"));
		WebElement findElement = driver.findElement(By.xpath("//input[@id='email']")); // Đặt biến để dùng nhiều lần
		
		// Find Elements
		driver.findElements(By.xpath("//input[@id='email']"));
		List<WebElement> findElements = driver.findElements(By.xpath("//input[@id='email']")); // Đặt biến để dùng nhiều lần
		
		// Đóng tab đang đứng (Nếu Browser đang đứng có 1 tab thì đóng Browser đó - giống driver.quit();
		driver.close();
		
		// Đóng Browser
		driver.quit();
		
		// So sánh dữ liệu
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/");
		
		// Lấy ra ID của window, tab mà driver đang đứng
		driver.getWindowHandle();
		
		// Lấy ra ID của tất cả window, tab
		driver.getWindowHandles();
		
		// Thao tác với driver.manager
		Options opt = driver.manage(); // Trả về interface (Chưa thao tác)
		
		opt.getCookies(); // Khi login thành công sẽ lưu lại cookies 
		//or
		driver.manage().getCookies();
		
		// Khai báo time để thao tác
		Timeouts time = opt.timeouts(); 
		// or
		driver.manage().timeouts();
		
		//Khoảng thời gian chờ Element xuất hiện trong 5s
		time.implicitlyWait(5, TimeUnit.SECONDS);
		
		// Khoảng thời gian chờ page load xong trong vòng 5s
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		// Khoảng thời gian chờ script được thực thi trong vòng 5s
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		// Mở to cửa sổ
		Window win = driver.manage().window();
		win.maximize();
		// or
		driver.manage().window().maximize();
		
		// Phần này sẽ học sau
		TargetLocator target = driver.switchTo();
		target.alert();
		target.frame("");
		target.window("");
			
	}

	@Test
	public void TC_02_Web_Element() {
		
		WebElement element = driver.findElement(By.xpath("//input[@id='email']"));
		
		// Xóa dữ liệu trước khi truyền vào
		element.clear();
		
		// Nhập dữ liệu vào
		element.sendKeys("");
		
		// Click vào
		element.click();
		
		// Lấy ra giá trị trong Attribute placeholder
		element.getAttribute("placeholder");
		
		// Chụp ảnh màn hình
		element.getScreenshotAs(OutputType.FILE);
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		
		// Lấy tên thẻ HTML của element đó -> để truyền vào 1 locator khác
		element.getTagName();
		
		// Lấy ra text
		element.getText();
		
		// Verify xem element có hiển thị hay không. Phạm vi: tất cả các loại element
		element.isDisplayed();
		// Mong đợi biến [element] hiển thị
		Assert.assertTrue(element.isDisplayed());
		// Mong đợi biến [element] không hiển thị
		Assert.assertFalse(element.isDisplayed());
		
		// Verify xem element có thao tác được hay không. Phạm vi: tất cả các loại element
		element.isEnabled();
		// Mong đợi biến [element] thao tác được
		Assert.assertTrue(element.isEnabled());
		// Mong đợi biến [element] không thao tác được
		Assert.assertFalse(element.isEnabled());
		
		// Verify xem element đã được chọn hay chưa? Phạm vi: Checkbox, Radio
		element.isSelected();
		// Mong đợi biến [element] đã được chọn
		Assert.assertTrue(element.isSelected());
		// Mong đợi biến [element] chưa được chọn
		Assert.assertFalse(element.isSelected());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}