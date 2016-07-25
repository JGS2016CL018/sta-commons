package jp.or.uken.jgs2015.sta.commons.excelUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jp.or.uken.jgs2015.sta.commons.util.Utility;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WorkbookEx {

	protected Workbook book;
	protected String inputPath;

	public WorkbookEx() {}

	public WorkbookEx open(String path) throws Exception {
		inputPath = path;
		book = WorkbookFactory.create(new FileInputStream(inputPath));
		return this;
	}

	public void save() throws Exception{
		save(false);
	}
	public void save(boolean overWrite) throws Exception{
		if (! overWrite) {
			Utility.renameBeforeOverwrite(inputPath);
		}
		book.write(new FileOutputStream(inputPath));
	}

	public void saveAs(String newFileName) throws Exception{
		saveAs(newFileName, false);
	}

	public void saveAs(String newFileName, boolean overWrite) throws Exception{
		if (! overWrite) {
			Utility.renameBeforeOverwrite(newFileName);
		}
		book.write(new FileOutputStream(newFileName));
	}

	public SheetEx getSheet(String name){
		return new SheetEx(book.getSheet(name));
	}

	public SheetEx getSheet(String name, int headerRowIndex){
		return new SheetEx(book.getSheet(name), headerRowIndex);
	}

	public void close() throws IOException{
		book.close();
	}
}
