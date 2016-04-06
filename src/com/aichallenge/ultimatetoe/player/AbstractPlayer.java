package com.aichallenge.ultimatetoe.player;

import com.aichallenge.ultimatetoe.field.MacroField;

/**
 * Created by Don on 06.04.2016.
 */
public abstract class AbstractPlayer {

    protected final int mPlayerId;

    public AbstractPlayer(int pPlayerId) {
        this.mPlayerId = pPlayerId;
    }

    public abstract int takeTurn(MacroField field, int... allowed);
}
