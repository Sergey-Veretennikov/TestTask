package ru.veretennikov.test.task;

import org.apache.log4j.Logger;
import ru.veretennikov.test.task.exception.CellValueIsNotNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class StringAnalysisAndConversion {
    private static final Logger LOGGER = Logger.getLogger(StringAnalysisAndConversion.class);
    private final Map<String, String> stringMapCells;

    public StringAnalysisAndConversion(Map<String, String> stringMapCells) {
        this.stringMapCells = stringMapCells;
    }

    public List<String> conductAnAnalysis() {
        List<String> stringListResult = new ArrayList<>();
        for (Map.Entry<String, String> cell : stringMapCells.entrySet()) {
            if (!Character.isDigit(cell.getKey().charAt(0))) {
                try {
                    stringListResult.add((new ExecutingStringFormula()
                            .execute(leadToNumbers(cell.getValue(), cell.getKey(), stringMapCells))
                            .toString()));
                } catch (CellValueIsNotNumberException e) {
                    LOGGER.error(e);
                    System.exit(0);
                }
            } else stringListResult.add("\n");
        }
        return stringListResult;
    }

    protected String leadToNumbers(String value, String cellNumber, Map<String, String> mapCells) throws CellValueIsNotNumberException {
        String stringCellValue = value;
        StringTokenizer stringTokenizer = new StringTokenizer(stringCellValue, "+-/*");
        while (stringTokenizer.hasMoreTokens()) {
            String string = stringTokenizer.nextToken();
            for (int i = 0; i < string.length(); i++) {
                if (!Character.isDigit(string.charAt(i)) && (string.charAt(i) != '.')) {
                    if (mapCells.get(string) != null) {
                        stringCellValue = stringCellValue.replace(string, "(" + mapCells.get(string) + ")");
                        stringMapCells.put(cellNumber, stringCellValue);
                    } else {
                        throw new CellValueIsNotNumberException(cellNumber);
                    }
                }
            }
        }
        return stringCellValue;
    }
}
