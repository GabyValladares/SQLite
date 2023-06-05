package com.example.sqlite.bdd;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {


    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tblUsuarios"+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "usu_cedula integer NOT NULL,"+
                "usu_nombre text NOT NULL,"+
                "usu_apellido text NOT NULL,"+
                "usu_correo_electronico text NOT NULL)");
       // db.execSQL("create table suma (id integer primary key autoincrement )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //se ejecuta cuando se cambia la versión de la base de datos
        db.execSQL("DROP TABLE tblUsuarios");
        onCreate(db);
    }
}
