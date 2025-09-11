package org.impact;

import numberrangesummarizer.NumberRangeSummarizer;
import numberrangesummarizer.NumberRangeSummarizerImpl;

import java.util.Collection;

/**
 * @author Mogamad Ighsaan Gamieldien
 *
 * Class containing the main method that shows a brief implementation of NumberRangeSummarizerImpl.
 */

public class Main {
    public static void main(String[] args) {
        NumberRangeSummarizer summarizer= new NumberRangeSummarizerImpl();

        String list = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> collection = summarizer.collect(list);
        String summarizedCollection = summarizer.summarizeCollection(collection);

        System.out.println(collection);
        System.out.println(summarizedCollection);
    }
}