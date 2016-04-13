package com.aichallenge.ultimatetoe.util;

/**
 * Created by Don on 09.04.2016.
 */
public class MicroField {

    public final static int STATE_DRAW = 3, STATE_OPEN = 0, STATE_WIN_P1 = 1, STATE_WIN_P2 = 2;
    private int[] mField = new int[9];

    public int getState() {
        int empty = 0;
        for (int i = 0; i < 3; i++) {
            if (this.mField[i] != 0 &&
                    this.mField[i] == this.mField[i + 3] &&
                    this.mField[i] == this.mField[i + 6])
                return this.mField[i];
            else if (this.mField[(3 * i)] != 0 &&
                    this.mField[(3 * i)] == this.mField[(3 * i) + 1] &&
                    this.mField[(3 * i)] == this.mField[(3 * i) + 2])
                return this.mField[(3 * i)];
            else if (this.mField[i] == 0 ||
                    this.mField[i + 3] == 0 ||
                    this.mField[i + 6] == 0)
                empty++;
        }

        if (this.mField[0] != 0 &&
                this.mField[0] == this.mField[4] &&
                this.mField[0] == this.mField[8])
            return this.mField[0];
        else if (this.mField[2] != 0 &&
                this.mField[2] == this.mField[4] &&
                this.mField[2] == this.mField[6])
            return this.mField[2];
        return empty == 0 ? STATE_DRAW : STATE_OPEN;
    }

    public void put(int i, int value) {
        this.mField[i] = value;
    }

    public void put(int x, int y, int value) {
        this.put((y * 3) + x, value);
    }

    public void copy(MicroField field) {
        for (int i = 0; i < 9; i++)
            this.mField[i] = field.mField[i];
    }

    public int get(int pI) {
        return this.mField[pI];
    }
}
