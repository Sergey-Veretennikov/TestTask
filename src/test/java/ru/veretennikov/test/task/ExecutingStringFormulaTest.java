package ru.veretennikov.test.task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExecutingStringFormulaTest {
    private final ExecutingStringFormula executingStringFormula = new ExecutingStringFormula();

    @Test
    public void execute() {
        assertEquals(26, executingStringFormula.execute("5+6+15"), 0);
        assertEquals(26.9, executingStringFormula.execute("5.1+6.5+15.3"), 0);
        assertEquals(4.6, executingStringFormula.execute("5-6.0/15"), 0);
        assertEquals(71, executingStringFormula.execute("(5+6.0)+(15*4)"), 0);
        assertEquals(601.1, executingStringFormula.execute("(5+6.0)/10+15*(25+15)"), 0);
    }
}