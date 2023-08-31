import org.testng.TestNG;

public class TestngRunner {

    public static void main(String[] args)
    {
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] {XGLT_15177_Test.class });
        testng.run();
    }
}