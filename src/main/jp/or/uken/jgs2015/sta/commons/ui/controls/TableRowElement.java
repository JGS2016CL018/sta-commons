package jp.or.uken.jgs2015.sta.commons.ui.controls;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableRowElement extends BaseElement {

	public TableRowElement(WebElement _element) {
		super(_element);
	}

	public List<WebElement> columns() {
		return element.findElements(By.tagName("TD"));
	}

}
