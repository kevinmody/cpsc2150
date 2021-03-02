package cpsc2150.extendedTicTacToe;
import sun.font.CreatedFontTracker;

import java.util.*;

public interface IGameBoard {
    public int getNumRows();
    public int getNumColumns();
    public int getNumToWin();




    default public boolean checkSpace(BoardPosition pos){
        if(whatsAtPos(pos) !=' ') {
            return false;
        }
        return true;
    }
    public void placeMarker(BoardPosition marker, char player);

    default public boolean checkForDraw(){
        for(int i = 0; i < getNumRows();i++){
            for(int j = 0; j < getNumColumns();j++){
                BoardPosition pos = new BoardPosition(i,j);
                if(checkSpace(pos)){
                    return false;
                }
            }
        }
        return true;
    }

    public char whatsAtPos(BoardPosition pos);

    public String toString();

    /**
     *
     * @param lastPos is position of last player marked
     * @param player is the character placed
     * @return whether or not its a  win
     */


    default public boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        /*int r = lastPos.getRow();;
        int count = 0;
        for(int c = 0; c<this.getNumColumns();c++) {
            BoardPosition b = new BoardPosition(r, c);
            if (isPlayerAtPos(b, player)) {
                count++;
            } else {
                count = 0;
            }
        }
            if (count < this.getNumToWin()) {
                return false;
            } else {
                return true;
            }
    }
*/
        int count = 0;
        int min = 0;
        int max = getNumColumns();
        if ((lastPos.getRow() - getNumRows()) > min) {
            min = lastPos.getRow() - getNumRows();
        }
        for (int i = 0; i < getNumRows(); i++) {
            BoardPosition b = new BoardPosition(lastPos.getRow(), i);
            if (whatsAtPos(b) == player) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
    /**
     *
     * @param lastPos is position of last player marker
     * @param player is character of player
     * @return whetther or not its a win
     */
    default public boolean checkVerticalWin(BoardPosition lastPos, char player){
      /*  int c = lastPos.getColumn();
        int count = 0;
        for(int r = 0; r < this.getNumRows();r++) {
            BoardPosition b = new BoardPosition(r, c);
            if (isPlayerAtPos(b, player)) {
                count++;
            } else {
                count = 0;
            }
        }
        if(count < this.getNumToWin()){
            return false;
        } else {
            return true;
        }
    }
*/
        int count = 0;
        int min = 0;
        int max = getNumRows();

        if((lastPos.getColumn()-(getNumColumns()/2))>min) {
            min = lastPos.getColumn() - (getNumColumns()/ 2);
        }
        for(int i = min;i < max;i++){
            BoardPosition b = new BoardPosition(i, lastPos.getColumn());
            if(whatsAtPos(b)==player){
                count++;
                if(count==getNumToWin()){
                    return true;
                }
            }
            else{
                count = 0;
            }
        }
        return false;
        }
    /**
     *
     * @param lastPos is position of last player marked
     * @param player is the character placed
     * @return whether or not its a  win
     */
    default public boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int inLineA = 1, inLineB = 1;
        int row = lastPos.getRow() - 1;
        int col = lastPos.getColumn() - 1;
        while (col >= 0 && row >= 0 && inLineA < getNumToWin()){
            BoardPosition b = new BoardPosition(row, col);
            if (whatsAtPos(b) == whatsAtPos(lastPos)){
                inLineA++;
            }
            else {
                col = -1;
            }
            col--;
            row--;
        }
        row = lastPos.getRow() +1;
        col = lastPos.getColumn() + 1;
        while (col < getNumColumns() && row < getNumRows() && inLineA < getNumToWin()) {
            BoardPosition b = new BoardPosition(row, col);
            if (whatsAtPos(b) == whatsAtPos(lastPos)) {
                inLineA++;

            } else {
                col = getNumColumns();
            }
            col++;
            row++;
        }

        row = lastPos.getRow() - 1;
        col = lastPos.getColumn() + 1;


        while (col < getNumColumns() && row >= 0 && inLineA < getNumToWin() && inLineB < getNumToWin()) {
            BoardPosition b = new BoardPosition(row, col);
            if (whatsAtPos(b) == whatsAtPos(lastPos)) {
                inLineB++;

            } else {
                col = getNumColumns();
            }
            col++;
            row--;
        }
        row = lastPos.getRow() + 1;
        col = lastPos.getColumn() - 1;


        while (col >= 0 && row < getNumToWin() && inLineA < getNumToWin() && inLineB < getNumToWin()) {
            BoardPosition b = new BoardPosition(row, col);
            if (whatsAtPos(b) == whatsAtPos(lastPos)) {
                inLineB++;

            } else {
                col = -1;
            }
            col--;
            row++;
        }

        return (inLineA == getNumToWin() || inLineB == getNumToWin());
    }

    /**
     *
     * @param lastPos is position of last player marked
     * @param player is the character placed
     * @return whether or not its a  win
     */
    default public boolean checkForWinner(BoardPosition lastPos, char player)
    {
        return (checkHorizontalWin(lastPos, player) || checkVerticalWin(lastPos, player) || checkDiagonalWin(lastPos, player));

    }

    /**
     *
     * @param pos is postion is reading
     * @param player is character of player
     * @return boolean true or false
     * @pre pos is in range 8X8 array
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        if(whatsAtPos(pos)==player){
            return true;
        }
        return false;

    }

}
