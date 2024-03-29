package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project5 ConnectX
/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Project 4
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */
public class ConnectXController {

    //our current game that is being played
    private final IGameBoard curGame;

    //The screen that provides our view
    private final ConnectXView screen;

    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but

    private final int numPlayers;
    private int player;
    private final char[] playerTokens = new char[] {'X', 'O', 'E', 'H', 'Y', 'A', 'P', 'U', 'J', 'M'};
    private boolean finishGame;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np) {
        this.curGame = model;
        this.screen = view;
        numPlayers = np;

    }

    /**
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {
        int row = 0;
        if(finishGame) {
            this.newGame();
        }//starting the game here
        else{
            BoardPosition pos;
            if(!curGame.checkIfFree(col)){//to see if there is a free space in the column to put token if not move on to next column
                screen.setMessage("This column is full, please pick again.");
                return;
            }
            //getting rows for player position
            for(int i = 0; i < curGame.getNumRows(); i++){
                pos = new BoardPosition(i, col);
                if(curGame.whatsAtPos(pos) == ' '){
                    row = i;
                    break;
                }
            }
            BoardPosition newPos = new BoardPosition(row, col);
            //getting tokens from the player in the columns
            curGame.placeToken(playerTokens[player], col);
            screen.setMarker(newPos.getRow(), col, playerTokens[player]);
            //check to see if there is a win or a tie, if one of the cases are true play again or exit the game
            if(curGame.checkForWin(col)){
                screen.setMessage("Player " + playerTokens[player] + " wins! Press any button to play again.");
                finishGame = true;
            }//checking tie here
            else if(curGame.checkTie()){
                screen.setMessage("It's a tie! Press any button to play again.");
                finishGame = true;
            }
            else{//switching with the players turn
                player++;
                if(player >= numPlayers)
                    player = 0;
                screen.setMessage("It is " + playerTokens[player] + "'s turn. ");
            }
        }
    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame()
    {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
