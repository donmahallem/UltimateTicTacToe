package com.aichallenge.ultimatetoe.field;

/**
 * Created by Don on 06.04.2016.
 */
public class MacroField extends Field<MicroField> {

    public MacroField(MicroField[] pField) {
        super(pField);
        for (int i = 0; i < this.mField.length; i++)
            this.mField[i] = new MicroField();
    }

    public MacroField() {
        this(new MicroField[9]);
    }

    @Override
    public void copy(Field<MicroField> field) {
        for (int i = 0; i < this.mField.length; i++) {
            this.mField[i].copy(field.mField[i]);
        }
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
            int result = this.mField[i].getResult();
            if (result == RESULT_OPEN) {
                emtpy++;
                continue;
            } else if (result == RESULT_DRAW) {
                continue;
            }
            if (i <= 6 && result == this.mField[i + 1].getResult()
                    && result == this.mField[i + 2].getResult()) {
                return result;
            } else if (i <= 2 && result == this.mField[i + 3].getResult()
                    && result == this.mField[i + 6].getResult()) {
                return result;
            }
        }
        int result = this.mField[4].getResult();
        if (result == this.mField[0].getResult()
                && result == this.mField[8].getResult()) {
            return result;
        } else if (result == this.mField[6].getResult()
                && result == this.mField[2].getResult()) {
            return result;
        }
        return emtpy == 0 ? Field.RESULT_DRAW : Field.RESULT_OPEN;
    }

    @Override
    public void put(int x, int y, int value) {
        this.mField[((y / 3) * 3) + (x / 3)].put(x % 3, y % 3, value);
        this.mChanged = true;
    }


    @Override
    public void print2D() {
        for (int i = 0; i < 81; i++) {
            if (i % 9 == 0)
                System.out.println();
            int x = i % 9;
            int y = i / 9;
            System.out.print(this.get(x / 3, y / 3).get(x % 3, y % 3));
        }
        System.out.println();
    }

    @Override
    public void rotate() {

    }

    @Override
    public void mirror() {
        for (int i = 0; i < 3; i++) {
        }
    }
}
