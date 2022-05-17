package domain;

import java.util.List;

public class Grilla {

	private String nombre;
	private boolean visitada;
	
	public Grilla(){
		this.visitada = false;
	}
	
	public Grilla(String nombre){
		this.nombre = nombre;
		this.visitada = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isVisitada() {
		return visitada;
	}

	public void setVisitada(boolean visitada) {
		this.visitada = visitada;
	}

	public Object clone(){
		Grilla newHabitacion = new Grilla();
		
		newHabitacion.setNombre(this.getNombre());
		newHabitacion.setVisitada(this.isVisitada());
		
		return newHabitacion;
	}
	
	public String toString(){
		return nombre+"-"+visitada;
	}
	
	public static boolean todasVisitadas(List<Grilla> habitaciones){
		boolean allVisited = true;
		
		for(Grilla h : habitaciones)
			allVisited = allVisited && h.isVisitada();
		
		return allVisited;
	}

}
