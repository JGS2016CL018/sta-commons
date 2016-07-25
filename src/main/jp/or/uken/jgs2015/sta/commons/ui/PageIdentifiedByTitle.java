package jp.or.uken.jgs2015.sta.commons.ui;

import org.openqa.selenium.WebDriver;

public class PageIdentifiedByTitle extends Page {

	public PageIdentifiedByTitle(WebDriver _driver) {
		super(_driver);
	}

	@Override
	public Page activate() {
		if (helper.switchToWindowByTitle(title)) {
			return this;
		} else {
			return null;
		}
	}

	@Override
	public boolean verify() {
		return helper.verifyTitle(title);
	}

	@Override
	public boolean exists() {
		return (helper.getWindowHandleByTitle(title) != null);
	}

}
