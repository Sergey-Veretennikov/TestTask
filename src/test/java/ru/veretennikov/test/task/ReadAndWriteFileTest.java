package ru.veretennikov.test.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ReadAndWriteFileTest {
    private List<String> list = new ArrayList<>();
    private Map<String, String> stringMap = new LinkedHashMap<>();

    @Before
    public void setUp() throws Exception {
        list.add("100");
        list.add("A1+50/10");
        list.add("4.0+A5-52");
        list.add("\n");
        list.add("qaz");
        // new WritesTheResultFile().writeDown("Test.csv", list);
    }

    @Test
    public void readTheFileTest() {
        //  stringMap = new ReadAndWriteFile("Test.csv").readTheFile();

        assertEquals(list.get(0), stringMap.get("A1"));
        assertEquals(list.get(1), stringMap.get("B1"));
        assertEquals(list.get(2), stringMap.get("C1"));
        assertEquals(list.get(4), stringMap.get("A2"));
    }

    @After
    public void tearDown() throws Exception {
        new File("Test.csv").delete();
    }
}