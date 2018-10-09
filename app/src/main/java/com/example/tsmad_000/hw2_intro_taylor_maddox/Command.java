package com.example.tsmad_000.hw2_intro_taylor_maddox;

import java.util.LinkedList;
import java.util.Queue;

public class Command {
    //List for current commands to be undone.
    LinkedList<Long> commandQ = new LinkedList<>();

    //List for commands to be redone.
    LinkedList<Long> undone = new LinkedList<>();

    //Used to allow a redo to happen can only happen in an undo has happened an no further
    //changes to the clock hav been done
    boolean allowRedo = false;

    int sizeQ = commandQ.size();

    public void Command() {

    }

    /*
    Called by all buttons to edit clock besides redo and undo.
    If we were allowing redos, it sets that to false and clears the undone list.
    This is done to properly determine when redo is allowed.
    It then adds the command the user picked to the command list.
     */
    public void Push(long lastCommand) {
        if(allowRedo == true){
            allowRedo = false;
            undone.clear();
        }
        commandQ.add(lastCommand);
    }

    /*
    Called whenever undo is pressed
    if empty return nothing. Allow redo since there is something to redo now.
    returns the amount of milliseconds that was lasted added to the list.
     */
    public long Undo() {
        if (commandQ.isEmpty()) {
            return 0;
        } else {
            long undo = commandQ.pollLast();
            undone.add(undo);
            allowRedo = true;
            return undo;
        }

    }

    /*
    If allow redo is false or if list is empty return 0.
    Else we get the last item in the list to do a redo.
     */
    public long Redo() {
        if (allowRedo == false) {
            return 0;
        } else if (undone.isEmpty()) {
            return 0;
        } else {
            long redo = undone.pollLast();
            commandQ.add(redo);
            return redo;
        }
    }

}
