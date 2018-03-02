package foxtail;

import foxtail.board.Board;
import foxtail.board.BoardBuilder;
import foxtail.piece.Pawn;
import foxtail.player.WhitePlayer;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(Board.getBooleanRow(7).size());
        BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.setPiece(new Pawn(Color.BLACK,1));
        Board board = boardBuilder.build();
        WhitePlayer player = new WhitePlayer(board, null, null);
        System.out.println(board.toString());
    }

}