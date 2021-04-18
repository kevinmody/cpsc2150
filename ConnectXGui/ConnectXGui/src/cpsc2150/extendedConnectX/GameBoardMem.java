package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project4 ConnectX
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

    public class GameBoardMem extends AbsGameBoard {
        Map<Character, List<BoardPosition>> mapBoard = new HashMap<Character, List<BoardPosition>>();
        int maxRow;
        int maxCol;
        int numToWin;

        public GameBoardMem(int pRow, int pCol, int pWin) {
            maxRow = pRow;
            maxCol = pCol;
            numToWin = pWin;
        }
        /**
         * @return row length
         */
        public int getNumRows() {
            return maxRow;
        }

        /**
         * @return column height
         */
        public int getNumColumns() {
            return maxCol;
        }

        /**
         * @return number needs for a win
         */
        public int getNumToWin() {
            return numToWin;
        }

        
        public void placeToken(char p, int c){

            if(!mapBoard.containsKey(p)) {
                List<BoardPosition> playerPositions = new ArrayList<>();
                mapBoard.put(p, playerPositions);
            }
            for (int r = 0; r < getNumRows(); r++){
                if (whatsAtPos(new BoardPosition(r,c)) == ' ') {
                    mapBoard.get(p).add(new BoardPosition(r,c));
                    break;
                }
            }
        }

        /**
         * @param pos the position being stored or read
         * @return [returns the char that is in position pos of the game board. If there is no token at the spot it should return a blank space character ? ?.]
         * @pre none
         * @post empty the 1st player and the 2nd player
         */
        public char whatsAtPos(BoardPosition pos) {
            for (Map.Entry<Character, List<BoardPosition>> map : mapBoard.entrySet()) {
                if (isPlayerAtPos(pos, map.getKey())) {
                    return map.getKey();
                }
            }
            return ' ';
        }

        /**
         * @param pos is position being read
         * @param player is character of a player
         * @pre [pos has a row and a column, and the character exist on the board]
         * @post [true iff pos on the board is available otherwise false]
         * @return returns true if the player is at that position, or else return false.]
         */
        @Override
        public boolean isPlayerAtPos(BoardPosition pos, char player){
            if (!mapBoard.containsKey(player)){
                return false;
            }
            for (BoardPosition check : mapBoard.get(player)){
                if (check.equals(pos)){
                    return true;
                }
            }
            return false;
        }
    }
