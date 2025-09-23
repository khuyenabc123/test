package utils.readers;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private CellStyle cellstyle;
    private Color color;
    private String excelFilePath;
    private final Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String excelPath, String sheetName) {
        try {
            File file = new File(excelPath);

            if(!file.exists()) {
                file.createNewFile();

                System.out.println("File does not exist, so it were auto created!");
            }

            FileInputStream fileIn = new FileInputStream(excelPath);

            workbook = WorkbookFactory.create(fileIn);

            sheet = workbook.getSheet(sheetName);

            if(sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            this.excelFilePath = excelPath;

            sheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCellData(int rowNum, int colNum) throws Exception{
        try{
            cell = sheet.getRow(rowNum).getCell(colNum);

            String cellData = null;

            switch (cell.getCellType()){
                case STRING:
                    cellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell))
                    {
                        cellData = String.valueOf(cell.getDateCellValue());
                    }
                    else
                    {
                        cellData = String.valueOf((long)cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    cellData = "";
                    break;
            }

            return cellData;
        }catch (Exception e){
            return"";
        }
    }

    public String getCellData(String columnName, int rowNum) throws Exception {
        return getCellData(rowNum, columns.get(columnName));
    }

    public void setCellData(String text, int rowNum, int colNum) throws Exception {
        try{
            Row row = sheet.getRow(rowNum);

            if(row == null)
            {
                row = sheet.createRow(rowNum);
            }
            cell = row.getCell(colNum);

            if (cell == null) {
                cell = row.createCell(colNum);
            }

            cell.setCellValue(text);

            FileOutputStream fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);

            fileOut.flush();
            fileOut.close();
        }catch(Exception e){
            throw (e);
        }
    }
}
