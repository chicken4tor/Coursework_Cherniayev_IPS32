package com.cherniayev.sudokuscannersolver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SudokuBoard extends View {
    private final int boardColor;
    private final int cellFillColor;
    private final int cellHighligthColor;

    private final int numberColor;
    private final int numberColorSolved;

    private final Paint boardColorPaint = new Paint();
    private final Paint cellFillColorPaint = new Paint();
    private final Paint cellHighligthColorPaint = new Paint();
    private final Paint numberPaint = new Paint();

    private final Rect numberPaintBounds = new Rect();

    private int cellSize;
    private final Solver solver = new Solver();

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //Робимо масив в якому будуть зберігатися усі атрибути для класу SudokuBoard
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard,
                0, 0);

        //Екстрактимо атрибути щоб присвоїти їх нашим змінним у коді
        try {
            boardColor = a.getInteger(R.styleable.SudokuBoard_boardColor, 0);
            cellFillColor = a.getInteger(R.styleable.SudokuBoard_cellFillColor, 0);
            cellHighligthColor = a.getInteger(R.styleable.SudokuBoard_cellHighlightColor, 0);
            numberColor = a.getInteger(R.styleable.SudokuBoard_numberColor, 0);
            numberColorSolved = a.getInteger(R.styleable.SudokuBoard_numberColorSolved, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dimension = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        cellSize = dimension / 9;

        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Paint brush
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(16);
        //Paint bucket
        boardColorPaint.setColor(boardColor);
        boardColorPaint.setAntiAlias(true);

        cellFillColorPaint.setStyle(Paint.Style.FILL);
        cellFillColorPaint.setAntiAlias(true);
        cellFillColorPaint.setColor(cellFillColor);

        cellHighligthColorPaint.setStyle(Paint.Style.FILL);
        cellHighligthColorPaint.setAntiAlias(true);
        cellHighligthColorPaint.setColor(cellHighligthColor);

        colorCell(canvas, solver.getSelectedRow(), solver.getSelectedColumn());
        canvas.drawRect(0, 0, getWidth(), getHeight(), boardColorPaint);
        drawField(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isValid;

        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            solver.setSelectedRow((int) Math.ceil(y/cellSize));
            solver.setSelectedColumn((int) Math.ceil(x/cellSize));
            isValid = true;
        } else {
            isValid = false;
        }

        return isValid;
    }

    private void colorCell(Canvas canvas, int row, int column) {
        if (solver.getSelectedRow() != -1 && solver.getSelectedColumn() != -1) {
            canvas.drawRect((column - 1) * cellSize, 0, column * cellSize,
                    cellSize * 9, cellHighligthColorPaint);

            canvas.drawRect(0, (row - 1) * cellSize, cellSize * 9,
                    row * cellSize, cellHighligthColorPaint);

            canvas.drawRect((column - 1) * cellSize, (row - 1) * cellSize,
                    column * cellSize, row * cellSize, cellFillColorPaint);
        }

        invalidate();
    }

    private void drawField(Canvas canvas) {
        //Намалювати колонки
        for (int c = 0; c < 10; c++) {
            if (c % 3 == 0) {
                drawThickLine();
            } else {
                drawThinLine();
            }
            canvas.drawLine(cellSize * c, 0, cellSize * c, getWidth(), boardColorPaint);
        }

        //Намалювати рядки
        for (int r = 0; r < 10; r++) {
            if (r % 3 == 0) {
                drawThickLine();
            } else {
                drawThinLine();
            }
            canvas.drawLine(0, cellSize * r, getWidth(),
                    cellSize * r, boardColorPaint);
        }
    }

    private void drawThickLine() {
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(10);
        boardColorPaint.setColor(boardColor);
    }

    private void drawThinLine() {
        boardColorPaint.setStyle(Paint.Style.STROKE);
        boardColorPaint.setStrokeWidth(4);
        boardColorPaint.setColor(boardColor);
    }

    private void drawNumbers(Canvas canvas) {

        
    }
}