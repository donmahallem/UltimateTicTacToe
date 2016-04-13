package com.aichallenge.ultimatetoe.player;

import com.aichallenge.ultimatetoe.field.Field;

/**
 * Created by Don on 06.04.2016.
 */
public abstract class AbstractPlayer {

    protected final int mPlayerId;

    public AbstractPlayer(int pPlayerId) {
        this.mPlayerId = pPlayerId;
    }

    public abstract int[] takeTurn(Field field, int... allowed);

    public int getPlayerId() {
        return mPlayerId;
    }
}
