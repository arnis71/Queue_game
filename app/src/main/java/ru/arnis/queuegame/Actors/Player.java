package ru.arnis.queuegame.Actors;

import android.widget.FrameLayout;

import ru.arnis.queuegame.Actors.Actors;
import ru.arnis.queuegame.Base.Cell;
import ru.arnis.queuegame.R;

/**
 * Created by arnis on 15/05/16.
 */
public class Player extends Actors {

    public Player(int TTP)
    {
        this.timeToPass=TTP;
        currCell = new Cell(0,0,0);
    }

    public static Cell currCell;
    public static int currPos;
    public static FrameLayout player;
}
