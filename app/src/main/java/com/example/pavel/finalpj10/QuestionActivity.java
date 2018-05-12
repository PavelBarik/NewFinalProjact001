package com.example.pavel.finalpj10;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {
    Intent i;
TextView textView1,textView11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        textView1 = findViewById(R.id.textq1);
        textView11=findViewById(R.id.textq2);
        textView1.setText("Помощь");
        textView11.setText("Чтобы создать заметку, нажмите на желаемую дату. Заполните нужные вам поля заметки, к тому же вы можете установить звуковое напоминание, выбрав пункт 'Установить напоминание'. Также вы можете запланировать свой день, нажав на кнопку с человечком в нижнем правом углу экрана. В верхнем правом углу можно найти троеточие, выбрав его вы можете перейти во вкладки: 'Достижения' и 'Статистика'. Надеемся, что наше приложение будет полезным для вас.");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        switch (item.getItemId()) {


            case android.R.id.home:
                i = new Intent(QuestionActivity.this, MainActivity.class);
                startActivity(i);
                QuestionActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
