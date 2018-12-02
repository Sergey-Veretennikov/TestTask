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
import static org.junit.Assert.assertNotEquals;

public class ReadAndWriteFileTest {
    private List<String> stringListResult = new ArrayList<>();
    private Map<String, String> stringMapCells = new LinkedHashMap<>();

    @Before
    public void setUp() {
        stringListResult.add("100");
        stringListResult.add("A1+50/10");
        stringListResult.add("4.0+A5-52");
        stringListResult.add("\n");
        stringListResult.add("qaz");
        new ReadAndWriteFile().writeDown("Test.csv", stringListResult);
    }

    @Test
    public void readTheFile() {
        stringMapCells = new ReadAndWriteFile().readTheFile("Test.csv");

        assertEquals(stringListResult.get(0), stringMapCells.get("A1"));
        assertEquals(stringListResult.get(1), stringMapCells.get("B1"));
        assertEquals(stringListResult.get(2), stringMapCells.get("C1"));
        assertEquals(stringListResult.get(4), stringMapCells.get("A2"));
    }

    @Test
    public void writeDown() {
        new ReadAndWriteFile().writeDown("Test2.csv", stringListResult);
        stringMapCells = new ReadAndWriteFile().readTheFile("Test2.csv");

        assertEquals(stringListResult.get(0), stringMapCells.get("A1"));
        assertEquals(stringListResult.get(1), stringMapCells.get("B1"));
        assertEquals(stringListResult.get(2), stringMapCells.get("C1"));
        assertNotEquals(stringListResult.get(3), stringMapCells.get("A2"));
    }

    @After
    public void tearDown() {
        new File("Test.csv").delete();
        new File("Test2.csv").delete();
    }

}