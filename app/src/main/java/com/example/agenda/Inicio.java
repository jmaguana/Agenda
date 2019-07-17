package com.example.agenda;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agenda.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {

	ConexionSQLiteHelper conn;
	ListView listViewTareas;
	List<String> listaListas ;
	List<Integer> listaIds;

	List<String> listaViewTareas;
	List<Integer> listaTareas;

	Spinner spinner;

	int listaActual ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		listaActual = 1;
		conn = new ConexionSQLiteHelper(this);

		listViewTareas = (ListView)findViewById(R.id.lista_view);

		spinner = (Spinner)findViewById(R.id.spinner);

		listaListas = new ArrayList<>();
		listaIds = new ArrayList<>();

		consultar();

		ArrayAdapter<CharSequence> adaptador2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaListas);
		spinner.setAdapter(adaptador2);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				listaActual = listaIds.get(i);
				llenarLista(listaActual);

			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

		listViewTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				//Toast.makeText(Inicio.this,"Eligio el item: "+listaViewTareas.get(i),Toast.LENGTH_LONG).show();

				Intent insdf = new Intent(Inicio.this,ShowTareas.class);
				insdf.putExtra(Utilidades.CAMPO_ID_TABLA,listaTareas.get(i));
				startActivity(insdf);
			}
		});
	}

	private void llenarLista(Integer id) {
		listaTareas = new ArrayList<>();
		listaViewTareas = new ArrayList<>();

		SQLiteDatabase db = conn.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_ID_TABLA+","+Utilidades.CAMPO_TITULO+" FROM "+Utilidades.TABLA_TAREA+" WHERE "+Utilidades.CAMPO_ID_LISTA+"="+id,null);
		while (cursor.moveToNext()){
			listaTareas.add(cursor.getInt(0));
			listaViewTareas.add(cursor.getString(1));
		}

		ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaViewTareas);
		listViewTareas.setAdapter(adaptador);

	}

	public void consultar(){
		SQLiteDatabase db = conn.getReadableDatabase();
		String[] campos = {Utilidades.CAMPO_ID_LISTA,Utilidades.CAMPO_NOMBRE};
		Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_LISTA,null);
		while (cursor.moveToNext()){
			listaIds.add(cursor.getInt(0));
			listaListas.add(cursor.getString(1));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		if (id == R.id.nueva_lista) {
			Intent i = new Intent(Inicio.this, CrearLista.class);

			startActivity(i);
			finish();
			return true;
		}else if(id == R.id.nueva_tarea){
			Intent int3 = new Intent(Inicio.this, CrearTarea.class);
			int3.putExtra("lista",listaActual);
			startActivity(int3);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
