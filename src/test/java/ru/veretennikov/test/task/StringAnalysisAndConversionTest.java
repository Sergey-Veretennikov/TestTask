package ru.veretennikov.test.task;

import org.junit.Before;
import org.junit.Test;
import ru.veretennikov.test.task.exception.CellValueIsNotNumberException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StringAnalysisAndConversionTest {
    private List<String> stringListResult = new ArrayList<>();
    private Map<String, String> stringMapCells = new LinkedHashMap<>();

    @Before
    public void setUp() {
        stringMapCells.put("A1", "1*5/2");
        stringMapCells.put("B1", "15.0");
        stringMapCells.put("C1", "A1+5");
        stringMapCells.put("0", "");
        stringMapCells.put("A2", "4/5*10");
        stringMapCells.put("B2", "4");
        stringMapCells.put("C2", "A1+C1/B2");
        stringMapCells.put("1", "");
    }

    @Test
    public void conductAnAnalysis() {
        stringListResult = new StringAnalysisAndConversion(stringMapCells).conductAnAnalysis();

        assertEquals("2.5", stringListResult.get(0));
        assertEquals("15.0", stringListResult.get(1));
        assertEquals("7.5", stringListResult.get(2));
        assertEquals("8.0", stringListResult.get(4));
        assertEquals("4.0", stringListResult.get(5));
        assertEquals("4.375", stringListResult.get(6));
    }

    @Test(expected = CellValueIsNotNumberException.class)
    public void conductAnAnalysisException() throws CellValueIsNotNumberException {
        stringMapCells.put("A3", "asdf");
        StringAnalysisAndConversion stringAnalysisAndConversion = new StringAnalysisAndConversion(stringMapCells);
        stringAnalysisAndConversion.leadToNumbers(stringMapCells.get("A3"), "A3", stringMapCells);
    }

    @Test(expected = CellValueIsNotNumberException.class)
    public void conductAnAnalysisExceptionTwo() throws CellValueIsNotNumberException {
        stringMapCells.put("A3", "A1+C1/B22");
        StringAnalysisAndConversion stringAnalysisAndConversion = new StringAnalysisAndConversion(stringMapCells);
        stringAnalysisAndConversion.leadToNumbers(stringMapCells.get("A3"), "A3", stringMapCells);
    }

    @Test(expected = CellValueIsNotNumberException.class)
    public void conductAnAnalysisExceptionThree() throws CellValueIsNotNumberException {
        stringMapCells.put("A3", "A1+1ASD");
        StringAnalysisAndConversion stringAnalysisAndConversion = new StringAnalysisAndConversion(stringMapCells);
        stringAnalysisAndConversion.leadToNumbers(stringMapCells.get("A3"), "A3", stringMapCells);
    }

}