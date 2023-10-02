package helper;

public class SaveProjectData
{
    public void saveprojectData(String label,String value)
    {
        String path=System.getProperty("user.dir")+"/testdata/Runtimedata.xlsx";
        String sheetname="data";
        Read_write_Excel rd=new Read_write_Excel();
        rd.writeonExcelRuntimedata(path,label,sheetname,value);
    }
    public String getprojectData(String label)
    {
        String path=System.getProperty("user.dir")+"/testdata/Runtimedata.xlsx";
        String sheetname="data";
        Read_write_Excel rd=new Read_write_Excel();
        return rd.ReadRuntimedata(path,label,sheetname);
    }
}
