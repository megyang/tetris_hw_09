package org.cis1200.tetris;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class RWProperties {
    private static final String CONFIG = ".properties";

    public static void main(String[] args) throws IOException {
        Properties props = ReadFile(CONFIG);
        System.out.println("Background: " + props.getProperty("PATH_TO_FALLEN"));
        System.out.println("otherthings: " + props.getProperty("LEADERBOARD_PATH"));
    }

    public static Properties ReadFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException fnfe) {
            throw new IOException();
        } finally {
            if (fis != null)
                fis.close();
        }
        return prop;
    }
    public static void WriteFile(String filename, Properties pp) throws IOException{

// Write the properties to a file
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            pp.store(out, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
