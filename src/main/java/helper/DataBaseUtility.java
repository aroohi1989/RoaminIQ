package helper;

import wrapper.WebDriverWrapper;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseUtility extends WebDriverWrapper
{
    public static void connectToSqlDB(String jdbcUrl, String DBUname, String DBPwd) throws SQLException {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, DBUname, DBPwd);
            if(conn!=null)
            {
                System.out.println("Database is connected successfully");
            }
        }
        catch (Exception e)
        {    logger.error(e);
            System.out.println("SQL Exception occured:  " + e.getMessage());
        }
        finally {
            closeSQLdbConnection(conn);
        }
    }
    public static ArrayList<String[]> executeSQLdbQuery(String jdbcUrl, String DBUname, String DBPwd, String sqlQuery) throws SQLException
    {

        ArrayList<String[]> resultList =  new ArrayList<>();
        Connection con=null;
        ResultSet Rs = null;
        try
        {
            con = DriverManager.getConnection(jdbcUrl, DBUname, DBPwd);
            Statement St = con.createStatement();
            Rs = St.executeQuery(sqlQuery);
            ResultSetMetaData ResultData = Rs.getMetaData();
            int columnCount = ResultData.getColumnCount();
            while(Rs.next())
            {
                String[] rowData = new String[columnCount];
                for(int i=0;i<columnCount;i++)
                {
                    rowData[i] = Rs.getString(i+1);
                }
                resultList.add(rowData);
            }
        }
        catch(Exception e)
        {
            System.out.println("Occured sql execption" +e.getMessage());
        }
        finally
        {
            closeSQLdbConnection(con);
        }
        return resultList;
    }
    public static void closeSQLdbConnection(Connection con) throws SQLException
    {
        try
        {
            con.close();
            System.out.println("Connection closed successfully.");
            //logger.info("DB connection is closed");

        }
        catch(Exception e)
        {
            System.out.println("Error in db connection close" +e.getMessage());
            //logger.error("SQL Exception occured");
        }
    }



}
