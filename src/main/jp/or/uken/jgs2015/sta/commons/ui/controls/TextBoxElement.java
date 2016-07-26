package jp.or.uken.jgs2015.sta.commons.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextBoxElement extends BaseElement {

	public TextBoxElement(WebDriver _driver, By by) {
		super(_driver, by);
	}

	public String getText() {
		return element.getText();
	}

	public void setText(String text) {
		element.clear();
		element.sendKeys(text);
	}
}
