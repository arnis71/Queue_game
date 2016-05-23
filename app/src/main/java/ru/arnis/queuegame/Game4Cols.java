package ru.arnis.queuegame;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.arnis.queuegame.Actors.Cashier;
import ru.arnis.queuegame.Actors.Customer;
import ru.arnis.queuegame.Actors.OnCellFirstPositionOccupiedListener;
import ru.arnis.queuegame.Actors.Player;
import ru.arnis.queuegame.Actors.Threading;
import ru.arnis.queuegame.Base.Cell;
import ru.arnis.queuegame.Base.GameMeth;
import ru.arnis.queuegame.Base.GameSet;
import ru.arnis.queuegame.Base.Meth;
import ru.arnis.queuegame.Base.WayToGo;

public class Game4Cols extends AppCompatActivity implements GameMeth {

    public static FrameLayout player;
    private RelativeLayout leftLayout;
    private RelativeLayout centerLayout;
    private RelativeLayout rightLayout;
    private ImageView cashierLeft;
    private Button btn;
    public RelativeLayout main_layout;
    private ImageView cashierCenter;
    private ImageView cashierRight;
    Meth meth;
    Cashier cs1,cs2,cs3;
    private boolean firstGO = true;
    Handler pbHandlerLL = new Handler();
    Handler pbHandlerL = new Handler();
    Handler pbHandlerR = new Handler();
    Handler pbHandlerRR = new Handler();
    private ProgressBar progressBar;
    Player pl;
    private TextView cash1;
    private TextView cash2;
    private TextView cash3;
    CountDownTimer cdt;
    private CountDownTimer spawnTimer;
    private RelativeLayout farLeftLayout;
    private RelativeLayout farRightLayout;
    private TextView cash4;
    GameSet gameSet;
    private Cashier cs4;
    ArrayList<Thread> threads = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("happy", "onCreate: ");
        hideStatusBar();
        setContentView(R.layout.game_4_col);


        player = (FrameLayout)findViewById(R.id.player);
        Player.player=player;
        main_layout = (RelativeLayout)findViewById(R.id.main_layout);
        progressBar = (ProgressBar)player.findViewById(R.id.progressBar);

        farLeftLayout = (RelativeLayout)findViewById(R.id.far_left_layout);
        leftLayout = (RelativeLayout)findViewById(R.id.left_layout);
        rightLayout = (RelativeLayout)findViewById(R.id.right_layout);
        farRightLayout = (RelativeLayout)findViewById(R.id.far_right_layout);
        cashierLeft = (ImageView)findViewById(R.id.cashier_left);
        cashierCenter = (ImageView)findViewById(R.id.cashier_center);
        cashierRight = (ImageView)findViewById(R.id.cashier_right);
        cash2 = (TextView)findViewById(R.id.table_left);
        cash1 = (TextView)findViewById(R.id.table_far_left);
        cash3 = (TextView)findViewById(R.id.table_right);
        cash4 = (TextView)findViewById(R.id.table_far_right);

        btn = (Button)findViewById(R.id.spawn);
        btn.bringToFront();

        meth = new Meth();
        cs1 = new Cashier(50); //higher is faster
        cs2 = new Cashier(55);
        cs3 = new Cashier(45);
        cs4 = new Cashier(60);
        pl = new Player(100);
        //lvl1 = new LevelBuilder("Level 1",3,4);
    GameSet.initialize(4);


            main_layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    float startY= cashierLeft.getY();
                    float startX = cashierLeft.getX()+25;
                    float heightY = cashierLeft.getHeight();
                    float offsetX = leftLayout.getWidth();
                    float spacingY = heightY/5;
                    GameSet.saveCoordinates(offsetX,heightY,spacingY,startY,startX);

//                    saveCoordinates(cashierLeft, 1);
//                   // Log.d("happy", "saveCoordinates");
//                    saveCoordinates(cashierCenter, 2);
//                    //Log.d("happy", "saveCoordinates");
//                    saveCoordinates(cashierRight, 3);
//                   // Log.d("happy", "saveCoordinates");
//                    Meth.left.get(0).firstInQueue=true;
//                    Meth.center.get(0).firstInQueue=true;
//                    Meth.right.get(0).firstInQueue=true;

                    main_layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            });

        cs1.setOnCellFirstPositionOccupiedListener(new OnCellFirstPositionOccupiedListener() {
            @Override
            public void onEvent() {
                ProgressBar pb = (ProgressBar) GameSet.get(0).get(0).look.findViewById(R.id.progressBar);
                pb.setVisibility(View.VISIBLE);

                runPB(pb, pbHandlerLL, GameSet.get(0),cs1.speed);
            }
        });
        cs2.setOnCellFirstPositionOccupiedListener(new OnCellFirstPositionOccupiedListener() {
            @Override
            public void onEvent() {
                ProgressBar pb = (ProgressBar) GameSet.get(1).get(0).look.findViewById(R.id.progressBar);
                pb.setVisibility(View.VISIBLE);

                runPB(pb, pbHandlerL, GameSet.get(1),cs2.speed);


            }
        });

        cs3.setOnCellFirstPositionOccupiedListener(new OnCellFirstPositionOccupiedListener() {
            @Override
            public void onEvent() {
                ProgressBar pb = (ProgressBar) GameSet.get(2).get(0).look.findViewById(R.id.progressBar);
                pb.setVisibility(View.VISIBLE);

                runPB(pb,pbHandlerR, GameSet.get(2),cs3.speed);
            }
        });
        cs4.setOnCellFirstPositionOccupiedListener(new OnCellFirstPositionOccupiedListener() {
            @Override
            public void onEvent() {
                ProgressBar pb = (ProgressBar) GameSet.get(3).get(0).look.findViewById(R.id.progressBar);
                pb.setVisibility(View.VISIBLE);

                runPB(pb,pbHandlerRR, GameSet.get(3),cs4.speed);
            }
        });






    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (int i =0; i<threads.size();i++)
            threads.get(i).interrupt();

        Threading.status = false;
        threads = new ArrayList<>();
        GameSet.clearSet();
        Cashier.reset();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cdt!=null) cdt.cancel();
        if (spawnTimer!=null) spawnTimer.cancel();
        Threading.status = false;
        GameSet.actors=0;

    }

    @Override
    protected void onResume() {
        super.onResume();
        Threading.status=true;
        Log.d("happy2", "onResume: ");
    }


    @Override
    protected void onStart() {
        super.onStart();

        main_layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                boolean flag;
                try{
                    flag = Settings.debugButton.isChecked();
                }catch (NullPointerException e){
                    flag = false;
                }
                if (flag){
                    cdt = new CountDownTimer(200000,100) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int a = Meth.debugArrayTimes(GameSet.get(0));
                            int b = Meth.debugArrayTimes(GameSet.get(1));
                            int c = Meth.debugArrayTimes(GameSet.get(2));
                            int d = Meth.debugArrayTimes(GameSet.get(2));
                            cash1.setText(Integer.toString(a));
                            cash2.setText(Integer.toString(b));
                            cash3.setText(Integer.toString(c));
                            cash4.setText(Integer.toString(d));
                        }

                        @Override
                        public void onFinish() {

                        }
                    };
                    cdt.start();
                }
                if(btn.getVisibility()==View.INVISIBLE){
                    spawnTimer = new CountDownTimer(20000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            spawnAndGo();


                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(Game4Cols.this, "Game Over", Toast.LENGTH_SHORT).show();
                        }
                    };
                    spawnTimer.start();
                }
                main_layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        btn.setVisibility(Settings.debugButtonVisibility);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spawnAndGo();

            }
        });



        farLeftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerJump(GameSet.get(0));
            }
        });
        leftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerJump(GameSet.get(1));

            }
        });
        rightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerJump(GameSet.get(2));

            }
        });
        farRightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerJump(GameSet.get(3));
            }
        });







    }



    @Override
    public boolean checkBehind() {
        if (!firstGO&Player.currPos!=GameSet.lengthOfCol-1)
            return GameSet.get(Player.currCell.pos-1).get(Player.currPos+1).getOccupied();
        else {firstGO=false;return false;}
    }

    @Override
    public void playerJump(ArrayList<Cell> column) {
        if (GameSet.getLength(column) == GameSet.lengthOfCol || player.getVisibility() == View.GONE || progressBar.getProgress()>=2||column== GameSet.get(Player.currCell.pos-1)){
            //  Log.d("happy", "NOT JUMPING! compare "+column.hashCode()+" : "+lastKnownColumn.hashCode());
            return ;
        }



//                lastKnownColumn.get(lastKnownCount).setOccupied(false);
//                lastKnownColumn.get(lastKnownCount).look = null;
//                lastKnownColumn.get(lastKnownCount).actors=null;
                //Meth.decArrayCount(lastKnownColumn);

            Player.currCell.wipeCell();


            column.get(GameSet.getLength(column)).look = player;
            column.get(GameSet.getLength(column)).actors=pl;

            Meth.animateTo(player,column.get(GameSet.getLength(column)).x,column.get(GameSet.getLength(column)).y,200);

        if (checkBehind()) {
            Cell.notifyCells(GameSet.get(Player.currCell.pos-1), Player.currPos);
        }
            column.get(GameSet.getLength(column)).setOccupied(true);

            Player.currCell = column.get(GameSet.getLength(column)-1);
            Player.currPos = GameSet.getLength(column)-1;



    }


    @Override
    public void spawnAndGo() {

        WayToGo way;

        Customer newC = new Customer();
        newC.chooseItems().calculateTTP().setSmartLevel(); //can set manually 1,2,3
            way = newC.GO();
           // Log.d("happy", "glitches at modifier " + newC.modifier);

        if (GameSet.actors < GameSet.maxActors & GameSet.getLength(way.array)<GameSet.lengthOfCol) {

            RelativeLayout testView = (RelativeLayout) getLayoutInflater().inflate(R.layout.customer_view, null); // added main layout instead null


            ImageView iv = (ImageView) testView.findViewById(R.id.customer);
            switch (newC.modifier){
                case 1: iv.setImageResource(R.drawable.customer_rand);break;
                case 2: iv.setImageResource(R.drawable.customer_pseudo_smart);break;
                case 3: iv.setImageResource(R.drawable.customer);break;
            }

            ImageView bag = (ImageView) testView.findViewById(R.id.bag);
            bag.setVisibility(View.VISIBLE);
            switch (newC.timeToPass){
                case 50: bag.getLayoutParams().height=50;bag.getLayoutParams().width=50;break;
                case 100: bag.getLayoutParams().height=70;bag.getLayoutParams().width=70;break;
                case 200: bag.getLayoutParams().height=100;bag.getLayoutParams().width=100;break;
            }

            main_layout.addView(testView);
            testView.setX(main_layout.getLeft());
            testView.setY(main_layout.getBottom() - player.getHeight());


            //way = Customer.pickShortestQueue();
            way.array.get(GameSet.getLength(way.array)).look = testView;
            way.array.get(GameSet.getLength(way.array)).actors = newC;
            //way.array.get(Collection.getArrayCount(way.array)).actors.
            //Meth.incArrayCount(way.array);

            Meth.animateTo(way.array.get(way.index).look,way.array.get(way.index).x,way.array.get(way.index).y,200);

            way.array.get(way.index).setOccupied(true);



        } else return;
        GameSet.actors++;
    }


    @Override
    public void runPB(final ProgressBar pb, final Handler hand, final ArrayList<Cell> array,int speed){

//        new Thread(new Runnable() {
//            int a =status;
//            @Override
//            public void run() {
//                while(a<100)
//                {
//                    a++;
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    hand.post(new Runnable(){
//                        @Override
//                        public void run() {
//                            pb.setProgress(a);
//
//                        }
//                    });
//
//                }
//                pass(array);//wrong thread handling
//            }
//        }).start();
        Threading th = new Threading(pb,hand,array,speed);
        Thread newThrd = new Thread(th);
        newThrd.start();
        // Log.d("happy", "thread is " + newThrd.isAlive());

    }








    protected void hideStatusBar(){
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void cancelGame(){
        for (int i=0;i<GameSet.cols;i++)
            for(int j = 0;j<GameSet.lengthOfCol;j++)
                GameSet.get(i).get(j).wipeCell();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.bottomMargin = 40;

        player.setLayoutParams(params);

    }



}
