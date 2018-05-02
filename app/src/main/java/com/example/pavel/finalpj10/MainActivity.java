package com.example.pavel.finalpj10;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent i;
    DBHelper dbHelper;
    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);


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
