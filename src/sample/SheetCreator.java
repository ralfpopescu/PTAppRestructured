/**
 * Created by ralfpopescu on 4/27/17.
 */
package sample;

import java.io.*;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFFont;


public class SheetCreator {

    RowHandler rowHandler;
    Parser parser = new Parser();
    XSSFRow firstRow;
    CellStyle cellStyle;
    //XSSFWorkbook workbook;

    public SheetCreator(RowHandler rh, XSSFRow fr){
        rowHandler = rh;
        this.firstRow = fr;
    }


    public void makeSheet(){
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet spreadsheet = workbook.createSheet(
                "Schedule");

        CellCopyPolicy ccp = new CellCopyPolicy();
        ccp.setCondenseRows(true);
        ccp.setCopyCellFormula(true);
        ccp.setCopyCellStyle(true);
        ccp.setCopyCellValue(true);
        ccp.setCopyHyperlink(true);
        ccp.setCopyMergedRegions(true);
        ccp.setCopyRowHeight(true);
        ccp.setMergeHyperlink(true);

        ArrayList<ArrayList> all = rowHandler.getAllFiltered();
        int rowNum = 0;

        XSSFRow first = spreadsheet.createRow(rowNum);
        copyRow(firstRow, first, true, workbook);
        rowNum++;


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Cell startingDateCell = firstRow.getCell(1);
        String firstDate = sdf.format(startingDateCell.getDateCellValue());
        String sheetTitle = convertDate(firstDate);




        for(ArrayList<XSSFRow> job: all){
            String jobString = rowHandler.getJobFromList(job);
            String titleJobString = parser.reverse(jobString);
            XSSFRow jobTitle = spreadsheet.createRow(rowNum);
            Cell titleCell = jobTitle.createCell(0);

            titleCell.setCellValue(titleJobString);

            XSSFCellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            style.setFont(font);
            titleCell.setCellStyle(style);

            for (int i = 1; i < 8; i ++){
                Cell fillerCell = jobTitle.createCell(i);
                fillerCell.setCellStyle(style);
            }

            rowNum++;

            for(XSSFRow r: job){
                XSSFRow row = spreadsheet.createRow(rowNum);
                copyRowWithJobFilter(r, row, jobString);
                rowNum++;
            }
        }

        for (int i=0; i<30; i++){
            spreadsheet.autoSizeColumn(i);
        }

        //Write the workbook in file system
        try {
            FileOutputStream out = new FileOutputStream(
                    new File(sheetTitle + ".xlsx"));
            try {
                workbook.write(out);
                out.close();
                System.out.println(
                        "Writesheet.xlsx written successfully");
            } catch (IOException e){
                System.out.println("ay");
            }
        } catch (FileNotFoundException e){
            System.out.println(
                    "shit");

        }
    }


    public void copyRow(XSSFRow r1, XSSFRow r2, boolean flag, XSSFWorkbook workbook){ //flag for date row
        Iterator<Cell> cellIterator = r1.cellIterator();
        int cellid = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String[] weekdays = {"THURS","FRI","SAT","SUN","MON","TUES","WED"};
        int dateCount = 0;

        XSSFCellStyle dateStyle = workbook.createCellStyle();
        XSSFFont datefont = workbook.createFont();
        datefont.setBold(true);
        datefont.setColor(IndexedColors.WHITE.getIndex());
        dateStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        dateStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        //dateStyle.setAlignment(CellStyle.ALIGN_CENTER);
        dateStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        dateStyle.setFont(datefont);

        while (cellIterator.hasNext())
        {
            Cell cell = cellIterator.next();
            switch (cell.getCellType())
            {
                case Cell.CELL_TYPE_NUMERIC:
                    if(flag){
                        Cell r2cellnum = r2.createCell(cellid);
                        cellid++;
                        String cellValue = sdf.format(cell.getDateCellValue());
                        String[] split = cellValue.split("/");
                        cellValue = weekdays[dateCount] + " " + split[0] + "/" + split[1];
                        if(dateCount < 7) {
                            dateCount++;
                        }
                        r2cellnum.setCellValue(cellValue);



                        r2cellnum.setCellStyle(dateStyle);
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    Cell r2cell = r2.createCell(cellid);
                    cellid++;
                    if(!cell.getStringCellValue().contains("Total")) {
                        r2cell.setCellValue(cell.getStringCellValue());
                        r2cell.setCellStyle(dateStyle);
                    }
                    break;
            }
        }

    }

    public void copyRowWithJobFilter(XSSFRow r1, XSSFRow r2, String job){
        Iterator<Cell> cellIterator = r1.cellIterator();
        int cellid = 0;
        boolean first = true;
        int lastColumn = r1.getLastCellNum();

        while (cellid < lastColumn)
        {
            Cell cell = r1.getCell(cellid, MissingCellPolicy.CREATE_NULL_AS_BLANK);

            switch (cell.getCellType())
            {
                case Cell.CELL_TYPE_NUMERIC:

                    //Cell r2cellnum = r2.createCell(cellid);
                    cellid++;
                    //r2cellnum.setCellValue(cell.getNumericCellValue());
                    break;

                case Cell.CELL_TYPE_STRING:
                    Cell r2cell = r2.createCell(cellid);
                    cellid++;
                    if (cell.getStringCellValue() == null){
                        r2cell.setCellValue(" ");
                    } else {
                        r2cell.setCellValue(cell.getStringCellValue());

                    }

                    String celljob = parser.getJob(cell.getStringCellValue());


                    String[] cut = cell.getStringCellValue().split("-");
                    if(cut.length > 1) {
                        String[] cutAgain = cut[1].split("\\r?\\n");
                        String cutString = cut[0] + "-" + cutAgain[0];
                        r2cell.setCellValue(cutString);
                    }

                    //System.out.println(job + " " + celljob);
                    if(celljob.equals("n")){
                        break;
                    }


                    if(!celljob.equals(job)) {

                        r2cell.setCellValue(" ");
                    }
                    break;

                case Cell.CELL_TYPE_BLANK:
                    Cell r2cell2= r2.createCell(cellid);
                    cellid++;
                    r2cell2.setCellValue(" ");
            }
        }
    }

    public String convertDate(String date){
        String[] split = date.split("/");
        return "ScheduleStarting_" + split[0] + "." + split[1];
    }
}
