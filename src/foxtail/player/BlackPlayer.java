package foxtail.player;

import foxtail.Color;
import foxtail.board.Board;
import foxtail.board.move.Move;
import foxtail.piece.Piece;

import java.util.List;

public class BlackPlayer extends Player{

    public BlackPlayer(Board board, List<Move> playerMoves, List<Move> opponentMoves) {
        super(board, playerMoves, opponentMoves);
    }

    @Override
    public List<Piece> getPieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }

}
