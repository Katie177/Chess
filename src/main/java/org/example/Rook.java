package org.example;

public class Rook extends ChessPiece {

    public Rook(String color) {
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

        // Ладья может двигаться только по прямой
        if (toLine == line || toColumn == column) {
            if (!ChessBoard.isPathClear(chessBoard, line, column, toLine, toColumn)) {
                return false;
            }
            ChessPiece targetPiece = ChessBoard.getPieceAt(toColumn, toLine);
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }

        return false;  // Ладья не может двигаться в этом направлении
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public void setPosition(int line, int column) {

    }
}