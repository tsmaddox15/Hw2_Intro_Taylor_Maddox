package com.example.tsmad_000.hw2_intro_taylor_maddox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;

public class analogView extends AppCompatActivity {
    long date = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss:a");
    String dateString = sdf.format(date);
    CanvasClock tClock;
    Command c;
    Model m;
    Button hourM;
    Button hourP;
    Button minM;
    Button minP;
    Button secM;
    Button secP;
    Button dayP;
    Button dayM;
    Button monthM;
    Button monthP;
    Button undo;
    Button redo;

    Controller control;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analog_view);
        c = new Command();
        m = new Model();
        control = new Controller(m);

        tClock = findViewById(R.id.aClock);
        hourM = (Button) findViewById(R.id.hourM1);
        hourP = (Button) findViewById(R.id.hourP1);
        minM = (Button) findViewById(R.id.minuteM1);
        minP = (Button) findViewById(R.id.minuteP1);
        secM = (Button) findViewById(R.id.secM1);
        secP = (Button) findViewById(R.id.secPlus1);
        monthM = (Button) findViewById(R.id.monthM1);
        monthP = (Button) findViewById(R.id.monthP1);
        dayM = (Button) findViewById(R.id.dayMinus1);
        dayP = (Button) findViewById(R.id.dayPlus1);
        undo = (Button) findViewById(R.id.undo1);
        redo = (Button) findViewById(R.id.redo1);

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                date += 1000;
                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss a");
                                String dateString = sdf.format(date);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    /*
     Millisecond Conversions:
     3600000 = a hour
     60000 = a minute
     5000 = 5 seconds
     86400000 = a day
     657000000*4 + 7200000 = A month
     */
    public void changeTime(View v) {
        String test = control.setTime(date, v);
        long cDate = tClock.changeHands(date, v);
        //Has a case for Plus(P), and Minus(M) for each button.
        //Also includes redo and undo button.
        switch (v.getId()) {
            case R.id.hourM1:
                date += cDate;
                break;

            case R.id.hourP1:
                date += cDate;
                break;

            case R.id.minuteM1:
                date += cDate;
                break;

            case R.id.minuteP1:
                date += cDate;
                break;

            case R.id.secM1:
                date += cDate;
                break;

            case R.id.secPlus1:
                date += cDate;
                break;

            case R.id.dayMinus1:
                date += cDate;
                break;

            case R.id.dayPlus1:
                date += cDate;
                break;

            case R.id.monthM1:
                date += cDate;
                break;

            case R.id.monthP1:
                date += cDate;
                break;

                /*
                Returns the milliseconds that were last added to a list maintained in Command.java
                Based on that amount of time, goes through if,
                else to do the opposite command to the clock.
                Millisecond Conversions
                3600000 = a hour
                60000 = a minute
                5000 = 5 seconds
                86400000 = a day
                657000000*4 + 7200000 = A month
                 */
            case R.id.undo1:
                long undo = control.getUndo();
                if (undo == -3600000) {
                    date += 3600000;
                } else if (undo == 3600000) {
                    date += -3600000;
                } else if (undo == -60000) {
                    date += 60000;
                } else if (undo == 60000) {
                    date += -60000;
                } else if (undo == -5000) {
                    date += 5000;
                } else if (undo == 5000) {
                    date += -5000;
                } else if (undo == -86400000) {
                    date += 86400000;
                } else if (undo == 86400000) {
                    date += -86400000;
                } else if (undo == -657000000) {
                    date += 657000000;
                    date += 657000000;
                    date += 657000000;
                    date += 657000000;
                } else if (undo == 657000000) {
                    date += -657000000;
                    date += -657000000;
                    date += -657000000;
                    date += -657000000;

                }
                break;

            /*
                Returns the milliseconds that were last added to a list maintained in Command.java
                Based on that amount of time, goes through if,
                else to do the same command to the clock.
                Millisecond Conversions:
                3600000 = a hour
                60000 = a minute
                5000 = 5 seconds
                86400000 = a day
                657000000*4 + 7200000 = A month
                 */
            case R.id.redo1:
                long redo = control.getRedo();
                if (redo == -3600000) {
                    date += -3600000;
                } else if (redo == 3600000) {
                    date += 3600000;
                } else if (redo == -60000) {
                    date += -60000;
                } else if (redo == 60000) {
                    date += 60000;
                } else if (redo == -5000) {
                    date += -5000;
                } else if (redo == 5000) {
                    date += 5000;
                } else if (redo == -86400000) {
                    date += -86400000;
                } else if (redo == 86400000) {
                    date += 86400000;
                } else if (redo == -657000000) {
                    date += -657000000;
                    date += -657000000;
                    date += -657000000;
                    date += -657000000;
                } else if (redo == 657000000) {
                    date += 657000000;
                    date += 657000000;
                    date += 657000000;
                    date += 657000000;
                }
                break;
        }
    }

    public void newView(View v) {
        switch (v.getId()) {
            case R.id.newD1:
                Intent intent = new Intent(this, MainActivity.class);
                // It will open the activity
                startActivity(intent);
                break;
            case R.id.newA1:
                Intent intent2 = new Intent(this, analogView.class);
                // It will open the activity
                startActivity(intent2);
                break;

        }
    }
}
