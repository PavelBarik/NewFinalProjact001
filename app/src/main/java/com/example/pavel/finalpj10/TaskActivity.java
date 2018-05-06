package com.example.pavel.finalpj10;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.view.View.OnClickListener;
public class TaskActivity extends AppCompatActivity implements View.OnClickListener  {
    EditText Nazvanie,Mesto,Zametka;
    int check;
    String Null;
    Button Save,Clear;
    DBHelper dbHelper;
    CheckBox aSwitch;
    static String DATA2;
    String mesto,nazvanie,time,zametka;
    private TextView TimeDisplay;
    private TimePicker mTimePicker;
    private Button SetTime;
    TextView DaTa;
    private int hours;
    private int minutes;
    Intent i;
    int mYear;
    int mMonth;
    int mDay ;
    int defaultValue = 0;

    static final int TIME_DIALOG_ID = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Clear = findViewById(R.id.clear);
        Clear.setOnClickListener(this);

        Nazvanie = findViewById(R.id.naz);

        Zametka = findViewById(R.id.zam);

        Mesto = findViewById(R.id.mesto);

        Save = findViewById(R.id.save);
        Save.setOnClickListener(this);

        Null = "Напоминание не установлено.";



        setCurrentTime();
        ButtonListener();

        aSwitch = findViewById(R.id.swi);
        aSwitch.setOnClickListener(this);

        i = getIntent();
        mYear = i.getIntExtra("Year", defaultValue);
        mMonth = i.getIntExtra("Month", defaultValue);
        mDay = i.getIntExtra("Day", defaultValue);
        mMonth=mMonth+1;

        DaTa = findViewById(R.id.Data);
        TimeDisplay.setText(Null);

        DATA2 = mDay + "." + mMonth + "." + mYear;
        DaTa.setText(DATA2);


        SetTime.setEnabled(false);
        String DATA = mDay + "." + mMonth + "." + mYear;
        dbHelper = new DBHelper(this);
        dbHelper = new DBHelper(getBaseContext());
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        String SRAVNENIE = "SELECT * FROM "+DBHelper.TABLE_CONTACTS+" WHERE "+DBHelper.KEY_DATA+"="+ "\""+DATA+"\"" +";";
        Cursor cursor1 = db.rawQuery(SRAVNENIE, null);
        cursor1.moveToFirst();
        if (cursor1.getCount()!=0) {

            nazvanie = cursor1.getString(cursor1.getColumnIndex(DBHelper.KEY_NAZVANIE));
            zametka = cursor1.getString(cursor1.getColumnIndex(DBHelper.KEY_ZAMETKA));
            time = cursor1.getString(cursor1.getColumnIndex(DBHelper.KEY_TIME));
            mesto = cursor1.getString(cursor1.getColumnIndex(DBHelper.KEY_MESTO));
            check = cursor1.getInt(cursor1.getColumnIndex(DBHelper.KEY_CHECK));
            Nazvanie.setText(nazvanie);
            Mesto.setText(mesto);
            Zametka.setText(zametka);
            TimeDisplay.setText(time);
            if(check == 1) {
                aSwitch.setChecked(true);
                SetTime.setEnabled(true);
            }else {
                aSwitch.setChecked(false);
                SetTime.setEnabled(false);
                TimeDisplay.setText(Null);
            }
        }

        cursor1.close();
        db.close();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent;
                intent = new Intent(TaskActivity.this, NewTask.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            i = new Intent(TaskActivity.this, StatisticsActivity.class);
            startActivity(i);
        }
        if (id == R.id.action_dostijenia) {
            i = new Intent(TaskActivity.this, StatisticsActivity.class);
            startActivity(i);
        }
        switch (item.getItemId()) {


            case android.R.id.home:
                i = new Intent(TaskActivity.this, MainActivity.class );
                startActivity(i);
                TaskActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }






    public void setCurrentTime() {
        TimeDisplay = (TextView) findViewById(R.id.CurTime);
        mTimePicker = (TimePicker) findViewById(R.id.TimePick);

        final Calendar calendar = Calendar.getInstance();
        hours = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);

        //Настраиваем текущее время в TextView:
        TimeDisplay.setText(
                new StringBuilder().append(pad(hours))
                        .append(":").append(pad(minutes)));

        //Настраиваем текущее время в TimePicker:
        mTimePicker.setCurrentHour(hours);
        mTimePicker.setCurrentMinute(minutes);

    }

    //Добавляем слушателя нажатий кнопки:
    public void ButtonListener() {
        SetTime = (Button) findViewById(R.id.button);
        SetTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //При нажатии кнопки запускается диалог TimePickerDialog для выбора времени:
                showDialog(TIME_DIALOG_ID);

            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                //Задаем в TimePicker текущее время:
                return new TimePickerDialog(this,
                        timePickerListener, hours, minutes,false);
        }
        return null;
    }

    //Настраиваем диалоговое окно TimePickerDialog:
    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,int selectedMinute) {
                    hours = selectedHour;
                    minutes = selectedMinute;

                    //Настраиваем выбранное время в TextView:
                    TimeDisplay.setText(new StringBuilder().append(pad(hours))
                            .append(":").append(pad(minutes)));

                    //Настраиваем выбранное время в TimePicker:
                    mTimePicker.setCurrentHour(hours);
                    mTimePicker.setCurrentMinute(minutes);

                }
            };
    //Для показания минут настраиваем отображение 0 впереди чисел со значением меньше 10:
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }




    public class AlarmService extends Service {

        public void AlarmService(){
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

    }



    @Override
    public void onClick (View view){

        String TIME = TimeDisplay.getText().toString();
        String NAZVANIE = Nazvanie.getText().toString();
        String ZAMETKA =Zametka.getText().toString();
        String MESTO = Mesto.getText().toString();
        String DATA = mDay + "." + mMonth + "." + mYear;
        if(aSwitch.isChecked()){
            SetTime.setEnabled(true);
            TIME = TimeDisplay.getText().toString();
            check = 1;
        }else{
            SetTime.setEnabled(false);
            TIME=Null;
            TimeDisplay.setText(Null);
            check = 0;
        }

        switch (view.getId()){
            case R.id.save:
                dbHelper = new DBHelper(getBaseContext());
                SQLiteDatabase db;
                db = dbHelper.getReadableDatabase();

                if(TIME == Null){
                    SetTime.setEnabled(false);
                    aSwitch.setEnabled(false);
                    TimeDisplay.setText(Null);
                    check = 0;
                }
                String SRAVNENIE = "SELECT * FROM "+DBHelper.TABLE_CONTACTS+" WHERE "+DBHelper.KEY_DATA+"="+ "\""+DATA+"\"" +";";
                Cursor cursor = db.rawQuery(SRAVNENIE,null);

                String insertQuery = "INSERT INTO " + DBHelper.TABLE_CONTACTS + " (" + DBHelper.KEY_DATA + "," +
                        DBHelper.KEY_NAZVANIE + "," + DBHelper.KEY_MESTO + "," + DBHelper.KEY_TIME  + ","+DBHelper.KEY_CHECK+","+DBHelper.KEY_ZAMETKA+
                        ") VALUES (\"" + DATA +"\", \""+NAZVANIE+"\", \"" +MESTO+ "\", \""+TIME+"\", \""+check+"\","+"\""+ZAMETKA+"\");";
                cursor.moveToFirst();
                String updateQuery = "UPDATE " + DBHelper.TABLE_CONTACTS + " SET " + DBHelper.KEY_NAZVANIE +
                        " = \"" + NAZVANIE   + "\", " + DBHelper.KEY_MESTO + "= \"" + MESTO + "\", " +
                        DBHelper.KEY_TIME + "= \"" + TIME+ "\", "+DBHelper.KEY_CHECK + "= \"" + check+ "\", "+DBHelper.KEY_ZAMETKA + "= \"" + ZAMETKA + "\" " +" WHERE " + DBHelper.KEY_DATA  + "= \"" + DATA +"\";" ;
                db.close();

                if (cursor.getCount() == 0 ){
                    dbHelper = new DBHelper(getBaseContext());
                    db = dbHelper.getWritableDatabase();
                    db.execSQL(insertQuery);
                    db.close();
                    cursor.close();
                }else{
                    dbHelper = new DBHelper(getBaseContext());
                    db = dbHelper.getWritableDatabase();
                    db.execSQL(updateQuery);
                    db.close();
                    cursor.close();
                }
                i = new Intent(TaskActivity.this, MainActivity.class );
                startActivity(i);
                TaskActivity.this.finish();
                break;

            case R.id.clear:
                dbHelper = new DBHelper(this);
                dbHelper = new DBHelper(getBaseContext());
                SQLiteDatabase db1;
                db1 = dbHelper.getReadableDatabase();
                String deleteQuery = "DELETE FROM "+DBHelper.TABLE_CONTACTS+" WHERE "+DBHelper.KEY_DATA+"="+ "\""+DATA+"\"" +";";
                db1.execSQL(deleteQuery);
                Nazvanie.setText("");
                Mesto.setText("");
                Zametka.setText("");
                TimeDisplay.setText(Null);
                aSwitch.setChecked(false);
                SetTime.setEnabled(false);
                TaskActivity.this.finish();
                break;

        }

    }
}
