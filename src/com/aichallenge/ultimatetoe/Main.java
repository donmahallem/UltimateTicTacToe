package com.aichallenge.ultimatetoe;

import com.aichallenge.ultimatetoe.field.Field;
import com.aichallenge.ultimatetoe.player.MonteCarloPlayer;

public class Main {

    public static void main(String[] args) {
        Field lMacroField = new Field();
        lMacroField.print2D();
        lMacroField.put(8, 8, 1);
        lMacroField.put(7, 7, 1);
        lMacroField.put(6, 6, 1);
        //System.out.println(lMacroField.getResult() + " - " + lMacroField.get(2, 2).getResult());
        lMacroField.put(5, 5, 1);
        lMacroField.put(4, 4, 1);
        lMacroField.put(3, 3, 1);
        lMacroField.put(2, 2, 1);
        lMacroField.put(1, 1, 1);
        lMacroField.put(0, 0, 1);
        lMacroField.put(8, 0, 2);
        lMacroField.put(8, 8, 3);
        lMacroField.put(0, 8, 4);
        lMacroField.put(0, 0, 2);
        lMacroField.put(1, 1, 2);
        lMacroField.put(2, 2, 2);
        lMacroField.print2D();
        lMacroField.rotate(2);
        lMacroField.print2D();
        lMacroField.mirror();
        lMacroField.print2D();
        lMacroField.clear();
        MonteCarloPlayer lMonteCarloPlayer = new MonteCarloPlayer(1);
        lMonteCarloPlayer.takeTurn(lMacroField, 2);
        for (int i = 0; i < 9; i++) {
            System.out.println(i + " - " + lMacroField.getMacroResult(i));
        }

        //Runner.test();

        //Engine.playMatch(new RandomPlayer(1),new RandomPlayer(2));
        //System.out.println(lMacroField.getResult() + " - " + lMacroField.get(2, 2).getResult());
    }
}
