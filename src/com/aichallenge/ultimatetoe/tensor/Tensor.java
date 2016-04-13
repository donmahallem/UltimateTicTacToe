package com.aichallenge.ultimatetoe.tensor;

import com.aichallenge.ultimatetoe.field.Field;

/**
 * Tensor for evaluating a field
 */
public abstract class Tensor {

    /**
     * the id of the player who owns this tensor
     */
    protected final int mPlayerId;

    /**
     * Creates a tensor for the specified player
     *
     * @param pPlayerId the player id
     */
    public Tensor(int pPlayerId) {
        this.mPlayerId = pPlayerId;
    }

    /**
     * general method that is called to evaluate the next turn
     *
     * @param pField  the field to be evaluated
     * @param pMacros the macros to currently choose from
     * @return
     */
    public abstract double takeTurn(Field pField, int... pMacros);
}
