package jp.or.uken.jgs2015.sta.commons.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExpectedPageCondition implements ExpectedCondition<Boolean> {

	private Page page;

	public ExpectedPageCondition(Page _page) {
		page = _page;
	}

	@Override
	public Boolean apply(WebDriver driver) {
		return page.verify();
	}
}