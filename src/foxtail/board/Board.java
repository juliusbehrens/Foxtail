package foxtail.board;

import foxtail.Color;
import foxtail.board.move.Move;
import foxtail.piece.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    public static final int TILES = 64;
    public static final int TILES_PER_ROW = 8;

    private final List<Tile> board;
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;

    public static List<Boolean> getBooleanColumn(int columnNumber) {
        List<Boolean> column = new ArrayList<Boolean>();
        for(int i = 0; i < TILES; ++i) {
            column.add(false);
        }
        do {
            column.set(columnNumber, true);
            columnNumber += TILES_PER_ROW;
        } while(columnNumber < TILES);
        return Collections.unmodifiableList(column);
    }
    public static List<Boolean> getBooleanRow(int rowNumber) {
        List<Boolean> row = new ArrayList<Boolean>();
        for(int i = 0; i < TILES; ++i) {
            row.add(false);
        }
        for(int i = 0; i < TILES_PER_ROW; ++i) {
            row.set((rowNumber*(TILES_PER_ROW))+i,true);
        }
        return Collections.unmodifiableList(row);
    }

    public Board(BoardBuilder boardBuilder) {
        this.board = createBoard(boardBuilder);
        this.whitePieces = getWhitePieces(this.board);
        this.blackPieces = getBlackPieces(this.board);

        final List<Move> whiteMoves = getMoves(this.whitePieces);
        final List<Move> blackMoves = getMoves(this.blackPieces);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Board.TILES; ++i) {
            final String tileText = this.board.get(i).toString();
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % Board.TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public String toUnicodeString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Board.TILES; ++i) {
            final String tileText = this.board.get(i).toUnicodeString();
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % Board.TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private static List<Piece> getWhitePieces(final List<Tile> board) {
        final List<Piece> whitePieces = new ArrayList<Piece>();
        for(final Tile tile : board) {
            if(tile.isOccupied() && tile.getPiece().getColor() == Color.WHITE) {
                whitePieces.add(tile.getPiece());
            }
        }
        return Collections.unmodifiableList(whitePieces);
    }

    private static List<Piece> getBlackPieces(final List<Tile> board) {
        final List<Piece> blackPieces = new ArrayList<Piece>();
        for(final Tile tile : board) {
            if(tile.isOccupied() && tile.getPiece().getColor() == Color.BLACK) {
                blackPieces.add(tile.getPiece());
            }
        }
        return Collections.unmodifiableList(blackPieces);
    }

    private List<Move> getMoves(final List<Piece> pieces) {
        final List<Move> moves = new ArrayList<Move>();
        for(final Piece piece : pieces) {
            moves.addAll(piece.getMoves(this));
        }
        return Collections.unmodifiableList(moves);
    }

    private static List<Tile> createBoard(final BoardBuilder boardBuilder) {
        final List<Tile> board = new ArrayList<>();
        for(int i = 0; i < Board.TILES; ++i) {
            board.add(new Tile(i, boardBuilder.pieces.get(i)));
        }
        return Collections.unmodifiableList(board);
    }

    public static Board createStandardBoard() {
        final BoardBuilder builder = new BoardBuilder();
        // Black Layout
        builder.setPiece(new Rook(Color.BLACK, 0));
        builder.setPiece(new Knight(Color.BLACK, 1));
        builder.setPiece(new Bishop(Color.BLACK, 2));
        builder.setPiece(new Queen(Color.BLACK, 3));
        builder.setPiece(new King(Color.BLACK, 4));
        builder.setPiece(new Bishop(Color.BLACK, 5));
        builder.setPiece(new Knight(Color.BLACK, 6));
        builder.setPiece(new Rook(Color.BLACK, 7));
        builder.setPiece(new Pawn(Color.BLACK, 8));
        builder.setPiece(new Pawn(Color.BLACK, 9));
        builder.setPiece(new Pawn(Color.BLACK, 10));
        builder.setPiece(new Pawn(Color.BLACK, 11));
        builder.setPiece(new Pawn(Color.BLACK, 12));
        builder.setPiece(new Pawn(Color.BLACK, 13));
        builder.setPiece(new Pawn(Color.BLACK, 14));
        builder.setPiece(new Pawn(Color.BLACK, 15));
        // White Layout
        builder.setPiece(new Pawn(Color.WHITE, 48));
        builder.setPiece(new Pawn(Color.WHITE, 49));
        builder.setPiece(new Pawn(Color.WHITE, 50));
        builder.setPiece(new Pawn(Color.WHITE, 51));
        builder.setPiece(new Pawn(Color.WHITE, 52));
        builder.setPiece(new Pawn(Color.WHITE, 53));
        builder.setPiece(new Pawn(Color.WHITE, 54));
        builder.setPiece(new Pawn(Color.WHITE, 55));
        builder.setPiece(new Rook(Color.WHITE, 56));
        builder.setPiece(new Knight(Color.WHITE, 57));
        builder.setPiece(new Bishop(Color.WHITE, 58));
        builder.setPiece(new Queen(Color.WHITE, 59));
        builder.setPiece(new King(Color.WHITE, 60));
        builder.setPiece(new Bishop(Color.WHITE, 61));
        builder.setPiece(new Knight(Color.WHITE, 62));
        builder.setPiece(new Rook(Color.WHITE, 63));
        //white to move
        builder.setCurrentColor(Color.WHITE);
        //build the board
        return builder.build();
    }

    public Tile getTile(final int coordinate) {
        return board.get(coordinate);
    }
}
