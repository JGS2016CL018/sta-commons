package jp.or.uken.jgs2015.sta.commons.excelUtil;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class SheetEx implements Iterator<Row>, Iterable<Row> {

	protected Sheet sheet;
	protected HashMap<String, Integer> columnNamesMap;
	protected BidiMap<String, Integer> columnNames;
	protected int headerRowIndex = -1;

	public SheetEx(Sheet _sheet) {
		sheet = _sheet;
	}
	public SheetEx(Sheet _sheet, int headerRowIndex) {
		sheet = _sheet;
		setColumnNamesByHeaderRow(headerRowIndex);
	}

	public void setColumnNames(HashMap<String, Integer> _columnNamesMap){
		columnNamesMap = _columnNamesMap;
		columnNames = new DualHashBidiMap<String, Integer>(columnNamesMap);
	}

	public void setColumnNamesByHeaderRow(int _headerRowIndex){
		headerRowIndex = _headerRowIndex;
		Row headerRow = sheet.getRow(headerRowIndex);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (Cell cell : headerRow){
			map.put(cells(headerRow, cell.getColumnIndex()).getValue(), cell.getColumnIndex());
		}
		setColumnNames(map);
	}

	public CellEx cells(int row, int column)
	{
		return cells(sheet.getRow(row), column);
	}

	public CellEx cells(int row, String columnName)
	{
		return cells(row, getColumnIndexByName(columnName));
	}

	public CellEx cells(Row row, int column)
	{
		return new CellEx(row, column);
	}

	public CellEx cells(Row row, String columnName)
	{
		return cells(row, getColumnIndexByName(columnName));
	}

	protected int getColumnIndexByName(String columnName)
	{
		return columnNames.get(columnName);
	}

	protected String getColumnNameByIndex(int columnIndex)
	{
		return columnNames.getKey(columnIndex);
	}

	public boolean isHeader(Row row)
	{
		return (row.getRowNum()==headerRowIndex);
	}
	public boolean isBody(Row row)
	{
		return (row.getRowNum()>headerRowIndex);
	}

	@Override
	public boolean hasNext() {
		return sheet.rowIterator().hasNext();
	}

	@Override
	public Row next() {
		return sheet.rowIterator().next();
	}

	@Override
	public Iterator<Row> iterator() {
		return sheet.rowIterator();
	}

}
