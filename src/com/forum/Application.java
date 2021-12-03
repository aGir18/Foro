package com.forum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import es.lanyu.forum.Comentario;
import es.lanyu.forum.LikeDatado;
import es.lanyu.forum.Tema;
import es.lanyu.forum.Usuario;
import es.lanyu.forum.UsuarioExterno;
import es.lanyu.forum.UsuarioImpl;
import es.lanyu.forum.test.DatosPrueba;

import com.github.likes.Likes;

public class Application {

	private static final List<Comentario> COMENTARIOS = new ArrayList<>(DatosPrueba.COMENTARIOS);

	public static List<Comentario> getComentarios() {
		return COMENTARIOS;
	}

	public static void main(String[] args) {

		getComentarios().forEach(System.out::println);

		Usuario usuario3 = new UsuarioImpl("user3");
		Tema tema1 = getComentarios().get(0).getTema();
		comentar(usuario3, tema1, "Comentario añadido");
		getComentarios().forEach(System.out::println);

		Usuario usuarioExterno = new UsuarioExterno("usuarioexterno");
		comentar(usuarioExterno, tema1, "Soy un usuario externo");
		getComentarios().forEach(System.out::println);

		getComentarios().sort(null);
		System.out.println(System.lineSeparator() + "Ordenación natural por fecha descendente: ");
		getComentarios().forEach(System.out::println);

		System.out.println(System.lineSeparator() + "Recomendaciones (Likes):");
		Comentario comentario1 = getComentarios().get(0);
		Comentario comentario2 = getComentarios().get(1);
		Likes.recomendar(comentario1, usuario3.getNombre());
		Likes.recomendar(comentario2, usuario3.getNombre());
		Likes.recomendar(comentario2, usuarioExterno.getNombre());
		System.out.println(textoRecomendacion(comentario1));
		System.out.println(textoRecomendacion(comentario2));
		
		Comparator<Comentario> comparador = new Comparator<Comentario>() {

			@Override
			public int compare(Comentario c1, Comentario c2) {
				int resultado = Integer.compare(Likes.getLikesFor(c2).length, Likes.getLikesFor(c1).length);
				if (resultado == 0) {
					resultado = c1.getInstant().compareTo(c2.getInstant());
				}
				return resultado;
			}
		};
		Comentario comentario3 = getComentarios().get(2);
		Usuario usuario1 = getComentarios().get(0).getUsuario();
		Usuario usuario2 = getComentarios().get(1).getUsuario();
		Likes.recomendar(comentario3, usuario1.getNombre());
		Likes.recomendar(comentario3, usuario2.getNombre());
		
		System.out.println(System.lineSeparator() + "Ordenado por recomendación (likes):");
		getComentarios().sort(comparador);
		for (Comentario comentario : getComentarios()) {
			System.out.println(textoRecomendacion(comentario));
		}
		
		System.out.println(System.lineSeparator() + "Usando boundaries: ");
		Likes.getLikes().add(new LikeDatado<Comentario>(comentario3, usuario3.getNombre()));
		getComentarios().sort(comparador);
		for (Comentario comentario : getComentarios()) {
			System.out.println(textoRecomendacion(comentario));
		}
	}

	public static boolean comentar(Usuario usuario, Tema tema, String texto) {
		boolean agregado = getComentarios().add(new Comentario(usuario, texto, tema));
		if (agregado) {
			System.err.println("El comentario se añadió correctamente");
		} else {
			System.err.println("El comentario no se pudo añadir");
		}
		return agregado;
	}

	static Usuario mapStringToUsuario(Collection<Usuario> usuarios, String userName) {
		Usuario usuario = null;
		for (Usuario unUsuario : usuarios) {
			if (unUsuario.getNombre().equals(userName)) {
				usuario = unUsuario;
			}
		}

		return usuario;
	}

	private static <T extends Comentario> String textoRecomendacion(T contenido) {
		String texto = contenido.recortarTexto(contenido.getTexto(), 20) + " ";
		String likes = Likes.getLikesFor(contenido).length + "* ";
		String users = Arrays.toString(Likes.getLikesFor(contenido)).toUpperCase();
		
		return texto + likes + users;
	}

}