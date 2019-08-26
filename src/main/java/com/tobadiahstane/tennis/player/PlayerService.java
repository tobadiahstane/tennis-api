package com.tobadiahstane.tennis.player;

public class PlayerService implements Players {

	private final IPersistPlayers playerLog;
	private final IQueryPlayers playerQuery;
	PlayerService(IPersistPlayers playerLog, IQueryPlayers playerQuery){
		this.playerLog = playerLog;
		this.playerQuery = playerQuery;
	}
	@Override
	public int addPlayer(String playerName) {
		PlayerAdded player = new PlayerAdded(playerQuery.lookupNextPlayerId(), playerName);
		this.playerLog.storePlayerAdded(player);
		return player.playerId;
	}

}
