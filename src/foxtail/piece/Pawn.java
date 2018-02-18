package foxtail.piece;

import foxtail.Color;
import foxtail.board.Board;
import foxtail.board.Tile;
import foxtail.board.move.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(final int position, final Color color) {
        super(position, color);
    }

    @Override
    public List<Move> getMoves(final Board board) {
        final List<Move> moves = new ArrayList<>();
        final int[] candidates = {8, 16};
        for (final int candidate : candidates) {
            int candidateCoordinate = this.position + (this.getColor().getDirection() * candidate);

            if(!Tile.isValidCoordinate(candidateCoordinate)) {
                continue;
            } else if(candidate == 8 && !board.getTile(candidateCoordinate).isOccupied()) {
                //TODO add move
            } else if(candidate == 16 && (
                    (Board.getBooleanRow(1).get(this.position) && this.color.isBlack()) ||
                    (Board.getBooleanRow(6).get(this.position) && this.color.isWhite())
                    )) {
                if(!board.getTile(this.position + (this.color.getDirection() *8)).isOccupied() &&
                   !board.getTile(candidateCoordinate).isOccupied()) {
                    //TODO add move
                }
            }
        }
        return Collections.unmodifiableList(moves);
    }
}
