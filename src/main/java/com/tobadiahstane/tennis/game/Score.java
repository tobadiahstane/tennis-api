package com.tobadiahstane.tennis.game;

public class Score {

	private final int id;
	private final String score;
	private final int player1;
	private final int player2;
	
	public Score(int id, String score, int player1, int player2) {
		this.id = id;
		this.score = score;
		this.player1 = player1;
		this.player2 = player2;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getScore() {
		// TODO Auto-generated method stub
		return score;
	}

	public int getPlayer1() {
		// TODO Auto-generated method stub
		return player1;
	}

	public int getPlayer2() {
		// TODO Auto-generated method stub
		return player2;
	}

}
