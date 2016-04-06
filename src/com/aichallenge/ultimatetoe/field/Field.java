package com.aichallenge.ultimatetoe.field;

import java.util.Arrays;

/**
 * Created by Don on 06.04.2016.
 */
public abstract class Field<T> {

    public final static int RESULT_WIN_P1 = 1, RESULT_WIN_P2 = 2, RESULT_OPEN = 0, RESULT_DRAW = 3;
    protected final T[] mField;
    protected boolean mChanged = true;
    protected int mCachedResult = 0;

    public Field(T[] pField) {
        this.mField = pField;
    }

    public T get(int i) {
        return this.mField[i];
    }

    public T get(int x, int y) {
        return this.mField[(y * 3) + x];
    }

    public abstract void copy(Field<T> field);

    public abstract int getResult();

    public abstract void put(int x, int y, int value);

    public abstract void print2D();

    public abstract void rotate();

    public abstract void mirror();

    @Override
    public boolean equals(Object lpO) {
        if (this == lpO) return true;
        if (!(lpO instanceof Field)) return false;

        Field<?> llField = (Field<?>) lpO;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(mField, llField.mField);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(mField);
    }
}
