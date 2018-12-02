package ru.veretennikov.test.task;

import java.util.LinkedList;

public class ExecutingStringFormula {

    public Double execute(String stringFormula) {
        LinkedList<Double> st = new LinkedList<>();
        LinkedList<Character> op = new LinkedList<>();
        for (int i = 0; i < stringFormula.length(); i++) {
            char c = stringFormula.charAt(i);
            if (isDelim(c)) {
                continue;
            }
            if (c == '(') {
                op.add('(');
            } else if (c == ')') {
                while (op.getLast() != '(') {
                    processOperator(st, op.removeLast());
                }
                op.removeLast();
            } else if (isOperator(c)) {
                while (!op.isEmpty() && priority(op.getLast()) >= priority(c)) {
                    processOperator(st, op.removeLast());
                }
                op.add(c);
            } else {
                StringBuilder operand = new StringBuilder();
                while (i < stringFormula.length() && (Character.isDigit(stringFormula.charAt(i))
                        || (stringFormula.charAt(i)) == '.')) {
                    operand.append(stringFormula.charAt(i++));
                }
                --i;
                st.add(Double.parseDouble(String.valueOf(operand)));
            }
        }
        while (!op.isEmpty())
            processOperator(st, op.removeLast());
        return st.get(0);
    }

    private boolean isDelim(char c) {
        return c == ' ';
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private void processOperator(LinkedList<Double> st, char op) {
        double r = st.removeLast();
        double l = st.removeLast();
        switch (op) {
            case '+':
                st.add(l + r);
                break;
            case '-':
                st.add(l - r);
                break;
            case '*':
                st.add(l * r);
                break;
            case '/':
                st.add(l / r);
                break;
        }
    }
}
