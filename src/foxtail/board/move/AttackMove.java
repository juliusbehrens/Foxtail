package foxtail.board.move;

import foxtail.board.Board;
import foxtail.piece.Piece;

public class AttackMove extends Move {
    private final Piece attackedPiece;

    public AttackMove(final Board board,
                      final Piece piece,
                      final int destinationCoordinate,
                      final Piece attackedPiece) {

        super(board, piece, destinationCoordinate);
        this.attackedPiece = attackedPiece;
    }
}
