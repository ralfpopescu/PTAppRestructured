/**
 * Created by ralfpopescu on 4/26/17.
 */
package sample;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;

public class ExcelOrganizer
{
    static XSSFRow row;
    File file;
    File file2;
    public ExcelOrganizer(){

    }
    public boolean handleSheet() throws Exception
    {
        //MOD, Server, Bartender, Barback, Busser, Host, Food Runner, Parking, Security, Maintenance, Sushi, Kitchen
        //Dishwasher, Banquet Bartender, Banquet Cook, Banquet Server, Banquet Dishwasher, Basecamp, Event Sales, Inventory


        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = spreadsheet.iterator();
        RowHandler rowHandler = new RowHandler();
        SheetCreator sheetCreator = new SheetCreator(rowHandler, spreadsheet.getRow(0));

        while (rowIterator.hasNext())
        {
            row = (XSSFRow) rowIterator.next();
            rowHandler.determineJobs(row);
        }
        sheetCreator.makeSheet();
        fis.close();

        return true;
    }

    public void setFile1(File f){
        file = f;
    }

    public void setFile2(File f){
        file2 = f;
    }

    public File getFile1(){
        return file;
    }

    public File getFile2(){
        return file2;
    }


}
