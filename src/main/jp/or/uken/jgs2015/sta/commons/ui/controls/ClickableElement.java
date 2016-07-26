package jp.or.uken.jgs2015.sta.commons.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickableElement extends BaseElement {

	public ClickableElement(WebDriver _driver, By by) {
		super(_driver, by);
	}

	public ClickableElement(WebElement _element) {
		super(_element);
	}

	public void click() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));

		if (driver instanceof InternetExplorerDriver) {
			element.sendKeys(Keys.CONTROL);
			element.sendKeys(Keys.ENTER);
		} else {
			element.click();
		}
	}
}
