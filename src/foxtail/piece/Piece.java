package foxtail.piece;

import foxtail.Color;
import foxtail.board.Board;
import foxtail.board.move.Move;

import java.util.List;

public abstract class Piece {
    protected final int position;
    protected final Color color;
    protected final boolean firstMove;

    public Piece(final Color color, final int position) {
        this.color = color;
        this.position = position;

        //TODO more work
        this.firstMove = false;
    }

    public final int getPosition() {
        return this.position;
    }

    public final Color getColor() {
        return this.color;
    }

    public final boolean isFirstMove() {
        return this.firstMove;
    }

    public abstract List<Move> getMoves(final Board board);
    public abstract String toString();
    public abstract String toUnicodeString();
}
