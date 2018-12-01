package ru.veretennikov.test.task;


import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();
        StringAnalysisAndConversion stringAnalysisAndConversion = new StringAnalysisAndConversion();

        readAndWriteFile.writeDown("2.csv"
                , stringAnalysisAndConversion.conductAnAnalysis(readAndWriteFile.readTheFile("1.csv")));

    }
}
