package jp.or.uken.jgs2015.sta.commons.excelUtil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class CellEx {
	private Cell cell;

	public CellEx(Row row, int colum) {
		cell = row.getCell(colum, Row.CREATE_NULL_AS_BLANK);
	}

	public String getValue() {
		return cell.toString();
	}

	public void setValue(String value) {
		cell.setCellValue(value);
	}
}
