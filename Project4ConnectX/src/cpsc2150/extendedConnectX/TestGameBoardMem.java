package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project4 ConnectX
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGameBoardMem
{
    private IGameBoard newgameBoard(int row, int col, int numToWin)
    {
        return new GameBoardMem(row, col, numToWin);
    }

    private String newBoard(char[][] board, int r, int c)
    {
        String newBoard = "\n";
        for (int i = 0; i < c; i++)
        {
            if(i >= 10)
            {
                newBoard = newBoard + "|" + i;
            }
            else
            {
                newBoard = newBoard + "|" + ' ' + i;
            }
        }
        newBoard += "|\n";
        for (int j = r - 1; j >= 0; j--) {
            for (int k = 0; k < c; k++) {
                board[j][k] = ' ';
                char token = board[j][k];
                if(token != ' ')
                {
                    newBoard += ("|" + token + ' ');
                }
                else
                {
                    newBoard += ("|" + token + token);
                }

            }
            newBoard += ("|\n");
        }
        return newBoard;
    }

    @Test
    public void testConstructor_Std()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(6,7,4);
        char[][] CharArray = new char[6][7];
        String S = newBoard(CharArray,6, 7);
        assertEquals(gameBoard.toString(),S);
    }

    @Test
    public void testConstructor_Max()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(100, 100, 25);
        char[][] CharArray = new char[100][100];
        String S = newBoard(CharArray,100,100);
        assertEquals(gameBoard.toString(),S);
    }

    @Test
    public void testConstructor_Min()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(3, 3, 3);
        char[][] CharArray = new char[3][3];
        String S = newBoard(CharArray,3,3);
        assertEquals(gameBoard.toString(),S);
    }

    //Checking if there are no tokens in that specific column
    @Test
    public void testCheckIfFree_Blank_Board()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(6, 7, 4);
        assertEquals(gameBoard.checkIfFree(0), true);
    }

    //Checking if there is only one token in that specific column
    @Test
    public void testCheckIfFree_One_Token()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(6, 7, 4);
        gameBoard.placeToken('X',0);
        assertEquals(gameBoard.checkIfFree(0), true);
    }

    //Checking if the whole column is full
    @Test
    public void testCheckIfFree_Full_Column()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(6, 7, 4);
        for(int i = 0; i < 6; i++)
        {
            gameBoard.placeToken('X', 0);
        }
        assertEquals(gameBoard.checkIfFree(0), false);
    }

    //Testing horizontal win while the last token placed is in the farthest row and the tokens are being counted in
    //decrementing order
    @Test
    public void testHorizontalWin_Decrement()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(6, 7, 4);
        for(int i = 6; i > 2; i--)
        {
            gameBoard.placeToken('X', i);
        }
        BoardPosition pos = new BoardPosition(0, 6);
        assertEquals(gameBoard.checkHorizWin(pos, 'X'), true);
    }

    //Testing horizontal win based on the left and right side of the current position of the token placed
    @Test
    public void testHorizontalWin_Mid()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(10, 10, 4);
        for(int i = 5; i < 9; i++)
        {
            gameBoard.placeToken('X', i);
            if(i == 7)
            {
                gameBoard.placeToken(' ', i);
            }
        }
        gameBoard.placeToken('X', 7);
        BoardPosition pos = new BoardPosition(0, 5);
        assertEquals(gameBoard.checkHorizWin(pos, 'X'), true);
    }

    //Testing the horizontal win based on the num tokens of the same character being place in a row
    @Test
    public void testHorizontalWin_Num_Win()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(6, 7, 4);
        for(int i = 2; i < 6; i++)
        {
            gameBoard.placeToken('X', i);
        }
        BoardPosition pos = new BoardPosition(0, 2);
        assertEquals(gameBoard.checkHorizWin(pos, 'X'), true);
    }

    //Testing the horizontal win based on a different char being added to it at the end
    @Test
    public void testHorizontalWin_False()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(10,10,7);
        for(int i = 2; i < 8; i++)
        {
            gameBoard.placeToken('X', i);
        }
        gameBoard.placeToken('O',8);
        BoardPosition pos = new BoardPosition(0,2);
        assertEquals(gameBoard.checkHorizWin(pos,'X'),false);

    }

    //Testing vertical win in the left most column
    @Test
    public void testVerticalWin_LeftMost()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(5,8,4);
        for(int i = 0; i < 4; i++)
        {
            gameBoard.placeToken('X', 0);
        }
        BoardPosition pos = new BoardPosition(3,0);
        assertEquals(gameBoard.checkVertWin(pos, 'X'), true);

    }

    //Testing vertical win when it does not return true when it encounters a different token at the top of the column
    @Test
    public void testVerticalWin_False()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(5,8,4);
        for(int i = 0; i < 3; i++)
        {
            gameBoard.placeToken('X', 0);
        }
        gameBoard.placeToken('O', 0);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(gameBoard.checkVertWin(pos, 'X'), false);
    }

    @Test
    public void testCheckVerticalWin_Top()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(5,8,3);
        for(int i = 0; i <= 4; i++)
        {
            if(i == 1)
            {
                gameBoard.placeToken('O', 0);
            }
            else
            {
                gameBoard.placeToken('X', 0);
            }
        }
        BoardPosition pos = new BoardPosition(4,0);
        assertEquals(gameBoard.checkVertWin(pos, 'X'), true);
    }

    @Test
    public void testVerticalWin_Equal_To_Board_Height()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(5,5,5);
        for(int i = 0; i < 5; i++)
        {
            gameBoard.placeToken('X', 4);
        }
        BoardPosition pos = new BoardPosition(0,4);
        assertEquals(gameBoard.checkVertWin(pos, 'X'), true);
    }

    //Checking diagonal win by starting at the bottom left of board and going to the right
    @Test
    public void testDiagonalWin_Bottom_Left_To_Right()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8,4);
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j <= i; j++)
            {
                gameBoard.placeToken('X', i);
            }
        }
        BoardPosition pos = new BoardPosition(0, 0);
        assertEquals(gameBoard.checkDiagWin(pos, 'X'), true);
    }

    //Checking diagonal win by starting at the bottom right of board and then going left
    @Test
    public void testDiagonalWin_Bottom_Right_To_Left()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8, 4);
        for(int i = 0; i < 4; i++)
        {
            for(int j = 7; j >= i; j--)
            {
                gameBoard.placeToken('X', j);
            }
        }
        BoardPosition pos = new BoardPosition(0, 7);
        assertEquals(gameBoard.checkDiagWin(pos, 'X'), true);
    }

    //Checking diagonal win by starting at the top left of the board and going down and to the right
    @Test
    public void testDiagonalWin_Top_Left_To_Down_Right()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8, 4);
        int x = 0;
        for(int i = 6; i > 2; i--)
        {
            for(int j = 0; j <= i; j++)
            {
                gameBoard.placeToken('X', x);
            }
            x++;
        }
        BoardPosition pos = new BoardPosition(6, 0);
        assertEquals(gameBoard.checkDiagWin(pos, 'X'), true);
    }

    //Checking diagonal win by starting at the top right of the board and going down and to the left
    @Test
    public void testDiagonalWin_Top_Right_To_Down_Left()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8, 4);
        for(int i = 7; i > 3; i--)
        {
            for(int j = 0; j < i; j++)
            {
                gameBoard.placeToken('X', i);
            }
        }
        BoardPosition pos = new BoardPosition(6, 7);
        assertEquals(gameBoard.checkDiagWin(pos, 'X'), true);
    }

    //Checking diagonal win if it is a empty board
    @Test
    public void testDiagonalWin_Empty_Board()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8, 4);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(gameBoard.checkDiagWin(pos, 'X'), false);
    }

    //Checking diagonal win if the last position in in the middle of the board and the num to win has to account for
    //both the right and left side
    @Test
    public void testDiagonalWin_Middle_Of_Board()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8, 4);
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                gameBoard.placeToken('X', i);
            }
        }
        BoardPosition pos = new BoardPosition(1, 2);
        assertEquals(gameBoard.checkDiagWin(pos, 'X'), true);
    }

    //Checking diagonal win to see if it returns false when there is a different character that doesnt match the
    //previous character
    @Test
    public void testDiagonalWin_False()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8, 4);
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                gameBoard.placeToken('X', i);
            }
        }
        for(int i = 3; i < 4; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                gameBoard.placeToken('O', i);
            }
        }
        BoardPosition pos = new BoardPosition(1, 2);
        assertEquals(gameBoard.checkDiagWin(pos, 'X'), false);
    }

    //Checking to see if the whole board is filled properly
    @Test
    public void testCheckTie_Full()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8,4);
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                gameBoard.placeToken('X', j);
            }
        }
        assertEquals(gameBoard.checkTie(), true);
    }

    //Check tie if there are characters in the sides and the bottom of the board
    @Test
    public void testCheckTie_Corners_And_Bottom()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8,4);
        for(int i = 0; i < 7; i++)
        {
            gameBoard.placeToken('X', 0);
            gameBoard.placeToken('X', 7);
        }
        for(int i = 1; i < 7; i++)
        {
            gameBoard.placeToken('X', i);
        }
        assertEquals(gameBoard.checkTie(),false);
    }

    //Check tie if there are no characters in the board and it is just filled with spaces instead
    @Test
    public void testCheckTie_Empty_Board()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8,4);
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                gameBoard.placeToken(' ', j);
            }
        }
        assertEquals(gameBoard.checkTie(),false);
    }

    //Check tie if the board is only half full
    @Test
    public void testCheckTie_Half_Filled_Board()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(7,8,4);
        for(int i = 0; i < 6; i+=2)
        {
            for(int j = 0; j < 7;  j++)
            {
                gameBoard.placeToken('X', j);
                gameBoard.placeToken('O', j);
            }
        }
        assertEquals(gameBoard.checkTie(),false);
    }

    //Testing whether a blank character will be returned when there is no token placed at all and it's before the
    //first player places their token
    @Test
    public void test_whatsAtPos_BlankChar()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(6,7,4);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(gameBoard.whatsAtPos(pos), ' ');
    }

    //Testing whether a certain token is in a certain position or not
    @Test
    public void test_whatsAtPos_Token()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        gameBoard.placeToken('X', 0);
        gameBoard.placeToken('O', 0);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(gameBoard.whatsAtPos(pos), 'X');
    }

    //Checking the top row of board
    @Test
    public void test_whatsAtPos_Top_Of_Board()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        for(int i = 7; i < 8; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(j == 1|| j == 3|| j == 5 || j == 7)
                {
                    gameBoard.placeToken('X', i);
                }
                else
                {
                    gameBoard.placeToken('O', i);
                }
            }
        }
        BoardPosition pos = new BoardPosition(7,7);
        assertEquals(gameBoard.whatsAtPos(pos), 'X');
    }

    //Checking the top right of the board to make sure that the board isn't inverted with not equals
    @Test
    public void test_whatsAtPos_Top_Right_Of_Board()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        for(int i = 0; i < 8; i++)
        {
            gameBoard.placeToken('X', 8);
        }
        BoardPosition pos = new BoardPosition(7,0);
        assertNotEquals(gameBoard.whatsAtPos(pos), 'X');
    }

    @Test
    public void test_whatsAtPos_Inverted_Columns()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        gameBoard.placeToken('X', 8);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(gameBoard.whatsAtPos(pos), ' ');
    }

    //Testing whether a blank character will be returned when there is no token placed at all and it's before the
    //first player places their token
    @Test
    public void test_isPlayerAtPos_BlankChar()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(6,7,4);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(gameBoard.isPlayerAtPos(pos,' '), false);
    }

    //Testing whether a certain token is in a certain position or not
    @Test
    public void test_isPlayerAtPos_Token()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        gameBoard.placeToken('X', 0);
        gameBoard.placeToken('O', 0);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(gameBoard.isPlayerAtPos(pos, 'X'), true);
    }

    //Checking the top row of board
    @Test
    public void test_isPlayerAtPos_Top_Of_Board()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        for(int i = 7; i < 8; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(j == 1|| j == 3|| j == 5 || j == 7)
                {
                    gameBoard.placeToken('X', i);
                }
                else
                {
                    gameBoard.placeToken('O', i);
                }
            }
        }
        BoardPosition pos = new BoardPosition(7,7);
        assertEquals(gameBoard.isPlayerAtPos(pos, 'X'), true);
    }

    //Checking the top right of the board to make sure that the board isn't inverted with not equals
    @Test
    public void test_isPlayerAtPos_Top_Right_Of_Board()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        for(int i = 0; i < 8; i++)
        {
            gameBoard.placeToken('X', 8);
        }
        BoardPosition pos = new BoardPosition(7,0);
        assertNotEquals(gameBoard.isPlayerAtPos(pos, 'X'), true);
    }
    //checking the center of the board
    @Test
    public void test_isPlayerAtPos_CenterOftheBoard()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        for(int i = 0; i < 8; i++){
            gameBoard.placeToken('X', 4);
        }
        BoardPosition pos = new BoardPosition(5,0);
        System.out.println(gameBoard.toString());
        assertNotEquals(gameBoard.isPlayerAtPos(pos, 'X'), true);
    }

    @Test
    public void placeToken_Empty_Board()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        gameBoard.placeToken('X',0);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(gameBoard.isPlayerAtPos(pos, 'X'),true);
    }

    @Test
    public void placeToken_While_Already_Existing_Tokens()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        gameBoard.placeToken('X', 0);
        gameBoard.placeToken('X', 0);
        BoardPosition pos = new BoardPosition(1,0);
        assertEquals(gameBoard.isPlayerAtPos(pos, 'X'),true);
    }

    @Test
    public void placeToken_Alternate_Characters_Same_Column()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        gameBoard.placeToken('X', 0);
        gameBoard.placeToken('O', 0);
        BoardPosition pos = new BoardPosition(1,0);
        assertEquals(gameBoard.isPlayerAtPos(pos, 'O'),true);
    }

    @Test
    public void placeToken_Alternate_Characters_Same_Row()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        gameBoard.placeToken('X', 0);
        gameBoard.placeToken('O', 1);
        BoardPosition pos = new BoardPosition(0,1);
        assertEquals(gameBoard.isPlayerAtPos(pos, 'O'),true);
    }

    @Test
    public void placeToken_Last_Spot_In_Column()
    {
        IGameBoard gameBoard;
        gameBoard = newgameBoard(8,9,4);
        for(int i = 0; i < 8; i++)
        {
            gameBoard.placeToken('X', 0);
            BoardPosition pos = new BoardPosition(i, 0);
            assertEquals(gameBoard.isPlayerAtPos(pos, 'X'), true);
        }
    }


}
