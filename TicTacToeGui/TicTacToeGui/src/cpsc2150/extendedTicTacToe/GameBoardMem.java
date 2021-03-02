package cpsc2150.extendedTicTacToe;
import javafx.beans.binding.MapBinding;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
public class GameBoardMem extends AbsGameBoard implements IGameBoard {
   /* static int MaxRow = 100;
    static int MaxCol = 100;
    static int MaxWinSize = 25;
    */

    static Map<Character, List<BoardPosition>> mapBoard;
    static int ROW_SIZE;
    static int COL_SIZE;
    static int NUM_TO_WIN;

    /**
     * @param pRow is the length of rows
     * @param pCol is the height of columns
     * @param pWin the number needs for a win
     */

    public GameBoardMem(int pRow, int pCol, int pWin) {
        ROW_SIZE = pRow;
        COL_SIZE = pCol;
        mapBoard = new HashMap<>();
        NUM_TO_WIN = pWin;
    }

    /**
     * @param p is the player character
     * @param b is the board position being taken
     * @pre whatsAtPos(b) = ' '
     * @post whatsAtPos(b) = p
     */


    /**
     * @return row length
     */
    public int getNumRows() {
        return ROW_SIZE;
    }

    /**
     * @return column height
     */
    public int getNumColumns() {
        return COL_SIZE;
    }

    /**
     * @return number needs for a win
     */
    public int getNumToWin() {
        return NUM_TO_WIN;
    }



    @Override
    public void placeMarker(BoardPosition b, char p) {
        if (!mapBoard.containsKey(p)) {
            List<BoardPosition> list = new ArrayList<>();
            list.add(b);
            mapBoard.put(p, list);
        } else {
            mapBoard.get(p).add(b);
        }
    }

    /**
     * @param pos is the board position being checked
     * @return
     */
    @Override
    public char whatsAtPos(BoardPosition pos) {
        for (Map.Entry<java.lang.Character, List<BoardPosition>> entry : mapBoard.entrySet()) {
            List<BoardPosition> l = entry.getValue();
            for (BoardPosition b : l) {
                if (b.equals(pos)) {
                    return entry.getKey();
                }
            }
        }
        return ' ';
    }

    /**
     * @param pos    is the board position
     * @param player is the character search for
     * @return
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (!mapBoard.containsKey(player)) {
            return false;
        } else {
            return mapBoard.get(player).contains(pos);
        }
    }
}

