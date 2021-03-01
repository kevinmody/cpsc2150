package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project2 ConnectX
public abstract class AbsGameBoard implements IGameBoard{
    @Override
    public String toString() {
        StringBuilder gameBoard = new StringBuilder();
        BoardPosition[] boardPos = new BoardPosition[maxCol * maxRow];
        int count = 0;
        for (int i = 0; i < maxCol; i++) {
            gameBoard.append(" ");
            gameBoard.append(i);
        }
        gameBoard.append("\n");

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                boardPos[count] = new BoardPosition(i, j);
                gameBoard.append("|").append(this.whatsAtPos(boardPos[count]));
                count++;
            }
            gameBoard.append("|\n");
        }
        return gameBoard.toString();
    }


}
