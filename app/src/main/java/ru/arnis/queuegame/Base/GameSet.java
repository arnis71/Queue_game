package ru.arnis.queuegame.Base;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

import ru.arnis.queuegame.Actors.Cashier;
import ru.arnis.queuegame.Settings;

/**
 * Created by arnis on 22/05/16.
 */
public class GameSet {  // too much static REMOVE
    public static ArrayList<Cell> col1;
    public static ArrayList<Cell> col2;
    static ArrayList<Cell> col3;
    static ArrayList<Cell> col4;
    public static int lengthOfCol;
    public static int cols;
    public static int maxActors;
    public static int actors=0;

    public static void initialize(int howMany) {
        switch (howMany){
                case 2: col1 = new ArrayList<>();col2 = new ArrayList<>();cols=2;break;
                case 3: col1 = new ArrayList<>();col2 = new ArrayList<>();col3 = new ArrayList<>();cols=3;break;
                case 4: col1 = new ArrayList<>();col2 = new ArrayList<>();col3 = new ArrayList<>();col4 = new ArrayList<>();cols=4;break;
        }
        lengthOfCol = Character.getNumericValue(Settings.row.charAt(0));
        maxActors=lengthOfCol*cols;
    }

    public static void saveCoordinates(float offset,float height,float spacing,float startY,float startX) {
        int off=0;
        float y;
        for (int j = 0; j<cols; j++) {
            y=height;
            for (int i = 0; i < lengthOfCol; i++) {
                y+=startY +  height + spacing;
                Cell cell = new Cell(startX+off, y, j+1);
                get(j).add(cell);
            }
            get(j).get(0).firstInQueue=true;
            off+=offset;
        }
    }

    public static void clearSet(){
        col1 = null;
        col2=null;
        col3=null;
        col4=null;
    }










    public static ArrayList<Cell> get(int i){
        switch(i){
            case 0: return col1;
            case 1: return col2;
            case 2: return col3;
            case 3: return col4;
            default: return null;
        }
    }

    public static int getLength(ArrayList<Cell> array){
        int len =0;
        for (int i = 0; i< lengthOfCol; i++){
            if (array.get(i).getOccupied()&array.get(i).actors!=null&&array.get(i).look!=null)
                len++;
        }
        return len;
    }










//    public WayToGo getLowtOf3(int a,int b,int c){
//
//        if (a==b && b==c){
//            return randomWay();
//        }else if (a<=b && a<=c){
//            return new WayToGo(getLength(col1), col1);
//        }else if (b<=c && b<=a){
//            return new WayToGo(getLength(col2), col2);
//        }else return new WayToGo(getLength(col3), col3);
//    }
//
//
//    public WayToGo getQuickestOf3(int a, int b, int c){
//
//        WayToGo way = null;
//
//        if (a<=b && a<=c)
//            way = new WayToGo(getLength(col1), col1);
//
//        if (b<=c && b<=a)
//            way = new  WayToGo(getLength(col2), col2);
//
//        if (c<=b && c<=a)
//            way = new WayToGo(getLength(col3), col3);
//
//        return way;
//    }
//
//
//    public static int getLowestOf3(int a,int b,int c){
//
//        if (a<=b && a<=c){
//            return a;
//        }else if (b<=c && b<=a){
//            return b;
//        }else return c;
//    }

    @Nullable
    public static WayToGo randomWay(int max)
    {
        Random rnd = new Random();
        int i =rnd.nextInt(max);
        switch (i)
        {
            case 0: return new WayToGo(getLength(col1), col1);
            case 1: return new  WayToGo(getLength(col2), col2);
            case 2: return new WayToGo(getLength(col3), col3);
            case 3: return new WayToGo(getLength(col4), col4);
            default: return null;
        }
    }

//    public WayToGo getArrayTimes(GameSet gs,int timeToPass){
//        int result=0;
//        int resultBuf=0;
//        WayToGo wtg = new WayToGo(0,gs.get(0));
//
//        int time=0;
//
//      for (int j=0;j<gs.cols;j++){
//          resultBuf = Cashier.cashiers.get(j).speed*timeToPass;
//          for (int k =0;k<gs.lengthOfCol-1;k++){
//              time = Cashier.cashiers.get(j).speed* gs.get(j).get(k).actors.timeToPass;
//              resultBuf += time;
//          } if (resultBuf>result) {result=resultBuf; wtg = new WayToGo(gs.getLength(gs.get(j)),gs.get(j));}
//      }
////        resultL= Cashier.cashiers.get(0).speed*timeToPass;
////        resultC=Cashier.cashiers.get(1).speed*timeToPass;
////        resultR=Cashier.cashiers.get(2).speed*timeToPass;
////
////        while (i < 3 & col1.get(i).actors !=null) {
////            time = Cashier.cashiers.get(0).speed* col1.get(i).actors.timeToPass;
////            resultL += time;
////            i++;
////        }
////
////        //for center
////        i=0;
////
////        while (i < 3 & col2.get(i).actors !=null) {
////            time = Cashier.cashiers.get(1).speed* col2.get(i).actors.timeToPass;
////            resultC += time;
////            i++;
////        }
////
////        //for right
////        i=0;
////
////        while (i < 3 & col3.get(i).actors !=null) {
////            time = Cashier.cashiers.get(2).speed* col3.get(i).actors.timeToPass;
////            resultR += time;
////            i++;
////        }
//        return wtg;
//
//
//
//    }
}
