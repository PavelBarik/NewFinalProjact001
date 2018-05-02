package com.example.pavel.finalpj10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class NewTask extends AppCompatActivity {
EditText edit1,edit2,edit3,edit4,edit5,edit6;
    String Data;
    TextView DaTa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        DaTa = findViewById(R.id.Date);
        Data = TaskActivity.DATA2;
        DaTa.setText(Data);

    }
}
