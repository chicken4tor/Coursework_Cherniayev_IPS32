package com.cherniayev.sudokuscannersolver;

import java.util.ArrayList;
import java.util.Random;

public class Solver {

    int[][] board;
    ArrayList<ArrayList<Object>> emptyBoxIndex;
    int selectedRow;
    int selectedColumn;
    int K = 20; // No. Of missing digits

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

    public void getEmptyBoxIndexes() {
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
                        if (this.board[r][c] == this.board[row][column]
                                && row != r && column != c) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean solveSudoku(SudokuBoard display) {
        int row = -1, column = -1;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (this.board[r][c] == 0) {
                    row = r;
                    column = c;
                    break;
                }
            }
        }

        if (row == -1 || column == -1) {
            return true;
        }

        for (int i = 1; i < 10; i++) {
            this.board[row][column] = i;
            display.invalidate();

            if (checkСorrectness(row, column)) {
                if (solveSudoku(display)) {
                    return true;
                }
            }

            this.board[row][column] = 0;
        }

        return false;

    }

    public void cleanBoard() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                board[r][c] = 0;
            }
        }

        this.emptyBoxIndex = new ArrayList<>();
    }

    public void generateSudoku(){
        cleanBoard();

        fillValues();
    }

    // Sudoku Generator
    public void fillValues()
    {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, 3);

        // Remove Randomly K digits to make game
        removeKDigits();
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    void fillDiagonal()
    {

        for (int i = 0; i<9; i=i+3)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i<3; i++)
            for (int j = 0; j<3; j++)
                if (board[rowStart+i][colStart+j]==num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    void fillBox(int row,int col)
    {
        int num;
        for (int i=0; i<3; i++)
        {
            for (int j=0; j<3; j++)
            {
                do
                {
                    num = randomGenerator(9);
                }
                while (!unUsedInBox(row, col, num));

                board[row+i][col+j] = num;
            }
        }
    }

    // Random generator
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }

    // Check if safe to put in cell
    boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%3, j-j%3, num));
    }

    // check in the row for existence
    boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j<9; j++)
            if (board[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i<9; i++)
            if (board[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    boolean fillRemaining(int i, int j)
    {
        //  System.out.println(i+" "+j);
        if (j>=9 && i<9-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=9 && j>=9)
            return true;

        if (i < 3)
        {
            if (j < 3)
                j = 3;
        }
        else if (i < 9-3)
        {
            if (j==(int)(i/3)*3)
                j =  j + 3;
        }
        else
        {
            if (j == 9-3)
            {
                i = i + 1;
                j = 0;
                if (i>=9)
                    return true;
            }
        }

        for (int num = 1; num<=9; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                board[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;

                board[i][j] = 0;
            }
        }
        return false;
    }

    // Remove the K no. of digits to
    // complete game
    public void removeKDigits()
    {
        int count = K;
        while (count != 0)
        {
            int cellId = randomGenerator(9*9)-1;

            // System.out.println(cellId);
            // extract coordinates i  and j
            int i = (cellId/9);
            int j = cellId%9;
            if (j != 0)
                j = j - 1;

            // System.out.println(i+" "+j);
            if (board[i][j] != 0)
            {
                count--;
                board[i][j] = 0;
            }
        }
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
