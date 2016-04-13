package com.aichallenge.ultimatetoe.util;

/**
 * Created by Don on 09.04.2016.
 */
public class Runner {

    public static void test() {

        MicroField lMicroField = new MicroField();
        lMicroField.put(4, 1);
        lMicroField.put(1, 2);
        MicroField lMicroField1 = new MicroField();
        for (int i = 0; i < 9; i++) {
            if (lMicroField.get(i) != 0)
                continue;
            lMicroField1.copy(lMicroField);
            lMicroField1.put(i, 1);
            System.out.println(i + " - " + take(lMicroField1, 1, 2));
        }
    }


    private static double take(MicroField pMicroField, int player, int currentPlayer) {
        switch (pMicroField.getState()) {
            case MicroField.STATE_DRAW:
                return 0.55;
            case MicroField.STATE_WIN_P1:
                return player == 1 ? 1d : 0d;
            case MicroField.STATE_WIN_P2:
                return player == 2 ? 1d : 0d;
        }
        double sum = 0, num = 0;
        MicroField lMicroField = new MicroField();
        for (int i = 0; i < 9; i++) {
            if (pMicroField.get(i) != 0) {
                continue;
            }
            num++;
            lMicroField.copy(pMicroField);
            lMicroField.put(i, currentPlayer);
            sum += take(lMicroField, player, currentPlayer == 1 ? 2 : 1);
        }
        return sum / num;
    }
}
