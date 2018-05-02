package com.example.pavel.finalpj10;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewTask extends AppCompatActivity implements View.OnClickListener  {
    EditText edit1, edit2, edit3, edit4, edit5, edit6;
    String EdIt1, EdIt2, EdIt3, EdIt4, EdIt5, EdIt6;
    String Data;
    TextView DaTa;
    Button sAvE, DEL, BaCk;
    DBHelper dbHelper;
    Intent i;
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
        BaCk = findViewById(R.id.Back);
        dbHelper = new DBHelper(this);
        dbHelper = new DBHelper(getBaseContext());
        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();
        String SRAVNENIE = "SELECT * FROM " + DBHelper.TABLE_CONTACTS + " WHERE " + DBHelper.KEY_DATA + "=" + "\"" + Data + "\"" + ";";
        Cursor cursor1 = db.rawQuery(SRAVNENIE, null);
        cursor1.moveToFirst();
        if (cursor1.getCount() != 0) {
            EdIt1 = cursor1.getString(cursor1.getColumnIndex(DBHelper.KEY_EDIT1));
            EdIt2 = cursor1.getString(cursor1.getColumnIndex(DBHelper.KEY_EDIT2));
            EdIt3 = cursor1.getString(cursor1.getColumnIndex(DBHelper.KEY_EDIT3));
            EdIt4 = cursor1.getString(cursor1.getColumnIndex(DBHelper.KEY_EDIT4));
            EdIt5 = cursor1.getString(cursor1.getColumnIndex(DBHelper.KEY_EDIT5));
            EdIt6 = cursor1.getString(cursor1.getColumnIndex(DBHelper.KEY_EDIT6));
        }
        cursor1.close();
        db.close();
    }

    @Override
    public void onClick(View view) {
        String Edite1,Edite2,Edite3,Edite4,Edite5,Edite6;
        Edite1 = edit1.getText().toString();
        Edite2 = edit2.getText().toString();
        Edite3 = edit3.getText().toString();
        Edite4 = edit4.getText().toString();
        Edite5 = edit5.getText().toString();
        Edite6 = edit6.getText().toString();
        switch (view.getId()){
            case R.id.SaVe:
                dbHelper = new DBHelper(getBaseContext());
                SQLiteDatabase db;
                db = dbHelper.getReadableDatabase();


                String SRAVNENIE = "SELECT * FROM "+DBHelper.TABLE_CONTACTS+" WHERE "+DBHelper.KEY_DATA+"="+ "\""+Data+"\"" +";";
                Cursor cursor = db.rawQuery(SRAVNENIE,null);

                String insertQuery = "INSERT INTO " + DBHelper.TABLE_CONTACTS + " (" + DBHelper.KEY_EDIT1 + "," +
                        DBHelper.KEY_EDIT2 + "," + DBHelper.KEY_EDIT3 + "," + DBHelper.KEY_EDIT4 + ","+DBHelper.KEY_EDIT5+","+DBHelper.KEY_EDIT6+
                        ") VALUES (\"" + Edite1 +"\", \""+Edite2+"\", \"" +Edite3+ "\", \""+Edite4+"\", \""+Edite5+"\","+"\""+Edite6+"\");";
                cursor.moveToFirst();
                String updateQuery = "UPDATE " + DBHelper.TABLE_CONTACTS + " SET " + DBHelper.KEY_EDIT1 +
                        " = \"" + Edite1 + "\", " + DBHelper.KEY_EDIT2 + "= \"" + Edite2 + "\", " +
                        DBHelper.KEY_EDIT3 + "= \"" + Edite3+ "\", "+DBHelper.KEY_EDIT4 + "= \"" + Edite4+ "\", "+DBHelper.KEY_EDIT5 + "= \"" + Edite5 + "\", "+DBHelper.KEY_EDIT6 + "= \"" + Edite6 + "\" " +" WHERE " + DBHelper.KEY_DATA + "= \"" + Data +"\";" ;
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
                i = new Intent(NewTask.this, TaskActivity.class );
                startActivity(i);
                NewTask.this.finish();
                break;
            case R.id.Back:
                i = new Intent(NewTask.this, TaskActivity.class );
                startActivity(i);
                NewTask.this.finish();
                break;
            case R.id.Del:
                Edite1 = null;
                Edite2 = null;
                Edite3 = null;
                Edite4 = null;
                Edite5 = null;
                Edite6 = null;

                dbHelper = new DBHelper(this);
                dbHelper = new DBHelper(getBaseContext());
                SQLiteDatabase db1;
                db1 = dbHelper.getReadableDatabase();
                String deleteQuery = "UPDATE " + DBHelper.TABLE_CONTACTS + " SET " + DBHelper.KEY_EDIT1 +
                        " = \"" + Edite1 + "\", " + DBHelper.KEY_EDIT2 + "= \"" + Edite2 + "\", " +
                        DBHelper.KEY_EDIT3 + "= \"" + Edite3+ "\", "+DBHelper.KEY_EDIT4 + "= \"" + Edite4+ "\", "+DBHelper.KEY_EDIT5 + "= \"" + Edite5 + "\", "+DBHelper.KEY_EDIT6 + "= \"" + Edite6 + "\" " +" WHERE " + DBHelper.KEY_DATA + "= \"" + Data +"\";" ;
                db1.execSQL(deleteQuery);
                NewTask.this.finish();
                break;

        }

    }
}

