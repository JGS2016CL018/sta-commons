package jp.or.uken.jgs2015.sta.commons.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ButtonElement extends ClickableElement {

	public ButtonElement(WebDriver _driver, By by) {
		super(_driver, by);
	}

	public String caption() {
		return element.getAttribute("value");
	}

	public String type() {
		return element.getAttribute("type");
	}

}
