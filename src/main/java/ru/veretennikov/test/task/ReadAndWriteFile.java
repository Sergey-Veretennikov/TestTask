package ru.veretennikov.test.task;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ReadAndWriteFile {

    public Map<String, String> readTheFile(String path) {
        String line;
        Map<String, String> stringMap = new LinkedHashMap<>();
        String key;
        int intKey = 1;
        char charKey = 'A';
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] arrayOfStrings = line.split(";");
                for (String string : arrayOfStrings) {
                    key = String.valueOf(charKey);
                    stringMap.put(key + intKey, string);
                    charKey++;
                }
                stringMap.put(String.valueOf(i), "");
                i++;
                intKey++;
                charKey = 'A';
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringMap;
    }

    public void writeDown(String path, List<String> rezult) {
        List<String> rez = rezult;
        try (PrintWriter pw = new PrintWriter(new File(path))) {
            StringBuilder sb = new StringBuilder();
            for (String d : rez) {
                sb.append(d);
                if (!("\n".equals(d))) {
                    sb.append(";");
                }
            }
            pw.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
