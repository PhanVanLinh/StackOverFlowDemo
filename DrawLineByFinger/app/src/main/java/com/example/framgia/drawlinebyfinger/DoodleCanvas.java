package com.example.framgia.drawlinebyfinger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DoodleCanvas  extends View {

    private Paint mPaint;
    private Path mPath;

    public DoodleCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(100);
        mPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}