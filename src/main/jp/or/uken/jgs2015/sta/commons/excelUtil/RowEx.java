package jp.or.uken.jgs2015.sta.commons.excelUtil;

import org.apache.poi.ss.usermodel.Row;

public class RowEx {
	SheetEx sheet;
	Row row;

	public RowEx(SheetEx _sheet, Row _row) {
		sheet = _sheet;
		row = _row;
	}

	public CellEx cells(int column) {
		return new CellEx(row, column);
	}

	public CellEx cells(String columnName) {
		return cells(sheet.getColumnIndexByName(columnName));
	}

	public boolean isHeader() {
		return (sheet.isHeader(row));
	}

	public boolean isBody() {
		return (sheet.isBody(row));
	}
}
