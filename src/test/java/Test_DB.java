import base.BaseClass;
import dataProvider.ConfigReader;
import helper.DataBaseUtility;
import helper.ExceptionHandling;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Test_DB extends BaseClass
{
    @Test(description ="DB connection test",enabled = false)
    public void test_DB()
    {
        DataBaseUtility dbt= new DataBaseUtility();
        try {
            dbt.executeSQLdbQuery(ConfigReader.getPropertyvalue("jdbcUrl"), ConfigReader.getPropertyvalue("DBUname"), ConfigReader.getPropertyvalue("DBPwd"),ConfigReader.getPropertyvalue("sqlQuery"));
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
        }
    }
    @Test(description = "DB exceute")
    public void execute_DB()
    {
        DataBaseUtility dbt= new DataBaseUtility();
        ArrayList<String[]> arr= new ArrayList<>();
        try {
            arr=dbt.executeSQLdbQuery(ConfigReader.getPropertyvalue("jdbcUrl"), ConfigReader.getPropertyvalue("DBUname"), ConfigReader.getPropertyvalue("DBPwd"),ConfigReader.getPropertyvalue("sqlQuery"));
            for (String[] e:arr)
            {
                for (String str:e)
                {
                    System.out.print(str);
                    System.out.println(",");
                }
                System.out.println(e);
            }
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
        }

    }
}
