package ru.arnis.queuegame.Actors.Products;

import java.util.Random;

/**
 * Created by arnis on 08/05/16.
 */
public class Items {

    public String name;
    public int wait;


    public static Items pickItem()
    {
        Random rnd = new Random();

        switch (rnd.nextInt(3))
        {
            case 0: return new difficultItem("LONG");
            case 1: return new simpleItem("MODERATE");
            case 2: return new easyItem("FAST");
            default: return null;
        }
    }
}
