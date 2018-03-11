package foxtail.board.move;

import foxtail.board.Board;
import foxtail.board.BoardBuilder;
import foxtail.piece.Piece;

public class MajorMove extends Move {
    public MajorMove(final Board board,
                     final Piece piece,
                     final int destinationCoordinate) {
        super(board, piece, destinationCoordinate);
    }

    @Override
    public Board make() {
        final BoardBuilder boardBuilder = new BoardBuilder();
        for(final Piece piece : this.board.getCurrentPlayer().getPieces()) {
            //TODO hashcode and equals for pieces
            if(!this.piece.equals(piece)) {
                boardBuilder.setPiece(piece);
            }
        }
        for(final Piece piece : this.board.getCurrentPlayer().getOpponent().getPieces()) {
            boardBuilder.setPiece(piece);
        }
        //TODO move piece
        boardBuilder.setPiece(null);
        boardBuilder.setCurrentColor(this.board.getCurrentPlayer().getOpponent().getColor());

        return boardBuilder.build();
    }
}
