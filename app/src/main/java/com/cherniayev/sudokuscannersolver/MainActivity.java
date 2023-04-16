package com.cherniayev.sudokuscannersolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SudokuBoard gameBoard;
    private Solver gameBoardSolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameBoard = findViewById(R.id.SudokuBoard);
        gameBoardSolver = gameBoard.getSolver();
    }

    public void buttonOnePress(View view) {
        gameBoardSolver.setNumberPosition(1);
        gameBoard.invalidate();
    }

    public void buttonTwoPress(View view) {
        gameBoardSolver.setNumberPosition(2);
        gameBoard.invalidate();
    }

    public void buttonThreePress(View view) {
        gameBoardSolver.setNumberPosition(3);
        gameBoard.invalidate();
    }

    public void buttonFourPress(View view) {
        gameBoardSolver.setNumberPosition(4);
        gameBoard.invalidate();
    }

    public void buttonFivePress(View view) {
        gameBoardSolver.setNumberPosition(5);
        gameBoard.invalidate();
    }

    public void buttonSixPress(View view) {
        gameBoardSolver.setNumberPosition(6);
        gameBoard.invalidate();
    }

    public void buttonSevenPress(View view) {
        gameBoardSolver.setNumberPosition(7);
        gameBoard.invalidate();
    }

    public void buttonEightPress(View view) {
        gameBoardSolver.setNumberPosition(8);
        gameBoard.invalidate();
    }

    public void buttonNinePress(View view) {
        gameBoardSolver.setNumberPosition(9);
        gameBoard.invalidate();
    }
}