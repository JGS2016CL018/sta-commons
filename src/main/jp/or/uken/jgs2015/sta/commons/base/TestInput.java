package jp.or.uken.jgs2015.sta.commons.base;

import java.sql.ResultSet;
import java.util.HashMap;

public class TestInput {
	public String caseID;
	public HashMap<String, ResultSet> data;

	public TestInput() {
		data = new HashMap<String, ResultSet>();
	}

}
