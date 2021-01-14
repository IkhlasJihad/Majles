
package Models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportToExcel {
    private Workbook workbook ;
    public void writeExcel(List<Commitment> stdList, String excelFilePath) throws IOException {
        workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
       
        createHeader(sheet);
        int rowCount = 1;
        for (Commitment rec : stdList) {
            Row row = sheet.createRow(rowCount++);
            writeBook(rec, row, sheet);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }
    private void writeBook(Commitment stdComm, Row row, Sheet sheet) {
        Cell cell = row.createCell(1);
        cell.setCellStyle(styleCell(sheet));
        cell.setCellValue(stdComm.getCom_id());

        cell = row.createCell(2);
        cell.setCellStyle(styleCell(sheet));
        cell.setCellValue(stdComm.getCom_name());

        cell = row.createCell(3);
        cell.setCellStyle(styleCell(sheet));
        cell.setCellValue(stdComm.getCom_count());
        
    }
    
    private CellStyle styleCell(Sheet sheet){
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setWrapText(true);
        Font font = sheet.getWorkbook().createFont();
        font.setBold(false);
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        return cellStyle;
    }
    private void createHeader(Sheet sheet){
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setWrapText(true);
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);
        Row row = sheet.createRow(0);
        Cell cellID = row.createCell(1);
        Cell cellName = row.createCell(2);
        Cell cellLectureCount = row.createCell(3);
        cellID.setCellStyle(cellStyle); 
        cellID.setCellValue("رقم الطالب");
        cellName.setCellStyle(cellStyle); 
        cellLectureCount.setCellStyle(cellStyle); 
        cellName.setCellValue("الاسم");
        cellLectureCount.setCellValue("مجموع حضوره للمجلس");
    
    }
    

}
