package org.example;

public abstract class ChessPiece {
    public String color;
    public boolean check = true;
    private int line;    // Строка на доске
    private int column;  // Столбец на доске
    public int x, y;


    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public void setPosition(int line, int column) {
        this.line = line;
        this.column = column;
    }
}



