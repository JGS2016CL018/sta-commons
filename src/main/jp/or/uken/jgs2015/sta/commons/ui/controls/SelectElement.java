package jp.or.uken.jgs2015.sta.commons.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectElement extends BaseElement {

	Select selectElement;
	public SelectElement(WebDriver _driver, By by) {
		super(_driver, by);
		selectElement = new Select(element);
	}

	public SelectElement(WebElement _element) {
		super(_element);
		selectElement = new Select(element);
	}

	public void selectByValue(String value){
		selectElement.selectByValue(value);;
	}
	public void selectByIndex(int index){
		selectElement.selectByIndex(index);
	}
	public void selectByVisibleText(String text){
		selectElement.selectByVisibleText(text);
	}
}
