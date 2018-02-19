package foxtail.piece;

import foxtail.Color;
import foxtail.board.Board;
import foxtail.board.move.Move;

import java.util.List;

public abstract class Piece {
    protected final int position;
    protected final Color color;

    public Piece(final int position, final Color color) {
        this.position = position;
        this.color = color;
    }

    public final int getPosition() {
        return this.position;
    }

    public final Color getColor() {
        return this.color;
    }

    public abstract List<Move> getMoves(final Board board);
}
