package com.example.tsmad_000.hw2_intro_taylor_maddox;

import android.view.View;

public class Controller {
    private Model m;

    public Controller(Model model) {
        this.m = model;
    }

    public String setTime(long date, View v) {
        String newTime = m.setTime(date, v);
        return newTime;
    }

    public void setUndo(long undo) {
        m.setUndo(undo);
    }

    public void setRedo(long undo) {
        m.setRedo(undo);
    }

    public long getDate() {
        return m.getDate();
    }

    public long getUndo() {
        return m.getUndo();
    }


    public long getRedo() {
        return m.getRedo();
    }


}
