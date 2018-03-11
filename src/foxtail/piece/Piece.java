package foxtail.piece;

import foxtail.Color;
import foxtail.board.Board;
import foxtail.board.move.Move;

import java.util.List;

public abstract class Piece {
    final int position;
    final Color color;
    final boolean firstMove;

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
    public abstract Piece movePiece(Move move);

    public abstract boolean isPawn();
    public abstract boolean isKnight();
    public abstract boolean isBishop();
    public abstract boolean isRook();
    public abstract boolean isQueen();
    public abstract boolean isKing();
}
