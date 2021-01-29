package com.codebusters.data;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class ChapterBuilderTest {
    ChapterBuilder cb;
    @Before
    public void initializeChaptersTest() {
        cb = new ChapterBuilder();

    }

    @Test
    public void printChapters() {
        Iterator cbIterator = cb.getStory().entrySet().iterator();
        while(cbIterator.hasNext()){
            Map.Entry table = (Map.Entry) cbIterator.next();
            System.out.println(table);
        }
    }

    @Test
    public void verifyTablesExist() {
        assertTrue(cb.getStory().keySet().contains("Items"));
        assertTrue(cb.getStory().keySet().contains("Chapters"));
        assertTrue(cb.getStory().keySet().contains("Paths"));
    }

    @Test
    public void verifyItemsInFirstEntryItems() {
        HashMap<String,String> firstItems = (HashMap) cb.getStory().get("Items").get(0);
        assertTrue(firstItems.get("items").equals("matches, flashlight, water[1], machete"));
    }

    @Test
    public void verifyChaptersInFirstEntryCities() {
        HashMap<String, String> firstCities = (HashMap) cb.getStory().get("Chapters").get(0);
        assertTrue(firstCities.get("cities").equals("2,4"));
    }

    @Test
    public void verifyPathsInFirstEntryPathName() {
        HashMap<String, String> firstPaths = (HashMap) cb.getStory().get("Paths").get(0);
        assertTrue(firstPaths.get("pathName").equals("none"));
    }
}