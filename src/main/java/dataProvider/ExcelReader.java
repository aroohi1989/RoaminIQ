package dataProvider;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader
{
    static XSSFWorkbook wb;

    public static Object[][] getDataFromSheet(String sheetName)
    {

        System.out.println("************ Loading Data From Excel *******************");

        Object [][] arr=null;
        String WorkbookPath=ConfigReader.getPropertyvalue("testdataworkbook");
        try
        {
            // load excel
            wb=new XSSFWorkbook(new FileInputStream(WorkbookPath));

            // load sheet
            XSSFSheet sheet=wb.getSheet(sheetName);

            // get rows
            int row=sheet.getPhysicalNumberOfRows();

            // get columns
            int column=sheet.getRow(0).getPhysicalNumberOfCells();
            System.out.println("row "+row+"column "+column);

            // create array based on rows and column from excel
            arr=new Object[row-1][column];

            // transport from excel to 2D object array
            for(int i=1;i<row;i++)
            {
                for(int j=0;j<column;j++)
                {
                    System.out.println("i "+i+"j "+j);
                    System.out.println(getData(sheetName, i, j));
                    arr[i-1][j]=getData(sheetName, i, j);
                }
            }


        } catch (FileNotFoundException e)

        {
            System.out.println("Could not find the file "+e.getMessage());
        } catch (IOException e) {

            System.out.println("Could not load the file "+e.getMessage());
        }

        return arr;

    }
    public static String getData(String sheetName,int row,int column)
    {

        XSSFCell cell=wb.getSheet(sheetName).getRow(row).getCell(column);

        String data="";

        if(cell.getCellType()== CellType.STRING) {

            data=cell.getStringCellValue();

        }
        else if(cell.getCellType()==CellType.NUMERIC)
        {
            double dataInDouble=cell.getNumericCellValue();
            data=String.valueOf(dataInDouble);
        }
        else if(cell.getCellType()==CellType.BLANK)
        {
            data="";
        }

        return data;
    }
    }

