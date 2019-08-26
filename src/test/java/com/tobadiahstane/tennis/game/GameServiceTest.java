package com.tobadiahstane.tennis.game;

import org.junit.Test;
import org.junit.Assert;

import com.tobadiahstane.tennis.game.GameService;
import com.tobadiahstane.tennis.game.Games;


public class GameServiceTest {
	
	@Test
	public void startGameLooksUpNextGameIdFromPersistenceOneTest() {
		int p1 = 1, p2 = 2;
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		int initNextId = testLog.lookupNextGameId();
		Games testGames = makeTestGameService(testLog);
		int gameId = testGames.startGame(p1,p2);
		var gameOneFirstUpdate =testGames.callScore(gameId);
		Assert.assertEquals(gameOneFirstUpdate.gameId, initNextId);
		Assert.assertEquals(gameOneFirstUpdate.gameId, gameId);
		
	}

	@Test
	public void startGameLooksUpNextGameIdFromPersistenceTwoTest() {
		int p1 = 1, p2 = 2, p3 = 3, p4 = 4;
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		int initNextId = testLog.lookupNextGameId();
		Games testGames = makeTestGameService(testLog);
		testGames.startGame(p1,p2);
		int gameId = testGames.startGame(p3,p4);
		var gameTwoFirstUpdate = testGames.callScore(gameId);
		Assert.assertEquals(gameTwoFirstUpdate.gameId, initNextId+1);
		
	}
	
	@Test
	public void startGameReturnsGameIdTest() {
	int p1 = 1, p2 = 2;
	Games testGames = makeTestGameService();
	int gameId = testGames.startGame(p1,p2);
	var gameOneFirstUpdate = testGames.callScore(gameId);
	Assert.assertEquals(gameOneFirstUpdate.gameId, gameId);
	}
	
	@Test
	public void startGameReturnsGameUpdateWithPlayerIdsOneAndTwoTest() {
		int p1 = 1, p2 = 2;
		var gameOneFirstUpdate = returnStartGameUpdate(p1,p2);
		Assert.assertEquals(p1, gameOneFirstUpdate.playerOneId);
		Assert.assertEquals(p2, gameOneFirstUpdate.playerTwoId);	
	}
	
	@Test
	public void startGameReturnsGameUpdateWithPlayerIdsThreeAndFourTest() {
		int p1 = 3, p2 = 4;
		var gameOneFirstUpdate = returnStartGameUpdate(p1,p2);
		Assert.assertEquals(p1, gameOneFirstUpdate.playerOneId);
		Assert.assertEquals(p2, gameOneFirstUpdate.playerTwoId);	
	}
	
	@Test
	public void startGameReturnsGameUpdateWithPlayerScorestheSameTest() {
		int p1 = 1, p2 = 2;
		var gameOneFirstUpdate = returnStartGameUpdate(p1,p2);
		Assert.assertEquals(gameOneFirstUpdate.playerOnePoints, gameOneFirstUpdate.playerTwoPoints);
	}

	
	@Test
	public void nextScoreAwardsPointsUsingTestGameEngineTest() {
		int p1 = 1, p2 = 2;
		Games gameService = makeTestGameService();
		int gameId = gameService.startGame(p1, p2);
		gameService.nextScore(gameId, p1);
		GameUpdated secondUpdate = gameService.callScore(gameId);
		Assert.assertEquals(gameId, secondUpdate.gameId);
		Assert.assertNotEquals(secondUpdate.playerTwoPoints, secondUpdate.playerOnePoints);
	
	}

	@Test
	public void nextScoreStoresGameUpdated() {
		int p1 = 1, p2 = 2;
		var gameService = makeTestGameService();
		int gameId =gameService.startGame(p1, p2);
		gameService.nextScore(gameId, p1);
		GameUpdated nextUpdate = gameService.callScore(gameId);
		Assert.assertEquals(nextUpdate.getClass(), GameUpdated.class);
		Assert.assertNotEquals(nextUpdate.playerTwoPoints, nextUpdate.playerOnePoints);
	}
	
	private GameUpdated returnStartGameUpdate(int p1, int p2) {
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Games gameService = makeTestGameService(testLog);
		gameService.startGame(p1,p2);
		return testLog.lastStored();
		
	}

	private Games makeTestGameService() {
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		return makeTestGameService(testLog);
	}
	
	private Games makeTestGameService(PersistenceTestDouble testLog) {
		GameEngineTestDouble testEng = new GameEngineTestDouble();
		return makeTestGameService(testEng, testLog);
	}
	
	private Games makeTestGameService(GameEngineTestDouble testEng, PersistenceTestDouble testLog ) {
		return new GameService(testEng, testLog, testLog);
				
	}

	
	private class GameEngineTestDouble implements Games.IHandleGames{

		@Override
		public
		GameUpdated createGame(int gameId, int p1, int p2) {
			return new GameUpdated(gameId, p1, p2, 0, 0);
		};
		
		@Override
		public GameUpdated awardPoint(GameUpdated game, int player) {
			
			return player == 1?
					new GameUpdated (game.gameId, game.playerOneId, game.playerTwoId, game.playerOnePoints+1, game.playerTwoPoints):
	                new GameUpdated (game.gameId, game.playerOneId, game.playerTwoId, game.playerOnePoints, game.playerTwoPoints+1);
		}
		
		
	}
    	
	private class PersistenceTestDouble implements Games.IQueryGames, Games.IPersistGames {

		private int nextGameId;
		private GameUpdated lastStored;

		PersistenceTestDouble(){
			nextGameId = 1;

		}
		
		@Override
		public int lookupNextGameId() {
			return nextGameId;
		}
		
		@Override
		public GameUpdated callScore(int gameid) {
			return lastStored;
		}
		
		@Override
		public void storeUpdate(GameUpdated update) {
		this.lastStored = update;
		nextGameId = update.gameId + 1;
		
	    }
		
		@Override
		public GameUpdated loadGame(int gameId) {
			return lastStored;
		}
	
		public GameUpdated lastStored() {
			return lastStored;
		}
		
   }
	
}
	

