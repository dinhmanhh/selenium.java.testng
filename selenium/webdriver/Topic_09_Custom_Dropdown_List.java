
package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown_List {
	WebDriver driver;
	// Thư viện Wait - Wait tường minh 
	WebDriverWait explicitWait;
	
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
		
		// Khởi tạo Wait - phải để dưới dòng khởi tạo driver (firefox, chrome)
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	// @Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Faster");
		
		sleepInSecond(2);
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Slower");
		sleepInSecond(2);
		
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Prof.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(), "Prof.");
		sleepInSecond(2);
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role='option']", "Mrs.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(), "Mrs.");

	}
 
	// @Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInDropdown("div.dropdown", "div[role='option']", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.cssSelector("div[aria-checked='true']")).getAttribute("textContent"), "Stevie Feliciano");
		sleepInSecond(2);
		selectItemInDropdown("div.dropdown", "div[role='option']", "Christian");
		Assert.assertEquals(driver.findElement(By.cssSelector("div[aria-checked='true']")).getAttribute("textContent"), "Christian");
		sleepInSecond(2);
		selectItemInDropdown("div.dropdown", "div[role='option']", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.cssSelector("div[aria-checked='true']")).getAttribute("textContent"), "Jenny Hess");
		
		
	}
	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		sleepInSecond(2);
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		sleepInSecond(2);
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");	
	}
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
	}
	
	// Viet ham tranh lap lai code nhieu lan, chi can goi ham ra de dung
	// Di kem voi tham so
/*	public void selectItemInDropdown (String parentCss, String allItemCss, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentCss)).click();
		
		//  Chờ cho tất cả các item được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		// 
		// Đưa hết item trong dropdown vào 1 list
		List<WebElement> dropdownItems = driver.findElements(By.cssSelector(allItemCss));
		// Tìm xem item có đúng cái mình đang cần không - dùng vòng lặp foreach
		for (WebElement tempItem : dropdownItems) {
			String itemText = tempItem.getText();
			// in ra giá trị của các item trong dropdown
			System.out.println(itemText);
			// Kiểm tra text xem có đúng cái mình muốn không
			if (itemText.equals(expectedTextItem)) {
				tempItem.click();
				// thoát ra khỏi vòng lặp không xét các case còn lại nữa
				break;
				
			}
		}
	} */
	public void selectItemInDropdown (String parentCss, String allItemCss, String expectedItem) {
		driver.findElement(By.cssSelector(parentCss)).click();
		sleepInSecond(1);
		List<WebElement> dropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		for (WebElement checkItem : dropdownItems) {
			if (checkItem.getText().trim().equals(expectedItem)) {
				sleepInSecond(1);
				checkItem.click();
				break;
			} 
		}	
	}
	
	public void enterAndSelectItemInDropdown (String textboxCss, String allItemCss, String expectedItem) {
		// 1. Nhập expected item vào, tự động sổ ra danh sách các item matching
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedItem);
		sleepInSecond(1);
		List<WebElement> dropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
		for (WebElement checkItem : dropdownItems) {
			if (checkItem.getText().trim().equals(expectedItem)) {
				checkItem.click();
				break;
			} 
		}	
	} 
	
	public void sleepInSecond (long timeInSecond) {
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