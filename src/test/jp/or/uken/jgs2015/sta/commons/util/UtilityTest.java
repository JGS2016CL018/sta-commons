package jp.or.uken.jgs2015.sta.commons.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class UtilityTest {
	private WebDriver driver;
	private String baseUrl;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	private void setUpDriver() {
		System.setProperty("webdriver.ie.driver",
				"C:/selenium/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		baseUrl = "https://www.google.co.jp/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	public void tearDownDriver() {
		driver.quit();
	}

	@Test
	public void testCaptureWindow() throws IOException {
		setUpDriver();

		Path pngA = Utility.captureWindow(driver, "20160318.png");
		assertThat(pngA, instanceOf(Path.class));
		Path pngB = Utility.captureWindow(driver, "20160318\\20160318.png");
		assertThat(pngB, instanceOf(Path.class));
		Path pngC = Utility.captureWindow(driver, "20160318.png");
		assertThat(pngC, instanceOf(Path.class));
		Path pngD = Utility.captureWindow(driver, "20160318\\20160318.png");
		assertThat(pngD, instanceOf(Path.class));

		tearDownDriver();
	}

	@Test
	public void testRenameBeforeOverwrite() throws IOException {
		String expectedFileName = "expected.txt";
		Path tempDir = Files.createTempDirectory("temp");
		Path baseFile = tempDir.resolve(expectedFileName);

		// ファイルが存在しない場合は空振り
		assertThat(Files.exists(baseFile), is(false));
		assertThat(Utility.renameBeforeOverwrite(baseFile.toString()),
				nullValue());
		assertThat(Files.exists(baseFile), is(false));

		// ファイルが存在する場合はリネーム（リネーム後に同名のファイルが存在しない）
		Files.createFile(baseFile);
		assertThat(Files.exists(baseFile), is(true));
		Path newFile = Utility.renameBeforeOverwrite(baseFile.toString());
		assertThat(newFile, instanceOf(Path.class));
		assertThat(Files.exists(baseFile), is(false));

		// 後始末
		Files.delete(newFile);
		Files.delete(tempDir);
	}

	@Test
	public void testGFormatedDate() throws IOException {
		assertThat(Utility.getFormatedDate(LocalDateTime.of(2016, 7, 31, 14,
				25, 36, 789000000)), is("20160731142536789"));
		assertThat(Utility.getFormatedDate(LocalDateTime.of(2016, 7, 31, 14,
				25, 36, 789000000)), not("20160731142537789"));
	}

	@Test
	public void testRepeat() {
		assertThat(Utility.repeat("A", 5), is("AAAAA"));
		assertThat(Utility.repeat("ABC", 3), is("ABCABCABC"));
		assertThat(Utility.repeat("あいうえお", 2), is("あいうえおあいうえお"));
		assertThat(Utility.repeat("12", 0), is(""));
		thrown.expect(NegativeArraySizeException.class);
		assertThat(Utility.repeat("12", -1), anything());
	}
}
