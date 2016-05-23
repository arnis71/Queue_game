package ru.arnis.queuegame.Base;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import ru.arnis.queuegame.Actors.Actors;
import ru.arnis.queuegame.Actors.Cashier;
import ru.arnis.queuegame.Actors.Player;
import ru.arnis.queuegame.Game3Cols;

/**
 * Created by arnis on 08/05/16.
 */
public class Cell {
    public final float x;
    public final float y;

    public boolean firstInQueue=false;

    public int pos;

    public View look;

    public Actors actors;

    private boolean occupied;

    public void setOccupied(boolean set)
    {
        occupied=set;
        if (firstInQueue & set)
        Cashier.posToCashier(pos).mOnCellFirstPositionOccupiedListener.onEvent();
           // mOnCellFirstPositionOccupiedListener.onEvent();
    }

    public boolean getOccupied(){
        return occupied;
    }

public void wipeCell(){
    this.look=null;
    this.actors = null;
    setOccupied(false);
}

    public Cell(float _x, float _y, int _pos)
    {
        this.x = _x;
        this.y = _y;
        this.pos = _pos;
        occupied = false;

    }

    public void migrateFrom(Cell cell)
    {
        this.look = cell.look;
        this.actors = cell.actors;
        this.setOccupied(cell.occupied);
        cell.wipeCell();

    }

    public static void notifyCells(ArrayList<Cell> array, int index) {
        do {
            array.get(index).migrateFrom(array.get(index+1));
            if (array.get(index).look== Player.player){
                Player.currPos--;
                Player.currCell=array.get(Player.currPos);
            }
            try {
                Meth.animateTo(array.get(index).look, array.get(index).x, array.get(index).y, 200);
            }
            catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
            index++;
            if (index==GameSet.lengthOfCol-1) break;
           // Log.d("happy", "notifyCells at thread" + Thread.currentThread().getName());

        }while(!array.get(index).occupied & array.get(index+1).occupied);
    }
}
