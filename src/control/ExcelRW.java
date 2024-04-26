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
			FileInputStream file = new FileInputStream(pathName);	// Create an input stream for the pathName
	        Workbook workbook = new XSSFWorkbook(file);	// Create a new workbook object based on the input stream
	        Sheet sheet = workbook.getSheetAt(0);	// Get the first sheet of the excel file. We assume that the excel file read only has 1 sheet
	       
		// Creates 2D array (rows dynamic, cols defined by noOfCols) to store excel entries
	        ArrayList<Object[]> entry = new ArrayList<Object[]>();		// Create placeholder for the rows

			// Iterate the rows
	        for (Row row : sheet) {
	            if (isRowEmpty(row))	break;	// Exit if no more rows to read

	            Object[] colValue = new Object[noOfCols];	// Create placeholder for columns

			// Iterate the columns
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
	            entry.add(colValue);	// update cells read to the placeholder
	        }

			// Close the workbook and input stream when done
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
			FileInputStream fin = new FileInputStream(pathName);	// Create an input stream for the pathName
			Workbook workbook = WorkbookFactory.create(fin);		// Create a new workbook object based on the input stream
			Sheet sheet = workbook.createSheet();		// Create new sheet for the excel file
			workbook.removeSheetAt(0);			// Remove existing sheet
			
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
            
            fin.close();	// Close input stream when done

		// Create output stream and write table entries to excel file
            FileOutputStream fout = new FileOutputStream(pathName);	
            workbook.write(fout);

		// Close output stream and workbook when done
            fout.close();
		workbook.close();
            
            System.out.println("Successfully written to file!");
            return true;
		} catch (IOException e) {
	    	System.out.println("Failed to write to file!");
	    	return false;
	    }
	}

	// Checks whether a row in the excel sheet is empty. Used to stop reads when no more rows to read
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
