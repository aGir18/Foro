package es.lanyu.forum;

public class Tema {

	private String tema;

	public String getTema() {
		return tema;
	}

	public Tema(String tema) {
		this.tema = tema;
	}

	@Override
	public String toString() {
		return "Tema: " + getTema();
	}

}
