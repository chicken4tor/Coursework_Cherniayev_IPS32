package com.cherniayev.sudokuscannersolver;

public class Solver {

    private static int selectedRow;
    private static int selectedColumn;

    Solver() {
        selectedRow = -1;
        selectedColumn = -1;
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
