package com.forum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import es.lanyu.forum.Comentario;
import es.lanyu.forum.Tema;
import es.lanyu.forum.Usuario;
import es.lanyu.forum.UsuarioExterno;
import es.lanyu.forum.UsuarioImpl;
import es.lanyu.forum.test.DatosPrueba;
import com.github.likes.Likes;

public class Application {

	private final static List<Comentario> COMENTARIOS = new ArrayList<>(DatosPrueba.COMENTARIOS);

	static List<Comentario> getComentarios() {
		return COMENTARIOS;
	}

	public static void main(String[] args) {

		Usuario usuario3 = new UsuarioImpl("user3");
		Tema tema1 = getComentarios().get(0).getTema();
		comentar(usuario3, tema1, "Comentario añadido");
		getComentarios().forEach(System.out::println);

		Usuario usuarioExterno = new UsuarioExterno("usuarioExterno");
		comentar(usuarioExterno, tema1, "Soy un usuario externo");
		getComentarios().forEach(System.out::println);

		System.out.println(System.lineSeparator() + "Ordenado naturalmente por fecha descendente:");
		getComentarios().sort(null);
		getComentarios().forEach(System.out::println);

		System.out.println(System.lineSeparator() + "Recomendaciones (Likes): ");
		Comentario comentario1 = getComentarios().get(0);
		Comentario comentario2 = getComentarios().get(1);
		Likes.getLikesFor(comentario1);
		Likes.recomendar(comentario1, usuario3.getNombre());
		Likes.recomendar(comentario2, usuario3.getNombre());
		Likes.recomendar(comentario2, usuarioExterno.getNombre());
		System.out.println(textoRecomendacion(comentario1));
		System.out.println(textoRecomendacion(comentario2));

	}

	private static boolean comentar(Usuario usuario, Tema tema, String texto) {
		Comentario comentario = new Comentario(usuario, texto, tema);
		boolean agregado = getComentarios().add(comentario);
		if (agregado) {
			System.err.println("El comentario se añadió correctamente");
		} else {
			System.err.println("El comentario no se pudo añadir");
		}

		return agregado;
	}

	static Usuario mapStringToUsuarios(Collection<Usuario> usuarios, String userName) {
		Usuario usuario = null;
		for (Usuario unUsuario : usuarios) {
			if (unUsuario.getNombre().equals(userName)) {
				usuario = unUsuario;
			}
		}

		return usuario;
	}

	private static <T extends Comentario> String textoRecomendacion(T contenido) {
		String like = contenido.textoRecortado(contenido.getComentario(), 20) + " ";
		like += getComentarios().indexOf(contenido) + 1 + "* ";
		like += Arrays.toString(Likes.getLikesFor(contenido)).toUpperCase();
		return like;

	}

}
