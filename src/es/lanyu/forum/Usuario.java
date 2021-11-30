package es.lanyu.forum;

public interface Usuario {

	String getNombre();
	
	default String getToString() {
		String mayusculas = getNombre();
		return mayusculas.toUpperCase();
	};
	
}
