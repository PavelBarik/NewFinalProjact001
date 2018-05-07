package com.example.pavel.finalpj10;


import android.app.Dialog;
import android.content.Intent;
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
    TextView num,zv,text;

    int check,check1,check2;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);
        dialog = new Dialog(MainActivity.this);
        imageView = findViewById(R.id.image_progress);
        text = dialog.findViewById(R.id.text_progress);
        // Установите заголовок
        dialog.setTitle("Достижение");
        // Передайте ссылку на разметку
        dialog.setContentView(R.layout.dialog);
        // Найдите элемент TextView внутри вашей разметки
        // и установите ему соответствующий текст


        check = 1;
        dbHelper = new DBHelper(this);
        dbHelper = new DBHelper(getBaseContext());
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        String SRAVNENIE = "SELECT * FROM " + DBHelper.TABLE_CONTACTS + " WHERE " + DBHelper.KEY_CHECK + "=" + "\"" + check + "\"" + ";";
        Cursor cursor1 = db.rawQuery(SRAVNENIE, null);
        cursor1.moveToFirst();
        check1 = cursor1.getCount();
        check2 = cursor1.getCount();


        if (check2 == 3) {

            imageView.setImageResource(R.drawable.progress);
            text.setText("Спасибо за то что вы используете наше приложение. Вот вам награда!");
            dialog.show();
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
        /*Intent intentService = new Intent(this, ReminderService.class);
        startService(intentService);

        boolean onOffService = true;
        if(onOffService) {

            ReminderService.setServiceAlarm(this, onOffService);
            onOffService = false;
        }

*/
    }
}
