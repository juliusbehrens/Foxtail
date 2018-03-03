package foxtail;

import foxtail.board.Board;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(Board.getBooleanRow(7).size());
        Board board = Board.createStandardBoard();
        System.out.println(board);
    }

}