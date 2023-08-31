package dataProvider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider
{
    @DataProvider(name="loginDetails")
public static Object[][] getData()
    {
        String sheetName=ConfigReader.getPropertyvalue("loginSheetName");
        return ExcelReader.getDataFromSheet(sheetName);
    }
}
