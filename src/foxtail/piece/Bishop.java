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

public class Bishop extends Piece {

    public Bishop(final Color color, final int position) {
        super(color, position);
    }

    @Override
    public String toString() {
        return this.color.isWhite() ? "B" : "b";
    }

    @Override
    public String toUnicodeString() {
        return this.color.isWhite() ? "\u2657" : "\u265D";
    }

    @Override
    public List<Move> getMoves(final Board board) {
        final List<Move> moves = new ArrayList<>();
        final int[] candidates = {-9, -7, 7, 9};
        for (final int candidate : candidates) {
            int candidateCoordinate = this.position;
            while (Tile.isValidCoordinate(candidateCoordinate)) {
                if (
                        (Board.getBooleanColumn(0).get(this.position) && (
                                (candidate == -9)   ||
                                (candidate == 7) )) ||
                        (Board.getBooleanColumn(7).get(this.position) && (
                                (candidate == -7)   ||
                                (candidate == 9) ))
                   )
                {
                    break;
                }
                candidateCoordinate += candidate;
                if (Tile.isValidCoordinate(candidateCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateCoordinate);
                    if (!candidateDestinationTile.isOccupied()) {
                        moves.add(new MajorMove(board, this, candidateCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Color pieceAlliance = pieceAtDestination.getColor();
                        if (this.color != pieceAlliance) {
                            moves.add(new AttackMove(board, this, candidateCoordinate,
                                    pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return Collections.unmodifiableList(moves);
    }
}
