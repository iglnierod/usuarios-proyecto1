package model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLSX {

    public static void allUsersToXLSX(Users users, File file) {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("User");
        sheet.setColumnWidth(0, 3000);
        sheet.setColumnWidth(1, 1500);
        sheet.setColumnWidth(2, 8000);
        sheet.setColumnWidth(3, 8000);

        Row header = sheet.createRow(0);
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        // Crear las celdas del encabezado
        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(style);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Age");
        headerCell.setCellStyle(style);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Email");
        headerCell.setCellStyle(style);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Image");
        headerCell.setCellStyle(style);

        int rowIndex = 1;
        for (User user : users.getAllUsers()) {
            Row row = sheet.createRow(rowIndex);

            Cell cell = row.createCell(0);
            cell.setCellValue(user.getName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(user.getAge());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(user.getEmail());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(user.getImageName());
            cell.setCellStyle(style);

            rowIndex++;
        }

        try (FileOutputStream writer = new FileOutputStream(file)) {
            workbook.write(writer);
            System.out.println("XLSX generado con éxito.");
        } catch (IOException e) {
            System.err.println("No se pudo exportar a XLSX");
        }
    }
}
