/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
*/
public class ReadFromExcel {
    private XSSFRow row;
    private DBModel db ;
    public  void readFile(String fileName, DBModel db) throws FileNotFoundException, IOException, SQLException {
        this.db = db;
        db.setTransaction(false);
        FileInputStream fis;
        String stdID, lecID;
        try {
            System.out.println("------------------READING THE SPREADSHEET-------------------");
            fis = new FileInputStream(fileName);
            XSSFWorkbook workbookRead = new XSSFWorkbook(fis);
            XSSFSheet spreadsheetRead = workbookRead.getSheetAt(0);
            Iterator< Row> rowIterator = spreadsheetRead.iterator();
            while (rowIterator.hasNext()) {
                row = (XSSFRow) rowIterator.next();
                Iterator< Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    cell.setCellType(CellType.STRING);
                    switch (cell.getColumnIndex()) {
                        case 0:
                            System.out.print(
                                    cell.getStringCellValue() + " \t\t");
                            break;
                        case 1:
                            System.out.print(
                                    cell.getStringCellValue() + " \t\t");
                            break;
                    }
                }
                System.out.println();
                stdID = row.getCell(0).getStringCellValue();
                lecID = row.getCell(1).getStringCellValue();
                InsertRowInDB(stdID,lecID);
            }
            System.out.println("Values Inserted Successfully");

            fis.close();
            db.commit();
            db.setTransaction(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void InsertRowInDB(String sid, String lid) {
           try {
               db.insertIntoAttends(sid, lid);
        }  catch (SQLException ex) {
            Logger.getLogger(ReadFromExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


    
