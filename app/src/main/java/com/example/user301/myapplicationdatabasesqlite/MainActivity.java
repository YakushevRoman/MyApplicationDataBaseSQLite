package com.example.user301.myapplicationdatabasesqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MY_LOG = "my_log";

    public static final String DB_NAME = "SQLite_db_name";
    public static final int DB_VERSION = 1;
    public static final SQLiteDatabase.CursorFactory CURSOR_FACTORY = null;

    EditText editTextName, editTextEmail;
    Button buttonAdd, buttonClear, buttonRead;

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.etName);
        editTextEmail = findViewById(R.id.etEmail);

        buttonAdd = findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(this);

        buttonRead = findViewById(R.id.btnRead);
        buttonRead.setOnClickListener(this);

        buttonClear = findViewById(R.id.btnClear);
        buttonClear.setOnClickListener(this);

        dbHelper = new DBHelper(this, DB_NAME, CURSOR_FACTORY, DB_VERSION);
    }

    @Override
    public void onClick(View v) {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();

        sqLiteDatabase = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();

        switch (v.getId()){
            case R.id.btnAdd:
                contentValues.put("name", name);
                contentValues.put("email", email);
                Long id = sqLiteDatabase.insert("mySqliteDataBase", null, contentValues);
                Log.d(MY_LOG, " _id = " + id);
                break;
            case R.id.btnRead:
                Cursor cursor = sqLiteDatabase.query("mySqliteDataBase",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                    if (cursor.moveToFirst()){
                        do{
                            Log.d(MY_LOG,
                                    "ID : " + cursor.getInt(cursor.getColumnIndex("_id"))
                                            + " name :" + cursor.getString(cursor.getColumnIndex("name"))
                                            + " email: " + cursor.getString(cursor.getColumnIndex("email")));
                        }while (cursor.moveToNext());
                    }
                break;
            case R.id.btnClear:
                sqLiteDatabase.delete("mySqliteDataBase", null, null);
                break;
        }

    }
}
