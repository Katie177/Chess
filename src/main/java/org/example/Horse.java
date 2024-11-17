package org.example;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, находится ли целевая позиция за пределами доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // Проверка, является ли целевая позиция текущей
        if (line == toLine && column == toColumn) {
            return false;
        }

        if ((Math.abs(toColumn - column) == 2 && Math.abs(toLine - line) == 1) ||
                (Math.abs(toColumn - column) == 1 && Math.abs(toLine - line) == 2)) {
            ChessPiece targetPiece = ChessBoard.getPieceAt(toColumn, toLine);
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }
        return false;


    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public void setPosition(int line, int column) {

    }


}

