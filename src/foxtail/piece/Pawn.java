package foxtail.piece;

import foxtail.Color;
import foxtail.board.Board;
import foxtail.board.Tile;
import foxtail.board.move.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(final Color color, final int position) {
        super(color, position);
    }

    @Override
    public String toString() {
        return this.color.isWhite() ? "P" : "p";
    }

    @Override
    public String toUnicodeString() {
        return this.color.isWhite() ? "\u2659" : "\u265F";
    }

    @Override
    public boolean isPawn() {
        return true;
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
        return false;
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
        final int[] candidates = {8, 16, 7, 9};
        for (final int candidate : candidates) {
            int candidateCoordinate = this.position + (this.color.getDirection() * candidate);

            if (!Tile.isValidCoordinate(candidateCoordinate)) {
                continue;
            } else if (candidate == 8 && !board.getTile(candidateCoordinate).isOccupied()) {
                //TODO add move
            } else if (candidate == 16 && (
                    (Board.getBooleanRow(1).get(this.position) && this.color.isBlack()) ||
                    (Board.getBooleanRow(6).get(this.position) && this.color.isWhite())
                    )) {
                if(!board.getTile(this.position + (this.color.getDirection() *8)).isOccupied() &&
                   !board.getTile(candidateCoordinate).isOccupied()) {
                    //TODO add move
                }
            } else if (candidate == 7 && !(
                    (Board.getBooleanColumn(7).get(this.position) && this.color.isWhite()) ||
                    (Board.getBooleanColumn(0).get(this.position) && this.color.isBlack()) )
                    ) {
                if(board.getTile(candidateCoordinate).isOccupied()) {
                    final Piece candidatePiece = board.getTile(candidateCoordinate).getPiece();
                    if(this.color != candidatePiece.getColor()) {
                        //TODO add move
                    }
                }
            } else if (candidate == 9 && !(
                    (Board.getBooleanColumn(7).get(this.position) && this.color.isBlack()) ||
                    (Board.getBooleanColumn(0).get(this.position) && this.color.isWhite()) )
                    ) {
                if(board.getTile(candidateCoordinate).isOccupied()) {
                    final Piece candidatePiece = board.getTile(candidateCoordinate).getPiece();
                    if(this.color != candidatePiece.getColor()) {
                        //TODO add move
                    }
                }
            }
        }
        return Collections.unmodifiableList(moves);
    }
}
