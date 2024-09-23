package com.example.demo;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelNumbersReader {
    public int[] readNumbers(String filePath) {
        try {
            List<Integer> numbers = new ArrayList<>();
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                numbers.add((int) row.getCell(0).getNumericCellValue());
            }
            workbook.close();
            return numbers.stream().mapToInt(i -> i).toArray();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
