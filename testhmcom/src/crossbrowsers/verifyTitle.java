package crossbrowsers;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class verifyTitle {
 
	WebDriver driver;
	@BeforeTest
	@Parameters("browser")
  public void setUp(String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Nexus 5X");
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capabilities);
			//new RemoteWebDriver(service.getUrl(), capabilities);

		} else {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Selenium\\Sel\\geckodriver-v0.19.0-win64\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			// This is the location where you have installed Firefox on your
			// machine
			options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("moz:firefoxOptions", options);
			driver = new FirefoxDriver(capabilities);
		}

	}
	
	@Test
	public void verifyPageTitles() throws InterruptedException {
		driver.get("https://www.facebook.com");
		Thread.sleep(3000);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		WebElement cookieMessage = driver.findElement(By.xpath(".//*[@id='u_0_13]"));
		if (cookieMessage.isDisplayed()) {
			cookieMessage.click();
		}
		// Assert.assertEquals(driver.getTitle().toString(), "Facebook - Log In
		// or Sign Up");
		Assert.assertTrue(driver.getTitle().toString().equalsIgnoreCase("Facebook - Log In or Sign Up"),
				"Title is not matching");
		// driver.close();
	}

	@Test
	public void verifyGmail() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			driver.get("https://m.hm.com");
			Thread.sleep(3000);
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.getTitle());
		}
		Thread.sleep(50000);
	}

	@Test
	public void verifyYahoo() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			driver.get("https://www.yahoo.co.in");
			Thread.sleep(3000);
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.getTitle());
		}
		Thread.sleep(50000);
	}
	@AfterTest
	public void settleDown(){
		driver.close();
	}


}
