package org.example;

public class ChessBoard {
    public static ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling7() {
        // Проверка для белого короля
        if (nowPlayer.equals("White")) {
            // Получаем белого короля и ладью на A1
            King whiteKing = (King) board[0][4];  // белый король
            Rook whiteRook = (Rook) board[0][0];  // белая ладья

            // Проверяем, не двигались ли фигуры
            if (whiteKing == null || whiteRook == null || !whiteKing.getColor().equals("White") || !whiteRook.getColor().equals("White")) {
                return false;
            }
            if (!whiteKing.check || !whiteRook.check) {
                return false;  // Если одна из фигур уже двигалась, рокировка невозможна
            }

            // Проверяем, что между королем и ладьей нет других фигур
            if (board[0][1] != null || board[0][2] != null || board[0][3] != null) {
                return false; // Если между ними есть фигуры, рокировка невозможна
            }

            // Проверка на то, что король и его конечные позиции не находятся под атакой
            if (whiteKing.isUnderAttack(this, 0, 4) || whiteKing.isUnderAttack(this, 0, 2)) {
                return false; // Если король под атакой, рокировка невозможна
            }

            // Перемещаем короля и ладью
            board[0][4] = null; // Убираем короля с текущей позиции
            board[0][0] = null; // Убираем ладью с текущей позиции
            board[0][2] = whiteKing; // Перемещаем короля на c1
            board[0][3] = whiteRook; // Перемещаем ладью на d1

            // Обновляем состояние фигур
            whiteKing.check = false;
            whiteRook.check = false;
            nowPlayer = "Black"; // Переход хода

            return true; // Рокировка успешна
        }

        // Проверка для черного короля
        if (nowPlayer.equals("Black")) {
            // Получаем черного короля и ладью на H8
            King blackKing = (King) board[7][4];  // черный король
            Rook blackRook = (Rook) board[7][7];  // черная ладья

            // Проверяем, не двигались ли фигуры
            if (blackKing == null || blackRook == null || !blackKing.getColor().equals("Black") || !blackRook.getColor().equals("Black")) {
                return false;
            }
            if (!blackKing.check || !blackRook.check) {
                return false;  // Если одна из фигур уже двигалась, рокировка невозможна
            }

            // Проверяем, что между королем и ладьей нет других фигур
            if (board[7][5] != null || board[7][6] != null) {
                return false; // Если между ними есть фигуры, рокировка невозможна
            }

            // Проверка на то, что король и его конечные позиции не находятся под атакой
            if (blackKing.isUnderAttack(this, 7, 4) || blackKing.isUnderAttack(this, 7, 6)) {
                return false; // Если король под атакой, рокировка невозможна
            }

            // Перемещаем короля и ладью
            board[7][4] = null; // Убираем короля с текущей позиции
            board[7][7] = null; // Убираем ладью с текущей позиции
            board[7][6] = blackKing; // Перемещаем короля на g8
            board[7][5] = blackRook; // Перемещаем ладью на f8

            // Обновляем состояние фигур
            blackKing.check = false;
            blackRook.check = false;
            nowPlayer = "White"; // Переход хода

            return true; // Рокировка успешна
        }

        return false; // Невозможность рокировки
    }

    public boolean castling0() {
        // Проверка для белого короля
        if (nowPlayer.equals("White")) {
            // Получаем белого короля и ладью на H1
            King whiteKing = (King) board[0][4];  // белый король
            Rook whiteRook = (Rook) board[0][7];  // белая ладья

            // Проверяем, не двигались ли фигуры
            if (whiteKing == null || whiteRook == null || !whiteKing.getColor().equals("White") || !whiteRook.getColor().equals("White")) {
                return false;
            }
            if (!whiteKing.check || !whiteRook.check) {
                return false;  // Если одна из фигур уже двигалась, рокировка невозможна
            }

            // Проверяем, что между королем и ладьей нет других фигур
            if (board[0][5] != null || board[0][6] != null) {
                return false; // Если между ними есть фигуры, рокировка невозможна
            }

            // Проверка на то, что король и его конечные позиции не находятся под атакой
            if (whiteKing.isUnderAttack(this, 0, 4) || whiteKing.isUnderAttack(this, 0, 6)) {
                return false; // Если король под атакой, рокировка невозможна
            }

            // Перемещаем короля и ладью
            board[0][4] = null; // Убираем короля с текущей позиции
            board[0][7] = null; // Убираем ладью с текущей позиции
            board[0][6] = whiteKing; // Перемещаем короля на g1
            board[0][5] = whiteRook; // Перемещаем ладью на f1

            // Обновляем состояние фигур
            whiteKing.check = false;
            whiteRook.check = false;
            nowPlayer = "Black"; // Переход хода

            return true; // Рокировка успешна
        }

        // Проверка для черного короля
        if (nowPlayer.equals("Black")) {
            // Получаем черного короля и ладью на H8
            King blackKing = (King) board[7][4];  // черный король
            Rook blackRook = (Rook) board[7][7];  // черная ладья

            // Проверяем, не двигались ли фигуры
            if (blackKing == null || blackRook == null || !blackKing.getColor().equals("Black") || !blackRook.getColor().equals("Black")) {
                return false;
            }
            if (!blackKing.check || !blackRook.check) {
                return false;  // Если одна из фигур уже двигалась, рокировка невозможна
            }

            // Проверяем, что между королем и ладьей нет других фигур
            if (board[7][5] != null || board[7][6] != null) {
                return false; // Если между ними есть фигуры, рокировка невозможна
            }

            // Проверка на то, что король и его конечные позиции не находятся под атакой
            if (blackKing.isUnderAttack(this, 7, 4) || blackKing.isUnderAttack(this, 7, 6)) {
                return false; // Если король под атакой, рокировка невозможна
            }

            // Перемещаем короля и ладью
            board[7][4] = null; // Убираем короля с текущей позиции
            board[7][7] = null; // Убираем ладью с текущей позиции
            board[7][6] = blackKing; // Перемещаем короля на g8
            board[7][5] = blackRook; // Перемещаем ладью на f8

            // Обновляем состояние фигур
            blackKing.check = false;
            blackRook.check = false;
            nowPlayer = "White"; // Переход хода

            return true; // Рокировка успешна
        }

        return false;
    }

    public static ChessPiece getPieceAt(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return board[x][y];
        }
        return null;
    }

    public static boolean isPathClear(ChessBoard board, int startLine, int startColumn, int toLine, int toColumn) {
        // Проверяем, что фигура не пытается перемещаться в свою начальную позицию
        if (startLine == toLine && startColumn == toColumn) {
            return false;
        }

        if (startLine == toLine) {  // Горизонтальное движение
            int step = toColumn > startColumn ? 1 : -1;
            for (int i = startColumn + step; i != toColumn; i += step) {
                if (board.board[startLine][i] != null) {  // Путь заблокирован
                    return false;
                }
            }
        } else if (startColumn == toColumn) {  // Вертикальное движение
            int step = toLine > startLine ? 1 : -1;
            for (int i = startLine + step; i != toLine; i += step) {
                if (board.board[i][startColumn] != null) {  // Путь заблокирован
                    return false;
                }
            }
        }
        // Если движение по диагонали
        else if (Math.abs(toLine - startLine) == Math.abs(toColumn - startColumn)) {
            int rowStep = toLine > startLine ? 1 : -1;
            int colStep = toColumn > startColumn ? 1 : -1;

            int currentRow = startLine + rowStep;
            int currentCol = startColumn + colStep;

            while (currentRow != toLine && currentCol != toColumn) {
                if (board.board[currentRow][currentCol] != null) {  // Путь заблокирован
                    return false;
                }
                currentRow += rowStep;
                currentCol += colStep;
            }
        } else {
            return false;
        }

        return true;
    }

    public boolean isOccupied(int toLine, int toColumn) {
        return board[toLine][toColumn] != null;

    }
}
