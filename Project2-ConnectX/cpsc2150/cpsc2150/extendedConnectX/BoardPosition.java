package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project2 ConnectX

import java.util.*;

/**
 * @invariants 0 <= row <= 6 and 0 <= col <= 9
 */
public class BoardPosition {

    private int row;
    private int col;

    //constructor

    /**
     * @param r row of position
     * @param c column of position
     / @param playerChar character marker
     * @pre none
     * @post row = r. col = c, player = playerChar
     */
    public BoardPosition(int r, int c) {
        this.row = r;
        this.col = c;
    }

    /**
     * @return the row of the coordinate
     * @pre none
     * @post getRow = row
     */
    public int getRow() {
        //returns the row
        return row;
    }

    /**
     * @return the column of the coordinate
     * @pre none
     * @post getColumn = col
     */
    public int getColumn() {
        return col;
    }

    /**
     * @pre none
     * @post boolean
     * @param o is the object
     * @return true iff object o is true otherwise false and BoardPositions are equal with same row and column
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardPosition)) return false;
        BoardPosition that = (BoardPosition) o;
        return getRow() == that.getRow() &&
                col == that.col;
    }

    /**
     * @pre none
     * @post get row and get column,
     * @return string representation of board position
     */
    @Override
    public String toString() {
        String s = "<";
        s = s + this.getColumn();
        s = "," + this.getRow() + ">";
        return s;

    }
}


