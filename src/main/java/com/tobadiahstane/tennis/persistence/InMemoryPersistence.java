package com.tobadiahstane.tennis.persistence;

import java.util.ArrayList;
import java.util.List;

import com.tobadiahstane.tennis.game.GameUpdated;
import com.tobadiahstane.tennis.game.Games.IPersistGames;
import com.tobadiahstane.tennis.game.Games.IQueryGames;
import com.tobadiahstane.tennis.player.PlayerAdded;
import com.tobadiahstane.tennis.player.Players.IPersistPlayers;
import com.tobadiahstane.tennis.player.Players.IQueryPlayers;

public class InMemoryPersistence implements IPersistGames, IQueryGames, IPersistPlayers, IQueryPlayers {

	private List<GameUpdated> games;
	private List<PlayerAdded> players;
	
	public InMemoryPersistence() {
		this.games = new ArrayList<GameUpdated>();
		this.players = new ArrayList<PlayerAdded>();
	}
	
	@Override
	public void storeUpdate(GameUpdated update) {
		this.games.add((update.gameId - 1), update );
	}

	@Override
	public GameUpdated loadGame(int gameId) {
		return this.games.get(gameId- 1);
	}

	@Override
	public GameUpdated callScore(int gameId) {
		return null;
	}

	@Override
	public int lookupNextGameId() {
		return this.games.size() + 1;
	}

	@Override
	public int lookupNextPlayerId() {
		
		return this.players.size() + 1;
	}

	@Override
	public void storePlayerAdded(PlayerAdded player) {
	   this.players.add((player.playerId -1), player);
		
	}

	public String lookupPlayerName(int firstPlayerId) {
		
		return this.players.get(firstPlayerId - 1).playerName;
	}

}