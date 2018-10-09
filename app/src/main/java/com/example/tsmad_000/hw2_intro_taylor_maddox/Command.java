package com.example.tsmad_000.hw2_intro_taylor_maddox;

import java.util.LinkedList;
import java.util.Queue;

public class Command {
    LinkedList<Long> commandQ = new LinkedList<>();
    LinkedList<Long> undone = new LinkedList<>();

    int sizeQ = commandQ.size();
    public void Command(){

    }
    public void Push(long lastCommand) {
        commandQ.add(lastCommand);
    }
    public long Undo(){
        if(commandQ.isEmpty()){
            return 0;
        }
        else {
            long undo = commandQ.pollLast();
            undone.add(undo);
//            undo = (-1)*undo;
            return undo;
        }

    }
    public long redo(){
        long redo = undone.pollLast();
        return redo;
    }

}
