package com.tobadiahstane.tennis.game;

public interface Games {

	//int addPlayer(String playerName);
	
	int startGame(int p1, int p2);
	
	void nextScore(int gameId, int p1);

	GameUpdated callScore(int gameId);

	public interface IHandleGames {

		public GameUpdated createGame(int gameId, int p1, int p2);

		public GameUpdated awardPoint(GameUpdated game, int playerId);

	}
	
	public interface IQueryGames {
		public GameUpdated callScore(int gameId);

		public int lookupNextGameId();
		
		
	}
	
	public interface IPersistGames {
		public  void storeUpdate (GameUpdated update);
		
		public GameUpdated loadGame(int gameId);

		

	}

}
