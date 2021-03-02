package cpsc2150.extendedTicTacToe;
import java.util.*;
/**
 * @Correspondences
 * ROW_SIZE = r
 * COL_SIZE = c
 * NUM_TO_WIN = w
 */

/**
 * @invariants
 * 3 <= ROW_SIZE<=20
 * 3<=COL_SIZE<=20
 * 3<= NUM_TO_WIN <= 20 && NUM_TO_WIN <=ROW_SIZE && NUM_TO_WIN <= COL_SIZE
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{
    private char[][]grid;

    static int MaxRow = 100;
    static int MaxCol = 100;
    static int MaxWinSize = 25;
    static int ROW_SIZE;
    static int COL_SIZE;
    static int NUM_TO_WIN;


    public GameBoard(int r, int c ,int w)
    {
        //constructor
        ROW_SIZE = r;
        COL_SIZE = c;
        NUM_TO_WIN = w;
        grid = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = ' ';
            }
        }
    }



    @Override
    public int getNumRows() {
        return ROW_SIZE;
    }

    @Override
    public int getNumColumns() {
        return COL_SIZE;
    }

    @Override
    public int getNumToWin() {
        return NUM_TO_WIN;
    }
    /**
     *
     * @param marker is position to place player's character
     * @param player is character of player taking turn
     * @return void
     * @pre marker is in range of 8x8 array and unoccupied
     * @post player is assigned to index at marker
     */
    public void placeMarker(BoardPosition marker, char player)
    {
//places the character in marker on the position specified by
        //marker, and should not be called if the space is not available.
        grid[marker.getRow()][marker.getColumn()] = player;
    }




    /**
     *
     * @return boolean = true or false
     * @pre neither player has won
     */
    @Override
    public boolean checkForDraw() {
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                if (grid[i][j] == ' ')
                    return false;
            }

        }
        return true;
    }


    /**
         *
         * @param pos is position being read
         * @return char = X, O, or blank
         * @pre pos is in range of 8x8 array
         */
    @Override
    public char whatsAtPos(BoardPosition pos)
    {
//returns what is in the GameBoard at position pos
//If no marker is there it returns a blank space char ‘ ‘
        return grid[pos.getRow()][pos.getColumn()];
    }



}
