package ru.arnis.queuegame.Actors;

import java.util.Random;

import ru.arnis.queuegame.Actors.Products.Items;
import ru.arnis.queuegame.Base.GameSet;
import ru.arnis.queuegame.Base.Meth;
import ru.arnis.queuegame.Base.WayToGo;

/**
 * Created by arnis on 08/05/16.
 */
public class Customer extends Actors {
    public Items items;
    public int modifier;  // higher is smarter

    public Customer calculateTTP()
    {
        timeToPass = items.wait;

        return this;
    }

    public Customer chooseItems()
    {
        int i=1;
        while (i!=0)
        {
            items = Items.pickItem();
            i--;
        }
        return this;
    }

    public Customer setSmartLevel(int i) //for debug purpose
    {
        modifier = i;
        return this;
    }

    public Customer setSmartLevel()
    {
        Random rnd = new Random();
        modifier = rnd.nextInt(3)+1;
        return this;
    }

    public WayToGo GO(){
        if (modifier==3){
            return pickBestQueue();
        }
        else if(modifier==2){
            return pickShortestQueue();

        }
        else {
            return GameSet.randomWay(GameSet.cols);
        }

    }

    public WayToGo pickBestQueue(){
////        int left = Collection.getArrayTime(Collection.left,this.timeToPass);
////        int center = Collection.getArrayTime(Collection.center,this.timeToPass);
////        int right = Collection.getArrayTime(Collection.right,this.timeToPass);
//
//        //int result = Collection.getLowestOf3(left,center,right);
//
//
//
////        if (result==left)
////            return new WayToGo(Collection.leftCount,Collection.left);
////        else if (result==center)
////            return new WayToGo(Collection.centerCount,Collection.center);
////        else return new WayToGo(Collection.rightCount,Collection.right);
//
//        return gs.getArrayTimes(gs,this.timeToPass);
        int result=Cashier.cashiers.get(0).speed*this.timeToPass*100;
        int resultBuf=0;
        int j=0;
        int k = 0;
        WayToGo wtg = new WayToGo(0, GameSet.get(0));

        int time=0;

        for (j=0;j<GameSet.cols;j++){
            resultBuf = Cashier.cashiers.get(j).speed*this.timeToPass;
            k=0;
            while (k<GameSet.lengthOfCol-1 & GameSet.get(j).get(k).getOccupied()){
                time = Cashier.cashiers.get(j).speed* GameSet.get(j).get(k).actors.timeToPass;
                resultBuf += time;
                k++;
            } if (resultBuf<result) {result=resultBuf; wtg = new WayToGo(GameSet.getLength(GameSet.get(j)), GameSet.get(j));}
        }
//        resultL= Cashier.cashiers.get(0).speed*timeToPass;
//        resultC=Cashier.cashiers.get(1).speed*timeToPass;
//        resultR=Cashier.cashiers.get(2).speed*timeToPass;
//
//        while (i < 3 & col1.get(i).actors !=null) {
//            time = Cashier.cashiers.get(0).speed* col1.get(i).actors.timeToPass;
//            resultL += time;
//            i++;
//        }
//
//        //for center
//        i=0;
//
//        while (i < 3 & col2.get(i).actors !=null) {
//            time = Cashier.cashiers.get(1).speed* col2.get(i).actors.timeToPass;
//            resultC += time;
//            i++;
//        }
//
//        //for right
//        i=0;
//
//        while (i < 3 & col3.get(i).actors !=null) {
//            time = Cashier.cashiers.get(2).speed* col3.get(i).actors.timeToPass;
//            resultR += time;
//            i++;
//        }
        return wtg;
    }

    public static WayToGo pickShortestQueue()
    {
        int length=GameSet.lengthOfCol;
        int buf;
        WayToGo wtg = new WayToGo(0, GameSet.get(0));

        for (int j=0;j<GameSet.cols;j++){
            buf = GameSet.getLength(GameSet.get(j));
            if (buf<length) {
                length = buf;
                wtg = new WayToGo(GameSet.getLength(GameSet.get(j)), GameSet.get(j));
            }
        }
        return wtg;

        //return gs.getLowtOf3(gs.getLength(gs.get(0)), gs.getLength(gs.get(1)), gs.getLength(gs.get(2)));

//        if (Collection.leftCount==Collection.centerCount && Collection.centerCount==Collection.rightCount){
//            return Collection.randomWay();
//        }else if (Collection.leftCount<=Collection.centerCount && Collection.leftCount<=Collection.rightCount){
//            return new WayToGo(Collection.leftCount,Collection.left);
//        }else if (Collection.centerCount<=Collection.rightCount && Collection.centerCount<=Collection.leftCount){
//            return new WayToGo(Collection.centerCount,Collection.center);
//        }else return new WayToGo(Collection.rightCount,Collection.right);


    }





}
