package pom;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelManager {



    private static String fileLocation = "src\\main\\DataDriven.xlsx";
    public static Object[][] getData() throws IOException {

        File f1 = new File(fileLocation);
        FileInputStream fis = new FileInputStream(f1);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
       XSSFSheet sheet = wb.getSheetAt(0);

       String[][] data;

       int rowCount = sheet.getLastRowNum(); //2
       int colCount = sheet.getRow(0).getLastCellNum(); //2

        data = new String[rowCount][colCount];

        for(int i =1; i<=rowCount; i++) {
            for(int j= 0; j<colCount ; j++) {
                data[i-1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }

        wb.close();
        fis.close();

        return data;

    }
}
