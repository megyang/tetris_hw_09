package org.cis1200.tetris;

import org.cis1200.FileLineIterator;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

/**
 * TweetParser.csvDataToTrainingData() takes in a buffered reader that contains
 * tweets and iterates through the reader, one tweet at a time, removing parts
 * of the tweets that would be bad inputs to MarkovChain (for example, a URL).
 * It then parses tweets into sentences and returns those sentences as lists
 * of cleaned-up words.
 * <p>
 * Note: TweetParser's public methods are csvDataToTrainingData() and
 * getPunctuation(). These are the only methods that other classes should call.
 * <p>
 * All the other methods provided are helper methods that build up the code
 * you'll need to write those public methods. They have "package" (default, no
 * modifier) visibility, which lets us write test cases for them as long as
 * those test cases are in the same package.
 */
public class TetrisParser {

    /**
     * Regular Expressions
     * <p>
     * The regular expression {@code "^\\d+?\\;\\d+?\\;\\d+?"} matches data fields.
     * used to store game state
     * The regular expression ".*" matches _any_ sequence of characters. When
     * concatenated into the full regular expression, they match any sequence of
     * characters followed by a non-word character followed again by any
     * sequence of characters, or, any string containing a non-word character.
     * <p>
     * See https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern
     * .html for more details about Java's regular expressions.
     * <p>
     * tldr: use word.matches(NUM_WORDS) to determine if a field contains proper data
     * String.
     */
    private static final String NUM_WORDS = "^\\d+?\\;\\d+?\\;\\d+?";

    static List<String[]> csvDataToFallen(BufferedReader br) {
        String line;
        FileLineIterator flIter = new FileLineIterator(br);
        List<String[]> listOfRows = new LinkedList<>();
/*
        while (flIter.hasNext()) {
            line = flIter.next();
            String trimedline = line.trim();
            String[] col=trimedline.split("\\,");
            listOfRows.add(col);
        }
  */
        while (flIter.hasNext()) {
            line = flIter.next();
            String trimedline = line.trim();
            String[] col = trimedline.split("\\,");
            for (int i = 0; i < col.length; i++) {
                if (!col[i].equals("null") && !col[i].matches(NUM_WORDS)) {  //if
                    col[i] = "null";
                }
            }
            listOfRows.add(col);
        }
        return listOfRows;
    }
}
