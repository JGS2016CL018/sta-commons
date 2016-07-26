package jp.or.uken.jgs2015.sta.commons.base;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import jp.or.uken.jgs2015.sta.commons.util.Config;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumTestCase {
	protected static WebDriver driver;
	protected static StringBuffer verificationErrors = new StringBuffer();
	protected static Config config;

	@BeforeClass
	public static void beforeClass() throws Exception {
		config = new Config();
		initDriver();
		driver.manage()
				.timeouts()
				.implicitlyWait(config.defaultTimeOutSeconds(),
						TimeUnit.SECONDS);
	}

	@AfterClass
	public static void afterClass() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private static void initDriver() throws IOException {
		switch (config.browserName()) {
		case "Firefox":
			driver = new FirefoxDriver();
		case "InternetExplorer":
			System.setProperty("webdriver.ie.driver", config.ieDriverPath());
			driver = new InternetExplorerDriver();
		}
		driver.manage()
				.timeouts()
				.implicitlyWait(config.defaultTimeOutSeconds(),
						TimeUnit.SECONDS);
	}

}
