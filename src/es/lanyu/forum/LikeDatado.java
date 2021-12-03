package es.lanyu.forum;

import java.time.Instant;

import com.github.likes.Like;

public class LikeDatado<T extends DeUsuario & Datable> extends Like<T> {

	Instant fecha;
	
	public LikeDatado(T content, String user) {
		super(content, user);
	}

}
