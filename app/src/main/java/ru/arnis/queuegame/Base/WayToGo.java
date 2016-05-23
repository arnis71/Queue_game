package ru.arnis.queuegame.Base;

import java.util.ArrayList;

/**
 * Created by arnis on 08/05/16.
 */
public class WayToGo {
    public int index;
    public ArrayList<Cell> array;
    public WayToGo(int s, ArrayList<Cell> cell)
    {
        index = s;
        array = cell;
    }
}
