package foxtail.board;

import foxtail.piece.Piece;

public class Tile {
    private final int coordinate;
    private final Piece piece;

    public Tile(final int coordinate, final Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
    }

    @Override
    public String toString() {
        return this.isOccupied() ?
                (this.piece.getColor().isWhite() ?
                 this.piece.toString() :
                 this.piece.toString().toLowerCase())
                : "-";
    }

    public String toUnicodeString() {
        return this.isOccupied() ? this.piece.toUnicodeString() : " ";
    }

    public Piece getPiece() {
        return this.piece;
    }

    public boolean isOccupied() {
        return this.piece != null;
    }

    public static boolean isValidCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < Board.TILES;
    }
}
