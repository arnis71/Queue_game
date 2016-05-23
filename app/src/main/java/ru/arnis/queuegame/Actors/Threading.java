package ru.arnis.queuegame.Actors;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.arnis.queuegame.Base.Cell;
import ru.arnis.queuegame.Base.GameSet;
import ru.arnis.queuegame.Base.Meth;
import ru.arnis.queuegame.Game3Cols;

/**
 * Created by arnis on 14/05/16.
 */
public class Threading implements Runnable {

    private final ArrayList<Cell> array;
    ProgressBar pb;
    static public boolean status=true;
    Handler hand;
    int i=0;
    int cashierSpeed;

    public Handler mHandler = new Handler() {
    };

    public Threading(ProgressBar pb, Handler hand, ArrayList<Cell> array, int speed) {
        this.pb=pb;
        this.hand=hand;
        this.array = array;
        this.cashierSpeed = speed;
    }


    @Override
    public void run() {

        pb.setMax(array.get(0).actors.timeToPass);


        while(status & !Thread.currentThread().isInterrupted() & array.get(0)!=null & array.get(0).actors.timeToPass>0)
        {

            try {
                Thread.sleep(cashierSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("happy", "thread " + Thread.currentThread().getName());
           // Log.d("happy3", status + " "+ !Thread.interrupted()+ " "+array.get(0)+" "+array.get(0).actors.timeToPass);
            hand.post(new Runnable(){
                @Override
                public void run() {
                    try {
                        pb.setProgress(array.get(0).actors.timeToPass);
                        array.get(0).actors.timeToPass--;
                    }catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                }
            });

        }
        if (!status)
            return;

        //Log.d("happy", "pass at thread" + Thread.currentThread().getName());
        pass(array);


    }

    public void pass(final ArrayList<Cell> array){
        mHandler.post(new Runnable(){
            @Override
            public void run() {
           //     Log.d("happy", "handler at thread" + Thread.currentThread().getName());
                array.get(0).look.setVisibility(View.GONE);
                array.get(0).wipeCell();
                //array.get(0).actors =null;
                //if (array.get(0).actors!=null)
                GameSet.actors--;
                //Meth.decArrayCount(array);
               // array.get(0).setOccupied(false);
                if (array.get(1).getOccupied()){
                    Cell.notifyCells(array,0);}
            }
        });
    }
}
