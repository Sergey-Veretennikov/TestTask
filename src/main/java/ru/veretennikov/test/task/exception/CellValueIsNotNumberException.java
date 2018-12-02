package ru.veretennikov.test.task.exception;

public class CellValueIsNotNumberException extends Exception {
    public CellValueIsNotNumberException(String cellNumber) {
        super("Некорректное значение в ячейки номер: " + cellNumber);
    }
}
