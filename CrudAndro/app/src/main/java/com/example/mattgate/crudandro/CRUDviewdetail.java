package com.example.mattgate.crudandro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CRUDviewdetail extends AppCompatActivity {
    int id;
    String linea1="",linea2="",linea3="",linea4="",lineoculta1 ="", lineoculta2 ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudviewdetail);
        Bundle b = getIntent().getExtras();
        if(b != null){
            id = b.getInt("Id");
        }
        TextView V1 = (TextView)findViewById(R.id.VACIO1);
        TextView V2 = (TextView)findViewById(R.id.VACIO2);
        TextView V3 = (TextView)findViewById(R.id.VACIO3);
        TextView V4 = (TextView)findViewById(R.id.VACIO4);
        Button button =(Button)findViewById(R.id.button);
        Button button2 =(Button)findViewById(R.id.button2);
        mostrar();
        V1.setText(linea1);
        V2.setText(linea2);
        V3.setText(linea3);
        V4.setText(linea4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CRUDviewdetail.this,CRUDchange.class);
                intent.putExtra("Id",id);
                intent.putExtra("Nombre",lineoculta1);
                intent.putExtra("Apellido",lineoculta2);
                intent.putExtra("Direc",linea2);
                intent.putExtra("Correo",linea3);
                intent.putExtra("Tel",linea4);

                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              eliminarlo(id);
              onBackPressed();
            }
        });


    }
    private void mostrar(){
        AyudanteBase ayudante = new AyudanteBase(this,"BaseAgenda" ,null,1);
        SQLiteDatabase db = ayudante.getReadableDatabase();
        String sql = "SELECT * FROM AGENDA WHERE ID='"+id+"'";



        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();


        linea1 = c.getString(1) + " " + c.getString(2);
        lineoculta1 = c.getString(1);
        lineoculta2 =c.getString(2);
        linea2 = c.getString(3);
        linea3 = c.getString(4);
        linea4 = c.getString(5);

        db.close();


    }
    private void eliminarlo(int Id){
        AyudanteBase ayudante = new AyudanteBase(this,"BaseAgenda" ,null,1);
        SQLiteDatabase db = ayudante.getWritableDatabase();
        String sql = "DELETE FROM AGENDA WHERE ID ='"+Id+"'";
        db.execSQL(sql);
        db.close();
        Toast.makeText(this,"Contacto Eliminado con Exito!!!",Toast.LENGTH_SHORT).show();
    }



}
