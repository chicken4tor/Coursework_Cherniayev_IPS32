package com.cherniayev.sudokuscannersolver;

import java.util.ArrayList;

public class Solver {

    int[][] board;
    ArrayList<ArrayList<Object>> emptyBoxIndex;
    int selectedRow;
    int selectedColumn;

    Solver() {
        selectedRow = -1;
        selectedColumn = -1;

        board = new int[9][9];

        //Заповнюємо нашу доску нулями. 0 = пуста клітинка
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                board[r][c] = 0;
            }
        }

        emptyBoxIndex = new ArrayList<>();
    }

    private void getEmptyBoxIndexes() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (this.board[r][c] == 0) {
                    this.emptyBoxIndex.add(new ArrayList<>());
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size() - 1).add(r);
                    this.emptyBoxIndex.get(this.emptyBoxIndex.size() - 1).add(c);
                }
            }
        }
    }

    public void setNumberPosition(int num) {
        if (this.selectedRow != -1 && this.selectedColumn != -1) {
            //Якщо користувач помилився, то він може натиснути на той самий номер у вибраній
            //клітинці щоб зробити клітинку знов пустою
            if (this.board[this.selectedRow - 1][this.selectedColumn - 1] == num) {
                this.board[this.selectedRow - 1][this.selectedColumn - 1] = 0;
            } else {
                //В зворотньому випадку присвоїти клітинці значення, яке вибрав користувач
                this.board[this.selectedRow - 1][this.selectedColumn - 1] = num;
            }
        }
    }

    private boolean checkСorrectness(int row, int column) {
        //Умова щоб перевіряти тільки не пусті клітиночки
        if (this.board[row][column] > 0) {
            for (int i = 0; i < 9; i++) {
                //Перевірка рядка
                // row != i - Умова щоб не перевіряти одну й ту ж саму клітинку
                if (this.board[i][column] == this.board[row][column] && row != i) {
                    return false;
                }

                //Перевірка колонки
                if (this.board[row][i] == this.board[row][column] && column != i) {
                    return false;
                }

                int boxRow = row / 3;
                int boxColumn = column / 3;

                for (int r = boxRow * 3; r < boxRow * 3 + 3; r++) {
                    for (int c = boxColumn * 3; c < boxColumn * 3 + 3; c++) {
                        if (this.board[r][c] == this.board[row][boxColumn]
                                && row != r && column != c) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public ArrayList<ArrayList<Object>> getEmptyBoxIndex() {
        return this.emptyBoxIndex;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int r) {
        selectedRow = r;
    }

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public void setSelectedColumn(int c) {
        selectedColumn = c;
    }

}
