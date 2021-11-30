package com.forum;

import java.util.ArrayList;
import java.util.List;

import es.lanyu.forum.Comentario;
import es.lanyu.forum.Tema;
import es.lanyu.forum.Usuario;
import es.lanyu.forum.UsuarioImpl;
import es.lanyu.forum.test.DatosPrueba;

public class Application {
	
	private final static List<Comentario> COMENTARIOS = new ArrayList<>(DatosPrueba.COMENTARIOS);
	
	static List<Comentario> getComentarios() {
		return COMENTARIOS;
	}
	
	public static void main(String[] args) {

//		getComentarios().forEach(System.out::println);
		
		Usuario usuario3 = new UsuarioImpl("user3");
		Tema tema = getComentarios().get(0).getTema();
		comentar(usuario3, tema, "Comentario añadido");
		getComentarios().forEach(System.out::println);

	}
	
	public static boolean comentar(Usuario usuario, Tema tema, String texto) {
		Comentario comentario = new Comentario(usuario, texto, tema);
		boolean agregado = getComentarios().add(comentario);
		if (agregado) {
			System.err.println("El comentario se añadió correctamente");
		} else {
			System.err.println("El comentario no se pudo añadir");
		}
	
		return agregado;
	}

}
