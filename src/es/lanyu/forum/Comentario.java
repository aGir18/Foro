package es.lanyu.forum;

import java.time.Instant;

public class Comentario {

	private Usuario usuario;
	private String comentario;
	private Tema tema;
	private Instant tiempo;

	public Usuario getUsuario() {
		return usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public Tema getTema() {
		return tema;
	}

	public Instant getTiempo() {
		return tiempo;
	}

	public Comentario() {
		this(null, "Sin comentario", null, null);
	}
	
	public Comentario(Usuario usuario, String comentario, Tema tema) {
		this(usuario, comentario, tema, Instant.now());
	}

	public Comentario(Usuario usuario, String comentario, Tema tema, Instant tiempo) {
		this.usuario = usuario;
		this.comentario = comentario;
		this.tema = tema;
		this.tiempo = tiempo;
	}
	
	public static String textoRecortado(String texto, int longitudMaxima) {
		String textoRecortado = texto;
		if (texto.length() > longitudMaxima) {
			textoRecortado = texto.substring(0, longitudMaxima) + "...";
		}
		return textoRecortado;
	}

	@Override
	public String toString() {
		return getUsuario() + ": "  + textoRecortado(getComentario(), 20) + " en " + getTema() + " a las " + getTiempo();
	}

}
