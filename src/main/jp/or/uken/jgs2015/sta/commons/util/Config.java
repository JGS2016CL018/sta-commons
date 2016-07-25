package jp.or.uken.jgs2015.sta.commons.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
	private Properties prop;
	private String propPath = "resources/sta-commons.properties";

	public Config() throws IOException {
		prop = new Properties();
		InputStream propertiesFile = null;
		try{
			propertiesFile = new FileInputStream(propPath);
		}catch(Exception e){
			throw new FileNotFoundException("[" + propPath + "]");
		}
		prop.load(propertiesFile);
	}

	public Path evidenceSavePath() {
		return Paths.get(prop.getProperty("evidenceSavePath"));
	}
	public String browserName() {
		return prop.getProperty("browserName");
	}
	public String ieDriverPath() {
		return prop.getProperty("ieDriverPath");
	}
	public long defaultTimeOutSeconds() {
		return Long.parseLong(prop.getProperty("defaultTimeOutSeconds"));
	}
}
