package com.cherniayev.sudokuscannersolver;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SudokuBoard extends View {
    private final int boardColor;
    private final Paint boardColorPaint = new Paint();
    private int cellSize;

    public SudokuBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //Робимо масив в якому будуть зберігатися усі атрибути для класу SudokuBoard
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuBoard,
                0, 0);

        //Екстрактимо атрибути щоб присвоїти їх нашим змінним у коді
        try {
            boardColor = a.getInteger(R.styleable.SudokuBoard_boardColour, 0);
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

        canvas.drawRect(0, 0, getWidth(), getHeight(), boardColorPaint);
        drawField(canvas);
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
            canvas.drawLine(0, cellSize * r, getWidth(), cellSize * r, boardColorPaint);
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
}