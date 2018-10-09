package com.example.tsmad_000.hw2_intro_taylor_maddox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //    long date = System.currentTimeMillis();
//    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh-mm-ss a");
//    String dateString = sdf.format(date);
//    TextClock dClock;
    TextView tClock;
//    long date = System.currentTimeMillis();
    long date = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss:a");
    SimpleDateFormat sdfT = new SimpleDateFormat("hh:mm:ss:a");
    SimpleDateFormat sdfD = new SimpleDateFormat("MMM dd yyyy");
    String dateString = sdf.format(date);
    ;
    //    String time;
    Command c;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = new Command();
        tClock = (TextView) findViewById(R.id.dClock);
        hourM = (Button) findViewById(R.id.hourM);
        hourP = (Button) findViewById(R.id.hourP);
        minM = (Button) findViewById(R.id.minuteM);
        minP = (Button) findViewById(R.id.minuteP);
        secM = (Button) findViewById(R.id.secM);
        secP = (Button)findViewById(R.id.secPlus);
        monthM = (Button)findViewById(R.id.monthM);
        monthP = (Button)findViewById(R.id.monthP);
        dayM = (Button)findViewById(R.id.dayMinus);
        dayP = (Button)findViewById(R.id.dayPlus);
        undo = (Button)findViewById(R.id.undo);


//        dClock = (TextClock)findViewById(R.id.dClock1);
//        dateString = sdf.format(date);
//        Log.d("DateString: ", "Date " + date);
//        dClock.setChar  ;
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                TextView tClock = (TextView) findViewById(R.id.dClock);
                                date += 1000;
                                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss a");
                                String dateString = sdf.format(date);
                                tClock.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

    }

    public void changeTime(View v) {
        Calendar cal;
        Date newDate;
        Date timeBehind;
        String stringNewDate;
        switch (v.getId()) {
            case R.id.hourM:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.HOUR, -1);
                date += -3600000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + date);
                tClock.setText(stringNewDate);
                c.Push(-3600000);
                break;

            case R.id.hourP:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.HOUR, +1);
                date += 3600000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + stringNewDate);
                tClock.setText(stringNewDate);
                c.Push(3600000);
                break;

            case R.id.minuteM:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.MINUTE, -1);
                date += -60000 ;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + stringNewDate);
                tClock.setText(stringNewDate);
                c.Push(-60000);
                break;

            case R.id.minuteP:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.MINUTE, +1);
                date += 60000 ;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + stringNewDate);
                tClock.setText(stringNewDate);
                c.Push(60000);
                break;
            case R.id.secM:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.SECOND, -5);
                date += -5000 ;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                tClock.setText(stringNewDate);
                c.Push(-5000);
                break;
            case R.id.secPlus:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.SECOND, +5);
                date += 5000 ;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + stringNewDate);
                tClock.setText(stringNewDate);
                c.Push(5000);
                break;
            case R.id.dayMinus:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.DAY_OF_MONTH, -1);
                date += -86400000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                tClock.setText(stringNewDate);
                c.Push(-86400000);
                break;
            case R.id.dayPlus:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                date += 86400000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + stringNewDate);
                tClock.setText(stringNewDate);
                c.Push(86400000);
                break;
            case R.id.monthM:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.MONTH, -1);
                date +=  -657000000;
                date +=  -657000000;
                date +=  -657000000;
                date +=  -657000000;
                date +=  -7200000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + date);
                tClock.setText(stringNewDate);
                c.Push(-657000000);
                break;
            case R.id.monthP:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.MONTH, +1);
                date +=  657000000;
                date +=  657000000;
                date +=  657000000;
                date +=  657000000;
                date +=  7200000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + date);
                tClock.setText(stringNewDate);
                c.Push(657000000);
                break;
            case R.id.undo:
           long undo = c.Undo();
           if(undo == -3600000) {
               cal = Calendar.getInstance();
               newDate = new Date();
               newDate.setTime(date);
               cal.setTime(newDate);
               cal.add(Calendar.HOUR, +1);
               date += 3600000;
           }
           else if(undo == 3600000){
               cal = Calendar.getInstance();
               newDate = new Date();
               newDate.setTime(date);
               cal.setTime(newDate);
               cal.add(Calendar.HOUR, -1);
               date += -3600000;
               }
           else if(undo == -60000){
               cal = Calendar.getInstance();
               newDate = new Date();
               newDate.setTime(date);
               cal.setTime(newDate);
               cal.add(Calendar.MINUTE, +1);
               date += 60000;
           }
           else if(undo == 60000){
               cal = Calendar.getInstance();
               newDate = new Date();
               newDate.setTime(date);
               cal.setTime(newDate);
               cal.add(Calendar.MINUTE, -1);
               date += -60000;
           }
           else if(undo == -5000){
               cal = Calendar.getInstance();
               newDate = new Date();
               newDate.setTime(date);
               cal.setTime(newDate);
               cal.add(Calendar.SECOND, +5);
               date += 5000;
           }
           else if(undo == 5000){
               cal = Calendar.getInstance();
               newDate = new Date();
               newDate.setTime(date);
               cal.setTime(newDate);
               cal.add(Calendar.SECOND, -5);
               date += -5000;
           }
           else if(undo == -86400000){
               cal = Calendar.getInstance();
               newDate = new Date();
               newDate.setTime(date);
               cal.setTime(newDate);
               cal.add(Calendar.DAY_OF_MONTH, +1);
               date += 86400000;
           }
           else if(undo == 86400000){
               cal = Calendar.getInstance();
               newDate = new Date();
               newDate.setTime(date);
               cal.setTime(newDate);
               cal.add(Calendar.DAY_OF_MONTH, -1);
               date += -86400000;
           }
           else if(undo == -657000000){
               cal = Calendar.getInstance();
               newDate = new Date();
               newDate.setTime(date);
               cal.setTime(newDate);
               cal.add(Calendar.MONTH, +1);
               date += 657000000;
               date += 657000000;
               date += 657000000;
               date += 657000000;
               date +=  7200000;
           }
           else if(undo == 657000000){
               cal = Calendar.getInstance();
               newDate = new Date();
               newDate.setTime(date);
               cal.setTime(newDate);
               cal.add(Calendar.MONTH, -1);
               date += -657000000;
               date += -657000000;
               date += -657000000;
               date += -657000000;
               date +=  -7200000;
           }

        }
    }
}
