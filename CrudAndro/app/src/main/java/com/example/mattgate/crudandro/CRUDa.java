package com.example.mattgate.crudandro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CRUDa extends AppCompatActivity {


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cruda);

        Button add = (Button)findViewById(R.id.saved_button);
        Button view = (Button)findViewById(R.id.show_button);

         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent cambioadd =  new Intent(CRUDa.this,CRUDprueba.class);
                 startActivity(cambioadd);
             }
         });
         view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent cambioview =  new Intent(CRUDa.this,CRUDview.class);
                 startActivity(cambioview);
             }
         });
    }



}
