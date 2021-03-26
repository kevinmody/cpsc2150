package cpsc2150.extendedConnectX;
//Author: Kevin Mody
//Class: CPSC 2150
//Sec: 001
//Project: Project3 ConnectX
public abstract class AbsGameBoard implements IGameBoard{
    /**
     * @post toString = 6 x 7 board
     * @return a string representation of the GameBoard
     */
    @Override
    public String toString() {
        String newBoard = "\n";
        for (int i = 0; i < getNumColumns(); i++)
        {
            if(i >= 10)
            {
                newBoard = newBoard + "|" + i;
            }
            else
            {
                newBoard = newBoard + "|" + " " + i;
            }
        }
        newBoard += "|\n";
        for (int j = getNumRows() - 1; j >= 0; j--) {
            for (int k = 0; k < getNumColumns(); k++) {
                BoardPosition initialPosition = new BoardPosition(j, k);
                if(whatsAtPos(initialPosition) == ' ')
                {
                    newBoard += ("|" + ' ' + ' ');
                }
                else
                {
                    newBoard += ("|" + whatsAtPos(initialPosition) + " ");
                }
            }
            newBoard += ("|\n");
        }
        return newBoard;
    }
}
