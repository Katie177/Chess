package org.example;

public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
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

        if (Math.abs(toColumn - column) == Math.abs(toLine - line) || toLine == line || toColumn == column) {
            if (!ChessBoard.isPathClear(chessBoard, line, column, toLine, toColumn)) {
                return false;
            }
            ChessPiece targetPiece = ChessBoard.getPieceAt(toColumn, toLine);
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

}