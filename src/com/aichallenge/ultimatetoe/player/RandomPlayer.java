package com.aichallenge.ultimatetoe.player;

import com.aichallenge.ultimatetoe.field.Field;

import java.util.Random;

/**
 * Created by Don on 11.04.2016.
 */
public class RandomPlayer extends AbstractPlayer {

    private final Random mRandom = new Random();

    /**
     * {@inheritDoc}
     *
     * @param pPlayerId
     */
    public RandomPlayer(int pPlayerId) {
        super(pPlayerId);
    }

    @Override
    public int[] takeTurn(Field field, int... allowed) {
        int macro = 0;
        if (allowed.length == 1) {
            macro = allowed[0];
        } else {
            macro = allowed[this.mRandom.nextInt(allowed.length)];
        }
        int rand = 0;
        int[] take = new int[2];
        take[0] = (macro % 3) * 3;
        take[1] = (macro / 3) * 3;
        while (true) {
            rand = this.mRandom.nextInt(9);
            if (field.get(take[0] + rand % 3, take[1] + rand / 3) == 0) {
                take[0] += rand % 3;
                take[1] += rand / 3;
                break;
            }
        }
        return take;
    }
}
