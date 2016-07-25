package jp.or.uken.jgs2015.sta.commons.util;

import org.openqa.selenium.WebDriver;

public class WebDriverHelper {
	WebDriver driver;

	public WebDriverHelper(WebDriver _driver) {
		driver = _driver;
	}

	public String getWindowHandleByUrl(String url) {
		String currentWindowHandle = driver.getWindowHandle();
		if (verifyUrl(url)) {
			return currentWindowHandle;
		}

		String targetID = null;
		for (String windowID:driver.getWindowHandles()) {
			driver.switchTo().window(windowID);
			if (verifyUrl(url)) {
				targetID = windowID;
				break;
			}
		}

		driver.switchTo().window(currentWindowHandle);
		return targetID;
	}
	public boolean switchToWindowByUrl(String url) {
		if (verifyUrl(url)) {
			return true;
		}
		String targetID = getWindowHandleByUrl(url);
		if (targetID != null) {
			driver.switchTo().window(targetID);
			return true;
		} else {
			return false;
		}
	}
	public boolean verifyUrl(String url) {
		return driver.getCurrentUrl().startsWith(url);
	}

	public String getWindowHandleByTitle(String title) {
		String currentWindowHandle = driver.getWindowHandle();
		if (verifyTitle(title)) {
			return currentWindowHandle;
		}

		String targetID = null;
		for (String windowID:driver.getWindowHandles()) {
			driver.switchTo().window(windowID);
			if (verifyTitle(title)) {
				targetID = windowID;
				break;
			}
		}

		driver.switchTo().window(currentWindowHandle);
		return targetID;
	}
	public boolean switchToWindowByTitle(String title) {
		if (verifyTitle(title)) {
			return true;
		}
		String targetID = getWindowHandleByTitle(title);
		if (targetID != null) {
			driver.switchTo().window(targetID);
			return true;
		} else {
			return false;
		}
	}
	public boolean verifyTitle(String title) {
		return driver.getTitle().contains(title);
	}

	// for debugging
	public void dumpAllWindows() {
		String currentWindowHandle = driver.getWindowHandle();

		System.out.println(Utility.repeat("-", 50));

		for (String windowID:driver.getWindowHandles()) {
			driver.switchTo().window(windowID);
			System.out.println(getWindowInfo());
		}

		//for debug
		System.out.println(Utility.repeat("-", 50));

		driver.switchTo().window(currentWindowHandle);
	}
	public String getWindowInfo() {
		return String.format("[%1$s]%2$s", driver.getWindowHandle(), driver.getCurrentUrl());
	}
}
