package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project2 ConnectX
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
 */
import java.util.*;
/**
 * @invariants 0 <= row <= maxRow and 0 <= col <= maxCol
 */
public interface IGameBoard {

    public final int maxRow = 6;
    public final int maxCol = 9;
    public final int numToWin = 5;


    //returns the number of rows in the GameBoard
    /**
     * @pre none
     * @post maxRow = 6
     * @return the number of rows
     */
    default int getNumRows(){
        return maxRow;
    }
    /**
     * @pre none
     * @post maxCol = 9
     * @return the number of columns
     */
    //returns the number of columns in the GameBoard
    default int getNumColumns(){
        return maxCol;
    }
    /**
     * @pre none
     * @post numToWin = 5
     * @return the number of tokens in a row to win for the player
     */
    //returns the number of tokens in a row needed to win the game
    default int getNumToWin(){
        return numToWin;
    }

    /**
     * @pre 0 <= col <= maxCol
     * @post boolean
     * @param c is col
     * @return True iff there is free space to insert player token otherwise false
     */
    default boolean checkIfFree(int c){
        boolean free = false;

        for (int i = 0; i < maxRow; i++) {

            if (this.whatsAtPos(new BoardPosition(i,c))== ' ') {
                free = true;

            } else {
                break;
            }
        }
        return free;
    }

    /**
     * @pre [player tokens is equal to numToWin]
     * @post [player win the game either horizontally, vertically or diagonally]
     * @param c is the column
     * @return True iff player has won horizontally, vertically or diagonally 5 in a row otherwise false
     */
    default boolean checkForWin(int c){
        int row = 0;
        int i = 0;
        if(checkIfFree(c)) {
            while(this.whatsAtPos(new BoardPosition(i,c)) == ' ') {
                i++;
            }
            row = i;
        }
        BoardPosition pos = new BoardPosition(row, c);

        return checkDiagWin(pos, this.whatsAtPos(pos)) || checkHorizWin(pos, this.whatsAtPos(pos)) || checkVertWin(pos, this.whatsAtPos(pos));
    }

    /**
     * @pre [almost all the free places are taken in the gameboard]
     * @post [players are tied]
     * @return True iff checkIfFree is full otherwise false
     */
    default boolean checkTie(){
        int count = 0;
        for (int i = 0; i < maxCol; i++) {
            if (!checkIfFree(i)) {
                count+=6;
            }
        }
        return count == (maxRow*maxCol);
    }

    /**
     * @pre c is col 0 <= col <= maxCol, p = 'X' or 'O'
     * @post c is col >=1, p = 'X' or 'O'
     * @param p is the player char = 'X', 'O'
     * @param c is the comlumn of the game board
     */
   public void placeToken(char p, int c);
    /**
     * @pre 0 <= row <= maxRow, p = 'X' or 'O'
     * @post row >= maxRow, p = 'X' or 'O'
     * @param pos is position most recently marked
     * @param p is the player token
     * @return True if numToWin in a row horizontally
     */
    default boolean checkHorizWin(BoardPosition pos, char p){
        int counter = 0;
        for (int i = 0; i <= pos.getColumn(); i++) {
            if (isPlayerAtPos(new BoardPosition(pos.getRow(),i),p))
                counter++;
            else
                counter = 0;
        }
        return counter == getNumToWin();
    }

    /**
     * @pre 0 <= col <= maxCol, p = 'X' or 'O'
     * @post col >= maxCol, p = 'X' or 'O'
     * @param pos is position most recently marked
     * @param p is the player token
     * @return True iff numToWin in a row vertically otherwise false
     */
    default boolean checkVertWin(BoardPosition pos, char p){
        int counter = 0;
        for (int i = 0; i < getNumRows(); i++) {
            if (isPlayerAtPos(new BoardPosition(i , pos.getColumn()),p))
                counter++;

        }
        return counter == getNumToWin();
    }
//layer abstraction
    /**
     * @pre 0 <= row <= maxRow, 0 <= col <= maxCol, p = 'X' or 'O'
     * @post [player wins iff it is numToWin in a row diagonally], P = 'X' or 'O'
     * @param pos is position most recently marked
     * @param p is the player token
     * @return True iff numToWin in a row diagonally in left or right direction otherwise false
     */
    default boolean checkDiagWin(BoardPosition pos, char p){
        int count = 1;
        int index = 1;
        // checks spaces below and to the left
        while(pos.getRow() - index >= 0 && pos.getColumn() - index >= 0){
            if(isPlayerAtPos(new BoardPosition(pos.getRow()-index, pos.getColumn() - index),p)){
                count++;
                if(count == getNumToWin())
                    return true;
            } else
                break;
            index++;
        }
        index = 1;
        // checks spaces above and to the right
        while(pos.getRow()+index < getNumRows() && pos.getColumn()+index < getNumColumns()){
            if(isPlayerAtPos(new BoardPosition(pos.getRow()+index,pos.getColumn()+index),p)){
                count++;
                if (count == getNumToWin())
                    return true;
            }
            else
                break;
            index++;
        }
        //checks spaces above and to the left
        while(pos.getRow() + index < getNumRows() &&pos.getColumn() - index >= 0){
            if(isPlayerAtPos(new BoardPosition(pos.getRow()+index, pos.getColumn()-index),p)){
                count++;
                if (count == getNumToWin())
                    return true;
            }
            else
                break;
            index++;
        }
        index = 1;
        //checks spaces below and to the right
        while(pos.getRow() - index >= 0 && pos.getColumn() + index < getNumColumns()) {
            if(isPlayerAtPos(new BoardPosition(pos.getRow()-index, pos.getColumn()+index),p)) {
                count++;
                if (count == getNumToWin())
                    return true;
            } else
                break;
            index++;
        }
        return false;
    }

    /**
     * @param pos the position being stored or read
     * @return player = 'X', 'O' or ' '
     * @pre none
     * @post [pos is in range of gameboard]
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     *
     * @param pos is position being read
     * @param player is character of a player
     * @pre [pos has a row and a column]
     * @post [pos on the board is available]
     * @return [the position of the player]
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player){
        return this.whatsAtPos(pos) == player;
    }
}



