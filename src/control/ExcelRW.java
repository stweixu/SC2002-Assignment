package control;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelRW {
	public static ArrayList<Object[]> readFile(String pathName, int noOfCols) {
		try {
			FileInputStream file = new FileInputStream(pathName);
	        Workbook workbook = new XSSFWorkbook(file);
	        Sheet sheet = workbook.getSheetAt(0);
	        
	        ArrayList<Object[]> entry = new ArrayList<Object[]>();
	        
	        for (Row row : sheet) {
	            if (isRowEmpty(row))	break;

	            Object[] colValue = new Object[noOfCols];
	            
	            for (int i=0; i<noOfCols; i++) {
	            	Cell cell = row.getCell(i);
	                switch (cell.getCellType()) {
	                    case STRING:
	                    	colValue[i] = cell.getStringCellValue();
	                        break;
	                    case NUMERIC:
	                        colValue[i] = cell.getNumericCellValue();
	                        break;
	                    default:
	                    	colValue[i] = "-";
	                }
	            }
	            entry.add(colValue);
	        }
	        
	        workbook.close();
            file.close();
            
            return entry;
		} catch (IOException e) {
	    	System.out.println("Failed to open file!");
	    	return null;
	    }
	}
	
	public static boolean writeFile(ArrayList<Object[]> table, String pathName, int noOfCol) {		
		try {
			FileInputStream fin = new FileInputStream(pathName);
			Workbook workbook = WorkbookFactory.create(fin);		
			Sheet sheet = workbook.createSheet();
			workbook.removeSheetAt(0);
			
			int size = table.size();
			
			// Create row and write data
			for (int i=0; i<size; i++) {
	    		Object[] col = table.get(i);
	    		Row row = sheet.createRow(sheet.getLastRowNum() + 1);
	    		
	    		for (int j=0; j<noOfCol; j++) {
	    			Cell cell = row.createCell(j);
	    			if (col[j] instanceof Double)	cell.setCellValue((Double) col[j]);
	    			else	cell.setCellValue((String) col[j]);
	    		}
	    	}
            
            fin.close();
           
            FileOutputStream fout = new FileOutputStream(pathName);
            workbook.write(fout);
            fout.close();
			workbook.close();
            
            System.out.println("Successfully written to file!");
            return true;
		} catch (IOException e) {
	    	System.out.println("Failed to write to file!");
	    	return false;
	    }
	}
	
	public static boolean isRowEmpty(Row row) {
        if (row == null || row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK && cell.getCellType() != CellType._NONE) {
                return false;
            }
        }
        return true;
    }
	
}
