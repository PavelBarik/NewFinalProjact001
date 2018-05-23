package com.example.pavel.finalpj10;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class NewTask extends AppCompatActivity implements View.OnClickListener  {
    EditText edit1, edit2, edit3, edit4, edit5, edit6;
    String edIt1, edIt2, edIt3, edIt4, edIt5, edIt6;
    static String Data;
    TextView DaTa;
    Button sAvE, DEL;
    DBHelper2 dbHelper2;
    Intent i;
    String Edite1,Edite2,Edite3,Edite4,Edite5,Edite6;
    ImageView imageView;
    Dialog dialog;
    SharedPreferences sharedPreferences;
    public static final String APP_PREFERENCES = "mysettings";
    SharedPreferences mSettings;
    private static final String MY_SETTINGS = "my_settings";
    static int int2;
    TextView text;

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        sAvE = findViewById(R.id.SaVe);
        sAvE.setOnClickListener(this);
        DEL = findViewById(R.id.Del);
        DEL.setOnClickListener(this);

        dialog = new Dialog(NewTask.this);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        // Установите заголовок
        dialog.setTitle("Достижение");
        // Передайте ссылку на разметку
        dialog.setContentView(R.layout.dialog);

        // Найдите элемент TextView внутри вашей разметки
        // и установите ему соответствующий текст
        imageView = dialog.findViewById(R.id.image_progress);
        text = dialog.findViewById(R.id.text_progress);
        SharedPreferences sp2 = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);

int2 = 0;
// проверяем, первый ли раз открывается программа
        boolean hasVisited2 = sp2.getBoolean("hasVisited2", false);
        if (hasVisited2){
        int2 =1;
        }

        if (!hasVisited2) {

            imageView.setImageResource(R.drawable.progress2);
            text.setText("Вы решили распланировать свой день? Отлично! Вот вам награда!");
            dialog.show();
            SharedPreferences.Editor e = sp2.edit();
            e.putBoolean("hasVisited2", true);
            e.apply();
            int2 =1;
        }


        dbHelper2 = new DBHelper2(this);
        dbHelper2 = new DBHelper2(getBaseContext());
        SQLiteDatabase db;
        db = dbHelper2.getReadableDatabase();
        Data = DaTa.getText().toString();
        String SRAVNENIE = "SELECT * FROM "+DBHelper2.TABLE_CONTACTS+" WHERE "+DBHelper2.KEY_DATA+"="+ "\""+ Data +"\"" +";";
        Cursor cursor1 = db.rawQuery(SRAVNENIE, null);
        cursor1.moveToFirst();
        if (cursor1.getCount()!=0) {
            edIt1 = cursor1.getString(cursor1.getColumnIndex(DBHelper2.KEY_EDIT1));
            edIt2 = cursor1.getString(cursor1.getColumnIndex(DBHelper2.KEY_EDIT2));
            edIt3 = cursor1.getString(cursor1.getColumnIndex(DBHelper2.KEY_EDIT3));
            edIt4 = cursor1.getString(cursor1.getColumnIndex(DBHelper2.KEY_EDIT4));
            edIt5 = cursor1.getString(cursor1.getColumnIndex(DBHelper2.KEY_EDIT5));
            edIt6 = cursor1.getString(cursor1.getColumnIndex(DBHelper2.KEY_EDIT6));
            edit1.setText(edIt1);
            edit2.setText(edIt2);
            edit3.setText(edIt3);
            edit4.setText(edIt4);
            edit5.setText(edIt5);
            edit6.setText(edIt6);
        }

        cursor1.close();
        db.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                i = new Intent(NewTask.this, MainActivity.class );
                startActivity(i);
                NewTask.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {

        Edite1 = edit1.getText().toString();
        Edite2 = edit2.getText().toString();
        Edite3 = edit3.getText().toString();
        Edite4 = edit4.getText().toString();
        Edite5 = edit5.getText().toString();
        Edite6 = edit6.getText().toString();
        switch (view.getId()){
            case R.id.SaVe:
                dbHelper2 = new DBHelper2(getBaseContext());
                SQLiteDatabase db;
                db = dbHelper2.getReadableDatabase();
                String SRAVNENIE = "SELECT * FROM "+DBHelper2.TABLE_CONTACTS+" WHERE "+DBHelper2.KEY_DATA+"="+ "\""+Data+"\"" +";";
                Cursor cursor = db.rawQuery(SRAVNENIE,null);

                String insertQuery = "INSERT INTO " + DBHelper2.TABLE_CONTACTS + " ("+ DBHelper2.KEY_DATA + ","+ DBHelper2.KEY_EDIT1 + "," +
                        DBHelper2.KEY_EDIT2 + "," + DBHelper2.KEY_EDIT3 + "," + DBHelper2.KEY_EDIT4  + ","+DBHelper2.KEY_EDIT5+","+DBHelper2.KEY_EDIT6+
                        ") VALUES (\""+ Data+"\", \"" + Edite1 +"\", \""+Edite2+"\", \"" +Edite3+ "\", \""+Edite4+"\", \""+Edite5+"\","+"\""+Edite6+"\");";
                cursor.moveToFirst();
                String updateQuery = "UPDATE " + DBHelper2.TABLE_CONTACTS + " SET " + DBHelper2.KEY_EDIT1 +
                        " = \"" + Edite1 + "\", " + DBHelper2.KEY_EDIT2 + "= \"" + Edite2 + "\", " +
                        DBHelper2.KEY_EDIT3 + "= \"" + Edite3+ "\", "+DBHelper2.KEY_EDIT4 + "= \"" + Edite4+ "\", "+DBHelper2.KEY_EDIT5 + "= \"" + Edite5 + "\", "+DBHelper2.KEY_EDIT6 + "= \"" + Edite6 + "\" " +" WHERE " + DBHelper2.KEY_DATA + "= \"" + Data +"\";" ;
                db.close();

                if (cursor.getCount() == 0 ){
                    dbHelper2 = new DBHelper2(getBaseContext());
                    db = dbHelper2.getWritableDatabase();
                    db.execSQL(insertQuery);
                    db.close();
                    cursor.close();
                }else{
                    dbHelper2 = new DBHelper2(getBaseContext());
                    db = dbHelper2.getWritableDatabase();
                    db.execSQL(updateQuery);
                    db.close();
                    cursor.close();
                }
                i = new Intent(NewTask.this, MainActivity.class );
                startActivity(i);
                NewTask.this.finish();
                break;

            case R.id.Del:
                edit1.setText(null);
                edit2.setText(null);
                edit3.setText(null);
                edit4.setText(null);
                edit5.setText(null);
                edit6.setText(null);

                dbHelper2 = new DBHelper2(this);
                dbHelper2 = new DBHelper2(getBaseContext());
                SQLiteDatabase db1;
                db1 = dbHelper2.getReadableDatabase();
                String deleteQuery = "DELETE FROM "+DBHelper2.TABLE_CONTACTS+" WHERE "+DBHelper2.KEY_DATA+"="+ "\""+Data+"\"" +";";
                db1.execSQL(deleteQuery);
                NewTask.this.finish();
                i = new Intent(NewTask.this, MainActivity.class );
                startActivity(i);
                NewTask.this.finish();
                break;

        }

    }
}