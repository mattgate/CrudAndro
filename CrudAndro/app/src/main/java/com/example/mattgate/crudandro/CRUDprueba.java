package com.example.mattgate.crudandro;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CRUDprueba extends AppCompatActivity {

    Button saved,clear;
    EditText nombresedit,apellidosedit,direccionedit,correoedit,teledit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudprueba);

        saved = (Button) findViewById(R.id.saved_button);
        clear = (Button) findViewById(R.id.clear_button);
        nombresedit = (EditText) findViewById(R.id.nombre_edit);
        apellidosedit = (EditText) findViewById(R.id.apellido_edit);
        direccionedit = (EditText) findViewById(R.id.direc_edit);
        correoedit = (EditText) findViewById(R.id.correo_edit);
        teledit = (EditText) findViewById(R.id.tele_edit);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                guardadoenbase(nombresedit.getText().toString(),apellidosedit.getText().toString(),direccionedit.getText().toString()
                ,correoedit.getText().toString(),teledit.getText().toString());

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombresedit.setText("");
                apellidosedit.setText("");
                direccionedit.setText("");
                correoedit.setText("");
                teledit.setText("");
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void guardadoenbase(String nombres,String apellidos,String direccion,String correo,String telefono){
        AyudanteBase ayudante = new AyudanteBase(this,"BaseAgenda" ,null,1);
        SQLiteDatabase db = ayudante.getWritableDatabase();

        try{
            ContentValues c = new ContentValues();
            c.put("NOMBRES" ,nombres);
            c.put("APELLIDOS", apellidos);
            c.put("DIRECCION",direccion);
            c.put("CORREO",correo);
            c.put("TELEFONO",telefono);
            db.insert("AGENDA",null, c);
            db.close();
            Toast.makeText(this,"Contacto Guardado",Toast.LENGTH_SHORT).show();
        }catch ( Exception e){
            Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
