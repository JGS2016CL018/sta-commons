package jp.or.uken.jgs2015.sta.commons.ui;

import org.openqa.selenium.WebDriver;

public class PageIdentifiedByUrl extends Page {

	public PageIdentifiedByUrl(WebDriver _driver) {
		super(_driver);
	}

	@Override
	public Page activate() {
		if (helper.switchToWindowByUrl(baseUrl)) {
			return this;
		} else {
			return null;
		}
	}

	@Override
	public boolean verify() {
		return helper.verifyUrl(baseUrl);
	}

	@Override
	public boolean exists() {
		return (helper.getWindowHandleByUrl(baseUrl) != null);
	}

}
