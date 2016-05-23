package ru.arnis.queuegame;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ToggleButton;

/**
 * Created by arnis on 20/05/16.
 */
public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static ToggleButton debugButton;
    public static int debugButtonVisibility = View.INVISIBLE;
    private ImageView back;
    public static String column="4 columns";
    public static String row="4 columns";
    Spinner selectColumns;
    Spinner selectRows;
    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.settings_screen);


        debugButton = (ToggleButton) findViewById(R.id.button_debug);
        back = (ImageView)findViewById(R.id.button_back_settings);

        selectColumns = (Spinner) findViewById(R.id.select_columns);
        ArrayAdapter<CharSequence> adapterCol = ArrayAdapter.createFromResource(this,
                R.array.columns, android.R.layout.simple_spinner_item);
        adapterCol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectColumns.setAdapter(adapterCol);
        selectColumns.setOnItemSelectedListener(this);

        selectRows = (Spinner) findViewById(R.id.select_rows);
        ArrayAdapter<CharSequence> adapteRow = ArrayAdapter.createFromResource(this,
                R.array.rows, android.R.layout.simple_spinner_item);
        adapteRow.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectRows.setAdapter(adapteRow);
        selectRows.setOnItemSelectedListener(this);

        debugButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (debugButton.isChecked())debugButtonVisibility = View.VISIBLE;
                if (!debugButton.isChecked())debugButtonVisibility = View.INVISIBLE;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(v.getContext(),StartScreen.class);
                startActivity(home);
            }
        });

    }






//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        saveStates(false);
//    }


    @Override
    protected void onPause() {
        super.onPause();
        saveStates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStates();

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId())
        {
            case R.id.select_columns:
                column = parent.getItemAtPosition(position).toString();
                Log.d("happy", "col is "+column);
                break;

            case R.id.select_rows:
                row = parent.getItemAtPosition(position).toString();
                Log.d("happy", "row is "+row);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





    private void saveStates() {
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("check",debugButton.isChecked());
        editor.putString("column",column);
        editor.putString("row",row);
        editor.putInt("selected_column",selectColumns.getSelectedItemPosition());
        editor.putInt("selected_row",selectRows.getSelectedItemPosition());
        editor.apply();
    }

    private void loadStates() {
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        debugButton.setChecked(sharedPreferences.getBoolean("check", false));
        column = sharedPreferences.getString("column","3 columns");
        row = sharedPreferences.getString("row","4 rows");
        selectColumns.setSelection(sharedPreferences.getInt("selected_column",1));
        selectRows.setSelection(sharedPreferences.getInt("selected_row",0));

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


}


