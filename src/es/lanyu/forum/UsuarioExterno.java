package es.lanyu.forum;

import com.github.auth.User;

public class UsuarioExterno extends User implements Usuario {

	public UsuarioExterno(String userName) {
		super(userName);
	}

	@Override
	public String getNombre() {
		return getUserName();
	}

}
