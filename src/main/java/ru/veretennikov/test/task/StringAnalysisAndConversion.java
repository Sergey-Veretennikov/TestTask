package ru.veretennikov.test.task;

import java.util.*;

public class StringAnalysisAndConversion {

    public List<String> conductAnAnalysis(Map<String, String> map) {
        List<String> strings = new ArrayList<>();
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> me : set) {
            if (!Character.isDigit(me.getKey().charAt(0))) {
                String s = me.getValue();
                StringTokenizer stringTokenizer = new StringTokenizer(s, "+-/*");
                while (stringTokenizer.hasMoreTokens()) {
                    String string = stringTokenizer.nextToken();
                    System.out.println(string);
                    if (!Character.isDigit(string.charAt(0)) && Character.isDigit(string.charAt(1))) {
                        System.out.println(map.get(string));
                        s = s.replace(string, map.get(string));
                    }
                }

                strings.add((new ExecutingStringFormula().run(s).toString()));

            } else strings.add("\n");
        }
        return strings;
    }

}
