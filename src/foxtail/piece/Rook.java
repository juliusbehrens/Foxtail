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

public class Rook extends Piece {

    public Rook(final Color color, final int position) {
        super(color, position);
    }

    @Override
    public String toString() {
        return this.color.isWhite() ? "R" : "r";
    }

    @Override
    public String toUnicodeString() {
        return this.color.isWhite() ? "\u2656" : "\u265C";
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isBishop() {
        return false;
    }

    @Override
    public boolean isRook() {
        return true;
    }

    @Override
    public boolean isQueen() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public List<Move> getMoves(final Board board) {
        final List<Move> moves = new ArrayList<>();
        final int[] candidates = {-8, -1, 1, 8 };
        for (final int candidate : candidates) {
            int candidateCoordinate = this.position;
            while (Tile.isValidCoordinate(candidateCoordinate)) {
                if (
                        (Board.getBooleanColumn(0).get(this.position) && (
                                (candidate == -1)))   ||
                        (Board.getBooleanColumn(7).get(this.position) && (
                                (candidate == 1)))
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
