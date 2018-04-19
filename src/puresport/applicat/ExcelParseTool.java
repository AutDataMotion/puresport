/**
 * PureSport
 * create by zw at 2018年4月12日
 * version: v1.0
 */
package puresport.applicat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author zw 每一个Excel文件都将被解析成一个WorkBook对象； Excel的每一页都将被解析成一个Sheet对象；
 *         然后，Excel中的每一行都是一个Row对象， 每一个单元格都是一个Cell对象。
 */
public class ExcelParseTool {
	private static final String SUFFIX_2003 = ".xls";
	private static final String SUFFIX_2007 = ".xlsx";

	@SuppressWarnings("resource")
	public static List<MdlExcelRow> getWorkBookTable(String filePath) throws IOException, IllegalArgumentException {
		File file = new File(filePath);
		InputStream is = new FileInputStream(file);
		Workbook workbook = null;
		// 根据后缀，得到不同的Workbook子类，即HSSFWorkbook或XSSFWorkbook
		if (filePath.endsWith(SUFFIX_2003)) {
			workbook = new HSSFWorkbook(is);
		} else if (filePath.endsWith(SUFFIX_2007)) {
			workbook = new XSSFWorkbook(is);
		} else {
			throw new IllegalArgumentException("不是excel文件:" + filePath);
		}
		return parseWorkbook(workbook);
	}

	private static List<MdlExcelRow> parseWorkbook(Workbook workbook) {
		int numOfSheet = workbook.getNumberOfSheets();
		if (numOfSheet < 0) {
			return new ArrayList<MdlExcelRow>(0);
		}
		return parseSheet(workbook.getSheetAt(0));
		// 依次解析每一个Sheet
		// for (int i = 0; i < numOfSheet; ++i) {
		// Sheet sheet = workbook.getSheetAt(i);
		// parseSheet(sheet, sheetMdlList);
		// }
	}

	private static List<MdlExcelRow> parseSheet(Sheet sheet) {
		List<MdlExcelRow> mdlList = new LinkedList<>();
		int count = 0;
		// 利用迭代器，取出每一个Row
		Row row = null;
		Iterator<Row> iterator = sheet.iterator();
		while (iterator.hasNext()) {
			row = iterator.next();
			// 由于第一行是标题，因此这里单独处理
			if (count == 0) {
				count++;
				continue;
				// mUsedMethod = new ArrayList<>();
				// parseRowAndFindMethod(row);
			} else {
				// 其它行都在这里处理
				mdlList.add(parseRowAndFillData(row));
			}
			++count;
		}
		return mdlList;
	}

	// 开始解析具体的数据
	private static MdlExcelRow parseRowAndFillData(Row row) {
		// 同样利用parseRow得到具体每一行的数据
		return new MdlExcelRow(parseRow(row));
	}

	// 这里是解析每一行的代码
	private static List<String> parseRow(Row row) {
		List<String> rst = new ArrayList<>();
		Cell cell;
		// 利用迭代器得到每一个cell
		Iterator<Cell> iterator = row.iterator();
		while (iterator.hasNext()) {
			cell = iterator.next();
			// 取出cell中的value
			rst.add(getStrValue(cell));
		}
		return rst;
	}

	private static String getStrValue(Cell cell) {
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		}
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf((int)cell.getNumericCellValue());
		}
		return cell.getStringCellValue();

	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public static void main(String[] args) throws IllegalArgumentException, IOException {
		// TODO Auto-generated method stub
		List<MdlExcelRow> table = ExcelParseTool.getWorkBookTable("/home/zw/Downloads/user_admin.xls");
		if (Objects.isNull(table)) {
			System.out.println("empty");
		} else {
			table.stream().forEach(e -> System.out.println(e));
		}
	}

}
