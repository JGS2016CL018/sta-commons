package jp.or.uken.jgs2015.sta.commons.base;

import java.nio.file.Path;
import java.sql.ResultSet;
import java.util.HashMap;

public class TestResult {
	public boolean success;

	public HashMap<String, Path> evidence;
	public HashMap<String, ResultSet> data;

	public TestResult() {
		evidence = new HashMap<String, Path>();
		data = new HashMap<String, ResultSet>();
	}

}
