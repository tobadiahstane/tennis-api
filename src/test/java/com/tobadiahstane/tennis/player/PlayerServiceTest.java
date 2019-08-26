package com.tobadiahstane.tennis.player;

import org.junit.Test;
import org.junit.Assert;


public class PlayerServiceTest {

	@Test
	public void addingPlayerReturnsPlayerIdFirstPlayerTest() {
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Players playerService = newTestPlayers(testLog);
		int newPlayerId = playerService.addPlayer("Venus Williams");
		Assert.assertEquals(1, newPlayerId);
	}
	
	@Test
	public void addingPlayerStoresPlayertoPersistenceFirstPlayerTest() {
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Players playerService = newTestPlayers(testLog);
		int newPlayerId = playerService.addPlayer("Venus Williams");
		Assert.assertEquals(newPlayerId, testLog.lastPlayerStored.playerId);
		Assert.assertEquals("Venus Williams", testLog.lastPlayerStored.playerName);
	}
	
	@Test
	public void addingPlayerReturnsPlayerIdSecondPlayerTest() {
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Players playerService = newTestPlayers(testLog);
		playerService.addPlayer("Serena Williams");
		int newPlayerId = playerService.addPlayer("Venus Williams");
		Assert.assertEquals(2, newPlayerId);
		
	}
	
	public void addingPlayerStoresPlayertoPersistenceSecondPlayerTest() {
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Players playerService = newTestPlayers(testLog);
		playerService.addPlayer("Serena Williams");
		int newPlayerId = playerService.addPlayer("Venus Williams");
		Assert.assertEquals(newPlayerId, testLog.lastPlayerStored.playerId);
		Assert.assertEquals("Venus Williams", testLog.lastPlayerStored.playerName);
	}
	
	private Players newTestPlayers(PersistenceTestDouble testLog) {
		return new PlayerService(testLog, testLog);
	}
	
	private class PersistenceTestDouble implements Players.IPersistPlayers, Players.IQueryPlayers {

		private PlayerAdded lastPlayerStored;
		private int nextPlayerId;
		
		PersistenceTestDouble(){
			this.nextPlayerId = 1;
		}
		
		@Override
		public void storePlayerAdded(PlayerAdded added) {
		    lastPlayerStored = added;
		    nextPlayerId = added.playerId + 1;
			
		}
		
		@Override
		public int lookupNextPlayerId() {
			return nextPlayerId;
		}
	
		
	}
}
