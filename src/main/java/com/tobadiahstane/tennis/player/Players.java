package com.tobadiahstane.tennis.player;

public interface Players {

	int addPlayer(String playerName);

	public interface IPersistPlayers {

		void storePlayerAdded(PlayerAdded player);

		
	}
	
	public interface IQueryPlayers {
		
		int lookupNextPlayerId();
	}
}
