package com.example.tsmad_000.hw2_intro_taylor_maddox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class CanvasClock extends View {

    private int height, width = 0;
    private int padding = 0;
    private int fontSize = 0;
    private int numeralSpacing = 0;
    private int handTruncation, hourHandTruncation = 0;
    private int radius = 0;
    private Paint paint;
    private boolean isInit;
    private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private Rect rect = new Rect();


    public LinkedList<Calendar> newTime = new LinkedList<Calendar>();
    Calendar cal = Calendar.getInstance();
    float hour = cal.get(Calendar.HOUR_OF_DAY);
    //    float min = cal.get(Calendar.MINUTE);
    float min = 0;
    float sec = 0;
    //+ = 0 , - = 1.
    int lastOpType;
//    long cDate;

    long cDate;

    public CanvasClock(Context context) {
        super(context);
    }

    public CanvasClock(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasClock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initClock() {
        height = getHeight();
        width = getWidth();
        padding = numeralSpacing + 50;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13,
                getResources().getDisplayMetrics());
        int min = Math.min(height, width);
        radius = min / 2 - padding;
        handTruncation = min / 20;
        hourHandTruncation = min / 7;
        paint = new Paint();
        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            initClock();
        }

        canvas.drawColor(Color.WHITE);
        drawCircle(canvas);
        drawCenter(canvas);
        drawNumeral(canvas);
        drawHands(canvas);

        postInvalidateDelayed(500);
        invalidate();
    }

    public int changeHands(long date, View v) {
        switch (v.getId()) {
            case R.id.hourM1:
                hour += -1;
                break;

            case R.id.hourP1:
                hour += +1;
                break;
            case R.id.minuteM1:
                min += -1;
                break;

            case R.id.minuteP1:
                min += +1;
                break;
            case R.id.secM1:
                sec += -5;


            case R.id.secPlus1:
                sec += +5;
                break;
        }

        invalidate();
        return 0;
    }

    public void drawHand(Canvas canvas, double loc, boolean isHour) {
        double angle = Math.PI * loc / 30 - Math.PI / 2;
        int handRadius = isHour ? radius - handTruncation - hourHandTruncation : radius - handTruncation;
        canvas.drawLine(width / 2, height / 2,
                (float) (width / 2 + Math.cos(angle) * handRadius),
                (float) (height / 2 + Math.sin(angle) * handRadius),
                paint);
    }

    public void drawHands(Canvas canvas) {
        Calendar c;
        c = Calendar.getInstance();

        if (c.get(Calendar.SECOND) + sec == 60) {
            sec += -60;
            min++;
        }
        if (c.get(Calendar.SECOND) + sec == 60 && lastOpType == 1) {
            sec += -60;
            min--;
        }
        if (c.get(Calendar.MINUTE) + min >= 60) {
            min += -60;
            hour++;
        } else if (c.get(Calendar.MINUTE) + min >= 60 && lastOpType == 1) {
            min += -60;
            hour--;
        }
        hour = hour > 12 ? hour - 12 : hour;
        drawHand(canvas, (hour + 2 / 60) * 5f, true);
        drawHand(canvas, c.get(Calendar.MINUTE) + min, false);
        drawHand(canvas, c.get(Calendar.SECOND) + sec, false);
    }

    public void drawNumeral(Canvas canvas) {
        paint.setTextSize(fontSize);

        for (int number : numbers) {
            String tmp = String.valueOf(number);
            paint.getTextBounds(tmp, 0, tmp.length(), rect);
            double angle = Math.PI / 6 * (number - 3);
            int x = (int) (width / 2 + Math.cos(angle) * radius - rect.width() / 2);
            int y = (int) (height / 2 + Math.sin(angle) * radius + rect.height() / 2);
            canvas.drawText(tmp, x, y, paint);
        }
    }

    public void drawCenter(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, 12, paint);
    }

    public void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(android.R.color.black));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, height / 2, radius + padding - 10, paint);
    }

}


