package foxtail.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    public static final int TILES = 64;
    public static final int TILES_PER_ROW = 8;

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

    public Tile getTile(final int coordinate) {
        return null;
    }
}
