package jp.or.uken.jgs2015.sta.commons.ui.controls;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableElement extends BaseElement {

	public TableElement(WebDriver _driver, By by) {
		super(_driver, by);
	}

	public List<TableRowElement> rows() {
		List<TableRowElement> _rows = new ArrayList<TableRowElement>();
		for(WebElement row : element.findElements(By.tagName("TR"))) {
			_rows.add(new TableRowElement(row));
		}
		return _rows;
	}

	public String dump() {
		StringBuilder buf = new StringBuilder();

		int r = 0;
		for(TableRowElement row : rows()) {
			int c = 0;
			for(WebElement column : row.columns()) {
				buf.append("cell(" + r + ", " + c + ")=");
				buf.append("[" + column.getText() + "]\n");
				c++;
			}
			r++;
		}

		return buf.toString();
	}
}
