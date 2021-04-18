package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project4 ConnectX
/**
 * @invariants numTowin < numRows && numTowin < numCols
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {

    private final char[][] board;
    private final int numRows;
    private final int numCols;
    private final int numTokensToWin;

    public GameBoard(int rows, int column, int win){

        numRows = rows;
        numCols = column;
        numTokensToWin = win;
        board = new char[rows][column];

        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < column; k++) {
                board[j][k] = ' ';
            }
        }
    }
    public int getNumRows() {
        return numRows;
    }
    public int getNumColumns() {
        return numCols;
    }
    public int getNumToWin() {
        return numTokensToWin;
    }

    public void placeToken(char p, int c) {
        /*
        places a token p in column c on the game
        board. The token will be placed in the lowest available row in column c.
         */
        if (board[0][c] == ' ') {
            board[0][c] = p;
        } else {
            //If the added token is not in the bottom row
            for (int i = 1; i < getNumRows(); i++) {
                if (board[i][c] == ' ') {
                    board[i][c] = p;
                    break;
                }
            }
        }
    }
        public char whatsAtPos(BoardPosition pos){
        /*
        returns the char that is in position pos of
        the game board. If there is no token at the spot it should return a blank space character: ‘ ’.
         */
            return board[pos.getRow()][pos.getColumn()];
        }
    }

