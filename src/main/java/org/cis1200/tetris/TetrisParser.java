package org.cis1200.tetris;

import org.cis1200.FileLineIterator;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

public class TetrisParser {
    private static final String NUM_WORDS = "^\\d+?\\;\\d+?\\;\\d+?";

    static List<String[]> csvDataToFallenBlocks(BufferedReader br) {
        FileLineIterator flIter = new FileLineIterator(br);
        List<String[]> listOfRows = new LinkedList<>();

        while (flIter.hasNext()) {
            String line = flIter.next();
            String trimLine = line.trim();
            String[] lineCols = trimLine.split("\\,");
            for (int i = 0; i < lineCols.length; i++) {
                if (lineCols[i].equals("null") && lineCols[i].matches(NUM_WORDS)) {
                    lineCols[i] = "null";
                }
            }
            listOfRows.add(lineCols);
        }
        return listOfRows;
    }
}

