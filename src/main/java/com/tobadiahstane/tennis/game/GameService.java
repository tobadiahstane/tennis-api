package com.tobadiahstane.tennis.game;

public class GameService implements Games {

	private final IHandleGames engine;
	private final IPersistGames gameLog;
	private final IQueryGames gameQuery;

	public GameService(IHandleGames engine, IQueryGames gameQuery, IPersistGames gameLog) {
		this.gameLog = gameLog;
		this.gameQuery = gameQuery;
		this.engine = engine;
	}

	@Override
	public int startGame(int p1, int p2) {
		int nextGameId = this.gameQuery.lookupNextGameId();
		GameUpdated event = this.engine.createGame(nextGameId, p1, p2);
		this.gameLog.storeUpdate(event);
		return event.gameId;
	
	}
	
	@Override
	public void nextScore(int gameId, int playerId) {
		GameUpdated nextEvent = this.engine.awardPoint(this.gameLog.loadGame(gameId), playerId);
		this.gameLog.storeUpdate(nextEvent);
			
	}
	
	@Override
	public GameUpdated callScore(int gameId){
		return gameQuery.callScore(gameId);
	}
	
}
