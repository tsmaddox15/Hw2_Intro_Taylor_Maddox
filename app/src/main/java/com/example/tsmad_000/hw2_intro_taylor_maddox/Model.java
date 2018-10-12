package com.example.tsmad_000.hw2_intro_taylor_maddox;

import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Model {
    //values for total time and value we need to add to date for new value.
    long date;
    long cDate;

    //The value that needs to be redone/undone.
    long undo;
    long redo;

    Command c = new Command();
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss:a");

    public String setTime(long date, View v) {
        this.date = date;

        ////Used to get new time
        Calendar cal;
        Date newDate;
        Date timeBehind;
        String stringNewDate;

        //Based on what button was pressed, we change our cal and date for new time.
        //The time in milliseconds that was used is added to the undo list to be used for later.
        switch (v.getId()) {
            case R.id.hourM1:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.HOUR, -1);
                cDate = -3600000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + date);
                c.Push(-3600000);
                return stringNewDate;

            case R.id.hourP1:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.HOUR, +1);
                cDate = 3600000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + stringNewDate);
                c.Push(3600000);
                return stringNewDate;

            case R.id.minuteM1:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.MINUTE, -1);
                cDate = -60000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + stringNewDate);
              
                c.Push(-60000);
                return stringNewDate;

            case R.id.minuteP1:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.MINUTE, +1);
                cDate = 60000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + stringNewDate);
                c.Push(60000);
                return stringNewDate;

            case R.id.secM1:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.SECOND, -5);
                cDate = -5000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                c.Push(-5000);
                return stringNewDate;

            case R.id.secPlus1:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.SECOND, +5);
                cDate = 5000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + stringNewDate);
                c.Push(5000);
                return stringNewDate;

            case R.id.dayMinus1:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.DAY_OF_MONTH, -1);
                cDate = -86400000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                c.Push(-86400000);
                return stringNewDate;

            case R.id.dayPlus1:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                cDate = 86400000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + stringNewDate);
                c.Push(86400000);
                return stringNewDate;

            case R.id.monthM1:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.MONTH, -1);
                cDate = 0;
                cDate += -657000000;
                cDate += -657000000;
                cDate += -657000000;
                cDate += -657000000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + date);
                c.Push(-657000000);
                return stringNewDate;

            case R.id.monthP1:
                cal = Calendar.getInstance();
                newDate = new Date();
                newDate.setTime(date);
                cal.setTime(newDate);
                cal.add(Calendar.MONTH, +1);
                cDate = 0;
                cDate += 657000000;
                cDate += 657000000;
                cDate += 657000000;
                cDate += 657000000;
                timeBehind = cal.getTime();
                stringNewDate = sdf.format(timeBehind);
                Log.d("DateString: ", "String " + date);
                c.Push(657000000);
                return stringNewDate;
            //Calls command class to find the next item to undo.
            //What to do is determined by what value was added to date that is stored in a list.
            case R.id.undo1:
                long undo = c.Undo();
                if (undo == 0) {
                    setUndo(0);
                }
                if (undo == -3600000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.HOUR, +1);
                    setUndo(undo);
                } else if (undo == 3600000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.HOUR, -1);
                    setUndo(undo);
                } else if (undo == -60000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.MINUTE, +1);
                    setUndo(undo);
                } else if (undo == 60000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.MINUTE, -1);
                    setUndo(undo);
                } else if (undo == -5000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.SECOND, +5);
                    setUndo(undo);
                } else if (undo == 5000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.SECOND, -5);
                    setUndo(undo);
                } else if (undo == -86400000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.DAY_OF_MONTH, +1);
                    setUndo(undo);
                } else if (undo == 86400000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    setUndo(undo);
                } else if (undo == -657000000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.MONTH, +1);
                    setUndo(undo);
                } else if (undo == 657000000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.MONTH, -1);
                    setUndo(undo);

                }
                break;
            //Calls command class to find the next item to redo.
            //What to do is determined by what value was added to date that is stored in a list.
            case R.id.redo1:
                long redo = c.Redo();
                if (redo == 0) {
                    setRedo(0);
                }
                if (redo == -3600000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.HOUR, -1);
                    setRedo(redo);
                } else if (redo == 3600000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.HOUR, +1);
                    setRedo(redo);
                } else if (redo == -60000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.MINUTE, -1);
                    setRedo(redo);
                } else if (redo == 60000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.MINUTE, +1);
                    date += 60000;
                } else if (redo == -5000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.SECOND, -5);
                    setRedo(redo);
                } else if (redo == 5000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.SECOND, 5);
                    setRedo(redo);
                } else if (redo == -86400000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    setRedo(redo);
                } else if (redo == 86400000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.DAY_OF_MONTH, +1);
                    setRedo(redo);
                } else if (redo == -657000000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.MONTH, -1);
                    setRedo(redo);
                } else if (redo == 657000000) {
                    cal = Calendar.getInstance();
                    newDate = new Date();
                    newDate.setTime(date);
                    cal.setTime(newDate);
                    cal.add(Calendar.MONTH, +1);
                    setRedo(redo);
                }
                break;


        }

        return "";
    }


    public void setUndo(long undo) {
        this.undo = undo;
    }

    public void setRedo(long undo) {
        this.redo = undo;
    }

    public long getDate() {
        return cDate;
    }

    public long getUndo() {
        return this.undo;
    }


    public long getRedo() {
        return this.redo;
    }
}
