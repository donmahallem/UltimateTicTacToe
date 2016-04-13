package com.aichallenge.ultimatetoe;

import com.aichallenge.ultimatetoe.field.Field;
import com.aichallenge.ultimatetoe.player.AbstractPlayer;

/**
 * Created by Don on 10.04.2016.
 */
public class Engine {

    public static int playMatch(AbstractPlayer p1, AbstractPlayer p2) {
        Field lField = new Field();
        int move = 0;
        int[] allowed = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1}, take;
        while (true) {
            if (move % 2 == 0) {
                take = p1.takeTurn(lField, allowed);
                lField.put(take[0], take[1], p1.getPlayerId());
            } else {
                take = p2.takeTurn(lField, allowed);
                lField.put(take[0], take[1], p2.getPlayerId());
            }
            final int state = lField.getResult();
            if (state != Field.RESULT_OPEN) {
                System.out.println("Winner is: " + state);
                break;
            }

            move++;
        }
        return 0;
    }
}
