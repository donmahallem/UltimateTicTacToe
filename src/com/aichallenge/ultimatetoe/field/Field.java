package com.aichallenge.ultimatetoe.field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Don on 06.04.2016.
 */
public class Field {

    public final static int RESULT_WIN_P1 = 1, RESULT_WIN_P2 = 2, RESULT_OPEN = 0, RESULT_DRAW = 3;
    protected final int[][] mField;
    protected int[] mMacroResult = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
    protected boolean mChanged = true;
    protected int mCachedResult = 0;
    private boolean[] mMacroChanged = new boolean[]{true, true, true, true, true, true, true, true, true};

    public Field(int[][] pField) {
        this.mField = pField;
    }

    public Field() {
        this(new int[9][9]);
    }

    public static int getMacroBlockStart(int macro) {
        return Field.getMacroBlockStart(macro % 3, macro / 3);
    }

    public static int getMacroBlockStart(int x, int y) {
        return (y * 27) + (x * 3);
    }

    public int get(int i) {
        return this.mField[i % 9][i / 9];
    }

    /**
     * gets the current value
     *
     * @param x x-Coordinate
     * @param y y-Coordinate
     * @return the value for coordinates <code>x</code>,<code>y</code>
     */
    public int get(int x, int y) {
        return this.mField[x][y];
    }

    /**
     * Copies the <code>field</code> into the current one
     *
     * @param field the field to copy
     */
    public void copy(Field field) {
        for (int i = 0; i < 81; i++) {
            this.mField[i % 9][i / 9] = field.mField[i % 9][i / 9];
        }
        for (int i = 0; i < this.mMacroResult.length; i++) {
            this.mMacroResult[i] = field.mMacroResult[i];
        }
    }

    /**
     * Force refreshes the {@link Field#mMacroResult} cache
     */
    public void cacheMacroResults() {
        for (int i = 0; i < 9; i++) {
            this.mMacroResult[i] = this.getMacroResult(i % 3, i / 3, true);
        }
    }

    /**
     * sets the value of the field for the positions
     *
     * @param x     x-Coordinate
     * @param y     y-Coordinate
     * @param value value to be set to these coordinates
     */
    public void put(int x, int y, int value) {
        this.mField[x][y] = value;
        this.mMacroChanged[((y / 3) * 3) + (x / 3)] = true;
        //System.out.println("put("+x+","+y+") - "+(((y/3)*3)+(x/3)));
    }

    /**
     * prints the field to {@link System#out}
     */
    public void print2D() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (x == 0) {
                    System.out.println();
                    if (y % 3 == 0 && y != 0) {
                        System.out.println("---------------");
                    }
                }
                if (x % 3 == 0 && x != 0)
                    System.out.print(" | ");
                System.out.print(this.mField[x][y]);
            }
        }
    }

    public void rotate() {
        this.rotate(1);
    }

    public void rotate(int times) {
        if (times % 4 == 0)
            return;
        int buf = 0;
        if (times % 4 >= 2) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y <= 4; y++) {
                    buf = this.mField[x][y];
                    this.mField[x][y] = this.mField[8 - x][8 - y];
                    this.mField[8 - x][8 - y] = buf;
                }
            }
        }
        if (times % 2 == 1) {
            for (int x = 0; x < 4; x++) {
                for (int y = x; y < 9 - x - 1; y++) {
                    buf = this.mField[x][y];
                    this.mField[x][y] = this.mField[9 - y - 1][x];
                    this.mField[9 - y - 1][x] = this.mField[9 - x - 1][9 - y - 1];
                    this.mField[9 - x - 1][9 - y - 1] = this.mField[y][9 - x - 1];
                    this.mField[y][9 - x - 1] = buf;
                }
            }
        }
    }

    /**
     * Gets the current result for marco <code>macro</code>
     *
     * @param macro macro number
     * @return result for the macro
     * @see #getMacroResult(int, int)
     */
    public int getMacroResult(int macro) {
        return this.getMacroResult(macro % 3, macro / 3);
    }

    /**
     * Gets the current result for marco <code>macro</code>
     *
     * @param macro macro number
     * @param force force refresh the cache
     * @return result for the macro
     * @see #getMacroResult(int, int, boolean)
     */
    public int getMacroResult(int macro, boolean force) {
        return this.getMacroResult(macro % 3, macro / 3, force);
    }

    /**
     * Convience method for {@link Field#getMacroResult(int, int, boolean)} with no force
     * Returns the result for the macro
     *
     * @param x x-Coordinate for the macro
     * @param y y-Coordinate for the macro
     * @return one of {@link Field#RESULT_DRAW},{@link Field#RESULT_OPEN},{@link Field#RESULT_WIN_P2},{@link Field#RESULT_WIN_P1}
     */
    public final int getMacroResult(int x, int y) {
        return this.getMacroResult(x, y, false);
    }

    /**
     * Returns the result for the macro
     *
     * @param x     x-Coordinate for the macro
     * @param y     y-Coordinate for the macro
     * @param force force refresh
     * @return one of {@link Field#RESULT_DRAW},{@link Field#RESULT_OPEN},{@link Field#RESULT_WIN_P2},{@link Field#RESULT_WIN_P1}
     */
    public final int getMacroResult(int x, int y, boolean force) {
        final int macroNum = (y * 3) + x;
        if (!this.mMacroChanged[macroNum] && !force)
            return this.mMacroResult[macroNum];
        this.mMacroResult[macroNum] = this.getMacroResultInt(x, y);
        this.mMacroChanged[macroNum] = false;
        return this.mMacroResult[macroNum];
    }

    /**
     * Internal method for {@link Field#getMacroResultInt(int, int)}
     *
     * @param x
     * @param y
     * @return
     */
    private final int getMacroResultInt(int x, int y) {
        int empty = 0;
        final int startX = (x * 3);
        final int startY = (y * 3);
        for (int i = 0; i < 3; i++) {
            if (this.mField[startX + i][startY] != 0 &&
                    this.mField[startX + i][startY] == this.mField[startX + i][startY + 1] &&
                    this.mField[startX + i][startY] == this.mField[startX + i][startY + 2])
                return this.mField[startX + i][startY];
            else if (this.mField[startX][(startY) + i] != 0 &&
                    this.mField[startX][(startY) + i] == this.mField[startX + 1][(startY) + i] &&
                    this.mField[startX][(startY) + i] == this.mField[startX + 2][(startY) + i])
                return this.mField[startX][(startY) + i];
            else if (this.mField[startX + i][(startY)] == 0 ||
                    this.mField[startX + i][(startY) + 1] == 0 ||
                    this.mField[startX + i][(startY) + 2] == 0)
                empty++;
        }
        if (this.mField[startX][startY] != 0 &&
                this.mField[startX][startY] == this.mField[startX + 1][startY + 1] &&
                this.mField[startX][startY] == this.mField[startX + 2][startY + 2]) {
            return this.mField[startX][startY];
        } else if (this.mField[startX + 2][startY] != 0 &&
                this.mField[startX + 2][startY] == this.mField[startX + 1][(startY) + 1] &&
                this.mField[startX + 2][startY] == this.mField[startX][startY + 2]) {
            return this.mField[startX + 2][startY];
        }
        return empty > 0 ? Field.RESULT_OPEN : Field.RESULT_DRAW;
    }

    /**
     * Mirrors the field along the center vertical axis
     */
    public void mirror() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 9; y++) {
                int buf = this.mField[x][y];
                this.mField[x][y] = this.mField[8 - x][y];
                this.mField[8 - x][y] = buf;
            }
        }
    }

    @Override
    public boolean equals(Object lpO) {
        if (this == lpO) return true;
        if (!(lpO instanceof Field)) return false;

        Field llField = (Field) lpO;

        return Arrays.deepEquals(mField, llField.mField);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(mField);
    }

    public int getResult() {
        int empty = 0;
        int[] results = new int[9];
        for (int i = 0; i < 9; i++) {
            results[i] = this.getMacroResult(i);
        }
        for (int i = 0; i < 3; i++) {
            final int y = (i * 3);
            if (results[i] != Field.RESULT_OPEN &&
                    results[i] != Field.RESULT_DRAW &&
                    results[i] == results[i + 3] &&
                    results[i] == results[i + 6])
                return results[i];
            else if (results[y] != Field.RESULT_OPEN &&
                    results[y] != Field.RESULT_DRAW &&
                    results[y] == results[y + 1] &&
                    results[y] == results[y + 2])
                return results[i];
            else if (results[i] == 0 &&
                    results[i] == results[i + 3] &&
                    results[i] == results[i + 6])
                empty++;
        }
        if (results[0] != Field.RESULT_DRAW &&
                results[0] != Field.RESULT_OPEN &&
                results[0] == results[4] &&
                results[0] == results[8]) {
            return results[0];
        } else if (results[2] != Field.RESULT_DRAW &&
                results[2] != Field.RESULT_OPEN &&
                results[2] == results[4] &&
                results[2] == results[6]) {
            return results[2];
        }
        return empty > 0 ? Field.RESULT_OPEN : Field.RESULT_DRAW;
    }

    /**
     * Clears the Field
     */
    public void clear() {
        for (int x = 0; x < this.mField.length; x++)
            for (int y = 0; y < this.mField[x].length; y++)
                this.mField[x][y] = 0;
    }

    public List<Integer> getOpenMacros() {
        List<Integer> mOpen = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            if (this.getMacroResult(i)==0)
                mOpen.add(i);
        return mOpen;
    }
}
