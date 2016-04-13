package com.aichallenge.ultimatetoe.tensor;

import com.aichallenge.ultimatetoe.field.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Monte Carlo implementation for evaluating the next turn
 */
public class MonteCarloTensor extends Tensor {

    private final long mTimePerTurn;
    private final Random mRandom = new Random();

    public MonteCarloTensor(int pPlayerId) {
        this(pPlayerId, 100);
    }

    public MonteCarloTensor(int pPlayerId, int pTimePerTurn) {
        super(pPlayerId);
        this.mTimePerTurn = pTimePerTurn;
    }

    @Override
    public double takeTurn(Field pField, int... pMacros) {
        final long lStartTime = System.currentTimeMillis();
        List<Integer> allowed = getAllowedMoves(pField, pMacros);
        int rand = 0;
        double runs = 0, sum = 0;
        Field f = new Field();
        while (true) {
            runs++;
            rand = this.mRandom.nextInt(allowed.size());
            f.copy(pField);
            sum += monteCarloTurns(f, (((rand % 9) / 3) * 3) + ((rand / 9) / 3), this.mPlayerId == 1 ? 2 : 1);
            if ((System.currentTimeMillis() - lStartTime) > this.mTimePerTurn) {
                System.out.println("LOL " + runs);
                break;
            }
        }
        return sum / runs;
    }

    private double monteCarloTurns(Field pField, int pMacro, int currentPlayer) {
        //System.out.println("monteCarloTurns(-,"+pMacro+","+currentPlayer+")");
        switch (pField.getResult()) {
            case Field.RESULT_WIN_P1:
                return this.mPlayerId == 1 ? 1d : -1d;
            case Field.RESULT_WIN_P2:
                return this.mPlayerId == 2 ? 1d : -1d;
            case Field.RESULT_DRAW:
                return 0.0;
        }
        int lMacro = 0;
        if (pField.getMacroResult(pMacro, true) == Field.RESULT_OPEN) {
            lMacro = pMacro;
        } else {
            List<Integer> mOpenMacros = pField.getOpenMacros();
            //pField.print2D();
            //System.out.println(mOpenMacros.size());
            lMacro = mOpenMacros.get(this.mRandom.nextInt(mOpenMacros.size()));
        }
        final int startX = (lMacro % 3) * 3;
        final int startY = (lMacro / 3) * 3;
        //System.out.println("tk macro: "+lMacro+" - "+pMacro +" | "+startX+" - "+startY);
        int x = 0, y = 0;
        while (true) {
            x = startX + this.mRandom.nextInt(3);
            y = startY + this.mRandom.nextInt(3);
            if (pField.get(x, y) == 0) {
                pField.put(x,
                        y,
                        currentPlayer);
                int macro = (x % 3) + ((y % 3) * 3);
                //System.out.println("put "+x+"-"+y+ " => "+macro);
                return monteCarloTurns(pField,
                        macro,
                        currentPlayer == 1 ? 2 : 1);
            }
        }
    }

    private List<Integer> getAllowedMoves(Field pField, int... pMacros) {
        List<Integer> lAllowed = new ArrayList<>();
        int xStart = 0, yStart = 0, pos = 0;
        for (int lMacro : pMacros) {
            xStart = (lMacro % 3) * 3;
            yStart = (lMacro / 3) * 3;
            for (int i = 0; i < 9; i++) {
                pos = xStart + (i % 3) + ((yStart + (i / 3)) * 9);
                if (pField.get(pos) == 0) {
                    lAllowed.add(pos);
                }
            }
        }
        return lAllowed;
    }
}
