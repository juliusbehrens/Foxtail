package foxtail.board.move;

import foxtail.board.Board;
import foxtail.piece.Piece;

public class MajorMove extends Move {
    public MajorMove(final Board board,
                     final Piece piece,
                     final int destinationCoordinate) {
        super(board, piece, destinationCoordinate);
    }
}
