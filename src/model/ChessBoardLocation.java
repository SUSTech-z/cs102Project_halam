package model;

import java.util.Objects;

public class ChessBoardLocation {
    private int row, column;

    public ChessBoardLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {//判断棋子是否相同，是否为空或不同类，位置是否相同
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoardLocation location = (ChessBoardLocation) o;
        return row == location.row && column == location.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
