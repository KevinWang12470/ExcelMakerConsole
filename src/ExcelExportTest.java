import java.io.File;
import java.io.FileOutputStream;

/*
 * for some reason,
 * eclipse does not automatically import 'org.apache.poi.ss.usermodel.Sheet'
 *  when I use the Sheet interface..
 */
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ExcelExportTest {

    /**
     * Something.
     */
    //public static final HorizontalAlignment CENTER;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ExcelExportTest() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) throws Exception {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //out.print("Name your excel file: ");

        //String name = in.nextLine();

        //Create Blank workbook
        Workbook excelFile = new XSSFWorkbook();

        //Create file system given specific name
        FileOutputStream exOut = new FileOutputStream(
                new File("brandNewSheet.xlsx"));

        //put this between exout and exout.close()
        Sheet page1 = excelFile.createSheet("Schedule Table");

        //Initialize the row
        Row row = page1.createRow(0);

        //make some time number variables
        int startTime = 6;
        int endTime = 22;
        int currentRow = 1;
        //Put in some cells

        Cell cell = row.createCell(0);

        //24hr time template
        //start hour to end hour
//        for (int i = startTime; i <= endTime; i++) {
//            //print :00 and :30
//            /*
//             * if desired, all cells can show time by setting j < 4
//             */
//            for (int j = 0; j < 2; j++) {
//                row = page1.createRow(currentRow);
//                cell = row.createCell(0);
//
//                //ensure :00 is printed instead of :0
//                if (j == 0) {
//                    cell.setCellValue(i + ":00");
//
//                } else if (i != endTime) {
//
//                    //in this format in case all cells show time (j * 15)
//                    cell.setCellValue(i + ":" + j * 30);
//                }
//                /*
//                 * move down the sheet according to the timeline. Each cell
//                 * counts as 15 minutes. just do currentRow++ if all cells are
//                 * to show time.
//                 */
//                currentRow = currentRow + 2;
//            }
//        }

        //12hr time template
        //start hour to end hour
        for (int i = startTime; i <= endTime; i++) {
            //print :00 and :30
            /*
             * if desired, all cells can show time by setting j < 4
             */
            for (int j = 0; j < 2; j++) {
                row = page1.createRow(currentRow);
                cell = row.createCell(0);

                //ensure :00 is printed instead of :0
                if (j == 0) {

                    //if in the 12 AM area
                    if (i == 0) {
                        cell.setCellValue("12:00AM");
                    }
                    //if PM
                    if (i > 12) {
                        cell.setCellValue(i - 12 + ":00PM");

                    }
                    //if AM
                    else {
                        cell.setCellValue(i + ":00AM");
                    }

                } else if (i != endTime) {

                    //in this format in case all cells show time (j * 15)
                    //if 12 AM
                    if (i == 0) {
                        cell.setCellValue("12:" + j * 30 + "AM");
                    }
                    //if PM
                    if (i > 12) {
                        cell.setCellValue(i - 12 + ":" + j * 30 + "PM");
                    }
                    //if AM
                    else {
                        cell.setCellValue(i + ":" + j * 30 + "AM");
                    }
                }

                /*
                 * move down the sheet according to the timeline. Each cell
                 * counts as 15 minutes. just do currentRow++ if all cells are
                 * to show time.
                 */
                currentRow = currentRow + 2;

            }
        }

//        cell.setCellValue("This is Cell 1A");

        CellStyle style1 = excelFile.createCellStyle();

        style1.setWrapText(true);
        style1.setAlignment(HorizontalAlignment.CENTER);
//        cell.setCellStyle(style1);

        //Merged cell
//        row = page1.createRow(1);
//        cell = row.createCell(0);
//
//        cell.setCellStyle(style1);
//        page1.addMergedRegion(new CellRangeAddress(1, 7, 0, 0));
//        cell.setCellValue(
//                "Let's put some effort into this program so people can actually use it!");

        //write operation workbook using file out object
        excelFile.write(exOut);

        excelFile.close();

        //exOut.flush();

        out.println("file created successfully");

        int[] basicTimeFrame = { 10, 15 };

        ExcelMakerConsole.createSheetTemplate("createSheetTemplateTest.xlsx",
                true, basicTimeFrame);
        in.close();
        out.close();
    }

}
