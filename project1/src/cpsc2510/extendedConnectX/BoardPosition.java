/**
 * Board position is class for coordinates of inputs
 * This class is bounded by column and row
 */
package cpsc2510.extendedConnectX;
//Author: Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 02/07/2021

public class BoardPosition {
    private final int column;
    private final int row;

    public BoardPosition(int column, int row){
        this.column = column;
        this.row = row;
    }
    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }
}
