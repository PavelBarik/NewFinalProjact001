package com.example.pavel.finalpj10;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent i;
    DBHelper dbHelper;
    CalendarView calendarView;
    ImageView imageView;
    TextView text;
    SharedPreferences sharedPreferences;
    Boolean flag;
    public static final String APP_PREFERENCES = "mysettings";
    SharedPreferences mSettings;
    private static final String MY_SETTINGS = "my_settings";
    int check,check1,check2;
    Dialog dialog;
    String SAVED_NUM = "0";
    String Int1 = "1";
    static int image1;
    long hours,minuts,nowtime;
    String hours1,minuts2;
    String TimeString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = findViewById(R.id.calendarView1);
        dialog = new Dialog(MainActivity.this);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        // Установите заголовок
        dialog.setTitle("Достижение");
        // Передайте ссылку на разметку
        dialog.setContentView(R.layout.dialog);

        // Найдите элемент TextView внутри вашей разметки
        // и установите ему соответствующий текст
        imageView = dialog.findViewById(R.id.image_progress);
        text = dialog.findViewById(R.id.text_progress);
        image1 = 0;

        check = 1;
        dbHelper = new DBHelper(this);
        dbHelper = new DBHelper(getBaseContext());
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        String SRAVNENIE = "SELECT * FROM " + DBHelper.TABLE_CONTACTS + ";";
        Cursor cursor1 = db.rawQuery(SRAVNENIE, null);
        cursor1.moveToFirst();
        check1 = cursor1.getCount();
        check2 = cursor1.getCount();

        TimeString=TaskActivity.TIME;
        if(TimeString != null) {
            Intent intentService = new Intent(this, ReminderService.class);
            hours1 = TimeString.substring(0, 1);
            minuts2 = TimeString.substring(4, 5);
            hours = Long.parseLong(hours1);
            minuts = Long.parseLong(minuts2);
            hours = hours * 3600000;
            minuts = minuts * 60000;
            intentService.putExtra("hours", hours);
            intentService.putExtra("minuts", minuts);
            nowtime = System.currentTimeMillis();
            intentService.putExtra("nowtime", nowtime);

        startService(intentService);

        boolean onOffService = true;
        if(onOffService) {

            ReminderService.setServiceAlarm(this, onOffService);
            onOffService = false;
        }


        }


        SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
// проверяем, первый ли раз открывается программа
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        SharedPreferences sp1 = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
// проверяем, первый ли раз открывается программа
        boolean hasVisited1 = sp.getBoolean("hasVisited1", false);
        if (hasVisited){
            image1 =1;
        }
        if (check2 == 3 && !hasVisited ) {

            imageView.setImageResource(R.drawable.progress1);
            text.setText("Мы заметили, что вы много трудитесь! Вот вам награда за работу!");
            dialog.show();
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.apply();
            image1 = 1;

        }
        if (!hasVisited1) {

            imageView.setImageResource(R.drawable.progress);
            text.setText("Спасибо за то, что вы используете наше приложение. Вот вам награда!");
            dialog.show();
            SharedPreferences.Editor e = sp1.edit();
            e.putBoolean("hasVisited1", true);
            e.apply();
        }
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;

                String selectedDate = new StringBuilder().append(mDay)
                        .append("-").append(mMonth + 1).append("-").append(mYear)
                        .append(" ").toString();
                Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_SHORT).show();

                i = new Intent(MainActivity.this, TaskActivity.class);
                i.putExtra("Year", mYear);
                i.putExtra("Month", mMonth);
                i.putExtra("Day", mDay);

                startActivity(i);

            }
        });


    }
}
