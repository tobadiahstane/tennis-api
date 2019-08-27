package com.tobadiahstane.tennis.api;

import com.tobadiahstane.tennis.player.Player;
import com.tobadiahstane.tennis.player.Players;

public class PlayerController {
	
	private final Players players;

	public PlayerController (Players players) {
		this.players = players;
	}

	public Player player(String name) {
		int playerId = this.players.addPlayer(name);
		return new Player (playerId, name);
	}

}
