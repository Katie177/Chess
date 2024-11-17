package org.example;

public class King extends ChessPiece {

    public King(String color) {
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

        // Проверка, что король может двигаться в любое поле вокруг себя
        if (Math.abs(toColumn - column) <= 1 && Math.abs(toLine - line) <= 1) {
            ChessPiece targetPiece = ChessBoard.getPieceAt(toColumn, toLine);
            if (targetPiece == null || !targetPiece.getColor().equals(this.color)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int newLine = line + i;
                int newColumn = column + j;

                if (newLine >= 0 && newLine < 8 && newColumn >= 0 && newColumn < 8) {
                    ChessPiece piece = board.board[newLine][newColumn];
                    if (piece != null && !piece.getColor().equals(this.color)) {
                        if (piece.canMoveToPosition(board, newLine, newColumn, line, column)) {
                            return true; // Если фигура противника может атаковать
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isKingInCheck(ChessBoard board, String color) {
        int kingLine = -1, kingColumn = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board.board[i][j];
                if (piece instanceof King && piece.getColor().equals(color)) {
                    kingLine = i;
                    kingColumn = j;
                    break;
                }
            }
            if (kingLine != -1) break;
        }

        if (kingLine == -1) {
            System.out.println("Король не найден!");
            return false;
        }

        King king = (King) board.board[kingLine][kingColumn];
        return king.isUnderAttack(board, kingLine, kingColumn);
    }

}