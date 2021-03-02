package cpsc2150.extendedTicTacToe;
import java.util.*;
/**
 * @invariant 7>=row>=0 and 7>=column>=0
 *
 */
public class BoardPosition {
    int r, c;

    /**
     *
     * @param row is row index of position
     * @param column is column index of position
     * @pre row>=0 and row<=7 and column>=0 and column<=7
     * @post row>=0 and row<=7 and column>=0 and column<=7
     */
    public BoardPosition(int row, int column)
    {
    //constructor
    r = row;
    c = column;
    }

    /**
     * @pre row>=0 and <=7
     * @post row>-0 and <=7
     * @return int = row index
     */
    public int getRow()
    {
    return r;
    }
    /**
     * @pre column>=0 and <=7
     * @post column>-0 and <=7
     * @return int = column index
     */
    public int getColumn()
    {
    return c;
    }

    /**
     * @return boolean = true or false
     * @pre row>=0 and row<=7 and column>=0 and column<=7
     * @post row>=0 and row<=7 and column>=0 and column<=7
     */
    public boolean equals(Object b)
    {
    if(b instanceof BoardPosition){
        if(((BoardPosition) b).getRow() == this.getRow() && ((BoardPosition) b).getColumn() == this.getColumn()){
            return true;
        }
    }
    return false;
    }

    /**
     *
     * @param row is the row index of the board position
     * @param column is the column index of the board position
     * @return String in form <row>,<column>
     * @pre row>=0 and row<=7 and column>=0 and column<=7
     * @post row>=0 and row<=7 and column>=0 and column<=7
     */
    public String toString(int row, int column)
    {
    //formats row and column indexes in string format
    String s = "<"+row+", "+column+">";
    return s;
    }
}
