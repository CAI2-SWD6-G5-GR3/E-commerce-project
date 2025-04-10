package com.demoblaze.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        // Load the Excel file from the resources folder
        String filePath = "src/main/resources/testdata/testdata.xlsx";  // Path to your test data Excel file
        InputStream file = getClass().getClassLoader().getResourceAsStream("testdata/testdata.xlsx"); // Optional, if you want to use classpath
        Workbook workbook;

        if (file != null) {
            workbook = new XSSFWorkbook(file);
        } else {
            throw new IOException("Excel file not found");
        }

        // Access the sheet by name
        Sheet sheet = workbook.getSheet("LoginData"); // Specify the sheet name

        // Get the number of rows and columns
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        // Create a 2D array to hold the data from the sheet
        Object[][] data = new Object[rowCount - 1][colCount]; // Exclude the header row

        // Read the data from Excel sheet and populate the array
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell.getStringCellValue(); // Read the cell value as a string
            }
        }

        // Close the workbook and input stream
        workbook.close();
        file.close();

        return data; // Return the data to be used by TestNG
    }
}
