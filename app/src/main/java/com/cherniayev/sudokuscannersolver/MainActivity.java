package com.cherniayev.sudokuscannersolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {

    private SudokuBoard gameBoard;
    private Solver gameBoardSolver;

    private Button solveButton, createButton, scanButton, clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameBoard = findViewById(R.id.SudokuBoard);
        gameBoardSolver = gameBoard.getSolver();

        solveButton = findViewById(R.id.solveButton);
        createButton = findViewById(R.id.createButton);
        scanButton = findViewById(R.id.scanButton);
        clearButton = findViewById(R.id.clearButton);
    }

    static {
        if (OpenCVLoader.initDebug()) {
            Log.d("Check", "OpenCv configured successfully");
        } else {
            Log.d("Check", "OpenCv doesn’t configured successfully");
        }
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

    public void buttonSolvePress(View view) {
        gameBoardSolver.getEmptyBoxIndexes();

        SolveBoardThread solveBoardThread = new SolveBoardThread();

        new Thread(solveBoardThread).start();

        gameBoard.invalidate();
    }

    public void buttonScanPress(View view) {
        Intent intent = new Intent(this, activity_camera.class);
        startActivity(intent);
    }

    public void buttonClearPress(View view) {
        gameBoardSolver.cleanBoard();
        gameBoard.invalidate();
    }

    public void buttonCreatePress(View view) {

    }

    class SolveBoardThread implements Runnable {
        @Override
        public void run() {
            gameBoardSolver.solveSudoku(gameBoard);
        }
    }
}