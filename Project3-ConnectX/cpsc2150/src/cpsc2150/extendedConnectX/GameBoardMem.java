package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project3 ConnectX

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
            if(!mapBoard.containsKey(p)){
                mapBoard.put(p, new ArrayList<>());
            }
            for(int i = 0; i < getNumRows();i++){
                BoardPosition board = new BoardPosition(i, c);
                if(whatsAtPos(board)== ' '){
                    mapBoard.get(p).add(board);
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
            for (HashMap.Entry<Character, List<BoardPosition>> map : mapBoard.entrySet()) {
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
            if(!mapBoard.containsKey(player)){
                return false;
            }
            for(BoardPosition bp : mapBoard.get(player)){
                if(bp.equals(pos)){
                    return true;
                }
            }
            return false;
        }
    }
