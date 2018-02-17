package foxtail.board;

import foxtail.piece.Piece;

public class Tile {
    private final int coordinate;
    private final Piece piece;

    public Tile(final int coordinate, final Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
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
