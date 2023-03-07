package com.webapiserver.largexlsx;

import com.opencsv.CSVWriter;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Ignore("need large file for testing")
public class ReadLargeFileUnitTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private Object FileInputStream;

    // utilsjjj

    private final void logMemory() {
        logger.info("Max Memory: {} Mb", Runtime.getRuntime()
                .maxMemory() / 1048576);
        logger.info("Total Memory: {} Mb", Runtime.getRuntime()
                .totalMemory() / 1048576);
        logger.info("Free Memory: {} Mb", Runtime.getRuntime()
                .freeMemory() / 1048576);
    }

@Test
    public final void givenUsingApacheIo_whenStreamingThroughAFile_thenCorrectxl() throws IOException, FileNotFoundException {

    try (InputStream is = new FileInputStream("C:\\Users\\bsagar8\\sagarapidev\\my\\csv\\excelOutput.xlsx");
         ReadableWorkbook wb = new ReadableWorkbook(is)) {

        StopWatch watch = new StopWatch();
        watch.start();
        wb.getSheets().forEach(sheet ->
        {
            try (Stream<org.dhatim.fastexcel.reader.Row> rows = sheet.openStream()) {

                rows.skip(1).forEach(r -> {
                 logger.info("row:{}\n",r);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
            watch.stop();
            System.out.println("Processing time :: " + watch.getTime(TimeUnit.MILLISECONDS));
        });
    }
}
@Test
 public void a() throws IOException {
   IOUtils.setByteArrayMaxOverride(551141527);

  //  String filepath = "";
    FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\bsagar8\\Downloads\\excelOutput.xlsx"));

    Workbook workbook = new XSSFWorkbook(inputStream); // create workbook object from input stream
    int numSheets = workbook.getNumberOfSheets();
    int chunkSize = 100;
    for (int i = 0; i < numSheets; i++) {
        XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(i);
        int numRows = sheet.getLastRowNum() + 1;

        // Iterate over the rows in the sheet in chunks
        for (int startRow = 0; startRow < numRows; startRow += chunkSize) {
            int endRow = Math.min(startRow + chunkSize, numRows);

            for (int j = startRow; j < endRow; j++) {
                XSSFRow row = sheet.getRow(j);
                logger.info("row",row);
            }
        }
    }
    inputStream.close();
    workbook.close();

}

@Test
    public void t() throws IOException {
    IOUtils.setByteArrayMaxOverride(551141527);
    // Create a new XSSFWorkbook
    XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("C:\\Users\\bsagar8\\Downloads\\excelOutput.xlsx"));

// Get the first sheet
    XSSFSheet sheet = workbook.getSheetAt(0);

// Create a new CSVWriter
    CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\bsagar8\\sagarapidev\\my\\csv\\excelOutpufile.csv"));

// Loop through rows and columns and write the data to the CSV file
    for (Row row : sheet) {
        List<String> rowData = new ArrayList<>();
        for (Cell cell : row) {
            rowData.add(cell.toString());
        }
        String[] data = rowData.toArray(new String[0]);
        writer.writeNext(data);
    }

// Close the CSVWriter and the XSSFWorkbook
    writer.close();
    workbook.close();

}
@Test
public void t2() throws IOException {
    IOUtils.setByteArrayMaxOverride(551141527);
    // Create a new workbook
    SXSSFWorkbook workbook = new SXSSFWorkbook(new XSSFWorkbook(new FileInputStream("C:\\Users\\bsagar8\\Downloads\\excelOutput.xlsx")), 100,true,true);

// Get the first sheet
    Sheet sheet = workbook.getSheet("Detailed Comparison Rows");

// Loop through rows and columns and read the data from the Excel file
    for (Row row : sheet) {
        logger.info("row",row);
    }

// Close the workbook
    workbook.close();

}
@Test
public void testing() throws Exception {
    IOUtils.setByteArrayMaxOverride(1000000000);
    FileInputStream fis = new FileInputStream(new File("C:\\Users\\bsagar8\\Downloads\\excelOutput.xlsx"));
    XSSFWorkbook workbook = new XSSFWorkbook (fis);
    XSSFSheet sheet = workbook.getSheetAt(0);
    Iterator ite = sheet.rowIterator();
    while(ite.hasNext()){
        Row row = (Row) ite.next();
        Iterator<Cell> cite = row.cellIterator();
        while(cite.hasNext()){
            Cell c = cite.next();
            System.out.print(c.toString() +"  ");
        }
        System.out.println();
    }
    fis.close();

}
}


