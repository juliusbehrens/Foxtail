package foxtail.piece;

import foxtail.Color;
import foxtail.board.Board;
import foxtail.board.Tile;
import foxtail.board.move.AttackMove;
import foxtail.board.move.MajorMove;
import foxtail.board.move.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class King extends Piece {

    public King(final Color color, final int position) {
        super(color, position);
    }

    @Override
    public List<Move> getMoves(final Board board) {
        final List<Move> moves = new ArrayList<>();
        final int[] candidates = {-9, -8, -7, -1, 1, 7, 8, 9};
        for (final int candidate : candidates) {
            final int candidateCoordinate = this.position + candidate;

            if (
                    (Board.getBooleanColumn(0).get(this.position) && (
                            (candidate == -9)  ||
                            (candidate == -1)  ||
                            (candidate == 7))) ||
                    (Board.getBooleanColumn(7).get(this.position) && (
                            (candidate == -7)  ||
                            (candidate == 1)   ||
                            (candidate == 9)))
                    //TODO check this rules for first and last row
                    )
            {
                continue;
            }

            if (Tile.isValidCoordinate(candidateCoordinate)) {
                final Tile candidateTile = board.getTile(candidateCoordinate);
                if (!candidateTile.isOccupied()) {
                    moves.add(new MajorMove(board, this, candidateCoordinate)); } else {
                    final Piece pieceAtDestination = candidateTile.getPiece();
                    if (this.color != pieceAtDestination.getColor()) {
                        moves.add(new AttackMove(board, this, candidateCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return Collections.unmodifiableList(moves);
    }
}
