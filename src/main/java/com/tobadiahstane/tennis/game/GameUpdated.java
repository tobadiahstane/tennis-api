package com.tobadiahstane.tennis.game;

public class GameUpdated {

	public final int gameId;
	public final int playerOnePoints;
	public final int playerTwoPoints;
	public final int playerOneId;
	public final int playerTwoId;
	public final int winnerId;
	public final boolean didPlayerWin;
	

	public GameUpdated(int gameId, int playerOneId, int playerTwoId, int playerOnePoints, int playerTwoPoints) {
		this.gameId = gameId;
		this.playerOnePoints = playerOnePoints;
		this.playerTwoPoints = playerTwoPoints;
		this.playerOneId = playerOneId;
		this.playerTwoId = playerTwoId;
		this.didPlayerWin = false;
		this.winnerId = 0;
	}
	
	public GameUpdated(int gameId, int playerOneId, int playerTwoId, int playerOnePoints, int playerTwoPoints, boolean didPlayerWin, int winnerId) {
		this.gameId = gameId;
		this.playerOnePoints = playerOnePoints;
		this.playerTwoPoints = playerTwoPoints;
		this.playerOneId = playerOneId;
		this.playerTwoId = playerTwoId;
		this.didPlayerWin = didPlayerWin;
		this.winnerId = winnerId;
	}
	
		
}

