package ru.arnis.queuegame.Base;

import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import ru.arnis.queuegame.Actors.Cashier;

/**
 * Created by arnis on 08/05/16.
 */
public class Meth {


    public synchronized static void animateTo(View v, float x, float y, int duration){
        v.animate()
                .x(x)
                .y(y)
                .setDuration(duration);

    }




    public static int debugArrayTimes(ArrayList<Cell> array){
        int i=0;
        int result=0;

        int time=0;

        while (i < GameSet.lengthOfCol-1 & array.get(i).actors !=null) {
            time = Cashier.cashiers.get(array.get(0).pos-1).speed+array.get(i).actors.timeToPass;
            result += time;
            i++;
        }

        return result;



    }





//    public static int getArrayCount(ArrayList<Cell> array)
//    {
//        if (array.equals(left))
//            return leftCount;
//        else if (array.equals(center))
//            return centerCount;
//        else return rightCount;
//    }
//
//    public static int incArrayCount(ArrayList<Cell> array)
//    {
//        if (array.equals(left))
//            return leftCount++;
//        else if (array.equals(center))
//            return centerCount++;
//        else return rightCount++;
//    }
//
//    public static int decArrayCount(ArrayList<Cell> array)
//    {
//        if (array.equals(left))
//            return leftCount--;
//        else if (array.equals(center))
//            return centerCount--;
//        else return rightCount--;
//    }


}
