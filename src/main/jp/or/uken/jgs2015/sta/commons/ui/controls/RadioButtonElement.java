package jp.or.uken.jgs2015.sta.commons.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RadioButtonElement extends ClickableElement {

	public RadioButtonElement(WebDriver _driver, By by) {
		super(_driver, by);
	}

	public RadioButtonElement(WebElement _element) {
		super(_element);
	}

}
