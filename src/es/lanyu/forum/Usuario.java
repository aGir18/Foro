package es.lanyu.forum;

public interface Usuario {

	String getNombre();
	
	default String getToString() {
		return getNombre().toUpperCase();
	};

}