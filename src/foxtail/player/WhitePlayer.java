package foxtail.player;

import foxtail.Color;
import foxtail.board.Board;
import foxtail.board.move.Move;
import foxtail.piece.Piece;

import java.util.List;

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final List<Move> playerMoves, final List<Move> opponentMoves) {
        super(board, playerMoves, opponentMoves);
    }

    @Override
    public List<Piece> getPieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }

}
