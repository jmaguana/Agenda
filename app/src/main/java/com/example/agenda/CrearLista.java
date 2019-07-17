package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.utilidades.Utilidades;

public class CrearLista extends AppCompatActivity {

	EditText nombreLista;
	ConexionSQLiteHelper conn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_lista);
		conn = new ConexionSQLiteHelper(this);
		nombreLista = (EditText)findViewById(R.id.etNuevaLista);
	}

	public void insertar(){
		SQLiteDatabase db = conn.getWritableDatabase();
		if(nombreLista.getText().length()>0){


		String insert1 = "INSERT INTO "+ Utilidades.TABLA_LISTA+" ("+Utilidades.CAMPO_NOMBRE+") VALUES('"+this.nombreLista.getText().toString()+"')";

		db.execSQL(insert1);

		}else{
			Toast.makeText(this,"Escriba el nombre de la nueva Lista",Toast.LENGTH_LONG).show();
		}

	}
	public void crear(View v){
		insertar();
		Intent i = new Intent(this, Inicio.class);

		startActivity(i);
		Toast.makeText(this,"Lista Creada",Toast.LENGTH_LONG).show();
		finish();
	}
	public void cancelar(View v){
		Intent i = new Intent(this, Inicio.class);

		startActivity(i);
		finish();
	}
}
