package jp.or.uken.jgs2015.sta.commons.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverHelperTest {
	private WebDriver driver;
	private String baseUrl;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	private void setUpDriver() {
		System.setProperty("webdriver.ie.driver",
				"C:/selenium/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		baseUrl = "https://www.google.co.jp/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	public void tearDownDriver() {
		driver.quit();
	}

	@Test
	public void testGetWindowHandleByUrl() {
		setUpDriver();

		WebDriverHelper driverHelper = new WebDriverHelper(driver);

		String windowID = driverHelper
				.getWindowHandleByUrl("http://www.google.co.jp");
		assertThat(windowID, nullValue());
		String windowID2 = driverHelper
				.getWindowHandleByUrl("https://www.google.co.jp");
		assertThat(windowID2, notNullValue());

		tearDownDriver();
	}
}
