package ru.arnis.queuegame.Base;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by arnis on 22/05/16.
 */
public interface GameMeth {
    public void playerJump(ArrayList<Cell> column);
    public boolean checkBehind();
    public void spawnAndGo();
    public void runPB(final ProgressBar pb, final Handler hand, final ArrayList<Cell> array, int speed);
}
