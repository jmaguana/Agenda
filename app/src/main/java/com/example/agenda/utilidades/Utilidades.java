package com.example.agenda.utilidades;

public class Utilidades {

	//Campos de tabla tarea

	public static final String TABLA_TAREA = "tarea";
	public static final String CAMPO_ID_TABLA = "id_tabla";
	public static final String CAMPO_TITULO = "titulo";
	public static final String CAMPO_DESCRIPCION = "descripcion";
	public static final String CAMPO_DURACION = "duracion";
	public static final String CAMPO_PRIORIDAD = "prioridad";
	public static final String CAMPO_FECHA = "fecha";
	public static final String CAMPO_HORA = "hora";

	public static final String TABLA_LISTA = "lista";
	public static final String CAMPO_ID_LISTA = "id_lista";
	public static final String CAMPO_NOMBRE = "nombre";

	public static final String CREAR_TABLA_TAREA = "CREATE TABLE "+
			TABLA_TAREA+" ("+CAMPO_ID_TABLA+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_TITULO+
			" TEXT,"+CAMPO_DESCRIPCION+" TEXT, "+CAMPO_DURACION
			+" INTEGER, "+CAMPO_PRIORIDAD+" TEXT, "+CAMPO_FECHA+
			" TEXT, "+CAMPO_HORA+" TEXT,"+CAMPO_ID_LISTA+" INTEGER)";




	public static final String CREAR_TABLA_LISTA = "CREATE TABLE "+TABLA_LISTA+
			" ("+CAMPO_ID_LISTA+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_NOMBRE+" TEXT NO NULL)";
}
