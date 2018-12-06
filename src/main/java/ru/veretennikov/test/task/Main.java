package ru.veretennikov.test.task;


public class Main {

    public static void main(String[] args) {
        ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();
        StringAnalysisAndConversion stringAnalysisAndConversion =
                new StringAnalysisAndConversion(readAndWriteFile.readTheFile("1.csv"));

        readAndWriteFile.writeDown("2.csv", stringAnalysisAndConversion.conductAnAnalysis());
    }
}
