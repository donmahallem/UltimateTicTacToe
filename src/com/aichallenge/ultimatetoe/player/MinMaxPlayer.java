package com.aichallenge.ultimatetoe.player;

import com.aichallenge.ultimatetoe.field.Field;
import com.aichallenge.ultimatetoe.field.MacroField;

/**
 * Created by Don on 06.04.2016.
 */
public class MinMaxPlayer extends AbstractPlayer {

    private static final int MAX_DEPTH = 10;
    private final int mMaxDepth;

    public MinMaxPlayer(int pPlayerId) {
        super(pPlayerId);
        this.mMaxDepth = MAX_DEPTH;
    }

    @Override
    public int takeTurn(MacroField field, int... allowed) {
        if (allowed.length == 1) {
            return this.takeTurnInt(field, allowed[0]);
        } else {
            int[] take = null;
            for (int i : allowed) {

            }
        }
    }

    private double takeTurnInt(MacroField field, int x, int y, int currentPlayer, int depth) {
        MacroField f = new MacroField();
        f.copy(field);
        f.put(x, y, currentPlayer);
        final int result = f.getResult();
        if (result == this.mPlayerId) {
            return 1d;
        } else if (result == Field.RESULT_DRAW) {
            return 0.55;
        } else if ((result == Field.RESULT_WIN_P1 ||
                result == Field.RESULT_WIN_P2)
                && result != currentPlayer) {
            return 0;
        } else if (depth > this.mMaxDepth) {
            return 0.5;
        }
        int take = -1;
        double highest = 0;
        for (int i = 0; i < 9; i++) {

            final double current = this.takeTurnInt(f, x, x, currentPlayer == 1 ? 2 : 1, depth + 1);
            if (current > highest || take == -1) {
                take = i;
                highest = current;
            }
        }
    }

    private double takeTurnIntMacro(MacroField field, int macro, int currentPlayer, int depth) {
        if (field.get(macro).getResult() == Field.RESULT_OPEN) {
            return this.takeTurnIntMicro(field, macro, currentPlayer, depth);
        } else {
            double num = 0, sum = 0;
            for (int i = 0; i < 9; i++) {
                if (field.get(macro).getResult() == Field.RESULT_OPEN) {
                    sum += this.takeTurnIntMicro(field, macro, currentPlayer, depth);
                    num++;
                }
            }
            return sum / num;
        }
    }

    private double takeTurnIntMicro(MacroField pField, int pMacro, int pCurrentPlayer, int pDepth) {
        double num = 0, sum = 0;
        MacroField lMacroField = new MacroField();
        for (int i = 0; i < 9; i++) {
            if (pField.get(pMacro).get(i) != 0) {
                continue;
            }
            lMacroField.copy(pField);
            lMacroField.get(pMacro).put(i, pCurrentPlayer);
            num++;
            sum += takeTurnIntMacro(lMacroField, i, pCurrentPlayer == 1 ? 2 : 1, pDepth + 1);
        }
        return sum / num;
    }
}
