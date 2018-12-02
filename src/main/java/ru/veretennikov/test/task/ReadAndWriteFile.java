package ru.veretennikov.test.task;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ReadAndWriteFile {
    private static final Logger LOGGER = Logger.getLogger(ReadAndWriteFile.class);

    public Map<String, String> readTheFile(String path) {
        String line;
        Map<String, String> stringMapCells = new LinkedHashMap<>();
        String key;
        int intKey = 1;
        char charKey = 'A';
        int numberKey = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] arrayOfStringsValueCell = line.split(";");
                for (String stringValue : arrayOfStringsValueCell) {
                    key = String.valueOf(charKey);
                    stringMapCells.put(key + intKey, stringValue);
                    charKey++;
                }
                stringMapCells.put(String.valueOf(numberKey), "");
                numberKey++;
                intKey++;
                charKey = 'A';
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return stringMapCells;
    }

    public void writeDown(String path, List<String> stringListResult) {
        try (PrintWriter pw = new PrintWriter(new File(path))) {
            StringBuilder sb = new StringBuilder();
            for (String stringValue : stringListResult) {
                sb.append(stringValue);
                if (!("\n".equals(stringValue))) {
                    sb.append(";");
                }
            }
            pw.write(sb.toString());
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
