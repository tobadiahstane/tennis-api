package com.tobadiahstane.tennis.player;

public class PlayerAdded {

	public final String playerName;
	public final int playerId;

	public PlayerAdded(int playerId, String playerName) {
		this.playerId = playerId;
		this.playerName = playerName;
	}

}
