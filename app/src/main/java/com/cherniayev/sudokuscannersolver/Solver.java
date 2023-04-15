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
                board [r][c] = 0;
            }
        }

        emptyBoxIndex = new ArrayList<>();
    }

    private void getEmptyBoardIndxs() {
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

    private void setNumberPosition(int num) {
        if (this.selectedRow != -1 && this.selectedColumn != -1) {
            //Якщо користувач помилився, то він може натиснути на той самий номер у вибраній
            //клітинці щоб зробити клітинку знов пустою
            if (this.board[this.selectedRow][this.selectedColumn] == num) {
                this.board[this.selectedRow][this.selectedColumn] = 0;
            } else {
                //В зворотньому випадку присвоїти клітинці значення, яке вибрав користувач
                this.board[this.selectedRow][this.selectedColumn] = num;
            }
        }
    }

    public int[][] getBoard(){
        return this.board;
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

    public void setSelectedColumn(int c) { selectedColumn = c; }

}
