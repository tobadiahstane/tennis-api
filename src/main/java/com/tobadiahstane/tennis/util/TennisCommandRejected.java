package com.tobadiahstane.tennis.util;

public class TennisCommandRejected extends IllegalArgumentException {

	private static final long serialVersionUID = -7162579239696261670L;

	public TennisCommandRejected() {
	}

	public TennisCommandRejected(String s) {
		super(s);
		
	}

	public TennisCommandRejected(Throwable cause) {
		super(cause);
	}

	public TennisCommandRejected(String message, Throwable cause) {
		super(message, cause);
	}

}
