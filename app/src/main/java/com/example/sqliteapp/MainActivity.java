package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    clsDb ohDB = new clsDb(this,"dbLibrary",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.etname);
        EditText email = findViewById(R.id.etemail);
        EditText password =  findViewById(R.id.etpassword);
        ImageButton add = findViewById(R.id.btnadd);
        ImageButton search = findViewById(R.id.btnsearch);
        ImageButton update = findViewById(R.id.btnupdate);
        ImageButton delete = findViewById(R.id.btndelete);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser(name.getText().toString(), email.getText().toString(), password.getText().toString());
            }
        });

    }

    private void saveUser(String sEmail, String sName, String sPassword) {

//        clsDb ohDB = new clsDb(this,"dbLibrary",null,1);
        SQLiteDatabase db = ohDB.getWritableDatabase();
        try
        {
            //Contenedor de datos del contacto
            ContentValues cvUser = new ContentValues();
            cvUser.put("fullname",sName);
            cvUser.put("email",sEmail);
            cvUser.put("password",sPassword);
            db.insert("user",null,cvUser);
            db.close();
            Toast.makeText(getApplicationContext(),"User added succesfully...",Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}