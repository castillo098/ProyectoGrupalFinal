package com.example.mainactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//Importacion de SQLITEOPENHELPER
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    //Constructur generado de tipo SQLITEOPENHELPER
    public AdminSQLiteOpenHelper
    (@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Metodo onCreate con parametro SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        //Se creo un tabla articulos donde se va almecenar datos junto los atributos
        BaseDeDatos.execSQL("create table articulos(codigo int primary key, descripcion text, precio real)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
