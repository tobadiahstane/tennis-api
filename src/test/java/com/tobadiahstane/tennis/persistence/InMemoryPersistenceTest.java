package com.tobadiahstane.tennis.persistence;


import org.junit.Assert;
import org.junit.Test;

import com.tobadiahstane.tennis.game.GameUpdated;
import com.tobadiahstane.tennis.game.Score;
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
	public void CallScoreLoveLoveTest(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,0,0);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("Love-Love", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	@Test
	public void CallScore15LoveTest(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,1,0);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("15-Love", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	@Test
	public void CallScoreLove15Test(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,0,1);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("Love-15", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	@Test
	public void CallScore30LoveTest(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,2,0);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("30-Love", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	@Test
	public void CallScore40LoveTest(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,3,0);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("40-Love", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	@Test
	public void CallScore1515Test(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,1,1);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("15-15", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	@Test
	public void CallScore3015Test(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,2,1);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("30-15", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	@Test
	public void CallScore3030Test(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,2,2);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("30-30", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	@Test
	public void CallScore4030Test(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,3,2);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("40-30", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	@Test
	public void CallScore4040AsDEUCETest(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,3,3);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("DEUCE!", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}

	@Test
	public void CallScoreForGameWonTest(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		PlayerAdded player1 = new PlayerAdded(1,"Serena Williams");
		testLog.storePlayerAdded(player1);
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,4,2,true,false,1);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("Winner, Serena Williams", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	@Test
	public void CallScoreForGameAdvantageTest(){
		InMemoryPersistence testLog = new InMemoryPersistence();
		int firstId = testLog.lookupNextGameId();
		PlayerAdded player1 = new PlayerAdded(1,"Serena Williams");
		testLog.storePlayerAdded(player1);
		GameUpdated testUpdated = new GameUpdated (firstId,1,2,4,3,false,true,1);
		testLog.storeUpdate(testUpdated);
		Score score  = testLog.callScore(firstId);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("Advantage, Serena Williams", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	
		
}
