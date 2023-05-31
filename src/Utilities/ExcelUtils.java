	package Utilities;
	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.HashMap;
	import java.util.Map;

	public class ExcelUtils {

	    public static Map<String, String> readTestDataFromExcel(String filePath, String sheetName) {
	        Map<String, String> testData = new HashMap<>();

	        try (FileInputStream fileInputStream = new FileInputStream(filePath);
	             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
	            Sheet sheet = workbook.getSheet(sheetName);
	            Row headerRow = sheet.getRow(0);

	            for (Cell cell : headerRow) {
	                int columnIndex = cell.getColumnIndex();
	                String columnName = cell.getStringCellValue().trim();

	                Cell dataCell = sheet.getRow(1).getCell(columnIndex);
	                String cellValue = getCellValueAsString(dataCell);

	                testData.put(columnName, cellValue);
	                System.out.println(columnName+" : "+cellValue);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return testData;
	    }

	    private static String getCellValueAsString(Cell cell) {
	        if (cell == null)
	            return "";

	        if (cell.getCellType() == CellType.STRING)
	            return cell.getStringCellValue().trim();
	        else if (cell.getCellType() == CellType.NUMERIC)
	            return String.valueOf((int) cell.getNumericCellValue());
	        else if (cell.getCellType() == CellType.BOOLEAN)
	            return String.valueOf(cell.getBooleanCellValue());
	        else
	            return "";
	    }
	}
