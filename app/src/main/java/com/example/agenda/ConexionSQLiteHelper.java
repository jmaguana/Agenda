package com.example.agenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.agenda.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

	public ConexionSQLiteHelper(Context context) {
		super(context, "db_tereas", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(Utilidades.CREAR_TABLA_TAREA);
		sqLiteDatabase.execSQL(Utilidades.CREAR_TABLA_LISTA);
		sqLiteDatabase.execSQL("INSERT INTO "+ Utilidades.TABLA_LISTA+" ("+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_ID_LISTA+") VALUES('default', 1)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_TAREA);
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_LISTA);
		onCreate(sqLiteDatabase);
	}
}
