package org.cis1200.tetris;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private static final String CONFIG = "files/.properties";

    public static void main(String args[]) throws IOException {
        Properties props = readConfigFile(CONFIG);
        System.out.println("Background: "+ props.getProperty("Background"));
        System.out.println("otherthings: "+props.getProperty("otherthings"));
    }
    public static Properties readConfigFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(IOException fnfe) {
            fnfe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }
}
