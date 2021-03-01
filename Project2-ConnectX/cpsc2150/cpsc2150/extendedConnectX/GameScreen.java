package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project2 ConnectX


import java.util.*;
/**
 * @invariants 0 <= row <= maxRow and 0 <= col <= maxCol
 */
public class GameScreen {
    public static void main(String[] args){
        char player1,player2;
        System.out.println("Welcome to 6x9 ConnectX \n" +
                "To win you must get 5 in a row either vertically, horizontally or diagonally.\n" +
                "Would you like to play the game ? Yes/No");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if(input.toLowerCase().equals("yes")) {
            player1 = 'X';
            player2 = 'O';
            boolean playAgain = true;
            while(playAgain) {
                GameBoard board = new GameBoard();
                System.out.println(board);
                int turnNum = 1;
                System.out.println("Player " + player1 + ", please enter what column you would like to place your token.");
                input = scanner.nextLine();
                int colNum = Integer.parseInt(input);
                board.placeToken(player1, colNum);
                boolean status = false;
                while(!status) {
                    turnNum += 1;
                    System.out.println(board);
                    if ( (turnNum % 2) == 1) {
                        System.out.println("Player " + player1 + ", please enter what column you would like to place your token");
                        input = scanner.nextLine();
                        colNum = Integer.parseInt(input);
                        while(colNum < 0 || colNum > 8){
                            System.out.println("Player " + player1+ ", column cannot be less than 0 nor more than 8.");
                            System.out.println("Player " + player1 + ", please enter what column you would like to place your token");
                            input = scanner.nextLine();
                            colNum = Integer.parseInt(input);
                        }
                        board.placeToken(player1, colNum);

                    } else {
                        System.out.println("Player " + player2 + ", please enter what column you would like to place your token");
                        input = scanner.nextLine();
                        colNum = Integer.parseInt(input);
                        while (colNum < 0 || colNum > 8){
                            System.out.println("Player " + player2 + ", column cannot be less than 0 nor more than 8.");
                            System.out.println("Player " + player2 + ", please enter what column you would like to place your token");
                            input = scanner.nextLine();
                            colNum = Integer.parseInt(input);
                        }
                        board.placeToken(player2, colNum);
                    }
                    if(board.checkForWin(colNum) || board.checkTie()) {
                        status = true;
                        System.out.println(board);
                    }

                }
                if( (turnNum % 2) == 1 && !(board.checkTie())) {
                    System.out.println("Player " + player1 + ", you won!");


                } else if( (turnNum % 2) == 0 && !(board.checkTie())) {
                    System.out.println("Player " + player2 + ", you won!");
                } else {
                    System.out.println("No one won!");
                }
                System.out.println("Would you like to play again? Yes/No");
                input = scanner.nextLine();

                if(input.toLowerCase().equals("yes")){
                    board = new GameBoard();
                }
                else if(input.toLowerCase().equals("no")) {
                    playAgain = false;
                    System.out.println("Thanks for playing!");

                }
                else {
                    int i = 0;
                    while(i == 0){
                        System.out.println("Please enter again!");
                        input = scanner.nextLine();
                        if(input.toLowerCase().equals("yes")){
                            board = new GameBoard();
                            i++;
                        }
                        else if(input.toLowerCase().equals("no")) {
                            playAgain = false;
                            System.out.println("Thanks for playing!");
                            i++;
                        }

                }

                }
            }
        } else {
            System.out.println("You Entered: No\nThanks! See you next Time :)");
        }

    }

}
