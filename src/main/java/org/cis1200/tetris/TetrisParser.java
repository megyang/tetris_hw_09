package org.cis1200.tetris;

import org.cis1200.FileLineIterator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;

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
     * For the purposes of this project, we consider "word characters" to be
     * alphanumeric characters [a-zA-Z0-9] and apostrophes [']. A word is "bad"
     * if it contains some other character. (In particular, twitter mentions
     * like "@user" are "bad".)
     * <p>
     * The regular expression BAD_WORD_REGEX expresses those constraints -- any
     * String that matches it is considered "bad" and will be removed from the
     * training data.
     * <p>
     * The regular expression {@code "[\\W&&[^']]"} matches non-word characters.
     * The regular expression ".*" matches _any_ sequence of characters. When
     * concatenated into the full regular expression, they match any sequence of
     * characters followed by a non-word character followed again by any
     * sequence of characters, or, any string containing a non-word character.
     * <p>
     * Similarly, the URL_REGEX matches any substring that starts a word with
     * "http" and continues until some whitespace occurs. See the removeURLs
     * static method.
     * <p>
     * See https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern
     * .html for more details about Java's regular expressions.
     * <p>
     * tldr: use word.matches(BAD_WORD_REGEX) to determine if word is a bad
     * String.
     */
    private static final String BAD_WORD_REGEX = ".*[\\W&&[^']].*";
    private static final String NUM_WORDS = "^\\d+?";
    static String replacePunctuation(String cell) {
            cell = cell.replace(';', ',');
        return cell;
    }
    static List<String[]> csvDataToFallen(BufferedReader br) {
        String line;
        FileLineIterator flIter = new FileLineIterator(br);
        List<String[]> listOfRows = new LinkedList<>();

        while (flIter.hasNext()) {
            line = flIter.next();
            String trimedline = line.trim();
            String[] col=trimedline.split("\\,");
            listOfRows.add(col);
        }

        return listOfRows;
    }
}
