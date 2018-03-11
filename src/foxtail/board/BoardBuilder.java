package foxtail.board;

import foxtail.Color;
import foxtail.piece.Piece;

import java.util.HashMap;
import java.util.Map;


public final class BoardBuilder {

     Map<Integer, Piece> pieces;
     Color currentColor; //nextMoveMake

    public BoardBuilder() {
        this.pieces = new HashMap<>();
    }

    public BoardBuilder setPiece(final Piece piece) {
        this.pieces.put(piece.getPosition(), piece);
        return this;
    }

    public BoardBuilder setCurrentColor(final Color color) {
        this.currentColor = color;
        return this;
    }

    public Board build() {
        return new Board(this);
    }

}
