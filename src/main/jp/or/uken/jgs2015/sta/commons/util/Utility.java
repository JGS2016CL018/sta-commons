package jp.or.uken.jgs2015.sta.commons.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	public Utility() {
	}

	public static Path captureWindow(WebDriver driver, String fileName) throws IOException {
		return captureWindow(driver, fileName, false);
	}

	public static Path captureWindow(WebDriver driver, String fileName, boolean overwrite) throws IOException {
		if (driver instanceof TakesScreenshot) {
			TakesScreenshot screen = (TakesScreenshot)driver;
			Path fullFileName = new Config().evidenceSavePath().resolve(fileName);
			Files.createDirectories(fullFileName.getParent());
			if (! overwrite) {
				renameBeforeOverwrite(fullFileName);
			}
			Files.write(fullFileName, screen.getScreenshotAs(OutputType.BYTES));
			return fullFileName;
		} else {
			return null;
		}
	}

	public static Path renameBeforeOverwrite(String fullFileName) throws IOException {
		return renameBeforeOverwrite(Paths.get(fullFileName));
	}
	public static Path renameBeforeOverwrite(Path fullFileName) throws IOException {
		if (Files.exists(fullFileName)) {
			Path newFilePath = generateNewFileName(fullFileName);
			Files.move(fullFileName, newFilePath);
			return newFilePath;
		} else {
			return null;
		}
	}
	private static Path generateNewFileName(Path fullFileName) {
		StringBuilder newFileName = new StringBuilder();
		newFileName.append("BK");
		newFileName.append(getFormatedDate());
		newFileName.append(".");
		newFileName.append(fullFileName.getFileName().toString());

		return fullFileName.resolveSibling(newFileName.toString());
	}

	public static String getFormatedDate() {
		return getFormatedDate(LocalDateTime.now());
	}
	public static String getFormatedDate(LocalDateTime target) {
		return target.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
	}

	public static String repeat(String expression, int times) {
		return new String(new char[times]).replace("\0", expression);
	}
}
