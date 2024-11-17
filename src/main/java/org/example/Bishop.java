package org.example;

public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine == line && toColumn == column) {
            return false;
        }
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }
        if (Math.abs(toColumn - column) == Math.abs(toLine - line)) {
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
        return "B";
    }

}