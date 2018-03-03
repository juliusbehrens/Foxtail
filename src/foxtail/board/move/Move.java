package foxtail.board.move;

import foxtail.board.Board;
import foxtail.piece.Piece;

public abstract class Move {

    protected final Board board;
    protected final Piece piece;
    protected final int destinationCoordinate;

    public Move(final Board board, final Piece piece, final int destinationCoordinate) {
        this.board = board;
        this.piece = piece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public final Board getBoard() {
        return this.board;
    }

    public final int getCurrentCoordinate() {
        return this.piece.getPosition();
    }

    public final int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public final Piece getPiece() {
        return this.piece;
    }

    public abstract Board make();
}