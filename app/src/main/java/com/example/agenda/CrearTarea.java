package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.agenda.utilidades.Utilidades;

public class CrearTarea extends AppCompatActivity {

	EditText etTitulo;
	EditText etDescripcion;
	EditText etDuracion;
	EditText etFecha;
	RadioButton rbAlta;
	RadioButton rbMedia;
	RadioButton rbBaja;

	EditText etHora;
	int lista;
	ConexionSQLiteHelper conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crear_tarea);
		conn = new ConexionSQLiteHelper(this);
		etTitulo = (EditText) findViewById(R.id.etTitulo);
		etDescripcion = (EditText)findViewById(R.id.etDescirpcion);
		etDuracion = (EditText)findViewById(R.id.etDurecion);
		etFecha = (EditText)findViewById(R.id.etFecha);
		rbAlta = (RadioButton) findViewById(R.id.rbAlta);
		rbMedia = (RadioButton) findViewById(R.id.rbMedia);
		rbBaja = (RadioButton) findViewById(R.id.rbBaja);

		etHora = (EditText)findViewById(R.id.etHora);

		etHora.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				TimePickerDialog timePickerDialog = new TimePickerDialog(CrearTarea.this, new TimePickerDialog.OnTimeSetListener() {
					@Override
					public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
						etHora.setText(hourOfDay + ":" + minutes);
					}
				},0,0,false);
				timePickerDialog.show();
			}
		});

		Intent iin= getIntent();
		Bundle b = iin.getExtras();

		if(b!=null)
		{
			lista =(int) b.get("lista");

		}else{
			lista = 1;
		}

	}
	public void guardar(View v){
		if (etTitulo.getText().length()>0 && etDuracion.getText().length()>0 && etDuracion.getText().length()>0 && etFecha.getText().length()>0 && etHora.getText().length()>0){
			SQLiteDatabase db = conn.getWritableDatabase();
			String prioridad ="Sin Prioridad";
			if(rbBaja.isChecked()){
				prioridad = "Baja";
			}else if(rbAlta.isChecked()){
				prioridad = "Alta";
			}else if(rbMedia.isChecked()){
				prioridad = "Media";
			}
			String inset = "INSERT INTO "+ Utilidades.TABLA_TAREA+" ("+Utilidades.CAMPO_TITULO+","+Utilidades.CAMPO_DESCRIPCION+","+Utilidades.CAMPO_DURACION+","+
					Utilidades.CAMPO_FECHA+","+Utilidades.CAMPO_PRIORIDAD+","+Utilidades.CAMPO_HORA+","+Utilidades.CAMPO_ID_LISTA+")"+
					" VALUES('"+etTitulo.getText().toString()+"','"+etDescripcion.getText().toString()+"',"+etDuracion.getText().toString()+",'"+etFecha.getText().toString()+
					"','"+prioridad+"','"+etHora.getText().toString()+"',"+lista+")";

			db.execSQL(inset);

			Intent i9 = new Intent(this, Inicio.class);

			startActivity(i9);
			Toast.makeText(this,"Tarea Creada",Toast.LENGTH_LONG).show();
			finish();

		}else{
			Toast.makeText(this,"Llene todos los valores requeridos",Toast.LENGTH_LONG).show();
		}
	}
	public void cacelarTarea(View v){
		Intent i6 = new Intent(this, Inicio.class);

		startActivity(i6);
		finish();
	}

}
