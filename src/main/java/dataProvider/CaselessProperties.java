package dataProvider;


import java.util.Properties;

public class CaselessProperties extends Properties
{
    @Override
    public synchronized Object put(Object key, Object value) {
        String lowercase = ((String) key).toLowerCase();
        return super.put(lowercase, value);
    }

    @Override
    public String getProperty(String key) {
        String lowercase = key.toLowerCase();
        return super.getProperty(lowercase);
    }
}
