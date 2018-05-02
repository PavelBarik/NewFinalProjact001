package com.example.pavel.finalpj10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewTask extends AppCompatActivity {
EditText edit1,edit2,edit3,edit4,edit5,edit6;
    String Data;
    TextView DaTa;
    Button sAvE,DEL,BaCk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        DaTa = findViewById(R.id.Date);
        Data = TaskActivity.DATA2;
        DaTa.setText(Data);
        edit1 = findViewById(R.id.Edit1);
        edit2 = findViewById(R.id.Edit2);
        edit3 = findViewById(R.id.Edit3);
        edit4 = findViewById(R.id.Edit4);
        edit5 = findViewById(R.id.Edit5);
        edit6 = findViewById(R.id.Edit6);
        sAvE = findViewById(R.id.SaVe);
        DEL = findViewById(R.id.Del);
        BaCk= findViewById(R.id.Back);

    }
}
