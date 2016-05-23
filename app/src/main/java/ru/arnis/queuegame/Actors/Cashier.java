package ru.arnis.queuegame.Actors;

import java.util.ArrayList;

/**
 * Created by arnis on 08/05/16.
 */
public class Cashier{
    public int speed;
    public static ArrayList<Cashier> cashiers = new ArrayList<>();
    public boolean active = true;


    public Cashier(int _speed) //max 100
    {
        this.speed = 100-_speed;
        cashiers.add(this);
    }

    public static void reset(){
        cashiers = new ArrayList<>();
    }

    public OnCellFirstPositionOccupiedListener mOnCellFirstPositionOccupiedListener;

    public void setOnCellFirstPositionOccupiedListener(OnCellFirstPositionOccupiedListener listener)
    {
        mOnCellFirstPositionOccupiedListener = listener;
    }

public static Cashier posToCashier(int pos){
    if (pos==1)
        return cashiers.get(0);
    else if (pos==2)
        return cashiers.get(1);
    else if (pos==3)
        return cashiers.get(2);
    else if (pos==4)
        return cashiers.get(3);
    else return null;
}
//    @Nullable
//    public static Cashier posToCashier(ArrayList<Cell> array){
//        if (array== Collection.left)
//            return cashiers.get(0);
//        else if (array== Collection.center)
//            return cashiers.get(1);
//        else if (array== Collection.right)
//            return cashiers.get(2);
//        else return null;
//    }

//    public void nextInLine(final ArrayList<Cell> array)
//    {
//        pb = (ProgressBar)array.get(0).look.findViewById(R.id.progressBar);
//        pb.setVisibility(View.VISIBLE);
//
//        if (array.get(0).actors!=null)
//            total = array.get(0).actors.timeToPass;
//        else total = 1000;
//        rate = this.speed;
//
//        if (active)
//        {
//            cdt = new CountDownTimer(total,rate) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    pb.incrementProgressBy(total/rate);
//                }
//
//                @Override
//                public void onFinish() {
//                    array.get(0).look.setVisibility(View.GONE);
//                    if (array.get(0).actors!=null)
//                        MainActivity.actors--;
//                    Queue.decArrayCount(array);
//                    array.get(0).setOccupied(false);
//                    if (array.get(1).getOccupied()){
//                        Cell.notifyCells(array,0);
//                        nextInLine(array);}
//
//                }
//            };
//
//            cdt.start();
//        }
//    }

//    @Override
//    public void run() {
//        if (station==1) {
//
//            if (Queue.left.get(0).occupied) {
//                pb = (ProgressBar) Queue.left.get(0).look.findViewById(R.id.progressBar);
//                pb.setVisibility(View.VISIBLE);
//
//                if (Queue.left.get(0).actors != null)
//                    total = Queue.left.get(0).actors.timeToPass;
//                else total = 1000;
//                rate = this.speed;
//
//                cdt = new CountDownTimer(total, rate) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        pb.incrementProgressBy(total / rate);
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        Queue.left.get(0).look.setVisibility(View.GONE);
//                        if (Queue.left.get(0).actors != null)
//                            MainActivity.actors--;
//                        Queue.decArrayCount(Queue.left);
//                        Queue.left.get(0).occupied = false;
//                        if (Queue.left.get(1).occupied) {
//                            Cell.notifyCells(Queue.left, 0);
//                            nextInLine(Queue.left);
//                        }
//
//                    }
//                };
//
//                cdt.start();
//            }
//        }
//
//    }
}
