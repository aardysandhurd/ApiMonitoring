package org.raman.abstractRes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetProperty {
    public String getPropertyValue(String property)
    {
        Properties prop = new Properties();
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/raman/resources/globalProp.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(fin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty(property);
    }
}
