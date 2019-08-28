package com.tobadiahstane.tennis.game;

import com.tobadiahstane.tennis.util.TennisCommandRejected;

public interface Games {


	int startGame(int p1, int p2) throws TennisCommandRejected;
	
	void nextScore(int gameId, int p1) throws TennisCommandRejected;

	Score callScore(int gameId);

	public interface IHandleGames {

		public GameUpdated createGame(int gameId, int p1, int p2);

		public GameUpdated awardPoint(GameUpdated game, int playerId);

	}
	
	public interface IQueryGames {
		public Score callScore(int gameId);

		public int lookupNextGameId();

		boolean alreadyPlaying(int playerId);

		boolean gameOver(int gameId);
		
		boolean isGame(int gameId);
		
	}
	
	public interface IPersistGames {
		public  void storeUpdate (GameUpdated update);
		
		public GameUpdated loadGame(int gameId);

		

	}

}

