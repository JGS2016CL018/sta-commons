package jp.or.uken.jgs2015.sta.commons.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConfigTest {
	private String propPath = "resources/sta-commons.properties";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConfigFileNotFound() throws IOException, URISyntaxException {
		System.out.println(Paths.get(new File(propPath).toURI()));
		Path source = Paths.get(new File(propPath).toURI());
		Path target = source.resolveSibling("renamed.sta-commons.properties");

		try {
			Files.move(source, target);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		try {
			new Config();
		} catch (FileNotFoundException e) {
			assertThat(e.getMessage(), is("[" + propPath + "]"));
		} finally {
			Files.move(target, source);
		}
	}
	@Test
	public void testConfig() throws IOException {
		Config config = new Config();
		assertThat(config, anything());
	}

	@Test
	public void testEvidenceSavePath() throws IOException {
		assertThat(new Config().evidenceSavePath(), is(Paths.get(".\\evidence\\")));
	}

}
