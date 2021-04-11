package cpsc2150.extendedConnectX;

//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project4 ConnectX
public abstract class AbsGameBoard implements IGameBoard {
    /**
     * @post toString = getNumRows() x getNumCols() board
     * @return a string representation of the GameBoard
     */
    @Override
    public String toString() {
        String newBoard = "";
        for (int i = 0; i < getNumColumns(); i++)
        {
            if (i < 10) {
                newBoard += "| " + i;
            } else {
                newBoard += "|" + i;
            }
        }
        newBoard += "|\n";
        for (int j = getNumRows() - 1; j >= 0; j--) {
            for (int k = 0; k < getNumColumns(); k++) {
                newBoard += "|" + whatsAtPos(new BoardPosition(j,k)) + " ";
            }
            newBoard += ("|\n");
        }
        return newBoard;
    }
}
