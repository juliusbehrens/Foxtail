package foxtail.player;

import foxtail.board.Board;
import foxtail.board.move.Move;
import foxtail.exceptions.InvalidBoardException;
import foxtail.piece.King;
import foxtail.piece.Piece;

import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final King king;
    protected final List<Move> playerMoves;

    public Player(final Board board, final List<Move> playerMoves, final List<Move> opponentMoves) {
        this.board = board;
        this.king = getKing();
        this.playerMoves = playerMoves;
    }

    private King getKing() {
        for(final Piece piece : this.getPieces()) {
            if(piece.isKing()) {
                return (King) piece;
            }
        }
        throw new InvalidBoardException("Missing piece: King");
    }

    public abstract List<Piece> getPieces();

}
