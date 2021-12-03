package es.lanyu.forum;

import java.time.Instant;

public class Comentario implements Comparable<Comentario>, DeUsuario, Datable {
	
	private Usuario usuario;
	private String texto;
	private Tema tema;
	private Instant instant;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public Tema getTema() {
		return tema;
	}
	
	public Instant getInstant() {
		return instant;
	}
	
	public Comentario() {
		this(null, "Sin comentarios", null);
	}
	
	public Comentario(Usuario usuario, String texto, Tema tema, Instant instant) {
		this.usuario = usuario;
		this.texto = texto;
		this.tema = tema;
		this.instant = instant;
	}
	
	public Comentario(Usuario usuario, String texto, Tema tema) {
		this(usuario, texto, tema, Instant.now());
	}
	
	public String recortarTexto(String texto, int longitudTexto) {
		if (texto.length() > longitudTexto) {
			texto = texto.substring(0, longitudTexto) + "... ";
		}
		return texto;
	}

	@Override
	public int compareTo(Comentario comentario) {
		return comentario.getInstant().compareTo(getInstant());
	}
	
	@Override
	public String toString() {
		return getUsuario().toString() + ": " + recortarTexto(getTexto(), 20)  +" en " + getTema() + " a las " + getInstant();
	}
	
}
