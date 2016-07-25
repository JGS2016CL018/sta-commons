package jp.or.uken.jgs2015.sta.commons.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseElement {
	protected WebElement element;
	protected WebDriver driver;
	public BaseElement(WebDriver _driver, By by){
		driver = _driver;
		element = driver.findElement(by);
	}
	public BaseElement(WebElement _element){
		element = _element;
	}
}
