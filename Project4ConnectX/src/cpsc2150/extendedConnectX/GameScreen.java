package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project4 ConnectX
import java.util.*;
import static cpsc2150.extendedConnectX.IGameBoard.minRowsCols;
import static cpsc2150.extendedConnectX.IGameBoard.maxRowsCols;
import static cpsc2150.extendedConnectX.IGameBoard.maxNumToWin;

public class GameScreen {
    public static final int minPlayers = 2;
    public static final int maxPlayers = 10;
    public static void main(String[] args) {
        System.out.println("Welcome to ConnectX Game :)\n");
        Scanner scanner = new Scanner(System.in);
        int rows;
        int column;
        int win;
        int indexPlayer = 0;
        int input;

        boolean gameMode = true;
        while(gameMode) {
            boolean wins = false;
            boolean ties = false;
            //Two players required for the game and equal to or less than ten (Error Checking)
            do {
                //Getting total number of players for the game
                System.out.println("How many players?");
                input = scanner.nextInt();
                scanner.nextLine();

                if (input < minPlayers) {
                    System.out.println("Must be at least 2 players");
                } else if (input > maxPlayers) {
                    System.out.println("Must be 10 players or fewer");
                }
            } while (input < minPlayers || input > maxPlayers);

            //Asking the players for what character they want to choose for the game
            List<Character> play = new ArrayList<>(input);
            for (int i = 1; i <= input; i++) {
                System.out.println("Enter the character to represent player " + i);
                char playerMarker = Character.toUpperCase(scanner.next().charAt(0));
                while (play.contains(playerMarker)) {
                    System.out.println(playerMarker + " is already taken as a player token!");
                    System.out.println("Enter the character to represent player " + i);
                    playerMarker = Character.toUpperCase(scanner.next().charAt(0));
                }
                play.add(playerMarker);
            }

            //Number of rows
            do {
                System.out.println("How many rows should be on the board?");
                rows = scanner.nextInt();
                scanner.nextLine();
                //Has to be between 3 and 100 (Error Checking)
                if (rows < minRowsCols || rows > maxRowsCols) {
                    System.out.println("Please enter a  number between 3 and 100");
                }
            } while (rows < minRowsCols || rows > maxRowsCols);

            //Number of columns
            do {
                System.out.println("How many columns should be on the board?");
                column = scanner.nextInt();
                scanner.nextLine();
                //Has to be between 3 and 100 (Error Checking)
                if (column < minRowsCols || column > maxRowsCols) {
                    System.out.println("Please enter a  number between 3 and 100");
                }
            } while (column < minRowsCols || column > maxRowsCols);


            //Number of tokens to win
            do {
                System.out.println("How many in a row to win?");
                win = scanner.nextInt();
                scanner.nextLine();
                //Has to be in between 3 and 25 and it has to be less than numrows and numcols
                while ((win < minRowsCols || win > maxNumToWin) || ((win > rows) || (win > column))){
                    System.out.println("Please enter a valid number of tokens between " + minRowsCols + " and " + maxNumToWin + ".");
                    System.out.println("Also, make sure that the value entered to win is less than the number of columns and rows.");
                    System.out.println("How many in a row to win?");
                    win = scanner.nextInt();
                    scanner.nextLine();
                }
            }
            while ((win < minRowsCols || win > maxNumToWin) || (win > rows) || (win > column));



            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            char gameType = Character.toUpperCase(scanner.next().charAt(0));
            //Error Checking for game type
            while (gameType != 'F' && gameType != 'M' && gameType != 'f' && gameType != 'm') {
                System.out.println("Please enter a valid option.");
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                gameType = scanner.next().charAt(0);
            }

            IGameBoard newBoard = new cpsc2150.extendedConnectX.GameBoard(rows, column, win);
            if (gameType == 'F') {
                newBoard = new cpsc2150.extendedConnectX.GameBoard(rows, column, win);
            } else {
                newBoard = new cpsc2150.extendedConnectX.GameBoardMem(rows, column, win);
            }

            //Creating and printing the new the gamebaord
            System.out.println(newBoard.toString());

            //Only  enter if the game hasnt been won or isnt a tie
            while (!wins || !ties) {
                System.out.println("Player " + play.get(indexPlayer) + ", what column do you want to place your marker in?");
                //Getting the column to place the token
                int userInput = scanner.nextInt();
                //Ignores the new line character after pressing "ENTER" after user enters the column
                scanner.nextLine();
                if (userInput < 0 || userInput >= newBoard.getNumColumns()) {
                    System.out.println("Please enter a valid column");
                    continue;
                }
                if (!newBoard.checkIfFree(userInput)) {
                    System.out.println("Column was full. Choose again.");
                    continue;
                }

                //Calling the placeToken method
                newBoard.placeToken(play.get(indexPlayer), userInput);
                System.out.println(newBoard.toString());
                //Checking the two methods required if someone wins or the game ends in tie
                wins = newBoard.checkForWin(userInput);
                ties = newBoard.checkTie();


                if (wins || ties) {
                    //If the game ends in a win
                    if (wins) {
                        System.out.println("Player " + play.get(indexPlayer) + " won the game!");
                    }
                    //If game ends in a tie
                    else if (ties) {
                        System.out.println("The game was a tie.");
                    }
                    //Asking if the want to play the game again
                    System.out.println("Would you like to play again? Y/N");
                    //Storing the player's response
                    char replay = scanner.next().charAt(0);

                    //If there is a non valid string entered by the user
                    while (replay != 'Y' && replay != 'y' && replay != 'N' && replay != 'n') {
                        System.out.println("Please enter a valid option.");
                        System.out.println("Would you like to play again? Y/N");
                        replay = scanner.next().charAt(0);
                    }
                    //Choices at the end of the game
                    if ((replay == 'Y') || (replay == 'y')) {
                        indexPlayer = -1;
                        wins = true;
                        ties = true;
                        gameMode = true;
                    } else if ((replay == 'N') || (replay == 'n')) {
                        System.out.println("Thanks for playing the game! Have a nice day :)");
                        wins = true;
                        ties = true;
                        gameMode = false;
                    }

                }
                //Resetting the player turn to O
                if ((indexPlayer + 1) != input) {
                    indexPlayer++;
                } else {
                    indexPlayer = 0;
                }
            }
        }
    }
}
