package cpsc2150.extendedTicTacToe;
import java.util.*;

public abstract class AbsGameBoard implements IGameBoard {
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(" ");

        for (int i = 0; i < getNumColumns(); i++) {
            s.append(i);
            s.append('|');
        }
        s.append('\n');

        for (int r = 0; r < getNumRows(); r++) {
            s.append(r);
            s.append('|');
            for (int c = 0; c < getNumColumns(); c++) {
                BoardPosition bp = new BoardPosition(r, c);
                s.append(whatsAtPos(bp));
                s.append('|');
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}

