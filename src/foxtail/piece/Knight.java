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

public class Knight extends Piece{

    public Knight(final Color color, final int position) {
        super(color, position);
    }

    @Override
    public List<Move> getMoves(final Board board) {
        final List<Move> moves = new ArrayList<>();
        final int[] candidates = { -17, -15, -10, -6, 6, 10, 15, 17 };
        for (final int candidate : candidates) {
            if (
                    (Board.getBooleanColumn(0).get(this.position) && (
                            (candidate == -17)  ||
                            (candidate == -10)  ||
                            (candidate == 6)    ||
                            (candidate == 15))) ||
                    (Board.getBooleanColumn(1).get(this.position) && (
                            (candidate == -10)  ||
                            (candidate == 6)))   ||
                    (Board.getBooleanColumn(6).get(this.position) && (
                            (candidate == -6)   ||
                            (candidate == 10)))  ||
                    (Board.getBooleanColumn(7).get(this.position) && (
                            (candidate == -15)  ||
                            (candidate == -6)   ||
                            (candidate == 10)   ||
                            (candidate == 17)))
               )
            {
                continue;
            }

            final int candidateCoordinate = this.position + candidate;
            if (Tile.isValidCoordinate(candidateCoordinate)) {
                final Tile candidateTile = board.getTile(candidateCoordinate);
                if (!candidateTile.isOccupied()) {
                    moves.add(new MajorMove(board, this, candidateCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateTile.getPiece();
                    if (this.color != pieceAtDestination.getColor()) {
                        moves.add(new AttackMove(board, this, candidateCoordinate,
                                pieceAtDestination));
                    }
                }
            }
        }
        return Collections.unmodifiableList(moves);
    }
}
