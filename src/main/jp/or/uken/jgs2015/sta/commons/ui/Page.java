package jp.or.uken.jgs2015.sta.commons.ui;

import java.io.IOException;
import java.nio.file.Path;

import jp.or.uken.jgs2015.sta.commons.ui.controls.ButtonElement;
import jp.or.uken.jgs2015.sta.commons.ui.controls.CheckboxElement;
import jp.or.uken.jgs2015.sta.commons.ui.controls.LinkElement;
import jp.or.uken.jgs2015.sta.commons.ui.controls.RadioButtonElement;
import jp.or.uken.jgs2015.sta.commons.ui.controls.SelectElement;
import jp.or.uken.jgs2015.sta.commons.ui.controls.TextBoxElement;
import jp.or.uken.jgs2015.sta.commons.util.Utility;
import jp.or.uken.jgs2015.sta.commons.util.WebDriverHelper;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	protected WebDriver driver;
	protected WebDriverHelper helper;
	protected String baseUrl = null;
	protected String title = null;
	protected boolean acceptNextAlert = true;
	protected long defaultTimeOutSeconds = 10;

	public Page(WebDriver _driver) {
		driver = _driver;
		helper = new WebDriverHelper(driver);
	}

	public Page open() {
		driver.get(baseUrl);
		waitForLoading();
		return this;
	}

	public void close() {
		if (activate() != null) {
			driver.close();
			for (String windowID : driver.getWindowHandles()) {
				driver.switchTo().window(windowID);
				return;
			}
		}
	}

	public abstract Page activate();

	public abstract boolean verify();

	public abstract boolean exists();

	public void waitForLoading() {
		waits().until(new ExpectedPageCondition(this));
	}

	public void waitForPopup() {
		waits().until(new ExpectedPopupWindowCondition(this));
	}

	public WebDriverWait waits() {
		return new WebDriverWait(driver, defaultTimeOutSeconds);
	}

	public Path capture(String fileName) throws IOException {
		return capture(fileName, false);
	}

	public Path capture(String fileName, boolean overwrite) throws IOException {
		return Utility.captureWindow(driver, fileName, overwrite);
	}

	// Elements
	public TextBoxElement findTextBoxElement(By by) {
		return new TextBoxElement(driver, by);
	}

	public ButtonElement findButtonElement(By by) {
		return new ButtonElement(driver, by);
	}

	public LinkElement findLinkElement(By by) {
		return new LinkElement(driver, by);
	}

	public CheckboxElement findCheckboxElement(By by) {
		return new CheckboxElement(driver, by);
	}

	public SelectElement findSelectElement(By by) {
		return new SelectElement(driver, by);
	}

	public RadioButtonElement findRadioButtonElement(By by) {
		return new RadioButtonElement(driver, by);
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
