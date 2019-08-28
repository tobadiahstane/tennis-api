package com.tobadiahstane.tennis.game;

import com.tobadiahstane.tennis.util.TennisCommandRejected;

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
	public int startGame(int p1, int p2) throws TennisCommandRejected {
		
		if (alreadyPlayingInGames(p1, p2)) {
			throw new TennisCommandRejected();
		}else {
		   try { //The call to 	   
			int nextGameId = this.gameQuery.lookupNextGameId();
		    GameUpdated event = this.engine.createGame(nextGameId, p1, p2);
		    this.gameLog.storeUpdate(event);
		    return event.gameId;
		   } catch (Throwable e) {
			   throw new TennisCommandRejected(e);
		   }
		}
	
	}
	
	private boolean alreadyPlayingInGames (int p1, int p2) throws TennisCommandRejected {
		try {
			return this.gameQuery.alreadyPlaying(p1) || this.gameQuery.alreadyPlaying(p2);
		} catch (Throwable e) {
			throw new TennisCommandRejected(e);
		}
	}
	
	@Override
	public void nextScore(int gameId, int playerId) throws TennisCommandRejected {
		if(cannotAddNextScore(gameId)) {
			throw new TennisCommandRejected();
		}else {
		    try {
			GameUpdated nextEvent = this.engine.awardPoint(this.gameLog.loadGame(gameId), playerId);
		    this.gameLog.storeUpdate(nextEvent);
		    } catch (Throwable e) {
		    	throw new TennisCommandRejected(e);
		    }
		}
	}
	
	private boolean cannotAddNextScore(int gameId) throws TennisCommandRejected {
		try {
		    return false == this.gameQuery.isGame(gameId) || false == this.gameQuery.gameOver(gameId);
		} catch (Throwable e) {
			throw new TennisCommandRejected(e);
		}
		
	}
	
	@Override
	public Score callScore(int gameId){
		try {
		    return gameQuery.callScore(gameId);
		} catch (Throwable e) {
			throw new TennisCommandRejected(e);
		}
	}
	
}
