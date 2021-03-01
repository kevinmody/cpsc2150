/**
 * GameScreen contains main class and helper functions
 * displays connect 4 containing X & O of corresponding player moves
 * This class is bounded by board, gamemover, changeTurn, player
 */
package cpsc2510.extendedConnectX;
//Author: Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 02/07/2021
import java.util.*;

public class GameScreen {
    private static final Scanner in = new Scanner(System.in);
    private static GameBoard board = new GameBoard();

    private static boolean gameOver = false;
    private static boolean changeTurn = true;
    private static char player = 'X';

    public static void main(String[] args) {
        System.out.println(board);
        while (!gameOver) {

            if (!changeTurn) {
                player = 'O';
            }
            int col = move(player);
            changeTurn = !changeTurn;
            System.out.println(board);

            gameControl(player, col);

        }
    }

    private static void gameControl(char player,int col){
        if(board.checkTie()){
            System.out.println("It's a Tie!");
            goAgain();
        }else if(board.checkForWin(col)) {
            System.out.println("Player " + player + " Won!");
            goAgain();
        }
    }

    private static void goAgain() {
        char redo = 'x';
        while (true) {
            try {
                redo = in.next().charAt(0);
            } catch (NumberFormatException e) {
            }
            System.out.println("Would you like to play again? Y/N");
            if('Y' <= redo) {
                board = new GameBoard();
                break;
            }else if(redo <= 'N') {
                gameOver = true;
                break;
            }
        }
    }
    public static int move(char player) {
        while (true) {
            int col = getColumnInput();

            System.out.println("Player" + player + ", what column do you want to place your marker in?");

            if (board.checkIfFree(col)) {
                System.out.println("Column is full");
            }else{
                board.placeToken(player, col);
                return col;
            }
        }

    }

    private static int getColumnInput() {
        int redo = -1;
        while (true) {
            try {
                redo = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
            }
            if (redo < 0){
                System.out.print("\nColumn cannot be less than 0");
            }else if (redo > 6) {
                System.out.print("\nColumn cannot be greater than 6");
            }else {
                break;
            }
        }
        return redo;
    }
}