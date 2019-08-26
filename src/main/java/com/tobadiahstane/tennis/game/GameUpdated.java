package com.tobadiahstane.tennis.game;

public class GameUpdated {

	public final int gameId;
	public final int playerOnePoints;
	public final int playerTwoPoints;
	public final int playerOneId;
	public final int playerTwoId;
	public final int winningId;
	public final boolean playerWon;
	public final boolean playerAdvantage;
	

	public GameUpdated(int gameId, int playerOneId, int playerTwoId, int playerOnePoints, int playerTwoPoints) {
		this.gameId = gameId;
		this.playerOnePoints = playerOnePoints;
		this.playerTwoPoints = playerTwoPoints;
		this.playerOneId = playerOneId;
		this.playerTwoId = playerTwoId;
		this.playerWon = false;
		this.playerAdvantage = false;
		this.winningId = 0;
	}
	
	public GameUpdated(int gameId, int playerOneId, int playerTwoId, int playerOnePoints, int playerTwoPoints, boolean playerWon, boolean playerAdvantage, int winningId) {
		this.gameId = gameId;
		this.playerOnePoints = playerOnePoints;
		this.playerTwoPoints = playerTwoPoints;
		this.playerOneId = playerOneId;
		this.playerTwoId = playerTwoId;
		this.playerWon = playerWon;
		this.playerAdvantage = playerAdvantage;
		this.winningId = winningId;
		
	}
	
		
}

