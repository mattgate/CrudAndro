package com.example.mattgate.crudandro;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CRUDchange extends AppCompatActivity {
    int id;
    String nombre,apellido,direc,correo,numero;
    Button modificar,clearl;
    EditText nombresedit,apellidosedit,direccionedit,correoedit,teledit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudchange);
        Bundle b = getIntent().getExtras();
        if(b != null){
            id = b.getInt("Id");
            nombre = b.getString("Nombre");
            apellido = b.getString("Apellido");
            direc = b.getString("Direc");
            correo = b.getString("Correo");
            numero = b.getString("Tel");
        }
        modificar = (Button) findViewById(R.id.modificar_button);
        clearl = (Button) findViewById(R.id.clearbutton);
        nombresedit = (EditText) findViewById(R.id.nombre_edit);
        apellidosedit = (EditText) findViewById(R.id.apellido_edit);
        direccionedit = (EditText) findViewById(R.id.direc_edit);
        correoedit = (EditText) findViewById(R.id.correo_edit);
        teledit = (EditText) findViewById(R.id.tele_edit);

        nombresedit.setText(nombre);
        apellidosedit.setText(apellido);
        direccionedit.setText(direc);
        correoedit.setText(correo);
        teledit.setText(numero);

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             modificarlo(id,nombresedit.getText().toString(),apellidosedit.getText().toString(),
                     direccionedit.getText().toString(),correoedit.getText().toString(),teledit.getText().toString());
                Intent cambioamodica =  new Intent(CRUDchange.this,CRUDview.class);
                startActivity(cambioamodica);
            }
        });
           clearl.setOnClickListener(new View.OnClickListener() {
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

    private void modificarlo(int Id,String Nombres,String Apellidos,String Direccion,String Correo, String Telefono){
        AyudanteBase ayudante = new AyudanteBase(this,"BaseAgenda" ,null,1);
        SQLiteDatabase db = ayudante.getWritableDatabase();
        String sql = "UPDATE AGENDA SET NOMBRES='"+Nombres +"',APELLIDOS='"+Apellidos+"',DIRECCION='"+Direccion+"',CORREO='"+Correo+
                "',TELEFONO='"+Telefono +"' WHERE ID ='"+Id+"'";
        db.execSQL(sql);
        db.close();
        Toast.makeText(this,"Cambio Realizado con Exito!!!",Toast.LENGTH_SHORT).show();
    }
}
