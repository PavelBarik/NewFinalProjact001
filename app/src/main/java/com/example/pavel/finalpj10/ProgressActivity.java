package com.example.pavel.finalpj10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

public class ProgressActivity extends AppCompatActivity {
Intent i;
ImageView imageView1,imageView2,imageView3;

int image11,image22;
public static final String APP_PREFERENCES = "mysettings";
    SharedPreferences mSettings;
    private static final String MY_SETTINGS = "my_settings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        imageView1 = findViewById(R.id.image_progress1);
        imageView2 = findViewById(R.id.image_progress2);
        imageView3 = findViewById(R.id.image_progress);
        image11 = MainActivity.image1;
        image22 = NewTask.int2;
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences sp2 = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);


// проверяем, первый ли раз открывается программа
        boolean hasVisited2 = sp2.getBoolean("hasVisited2", false);

        if(image11 == 1){
            imageView2.setVisibility(ImageView.VISIBLE);
        }else {
            imageView2.setVisibility(ImageView.INVISIBLE);
        }
        if(hasVisited2){
            imageView3.setVisibility(ImageView.VISIBLE);
        }else {
            imageView3.setVisibility(ImageView.INVISIBLE);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                i = new Intent(ProgressActivity.this, MainActivity.class );
                startActivity(i);
                ProgressActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
