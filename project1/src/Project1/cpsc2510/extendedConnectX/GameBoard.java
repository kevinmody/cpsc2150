/**
 * Gameboard contains all the main methods used by
 * The GameScreen.java
 * moves is incremented every turn and board contains player char
 * This class is bounded by board and moves
 */
package cpsc2510.extendedConnectX;
//Author: Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 02/07/2021

public class GameBoard {

    private final char[][] board; //p = empty, X = cross, O = circle.
    private final int[] moves = new int[1];

    public GameBoard(){
        moves[0] = 0;
        board = new char[6][9];
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 6; x++) {
                board[x][y] = 'p'; //Board initially empty
            }
        }
    }

    public GameBoard(GameBoard from, BoardPosition position, char sign){
        board = new char[6][9];
        for(int y = 0; y < 9; y++)
            for(int x = 0; x < 6; x++)
                board[x][y] = from.board[x][y];
        board[position.getColumn()][position.getRow()] = sign;
    }

    public boolean checkTie() {
        return moves[0] == 45;
    }

    public boolean checkHorizWin(BoardPosition pos, char p) {
        int x,y, plays = 0;
        for(x=0;x<6;x++){
            for(y=0;y<9;y++) {
                if(board[x][y] != p) {
                    break;
                }else {
                    plays += 1;
                }
            }
            if(plays==4) {
                return true;
            }
        }
        return false;
    }
    public boolean checkVertWin(BoardPosition pos, char p) {
        int x,y, plays = 0;
        for(x=0;x<9;x++){
            for(y=0;y<6;y++) {
                if(board[y][x] != p) {
                    break;
                }else {
                    plays += 1;
                }
            }
            if(plays==4) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagWin(BoardPosition pos, char p) {
        //main
        for (int row = 0; row < board.length - 3; row++)
        {
            for (int col = 0; col < board[row].length - 3; col++)
            {
                if (p == board[row + 1][col + 1] &&
                        p == board[row + 2][col + 2] &&
                        p == board[row + 3][col + 3])
                {
                    return true;
                }
            }
        }
        //counter
        for (int row = 0; row < board.length - 3; row++)
        {
            for (int col = 3; col < board[row].length; col++)
            {
                if (p == board[row + 1][col - 1] &&
                        p == board[row + 2][col - 2] &&
                        p == board[row + 3][col - 3])
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfFree(int c) {
        //Iterating over board
        for(int i=0; i<9; i++)
        {
            if(board[c][i] == 'p') {
                return false;
            }
        }
        return true;
    }

    public void placeToken(char p, int c){
        for(int i=0; i<9; i++)
        {
            if(board[c][i] == 'p') {
                board[c][i] = p;
                moves[0] += 1;
                return;
            }
        }
    }

    public boolean checkForWin(int c){

        int x,y;
        for(x = c; x < 6; x++) {
            for(y = 0; y < 9; y++) {

                BoardPosition pos = new BoardPosition(x, y);
                char p = whatsAtPos(pos);
                if(isPlayerAtPosition(pos, p)) {
                    return checkVertWin(pos, p) || checkDiagWin(pos, p) || checkHorizWin(pos, p);
                }else {
                    break;
                }
            }
        }
        return false;

    }

    public char whatsAtPos(BoardPosition pos){
        return board[pos.getColumn()][pos.getRow()];
    }

    public boolean isPlayerAtPosition(BoardPosition pos, char player){
        if(board[pos.getColumn()][pos.getRow()] == player) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder retString = new StringBuilder("\n");
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 6; x++) {
                if(x == 0) {
                    if ((board[x][y] == 'X') || (board[x][y] == 'O')) {
                        retString.append("|").append(board[x][y]).append("|");
                    } else {
                        retString.append("| |");
                    }
                }else {
                    if ((board[x][y] == 'X') || (board[x][y] == 'O')) {
                        retString.append(board[x][y]).append("|");
                    } else {
                        retString.append(" |");
                    }
                }
            }
            retString.append("\n");
        }
        return retString.toString();
    }
}