package com.aichallenge.ultimatetoe.player;

import com.aichallenge.ultimatetoe.field.Field;
import com.aichallenge.ultimatetoe.tensor.Tensor;

/**
 * General Purpose Tensor Player.
 */
public abstract class TensorPlayer extends AbstractPlayer {

    private final Tensor[] mTensors;

    public TensorPlayer(int pPlayerId, Tensor... pTensors) {
        super(pPlayerId);
        this.mTensors = pTensors;
    }


    @Override
    public int[] takeTurn(Field field, int... allowed) {
        double hightest = 0;
        int[] take = new int[]{-1, -1};
        int macroX, macroY;
        Field f = new Field();
        int posX = 0, posY = 0;
        for (int allow : allowed) {
            macroX = (allow % 3) * 3;
            macroY = (allow / 3) * 3;
            for (int i = 0; i < 9; i++) {
                posX = macroX + i % 3;
                posY = macroY + i / 3;
                if (field.get(posX, posY) != 0)
                    continue;
                f.copy(field);
                f.put(posX, posY, this.mPlayerId);
                double res = this.checkTensor(field, allowed);
                System.out.println(macroX + "|" + macroY + " res: " + res);
                if (res > hightest || take[0] == -1) {
                    hightest = res;
                    take[0] = posX;
                    take[1] = posY;
                }
            }
        }
        return take;
    }

    private double checkTensor(Field pField, int... pMacros) {
        if (this.mTensors.length == 1) {
            return this.mTensors[0].takeTurn(pField, pMacros);
        } else {
            double sum = 0;
            for (Tensor lTensor : this.mTensors) {
                sum += lTensor.takeTurn(pField, pMacros);
            }
            return sum / (1d * this.mTensors.length);
        }
    }
}
