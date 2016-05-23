package ru.arnis.queuegame.Base;

/**
 * Created by arnis on 15/05/16.
 */
public class LevelBuilder {

    public String name;
    public final int TOTAL_ROWS;
    public final int TOTAL_COLUMNS;

    public LevelBuilder(String name,int columns,int rows) {
        this.name = name;
        TOTAL_COLUMNS = columns;
        TOTAL_ROWS=rows;
    }

//    public void buildLvl(){
//        for (int i=0;i<TOTAL_COLUMNS;i++){
//            Collection.columns.add(new ArrayList<Cell>());
//            for (int j=0;j<TOTAL_ROWS;j++){
//                Collection.columns.get(i).add();
//            }
//        }
//    }
}
