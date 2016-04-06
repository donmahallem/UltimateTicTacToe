package com.aichallenge.ultimatetoe.field;

/**
 * Created by Don on 06.04.2016.
 */
public class MicroField extends Field<Integer> {

    public MicroField(Integer[] pField) {
        super(pField);
        for (int i = 0; i < this.mField.length; i++)
            this.mField[i] = 0;
    }

    public MicroField() {
        this(new Integer[9]);
    }

    @Override
    public void copy(Field<Integer> field) {
        for (int i = 0; i < this.mField.length; i++) {
            this.mField[i] = new Integer(field.mField[i]);
        }
        this.mCachedResult = field.mCachedResult;
        this.mChanged = field.mChanged;
    }

    @Override
    public int getResult() {
        if (!this.mChanged)
            return this.mCachedResult;
        this.mCachedResult = this.getResultInt();
        this.mChanged = false;
        return this.mCachedResult;
    }

    private int getResultInt() {
        int emtpy = 0;
        for (int i = 0; i < 9; i++) {
            int result = this.mField[i];
            if (result == 0) {
                emtpy++;
                continue;
            }
            if (i <= 6 && result == this.mField[i + 1]
                    && result == this.mField[i + 2]) {
                return result;
            } else if (i <= 2 && result == this.mField[i + 3]
                    && result == this.mField[i + 6]) {
                return result;
            }
        }
        int result = this.mField[4];
        if (result == this.mField[0]
                && result == this.mField[8]) {
            return result;
        } else if (result == this.mField[6]
                && result == this.mField[2]) {
            return result;
        }
        return emtpy == 0 ? Field.RESULT_DRAW : Field.RESULT_OPEN;
    }

    @Override
    public void put(int x, int y, int value) {
        this.mField[(y * 3) + x] = value;
        this.mChanged = true;
    }

    public void put(int i, int value) {
        this.mField[i] = value;
    }

    @Override
    public void print2D() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0)
                System.out.println();
            System.out.print(this.mField[i]);
        }
        System.out.println();
    }

    @Override
    public void rotate() {

    }

    @Override
    public void mirror() {
        for (int i = 0; i < 3; i++) {
            int buf = this.mField[i * 3];
            this.mField[i * 3] = this.mField[(i * 3) + 2];
            this.mField[(i * 3) + 2] = buf;
        }
    }
}
