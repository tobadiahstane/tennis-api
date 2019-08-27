package com.tobadiahstane.tennis.persistence;


import org.junit.Assert;
import org.junit.Test;

import com.tobadiahstane.tennis.game.GameUpdated;
import com.tobadiahstane.tennis.game.Games.IQueryGames;
import com.tobadiahstane.tennis.player.PlayerAdded;

import com.tobadiahstane.tennis.player.Players.IQueryPlayers;

public class InMemoryPersistenceTest {

	
	@Test
	public void givenNoGameUpdatedStoredNextIdReturnsOneTest(){
		IQueryGames testLog = new InMemoryPersistence();
		Assert.assertEquals(1,testLog.lookupNextGameId());
		
	}
	
	@Test
	public void givenOneGameUpdatedStoredNextIdReturnsTwoTest(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		GameUpdated testUpdated = new GameUpdated (1,1,2,0,0);
		testLog.storeUpdate(testUpdated);
		Assert.assertEquals(2,testLog.lookupNextGameId());
		
	}
	
	@Test
	public void givenOneGameUpdatedCanStoreTest() {
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,0,0);
		testLog.storeUpdate(testUpdated);
		GameUpdated testLoaded = testLog.loadGame(firstId);
		Assert.assertEquals(testUpdated,testLoaded);
	}
	
	@Test
	public void givenTwoGamesUpdatedCanStoreBothTest() {
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstGameId = testLog.lookupNextGameId();
		GameUpdated firstGame= new GameUpdated (firstGameId, 1,2,0,0);
		testLog.storeUpdate(firstGame);	
		int secondGameId = testLog.lookupNextGameId();
		GameUpdated secondGame= new GameUpdated (secondGameId, 3,4,0,0);
		testLog.storeUpdate(secondGame);
		GameUpdated firstLoaded = testLog.loadGame(firstGameId);
		GameUpdated secondLoaded = testLog.loadGame(secondGameId);
		Assert.assertEquals(firstGame, firstLoaded);
		Assert.assertEquals(secondGame, secondLoaded);
		
	}
	
	@Test
	public void canReturnNextPlayerIdTest() {
		IQueryPlayers testLog = new InMemoryPersistence();
		Assert.assertEquals(1, testLog.lookupNextPlayerId());
	}
	
	@Test
	public void givenPlayerAddedCanStorePlayerTest() {
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstPlayerId = testLog.lookupNextPlayerId();
		PlayerAdded playerOne = new PlayerAdded(firstPlayerId, "Venus Williams");
		testLog.storePlayerAdded(playerOne);
		String testPlayerName =  testLog.lookupPlayerName(firstPlayerId);
		Assert.assertEquals("Venus Williams",testPlayerName);
	}
	
	@Test
	public void afterStoringPlayerNextPlayerIdReturnsNextPlayerIdTest() {
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstPlayerId = testLog.lookupNextPlayerId();
		PlayerAdded playerOne = new PlayerAdded(firstPlayerId, "Venus Williams");
		testLog.storePlayerAdded(playerOne);
		int secondPlayerId = testLog.lookupNextPlayerId();
		Assert.assertEquals(2 ,secondPlayerId);
	}
	
	@Test
	public void StoringTwoPlayersTest() {
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstPlayerId = testLog.lookupNextPlayerId();
		PlayerAdded playerOne = new PlayerAdded(firstPlayerId, "Venus Williams");
		testLog.storePlayerAdded(playerOne);
		int secondPlayerId = testLog.lookupNextPlayerId();
		PlayerAdded playerTwo = new PlayerAdded(secondPlayerId, "Serena Williams");
		testLog.storePlayerAdded(playerTwo);
		String testPlayerName = testLog.lookupPlayerName(secondPlayerId);
		Assert.assertEquals("Serena Williams", testPlayerName);
	}
	
	@Test
	public void callScoreReturnsLastGameUpdatedTest(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,0,0);
		testLog.storeUpdate(testUpdated);
		GameUpdated testLoaded = testLog.callScore(firstId);
		Assert.assertEquals(testUpdated,testLoaded);
	}
	
}
