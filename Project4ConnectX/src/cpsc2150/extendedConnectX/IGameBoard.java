package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project4 ConnectX
/**
 * This interface holds the information of a ConnectX board, and processes what occurs within the game
 * @Defines: maxRows: Z - The number of rows on the game board
 *           maxCols: Z - The number of columns on the game board
 *           numToWin: Z - The number of tokens in a row needed to win
 *           board: char[][]- An array contains the tokens for the player
 *
 * @Initialization_Ensures: Board will be size numRows x numCols
 *
 * @Constraints: numRows = getNumRows && numCols = getNumCols && numToWin = getNumToWin
 *
 **/
import java.util.*;
public interface IGameBoard {

    public static final int minRowsCols= 3;
    public static final int maxRowsCols = 100;
    public static final int maxNumToWin= 25;


    //returns the number of rows in the GameBoard
    /**
     * @pre none
     * @post getNumRows() = maxRowsCols
     * @return the number of rows
     */
    public int getNumRows();
    /**
     * @pre none
     * @post getNumColumns() = maxRowsCols
     * @return the number of columns
     */
    //returns the number of columns in the GameBoard
    public int getNumColumns();
    /**
     * @pre none
     * @post getNumToWin() = maxNumToWin
     * @return the number of tokens in a row to win for the player
     */
    //returns the number of tokens in a row needed to win the game
    public int getNumToWin();

    /**
     * @pre 0 <= col <= maxCol
     * @post boolean true or false
     * @param c is col
     * @return [true if the column is able to take in another token,
     *         false if the column is not able to take in any more tokens at the given column]
     */
    default boolean checkIfFree(int c){
        cpsc2150.extendedConnectX.BoardPosition initialPosition = new cpsc2150.extendedConnectX.BoardPosition(getNumRows() - 1, c);
        return whatsAtPos(initialPosition) == ' ';
    }

    /**
     * @pre [player tokens is equal to numToWin]
     * @post [true or false whether player win the game either horizontally, vertically or diagonally]
     * @param c is the column
     * @return [true if the last token placed (which was placed in column c) resulted in the player winning the game. false if the last token placed does not result in the player winning the game]
     */
    default boolean checkForWin(int c) {
        int row = 0;
        char p;

        //Parsing through the gameboard to check based on user column
        for (int i = 0; i < getNumRows(); i++) {
            cpsc2150.extendedConnectX.BoardPosition initialPosition = new cpsc2150.extendedConnectX.BoardPosition(i, c);
            if (whatsAtPos(initialPosition) != ' ') {
                row = i;
            }
        }
        cpsc2150.extendedConnectX.BoardPosition pos = new cpsc2150.extendedConnectX.BoardPosition(row, c);
        //Calling whatAtPos method based on the coordinates of the above statement
        p = whatsAtPos(pos);

        //Checking for the win conditions for the 3 ways a player could win
        if (checkHorizWin(pos, p)) {
            return true;
        } else if (checkVertWin(pos, p)) {
            return true;
        } else return checkDiagWin(pos, p);
    }

    /**
     * @pre [almost all the free places are taken in the gameboard]
     * @post [players are tied]
     * @return true iff the game board results in a tie game, otherwise it returns false.
     */
    default boolean checkTie(){
        cpsc2150.extendedConnectX.BoardPosition initialPosition = new cpsc2150.extendedConnectX.BoardPosition(0,0);
        for (int i = 0; i < getNumRows(); i++) {
            for (int j = 0; j < getNumColumns(); j++) {
                initialPosition = new cpsc2150.extendedConnectX.BoardPosition(i,j);
                if (whatsAtPos(initialPosition) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @pre c is col 0 <= col <= maxCol, p = playertokens
     * @post c is col >=1, p = playertokens
     * @param p is the player char = 'X', 'O',....
     * @param c is the comlumn of the game board
     */
   public void placeToken(char p, int c);
    /**
     * @pre 0 <= row <= maxRow, p = playertokens
     * @post [boolean True iff row >= maxRow, p = playertokens otherwise false]
     * @param pos is position most recently marked
     * @param p is the player token
     * @return True if numToWin in a row horizontally
     */
    default boolean checkHorizWin(cpsc2150.extendedConnectX.BoardPosition pos, char p){
        int counter = 0;
        boolean win = false ;
        for (int i = 0; i < getNumColumns(); i++) {
            if (isPlayerAtPos(new cpsc2150.extendedConnectX.BoardPosition(pos.getRow(),i),p)) {
                counter++;
                if(counter==getNumToWin()){
                    return true;
                }
            }
            else
                counter = 0;
        }
        return counter == getNumToWin();
    }

    /**
     * @pre 0 <= col <= maxCol, p = playertokens
     * @post [boolean True iff col >= maxCol, p = playertoken otherwise false]
     * @param pos is position most recently marked
     * @param p is the player token
     * @return True iff numToWin in a row vertically otherwise false
     */
    default boolean checkVertWin(cpsc2150.extendedConnectX.BoardPosition pos, char p){
        int counter = 0;
        boolean win;
        for (int i = 0; i < getNumRows(); i++) {
            if (isPlayerAtPos(new cpsc2150.extendedConnectX.BoardPosition(i, pos.getColumn()), p)){
                counter++;
                if (counter == getNumToWin()) {
                    return true;
                }
            }
            else
                counter = 0;
        }
        return counter == getNumToWin();
    }

    /**
     * @pre 0 <= row <= maxRow, 0 <= col <= maxCol, p = 'X' or 'O'
     * @post [player wins and True iff it is numToWin in a row diagonally, P = playertokens otherwise false]
     * @param pos is position most recently marked
     * @param p is the player token
     * @return True iff numToWin in a row diagonally in left or right direction otherwise false
     */
    default boolean checkDiagWin(cpsc2150.extendedConnectX.BoardPosition pos, char p){
        int row = pos.getRow();
        int col = pos.getColumn();
        int counter = 1;
        char playerToken;
        //Not going back to the original position that's being passed
        //Only checking whats on the sides of it and never truly staring at the last token position

        //Going up and right
        //Incrementing both row and column at the same time
        for (int i = row + 1, j = col + 1; i < getNumRows() && j < getNumColumns(); i++, j++) {
            cpsc2150.extendedConnectX.BoardPosition initialPosition = new cpsc2150.extendedConnectX.BoardPosition(i, j);
            playerToken = whatsAtPos(initialPosition);
            if (playerToken == p ) {
                counter++;
            } else {
                break;
            }
        }
        if (counter == getNumToWin()) {
            return true;
        }
        //Going down and left
        //Decrementing both row and column at the same time
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            cpsc2150.extendedConnectX.BoardPosition initialPosition = new cpsc2150.extendedConnectX.BoardPosition(i, j);
            playerToken = whatsAtPos(initialPosition);
            if (playerToken == p){
                counter++;
            } else {
                break;
            }
        }
        if (counter >= getNumToWin()) {
            return true;
        }
        //Going up and left
        //Incrementing row Decrementing column
        counter = 1;
        for (int i = row + 1, j = col - 1; i < getNumRows() && j >= 0; i++, j--) {
            cpsc2150.extendedConnectX.BoardPosition initialPosition = new cpsc2150.extendedConnectX.BoardPosition(i, j);
            playerToken = whatsAtPos(initialPosition);
            if (playerToken == p){
                counter++;
            } else {
                break;
            }
        }
        if (counter == getNumToWin()) {
            return true;
        }
        //Going down and right
        //Decrementing row and incrementing column
        for (int i = row - 1, j = col + 1; i >= 0 && j < getNumColumns(); i--, j++) {
            cpsc2150.extendedConnectX.BoardPosition initialPosition = new cpsc2150.extendedConnectX.BoardPosition(i, j);
            playerToken = whatsAtPos(initialPosition);
            if (playerToken == p){
                counter++;
                if (counter == getNumToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }
        return counter == getNumToWin();
    }

    /**
     * @param pos the position being stored or read
     * @return [returns the char that is in position pos of the game board. If there is no token at the spot it should return a blank space character ? ?.]
     * @pre none
     * @post empty the 1st player and the 2nd player
     */
    public char whatsAtPos(cpsc2150.extendedConnectX.BoardPosition pos);

    /**
     *
     * @param pos is position being read
     * @param player is character of a player
     * @pre [pos has a row and a column, and the character exist on the board]
     * @post [true iff pos on the board is available otherwise false]
     * @return returns true if the player is at that position, or else return false.]
     */

    default boolean isPlayerAtPos(cpsc2150.extendedConnectX.BoardPosition pos, char player){
        return this.whatsAtPos(pos) == player;
    }
}



