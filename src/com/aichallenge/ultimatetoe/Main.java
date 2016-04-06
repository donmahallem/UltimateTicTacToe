package com.aichallenge.ultimatetoe;

import com.aichallenge.ultimatetoe.field.MacroField;

public class Main {

    public static void main(String[] args) {
        MacroField lMacroField = new MacroField();
        lMacroField.print2D();
        lMacroField.put(8, 8, 1);
        lMacroField.put(7, 7, 1);
        lMacroField.put(6, 6, 1);
        lMacroField.print2D();
        System.out.println(lMacroField.getResult() + " - " + lMacroField.get(2, 2).getResult());
        lMacroField.put(5, 5, 1);
        lMacroField.put(4, 4, 1);
        lMacroField.put(3, 3, 1);
        lMacroField.put(2, 2, 1);
        lMacroField.put(1, 1, 1);
        lMacroField.put(0, 0, 1);
        lMacroField.print2D();
        System.out.println(lMacroField.getResult() + " - " + lMacroField.get(2, 2).getResult());
    }
}
