package cpsc2150.extendedTicTacToe;
import java.util.*;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private final IGameBoard curGame;
    private final int numberOfPlayer;
    private static char player;
    //The screen that provides our view
    private final TicTacToeView screen;
    private boolean newMatch = false;
    private static char[] s;
    private static int playerIndex;
    public static final int MAX_PLAYERS = 10;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        curGame = model;
        screen = view;
        numberOfPlayer = np;
        // Some code is needed here.
        s = new char[np];
        s[0] = 'X';
        s[1] = 'O';
        if (np > 2) {
            s[2] = 'A';
        }
        if (np > 3) {
            s[3] = 'M';
        }
        if (np > 4) {
            s[4] = 'E';
            if (np > 5) {
                s[5] = 'J';
                if (np > 6) {
                    s[6] = 'K';
                    if (np > 7) {
                        s[7] = 'S';
                        if (np > 8) {
                            s[8] = 'V';
                            if (np > 9) {
                                s[9] = 'Z';
                            }
                        }
                    }
                }
            }
        }

        player = s[0];
        playerIndex = 0;
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        // Your code goes here.
        if (newMatch == true) {
            newGame();
        } else {
            BoardPosition b = new BoardPosition(row, col);

            if (curGame.checkSpace(b)) {

                //placing marker on model and view
                curGame.placeMarker(b, player);
                screen.setMarker(row, col, player);

                //prints winning message if move won the game
                if (curGame.checkForWinner(b, player)) {
                    screen.setMessage("Congratulations, Player " + player + "! You have won!");
                    newMatch = true;
                } else if (curGame.checkForDraw()) {
                    screen.setMessage("Draw!");
                    newMatch = true;//newGame();
                }
                //swaps user turn if not
                else {
                    if (player == s[numberOfPlayer - 1]) {
                        player = s[0];
                        playerIndex = 0;
                    } else {
                        player = s[playerIndex + 1];
                        playerIndex++;
                    }
                    screen.setMessage("It is " + player + "'s turn.");
                }
            }
            //Prints error if check space fails
            else {
                screen.setMessage("Position is already occupied. Choose another.");
            }
        }
    }
        private void newGame() {
            // You do not need to make any changes to this code.
            screen.dispose();
            GameSetupScreen screen = new GameSetupScreen();
            GameSetupController controller = new GameSetupController(screen);
            screen.registerObserver(controller);
        }
    }
