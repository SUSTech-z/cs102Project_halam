package model;

public class Square {
    private ChessBoardLocation location;
    private ChessPiece piece;

    public Square(ChessBoardLocation location) {
        this.location = location;
    }

    public ChessBoardLocation getLocation() {
        return location;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Square{" +
                "location=" + location.getRow() + "," +location.getColumn() +
                ";Color=" + piece.getColor().getRed() + " " +
                piece.getColor().getGreen() + " " + piece.getColor().getBlue() +
                '}';
    }
}
