package com.example.mattgate.crudandro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class AyudanteBase extends SQLiteOpenHelper {

    String Tabla = "CREATE TABLE AGENDA(ID INTEGER PRIMARY KEY,NOMBRES TEXT,APELLIDOS TEXT,DIRECCION TEXT,CORREO TEXT,TELEFONO TEXT)";
    String Borrartabla = "DROP TABLE AGENDA";
    public AyudanteBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Borrartabla);
        sqLiteDatabase.execSQL(Tabla);
    }
}
