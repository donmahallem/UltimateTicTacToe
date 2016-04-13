package com.aichallenge.ultimatetoe.tensor;

import com.aichallenge.ultimatetoe.field.Field;

/**
 * Created by Don on 10.04.2016.
 */
public class LearningTensor extends Tensor {
    public LearningTensor(int pPlayerId) {
        super(pPlayerId);
    }

    @Override
    public double takeTurn(Field pField, int... pMacros) {
        return 0;
    }
}
