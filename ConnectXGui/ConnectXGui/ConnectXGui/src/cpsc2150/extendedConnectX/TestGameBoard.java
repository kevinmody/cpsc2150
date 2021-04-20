package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project5 ConnectX
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {
    private IGameBoard gb(int r, int c, int w) {return new GameBoard(r, c, w); }

    private String boardArrayString(char [][] boardArray, int row, int col){
        String display = "";

        for(int i = 0; i < col; i++){
            if(i < 10){
                display += "| " + i;
            }
            else{
                display += "|" + i;
            }
        }

        display += "|\n";

        for(int i = row - 1; i >= 0; i--){
            for(int j = 0; j < col; j++){
                display += "|" + boardArray[i][j] + " ";
            }

            display += "|\n";
        }

        return display;

    }
    //This test case is distinct because it tests if the constructor can make the smallest possible board
    @Test
    public void test_Constructor_smallest(){
        char[][] boardArray;
        boardArray = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(3, 3, 3);

        assertEquals(board.toString(), boardArrayString(boardArray, 3, 3));
        assertEquals(3, board.getNumToWin());
    }
    //This test case is distinct because it tests if the constructor can make the biggest possible board
    @Test
    public void test_Constructor_biggest(){
        char[][] boardArray;
        boardArray = new char[100][100];
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(100, 100, 25);

        assertEquals(board.toString(), boardArrayString(boardArray, 100, 100));
        assertEquals(25, board.getNumToWin());
    }
    //This test case is distinct because it tests if the constructor can make a board
    //with unequal rows and columns
    @Test
    public void test_Constructor_different(){
        char[][] boardArray;
        boardArray = new char[30][20];
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 20; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(30, 20, 3);

        assertEquals(board.toString(), boardArrayString(boardArray, 30, 20));
        assertEquals(3, board.getNumToWin());
    }
    //This test case is distinct because it represents a standard test of a free column
    @Test
    public void test_CheckIfFree_empty(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(5, 5, 3);
        assertTrue(board.checkIfFree(2));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it represents a standard test of a column that
    //has tokens in it
    @Test
    public void test_CheckIfFree_one_space(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(5, 5, 3);
        board.placeToken('X', 4);
        boardArray[0][4] = 'X';
        assertTrue(board.checkIfFree(4));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it represents a standard test of a column that
    //isn’t free
    @Test
    public void test_CheckIfFree_full(){
        char[][] boardArray;
        boardArray = new char[5][5];
        IGameBoard board = gb(5, 5, 3);
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(j == 2){
                    boardArray[i][2] = 'X';
                    board.placeToken('X', 2);
                }
                else {
                    boardArray[i][j] = ' ';
                }
            }
        }

        assertFalse(board.checkIfFree(2));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));

    }
    //This test case is distinct because it represents a
    //standard test of there being no horizontal win
    @Test
    public void test_CheckHorizontalWin_empty(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(5, 5, 3);

        assertFalse(board.checkHorizWin(new BoardPosition(2, 2), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));

    }
    //This test case is distinct because it represents a standard test of conditions
    //being fulfilled for a horizontal win
    @Test
    public void test_CheckHorizontalWin_just_enough(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
                if(i == 0 && j < 3)
                    boardArray[i][j] = 'X';
            }
        }

        IGameBoard board = gb(5,5,3);
        for(int i = 0; i < 3; i++){
            board.placeToken('X', i);
        }

        assertTrue(board.checkHorizWin(new BoardPosition(0,2), 'X'));
        assertTrue(board.toString().equals(boardArrayString(boardArray,5,5)));
    }
    //This test case is distinct because it tests to make sure more than the specified
    //amount will result in a win
    @Test
    public void test_CheckHorizontalWin_more_than_enough(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
                if(i == 0 && j < 4)
                    boardArray[i][j] = 'X';
            }
        }

        IGameBoard board = gb(5,5,3);
        for(int i = 0; i < 4; i++){
            board.placeToken('X', i);
        }

        assertTrue(board.checkHorizWin(new BoardPosition(0,3), 'X'));
        assertTrue(board.toString().equals(boardArrayString(boardArray,5,5)));
    }
    //This test case is distinct because it tests to make sure
    //the function checks the tokens in a row are the same
    @Test
    public void test_CheckHorizontalWin_just_not_enough(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
                if(i == 0 && j < 4)
                    boardArray[i][j] = 'X';
            }
        }

        boardArray[0][2] = 'O';

        IGameBoard board = gb(5,5,3);
        for(int i = 0; i < 4; i++){
            if(i == 2)
                board.placeToken('O', i);
            else
                board.placeToken('X', i);

        }

        assertTrue(!board.checkHorizWin(new BoardPosition(0,3), 'X'));
        assertTrue(board.toString().equals(boardArrayString(boardArray,5,5)));
    }
    //This test case is distinct because it represents a standard case of there being
    //no vertical win
    @Test
    public void test_CheckVerticalWin_empty(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(5, 5, 3);

        assertTrue(!board.checkVertWin(new BoardPosition(2, 2), 'X'));
        assertTrue(board.toString().equals(boardArrayString(boardArray, 5, 5)));

    }
    //This test case is distinct because it represents a standard case of there being
    //a vertical win
    @Test
    public void test_CheckVerticalWin_just_enough(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
                if(i < 3 && j == 2)
                    boardArray[i][j] = 'X';
            }
        }

        IGameBoard board = gb(5,5,3);
        for(int i = 0; i < 3; i++){
            board.placeToken('X', 2);
        }

        assertTrue(board.checkVertWin(new BoardPosition(2,2), 'X'));
        assertTrue(board.toString().equals(boardArrayString(boardArray,5,5)));
    }
    //This test case is distinct because it represents a case where there is more than
    //enough for a vertical win
    @Test
    public void test_CheckVerticalWin_more_than_enough(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
                if(i < 4 && j == 2)
                    boardArray[i][j] = 'X';
            }
        }

        IGameBoard board = gb(5,5,3);
        for(int i = 0; i < 4; i++){
            board.placeToken('X', 2);
        }

        assertTrue(board.checkVertWin(new BoardPosition(3,2), 'X'));
        assertTrue(board.toString().equals(boardArrayString(boardArray,5,5)));
    }
    //This test case is distinct because it represents a case where the function checks to
    //make sure the tokens in a row are the same
    @Test
    public void test_CheckVerticalWin_just_not_enough(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
                if(i < 4 && j == 2)
                    boardArray[i][j] = 'X';
            }
        }

        boardArray[2][2] = 'O';

        IGameBoard board = gb(5,5,3);
        for(int i = 0; i < 4; i++){
            if(i == 2)
                board.placeToken('O', 2);
            else
                board.placeToken('X', 2);

        }

        assertFalse(board.checkVertWin(new BoardPosition(3, 2), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it represents a standard case of there being no diagonal win
    @Test
    public void test_CheckDiagonalWin_empty(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(5, 5, 3);

        assertFalse(board.checkDiagWin(new BoardPosition(0, 0), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it represent the standard case of there being
    //a diagonal win that starts from the left
    @Test
    public void test_CheckDiagonalWin_left_just_enough(){
        char[][] boardArray;
        boardArray = new char[4][4];
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';
        boardArray[0][1] = 'O';
        boardArray[1][1] = 'X';
        boardArray[0][2] = 'O';
        boardArray[1][2] = 'X';
        boardArray[1][0] = 'O';
        boardArray[2][2] = 'X';

        IGameBoard board = gb(4,4,3);

        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('X', 1);
        board.placeToken('O',2);
        board.placeToken('X', 2);
        board.placeToken('O', 0);
        board.placeToken('X', 2);

        assertTrue(board.checkDiagWin(new BoardPosition(2, 2), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 4, 4));

    }
    //This test case is distinct because it represents the case where there is more
    //than enough for a diagonal win that starts to the left
    @Test
    public void test_CheckDiagonalWin_left_more_than_enough() {
        char[][] boardArray;
        boardArray = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';
        boardArray[0][1] = 'O';
        boardArray[1][1] = 'X';
        boardArray[0][2] = 'O';
        boardArray[1][2] = 'X';
        boardArray[1][0] = 'O';
        boardArray[2][2] = 'X';
        boardArray[0][3] = 'X';
        boardArray[1][3] = 'O';
        boardArray[2][3] = 'X';
        boardArray[3][3] = 'X';

        IGameBoard board = gb(4, 4, 3);

        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('X', 1);
        board.placeToken('O', 2);
        board.placeToken('X', 2);
        board.placeToken('O', 0);
        board.placeToken('X', 2);
        board.placeToken('X',3);
        board.placeToken('O', 3);
        board.placeToken('X', 3);
        board.placeToken('X', 3);

        assertTrue(board.checkDiagWin(new BoardPosition(3, 3), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 4, 4));
    }
    //This test case is distinct because it represents the case where the function checks that the tokens in a
    //row are the same, for a diagonal that starts at the left
    @Test
    public void test_CheckDiagonalWin_left_just_not_enough() {
        char[][] boardArray;
        boardArray = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';
        boardArray[0][1] = 'O';
        boardArray[1][1] = 'X';
        boardArray[0][2] = 'O';
        boardArray[1][2] = 'X';
        boardArray[1][0] = 'O';
        boardArray[2][2] = 'O';
        boardArray[0][3] = 'X';
        boardArray[1][3] = 'O';
        boardArray[2][3] = 'X';
        boardArray[3][3] = 'O';

        IGameBoard board = gb(4, 4, 3);

        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('X', 1);
        board.placeToken('O', 2);
        board.placeToken('X', 2);
        board.placeToken('O', 0);
        board.placeToken('O', 2);
        board.placeToken('X',3);
        board.placeToken('O', 3);
        board.placeToken('X', 3);
        board.placeToken('O', 3);

        assertFalse(board.checkDiagWin(new BoardPosition(2, 3), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 4, 4));
    }
    //This test case is distinct because it represents the basic case where there is a
    //diagonal win that starts to the right
    @Test
    public void test_CheckDiagonalWin_right_just_enough(){
        char[][] boardArray;
        boardArray = new char[4][4];
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][3] = 'X';
        boardArray[0][2] = 'O';
        boardArray[1][2] = 'X';
        boardArray[0][1] = 'O';
        boardArray[1][1] = 'O';
        boardArray[2][1] = 'X';

        IGameBoard board = gb(4,4,3);

        board.placeToken('X', 3);
        board.placeToken('O', 2);
        board.placeToken('X', 2);
        board.placeToken('O',1);
        board.placeToken('O', 1);
        board.placeToken('X', 1);

        assertTrue(board.checkDiagWin(new BoardPosition(2,1), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 4, 4));

    }
    //This test case is distinct because it represents the case where there is a
    //diagonal win that starts to the right when there is more than enough tokens in a row
    @Test
    public void test_CheckDiagonalWin_right_more_than_enough(){
        char[][] boardArray;
        boardArray = new char[4][4];
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][3] = 'X';
        boardArray[0][2] = 'O';
        boardArray[1][2] = 'X';
        boardArray[0][1] = 'O';
        boardArray[1][1] = 'O';
        boardArray[2][1] = 'X';
        boardArray[0][0] = 'X';
        boardArray[1][0] = 'O';
        boardArray[2][0] = 'X';
        boardArray[3][0] = 'X';

        IGameBoard board = gb(4,4,3);

        board.placeToken('X', 3);
        board.placeToken('O', 2);
        board.placeToken('X', 2);
        board.placeToken('O',1);
        board.placeToken('O', 1);
        board.placeToken('X', 1);
        board.placeToken('X', 0);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('X', 0);

        assertTrue(board.checkDiagWin(new BoardPosition(3,0), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 4, 4));

    }
    //This test case is distinct because it represents the case where the function checks to make sure a
    //diagonal starting from the right has equivalent tokens in a row
    @Test
    public void test_CheckDiagonalWin_right_just_not_enough(){
        char[][] boardArray;
        boardArray = new char[4][4];
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][3] = 'X';
        boardArray[0][2] = 'O';
        boardArray[1][2] = 'X';
        boardArray[0][1] = 'O';
        boardArray[1][1] = 'O';
        boardArray[2][1] = 'X';
        boardArray[0][0] = 'X';
        boardArray[1][0] = 'O';
        boardArray[2][0] = 'X';
        boardArray[3][0] = 'O';

        IGameBoard board = gb(4,4,3);

        board.placeToken('X', 3);
        board.placeToken('O', 2);
        board.placeToken('X', 2);
        board.placeToken('O',1);
        board.placeToken('O', 1);
        board.placeToken('X', 1);
        board.placeToken('X', 0);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('O', 0);

        assertFalse(board.checkDiagWin(new BoardPosition(2, 0), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 4, 4));
    }
    //This test case is distinct because it represents the
    //standard case of there being no tie
    @Test
    public void test_CheckTie_empty(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(5, 5, 3);

        assertFalse(board.checkTie());
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it represents the standard case of there being a tie
    @Test
    public void test_CheckTie_full(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = 'X';
            }
        }

        IGameBoard board = gb(5,5,3);
        int rows = 0;
        while(rows < 5){
            for(int i = 0; i < 5; i++){
                board.placeToken('X', rows);
            }
            rows++;
        }

        assertTrue(board.checkTie());
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it ensures that the function checks every column
    @Test
    public void test_CheckTie_almost_full(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = 'X';
                if(j == 4)
                    boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(5,5,3);
        int rows = 0;
        while(rows < 4){
            for(int i = 0; i < 5; i++){
                board.placeToken('X', rows);
            }
            rows++;
        }

        assertFalse(board.checkTie());
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it ensures that the function checks every space
    //on the board
    @Test
    public void test_CheckTie_all_but_one(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = 'X';
            }
        }

        boardArray[4][4] = ' ';

        IGameBoard board = gb(5,5,3);
        int rows = 0;
        while(rows < 4){
            for(int i = 0; i < 5; i++){
                board.placeToken('X', rows);
            }
            rows++;
        }

        board.placeToken('X',4);
        board.placeToken('X',4);
        board.placeToken('X',4);
        board.placeToken('X',4);

        assertFalse(board.checkTie());
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it represents the standard case of no character
    //being on the position
    @Test
    public void test_WhatsAtPos_empty(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(5, 5, 3);

        assertEquals(' ', board.whatsAtPos(new BoardPosition(0, 0)));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it represents the standard case of a character
    //being on the position
    @Test
    public void test_WhatAtPos_player_x(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';

        IGameBoard board = gb(5, 5, 3);

        board.placeToken('X',0);

        assertEquals('X', board.whatsAtPos(new BoardPosition(0, 0)));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it ensures the function recognizes
    //characters that aren’t X or O
    @Test
    public void test_WhatsAtPos_player_K(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'K';

        IGameBoard board = gb(5, 5, 3);

        board.placeToken('K',0);

        assertEquals('K', board.whatsAtPos(new BoardPosition(0, 0)));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it ensures the
    //function is checking the correct position
    @Test
    public void test_WhatsAtPos_player_nearby(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';

        IGameBoard board = gb(5, 5, 3);

        board.placeToken('X',0);

        assertEquals(' ', board.whatsAtPos(new BoardPosition(1, 0)));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it ensures the function will return the correct character
    @Test
    public void test_WhatsAtPos_two_players() {
        char[][] boardArray;
        boardArray = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';
        boardArray[0][1] = 'O';

        IGameBoard board = gb(5, 5, 3);

        board.placeToken('X', 0);
        board.placeToken('O', 1);

        assertEquals('O', board.whatsAtPos(new BoardPosition(0, 1)));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it represents the standard case of no character
    //being on the position
    @Test
    public void test_IsPlayerAtPos_empty(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        IGameBoard board = gb(5, 5, 3);

        assertFalse(board.isPlayerAtPos(new BoardPosition(0, 0), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it represents the standard case of a character
    //being on the position
    @Test
    public void test_IsPlayerAtPos_player_X(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';

        IGameBoard board = gb(5, 5, 3);

        board.placeToken('X',0);

        assertTrue(board.isPlayerAtPos(new BoardPosition(0,0), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it ensures the function recognizes
    //characters that aren’t X and O
    @Test
    public void test_IsPlayerAtPos_player_K(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'K';

        IGameBoard board = gb(5, 5, 3);

        board.placeToken('K',0);

        assertTrue(board.isPlayerAtPos(new BoardPosition(0,0), 'K'));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it ensures that the function checks the correct position
    @Test
    public void test_IsPlayerAtPos_empty_space_nearby(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';

        IGameBoard board = gb(5, 5, 3);

        board.placeToken('X',0);

        assertFalse(board.isPlayerAtPos(new BoardPosition(0, 1), 'X'));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it ensures the function is looking for the correct character
    @Test
    public void test_IsPlayerAtPos_two_players(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';
        boardArray[0][1] = 'O';

        IGameBoard board = gb(5, 5, 3);

        board.placeToken('X', 0);
        board.placeToken('O', 1);

        assertTrue(board.isPlayerAtPos(new BoardPosition(0, 1), 'O'));
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it is testing if the function can add a token to
    //the first available column
    @Test
    public void test_PlaceToken_bottom_left(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';

        IGameBoard board = gb(5,5,3);
        board.placeToken('X',0);
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it is testing if the function can add a token to
    //the last available column
    @Test
    public void test_PlaceToken_bottom_right(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][4] = 'X';

        IGameBoard board = gb(5,5,3);
        board.placeToken('X',4);
        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it is testing if the function can add many
    //tokens over and over again
    @Test
    public void test_PlaceToken_fill_board(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][j] = 'X';
            }
        }

        IGameBoard board = gb(5,5,3);
        int rows = 0;
        while(rows < 5){
            for(int i = 0; i < 5; i++){
                board.placeToken('X', rows);
            }
            rows++;
        }

        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it is testing if the
    //function can add different tokens
    @Test
    public void test_PlaceToken_different_characters(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardArray[i][j] = ' ';
            }
        }

        boardArray[0][0] = 'X';
        boardArray[0][1] = 'O';

        IGameBoard board = gb(5, 5, 3);

        board.placeToken('X', 0);
        board.placeToken('O', 1);

        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
    //This test case is distinct because it is testing if the
    //function can add characters in a pattern
    @Test
    public void test_PlaceToken_fill_different_characters(){
        char[][] boardArray;
        boardArray = new char[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                boardArray[i][0] = 'S';
                boardArray[i][1] = 'H';
                boardArray[i][2] = 'R';
                boardArray[i][3] = 'E';
                boardArray[i][4] = 'K';
            }
        }

        IGameBoard board = gb(5,5,3);
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                board.placeToken('S', 0);
                board.placeToken('H',1);
                board.placeToken('R', 2);
                board.placeToken('E', 3);
                board.placeToken('K',4);
            }
        }

        assertEquals(board.toString(), boardArrayString(boardArray, 5, 5));
    }
}