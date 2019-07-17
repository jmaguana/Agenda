package com.example.agenda;

public class Tarea {

	private int id;
	private String titulo;
	private String descripcion;
	private int duracion;
	private String prioridad;
	private String fecha;
	private String hora;

	public Tarea(int id, String titulo, String descripcion, int duracion, String prioridad, String fecha, String hora) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.prioridad = prioridad;
		this.fecha = fecha;
		this.hora = hora;
	}

}
