package org.example;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
public class TestData {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;
    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");
            BufferedReader in = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            PROPERTIES = new Properties();
            PROPERTIES.load(in);
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace(); } } }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key); }
}