package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project2 ConnectX

import java.util.*;
//correspondences
/**
 * @invariants 0 <= row <= 6 and 0 <= col <= 9
 * @invariants numTowin < numRows && numTowin < numCols
 *
 * @correspondences: maxRow -
 *                   maxCol
 */
public class GameBoard extends AbsGameBoard{

    private final char[][] board;

    /**
     * @pre 0 <= row <= 6 and 0 <= col <= 9, [players = 'X' and 'O' which starts with player 'X']
     * @post [Board is created and Board is empty]
     */
    public GameBoard() {
        board = new char[maxRow][maxCol];
        for (int i = 0; i <= maxRow-1; i++) {
            for (int j = 0; j <= maxCol-1; j++) {
                board[i][j] = ' ';
            }
        }

    }

    /**
     * @param p is the player char = 'X', 'O'
     * @param c is the comlumn of the game board
     * @pre c is col 0 <= col <= maxCol, p = 'X' or 'O'
     * @post c is col >=1, p = 'X' or 'O'
     */
    public void placeToken(char p, int c) {
        /*
        places a token p in column c on the game
        board. The token will be placed in the lowest available row in column c.
         */
        if(checkIfFree(c)) {
            for(int i = getNumRows()-1; i >= 0; i--) {
                if(board[i][c] == ' ') {
                    board[i][c] = p;
                    break;
                }
            }
        }
    }

        /**
         * @pre none
         * @post [pos is in range of gameboard]
         * @param pos the position being stored or read
         * @return player = 'X', 'O' or ' '
         */
        public char whatsAtPos(BoardPosition pos){
        /*
        returns the char that is in position pos of
        the game board. If there is no token at the spot it should return a blank space character: ‘ ’.
         */
            return board[pos.getRow()][pos.getColumn()];
        }

    }

