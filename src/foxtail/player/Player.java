package foxtail.player;

import foxtail.Color;
import foxtail.board.Board;
import foxtail.board.move.Move;
import foxtail.board.move.MoveStatus;
import foxtail.board.move.MoveTransition;
import foxtail.exceptions.InvalidBoardException;
import foxtail.piece.King;
import foxtail.piece.Piece;

import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final King king;
    protected final List<Move> playerMoves;
    protected final List<Move> opponentMoves;

    private final boolean isInCheck;

    public Player(final Board board, final List<Move> playerMoves, final List<Move> opponentMoves) {
        this.board = board;
        this.king = establishKing();
        this.playerMoves = playerMoves;
        this.opponentMoves = opponentMoves;
        this.isInCheck = !Board.getAttacksOnPosition(this.king.getPosition(), this.opponentMoves).isEmpty();
    }

    private King establishKing() {
        for(final Piece piece : this.getPieces()) {
            if(piece.isKing()) {
                return (King) piece;
            }
        }
        throw new InvalidBoardException("Missing white king.");
    }

    public final King getKing() {
        return this.king;
    }

    public final boolean isLegalMove(final Move move) {
        return this.playerMoves.contains(move);
    }

    public final List<Move> getMoves() {
        return this.playerMoves;
    }

    public final boolean isInCheck() {
        return this.isInCheck;
    }

    public final boolean isInCheckMate() {
        return this.isInCheck && !hasEscapeMoves();
    }

    public final boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }

    public final boolean hasEscapeMoves() {
        for(final Move move : this.playerMoves) {
            final MoveTransition moveTransition = move(move);
            if(moveTransition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isCastled() {
        //TODO implement this
        return false;
    }

    public final MoveTransition move(final Move move) {
        if(!isLegalMove(move)) {
            return new MoveTransition(this.board, this.board , move, MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionBoard = move.make();
        final List<Move> kingAttacks = Board.getAttacksOnPosition(transitionBoard.getCurrentPlayer().getOpponent().getKing().getPosition(), transitionBoard.getCurrentPlayer().getMoves());
        if(!kingAttacks.isEmpty()) {
            return new MoveTransition(this.board, this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(this.board, transitionBoard, move, MoveStatus.DONE);
    }

    public abstract List<Piece> getPieces();
    public abstract Color getColor();
    public abstract Player getOpponent();

}
