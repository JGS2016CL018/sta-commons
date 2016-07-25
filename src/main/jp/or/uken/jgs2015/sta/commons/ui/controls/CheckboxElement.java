package jp.or.uken.jgs2015.sta.commons.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxElement extends ClickableElement {

	public CheckboxElement(WebDriver _driver, By by) {
		super(_driver, by);
	}

	public CheckboxElement(WebElement _element) {
		super(_element);
	}

	public void check(){
		if (! checked()){
			click();
		}
	}

	public void uncheck(){
		if (checked()){
			click();
		}
	}

	public boolean checked(){
		if (element.getAttribute("checked") == null) {
			return false;
		}
		return element.getAttribute("checked").equals("checked");
	}
}
