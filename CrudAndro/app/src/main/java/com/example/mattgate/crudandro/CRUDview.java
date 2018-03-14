package com.example.mattgate.crudandro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CRUDview extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;
    @Override
    public void onBackPressed() {
        Intent cambiomenu =  new Intent(CRUDview.this,CRUDa.class);
        startActivity(cambiomenu);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargarlistado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudview);
        listView = (ListView) findViewById(R.id.list);

        cargarlistado();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String[] parts = listado.get(i).split(" ");


                int idselect = Integer.parseInt(parts[0]);

                Intent intent = new Intent(CRUDview.this,CRUDviewdetail.class);
                intent.putExtra("Id",idselect);
                startActivity(intent);
            }
        });

        if(getSupportActionBar()!=null){
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarlistado(){
      listado = Listaagenda();
        ArrayAdapter<String> adaptado = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adaptado);

    }
    private ArrayList<String> Listaagenda(){
        ArrayList<String> datos = new ArrayList<String>();
        AyudanteBase ayudante = new AyudanteBase(this,"BaseAgenda" ,null,1);
        SQLiteDatabase db = ayudante.getReadableDatabase();
        String sql = "SELECT * FROM AGENDA";

        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()){

            do{
                String linea = c.getInt(0) + " ." + c.getString(1) + " " + c.getString(2);
                datos.add(linea);
            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }
}
