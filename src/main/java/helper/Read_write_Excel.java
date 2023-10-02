package helper;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import pages.Prebilling_processing;

import java.io.*;

public class Read_write_Excel
{
    public void read_users() throws Exception
    {
        File src= new File("C:\\Users\\dP-PL\\LearnMaven\\LearnMaven\\testdata\\Permissions.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        System.out.println("************ Loading Data From Excel *******************");
        XSSFSheet st=wb.getSheet("Users");
        int i =st.getLastRowNum();
        for(int j=1;j<i;j++)
        {
            String uname=st.getRow(j).getCell(0).getStringCellValue();
            String pwd =st.getRow(j).getCell(1).getStringCellValue();
            System.out.println("Username and password is "+uname+" and " +pwd );
        }
        wb.close();
    }

    public void read_DBI_Permission(String uname, WebDriver driver) throws Exception
    {
        boolean result;
        Prebilling_processing PB= new Prebilling_processing(driver);
        File src= new File("C:\\Users\\dP-PL\\LearnMaven\\LearnMaven\\testdata\\DBI.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        System.out.println("************ Loading Data From Excel *******************");
        XSSFSheet st=wb.getSheet(uname);

        int i =st.getLastRowNum();
        System.out.println("Number of rows are"+i);
        for(int j=1;j<=i;j++)
        {
            String Category=st.getRow(j).getCell(0).getStringCellValue();
            String Privilege =st.getRow(j).getCell(1).getStringCellValue();
            String Granted=st.getRow(j).getCell(2).getStringCellValue();
            System.out.println( " Category is " +Category+"Privilege is" +Privilege+ "Granted is " +Granted);
            result=PB.test_privilege(Category, Privilege, Granted);
            XSSFCell cell = st.getRow(j).createCell(3);
            if(result)
            {
                cell.setCellValue("Pass");
            }
            else
            {
                cell.setCellValue("Fail");
            }
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\dP-PL\\LearnMaven\\LearnMaven\\testdata\\"+uname+".xlsx");
            wb.write(outputStream);
        }
        wb.close();
    }
    public void writeonExcelRuntimedata(String path, String label,String sheetname,String setvalue)
    {
        try {
            File file= new File(path);
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet st=wb.getSheet(sheetname);
            int i =st.getLastRowNum();
            for(int j=0;j<=i;j++)
            {
                if(st.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(label))
                {
                    XSSFCell cell = st.getRow(j).createCell(1);
                    cell.setCellValue(setvalue);
                    break;
                }
            }
            FileOutputStream os = new FileOutputStream(path);
            wb.write(os);
            System.out.println("Writing on Excel file Finished ...");
            os.close();
            wb.close();
            fis.close();
        } catch (IOException e) {
            ExceptionHandling.handleException(e);
        }
    }
    public String ReadRuntimedata(String path, String label,String sheetname)
    {
        String str="";
        try {
            File src = new File(path);
            FileInputStream fis = new FileInputStream(src);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet st = wb.getSheet(sheetname);
            int i = st.getLastRowNum();
            for (int j = 1; j < i; j++) {
                if (st.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(label)) {
                     str = st.getRow(j).getCell(1).getStringCellValue();
                }
            }
            wb.close();
        } catch (Exception e) {
            ExceptionHandling.handleException(e);
        }
        return str;
    }
}
