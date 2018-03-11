package foxtail.board;

import foxtail.Color;
import foxtail.board.move.Move;
import foxtail.piece.*;
import foxtail.player.BlackPlayer;
import foxtail.player.Player;
import foxtail.player.WhitePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    public static final int TILES = 64;
    public static final int TILES_PER_ROW = 8;

    private final List<Tile> board;
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;
    private final List<Move> whiteMoves;
    private final List<Move> blackMoves;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;


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

    public Board(final BoardBuilder boardBuilder) {
        this.board = createBoard(boardBuilder);
        this.whitePieces = calculateWhitePieces();
        this.blackPieces = calculateBlackPieces();
        this.whiteMoves = calculateWhiteMoves();
        this.blackMoves = calculateBlackMoves();
        this.whitePlayer = new WhitePlayer(this, this.whiteMoves, this.blackMoves);
        this.blackPlayer = new BlackPlayer(this, this.whiteMoves, this.blackMoves);
        this.currentPlayer = boardBuilder.currentColor.choosePlayer(this.whitePlayer, this.blackPlayer);
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

    private List<Piece> calculateWhitePieces() {
        final List<Piece> whitePieces = new ArrayList<>();
        for(final Tile tile : this.board) {
            if(tile.isOccupied() && tile.getPiece().getColor() == Color.WHITE) {
                whitePieces.add(tile.getPiece());
            }
        }
        return Collections.unmodifiableList(whitePieces);
    }

    private List<Piece> calculateBlackPieces() {
        final List<Piece> blackPieces = new ArrayList<>();
        for(final Tile tile : this.board) {
            if(tile.isOccupied() && tile.getPiece().getColor() == Color.BLACK) {
                blackPieces.add(tile.getPiece());
            }
        }
        return Collections.unmodifiableList(blackPieces);
    }

    private List<Move> calculateWhiteMoves() {
        final List<Move> moves = new ArrayList<Move>();
        for(final Piece whitePiece : whitePieces) {
            moves.addAll(whitePiece.getMoves(this));
        }
        return Collections.unmodifiableList(moves);
    }

    private List<Move> calculateBlackMoves() {
        final List<Move> moves = new ArrayList<Move>();
        for(final Piece blackPiece : blackPieces) {
            moves.addAll(blackPiece.getMoves(this));
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

    public static List<Move> getAttacksOnPosition(final int position, List<Move> moves) {
        final List<Move> attackMoves = new ArrayList<>();
        for(final Move move : moves) {
            if(position == move.getDestinationCoordinate()) {
                attackMoves.add(move);
            }
        }
        return Collections.unmodifiableList(attackMoves);
    }

    public Tile getTile(final int coordinate) {
        return board.get(coordinate);
    }

    public List<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    public List<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public List<Move> getWhiteMoves() {
        return this.whiteMoves;
    }

    public List<Move> getBlackMoves() {
        return this.blackMoves;
    }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    public Player getBlackPlayer() {
        return this.blackPlayer;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

 }
