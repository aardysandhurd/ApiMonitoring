package org.raman.abstractRes;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelFileReader {
    private static final Logger logger = LogManager.getLogger(ExcelFileReader.class);
    
    public List<String> getListOfApis() {
        List<String> firstColumnValues = new ArrayList<>();
        String filePath = System.getProperty("user.dir") + "/src/main/java/org/raman/resources/api.xlsx";
        
        try (FileInputStream file = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() != 0) { // Skip header row
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (cell.getColumnIndex() == 0) { // First column
                            firstColumnValues.add(cell.getStringCellValue());
                        }
                    }
                }
            }
            
        } catch (FileNotFoundException e) {
            logger.error("Excel file not found: {}", filePath, e);
            return new ArrayList<>(); // Return empty list instead of null
        } catch (IOException e) {
            logger.error("Error reading Excel file: {}", filePath, e);
            return new ArrayList<>(); // Return empty list instead of null
        }
        
        return firstColumnValues;
    }
}
