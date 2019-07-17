package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.utilidades.Utilidades;

public class ShowTareas extends AppCompatActivity {

	int id;
	ConexionSQLiteHelper conn;

	TextView titulo;
	EditText descripcion;
	TextView duracion;
	TextView prioridad;
	TextView fecha;
	TextView hora;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_tareas);

		titulo = (TextView)findViewById(R.id.txtTitulo);
		descripcion = (EditText)findViewById(R.id.txtDescripcion);
		duracion = (TextView)findViewById(R.id.txtDuracion);
		prioridad = (TextView)findViewById(R.id.txtPrioridad);
		fecha = (TextView)findViewById(R.id.txtFecha);
		hora = (TextView)findViewById(R.id.txtHora);

		conn = new ConexionSQLiteHelper(this);

		descripcion.setEnabled(false);

		id = 0;
		Intent iin2= getIntent();
		Bundle b = iin2.getExtras();

		if(b!=null)
		{
			id =(int) b.get(Utilidades.CAMPO_ID_TABLA);

		}else{
			id = 1;
		}

		cargarDatos();

	}

	public void borrar(View v){
		SQLiteDatabase db = conn.getReadableDatabase();
		String[] parametros = {String.valueOf(id)};
		db.delete(Utilidades.TABLA_TAREA,Utilidades.CAMPO_ID_TABLA+"=?",parametros);
		Toast.makeText(this,"Tarea Eliminada",Toast.LENGTH_LONG).show();
		Intent i67 = new Intent(this, Inicio.class);

		startActivity(i67);
		finish();
	}
	public void atras(View v){
		Intent i65 = new Intent(this, Inicio.class);

		startActivity(i65);
		finish();
	}
	public void cargarDatos(){
		SQLiteDatabase db = conn.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_TITULO+","+Utilidades.CAMPO_DESCRIPCION+","+Utilidades.CAMPO_DURACION+","+
				Utilidades.CAMPO_FECHA+","+Utilidades.CAMPO_PRIORIDAD+","+Utilidades.CAMPO_HORA+" FROM "+Utilidades.TABLA_TAREA+" WHERE "+Utilidades.CAMPO_ID_TABLA+"="+id,null);
		cursor.moveToFirst();
		titulo.setText(cursor.getString(0));
		descripcion.setText(cursor.getString(1));
		duracion.setText("Duración: "+String.valueOf(cursor.getInt(2)));
		fecha.setText(cursor.getString(3));
		prioridad.setText("Prioridad: "+cursor.getString(4));
		hora.setText(cursor.getString(5));
			//listaTareas.add(cursor.getInt(0));
			//listaViewTareas.add(cursor.getString(1));

	}

}
