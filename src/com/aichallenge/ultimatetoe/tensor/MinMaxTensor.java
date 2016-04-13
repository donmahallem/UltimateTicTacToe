package com.aichallenge.ultimatetoe.tensor;

import com.aichallenge.ultimatetoe.field.Field;
import com.aichallenge.ultimatetoe.util.MicroField;

/**
 * Created by Don on 10.04.2016.
 */
public class MinMaxTensor extends Tensor {
    public MinMaxTensor(int pPlayerId) {
        super(pPlayerId);
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

    @Override
    public double takeTurn(Field pField, int... pMacros) {
        return 0;
    }
}
