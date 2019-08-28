package com.tobadiahstane.tennis.game;

import org.junit.Test;
import org.junit.Assert;

import com.tobadiahstane.tennis.game.GameService;
import com.tobadiahstane.tennis.game.Games;
import com.tobadiahstane.tennis.util.TennisCommandRejected;


public class GameServiceTest {
	
	@Test (expected = TennisCommandRejected.class)
	public void gameServiceCatchesIPersistGamesExceptionsStartGameTest(){
		int p1 = 1, p2 = 2;
		ExceptionTestDouble testLog = new ExceptionTestDouble();
		GameEngine engine = new GameEngine();
		Games testGames = new GameService(engine, testLog, testLog);
		int gameId = testGames.startGame(p1,p2);
		
	}
	
	@Test
	public void startGameLooksUpNextGameIdFromPersistenceOneTest() throws Exception {
		int p1 = 1, p2 = 2;
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		int initNextId = testLog.lookupNextGameId();
		Games testGames = makeTestGameService(testLog);
		int gameId = testGames.startGame(p1,p2);
		var gameOneFirstUpdate = testLog.loadGame(gameId);
		Assert.assertEquals(gameOneFirstUpdate.gameId, initNextId);
		Assert.assertEquals(gameOneFirstUpdate.gameId, gameId);
		
	}

	@Test
	public void startGameLooksUpNextGameIdFromPersistenceTwoTest() throws Exception {
		int p1 = 1, p2 = 2, p3 = 3, p4 = 4;
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		int initNextId = testLog.lookupNextGameId();
		Games testGames = makeTestGameService(testLog);
		testGames.startGame(p1,p2);
		int gameId = testGames.startGame(p3,p4);
		var gameTwoFirstUpdate = testLog.loadGame(gameId);
		Assert.assertEquals(gameTwoFirstUpdate.gameId, initNextId+1);
		
	}
	
	@Test
	public void startGameReturnsGameIdTest() throws Exception {
	int p1 = 1, p2 = 2;
	PersistenceTestDouble testLog = new PersistenceTestDouble();
	Games testGames = makeTestGameService(testLog);
	int gameId = testGames.startGame(p1,p2);
	var gameOneFirstUpdate = testLog.loadGame(gameId);
	Assert.assertEquals(gameOneFirstUpdate.gameId, gameId);
	}
	
	@Test
	public void startGameReturnsGameUpdateWithPlayerIdsOneAndTwoTest() throws Exception {
		int p1 = 1, p2 = 2;
		var gameOneFirstUpdate = returnStartGameUpdate(p1,p2);
		Assert.assertEquals(p1, gameOneFirstUpdate.playerOneId);
		Assert.assertEquals(p2, gameOneFirstUpdate.playerTwoId);	
	}
	
	@Test
	public void startGameReturnsGameUpdateWithPlayerIdsThreeAndFourTest() throws Exception {
		int p1 = 3, p2 = 4;
		var gameOneFirstUpdate = returnStartGameUpdate(p1,p2);
		Assert.assertEquals(p1, gameOneFirstUpdate.playerOneId);
		Assert.assertEquals(p2, gameOneFirstUpdate.playerTwoId);	
	}
	
	@Test
	public void startGameReturnsGameUpdateWithPlayerScorestheSameTest() throws Exception {
		int p1 = 1, p2 = 2;
		var gameOneFirstUpdate = returnStartGameUpdate(p1,p2);
		Assert.assertEquals(gameOneFirstUpdate.playerOnePoints, gameOneFirstUpdate.playerTwoPoints);
	}

	@Test (expected = TennisCommandRejected.class)
	public void startGameRejectsGameIfFirstPlayerPlayer1AlreadyPlaying() throws Exception{
		int p1 = 1, p2 = 2, p3 = 3;
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Games testGames = makeTestGameService(testLog);
		testGames.startGame(p1,p2);
		testGames.startGame(p1,p3);
		
	}
	@Test (expected = TennisCommandRejected.class)
	public void startGameRejectsGameIfFirstPlayerPlayer2AlreadyPlaying() throws Exception{
		int p1 = 1, p2 = 2, p3 = 3;
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Games testGames = makeTestGameService(testLog);
		testGames.startGame(p1,p2);
		testGames.startGame(p2,p3);
		
	}
	@Test (expected = TennisCommandRejected.class)
	public void startGameRejectsGameIfSecondPlayerPlayer1AlreadyPlaying() throws Exception{
		int p1 = 1, p2 = 2, p3 = 3;
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Games testGames = makeTestGameService(testLog);
		testGames.startGame(p1,p2);
		testGames.startGame(p3,p1);	
	}	
	
	@Test (expected = TennisCommandRejected.class)
	public void startGameRejectsGameIfSecondPlayerPlayer2AlreadyPlaying() throws Exception{
		int p1 = 1, p2 = 2, p3 = 3;
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Games testGames = makeTestGameService(testLog);
		testGames.startGame(p1,p2);
		testGames.startGame(p3,p2);	
	}	
	
	@Test (expected = TennisCommandRejected.class)
	public void nextScoreCatchesIPersistGamesExceptionsTest() {
		int p1 = 1;
		ExceptionTestDouble testLog = new ExceptionTestDouble();
		GameEngine engine = new GameEngine();
		Games testGames = new GameService(engine, testLog, testLog);
		testGames.nextScore(1,p1);
		
	}
	
	@Test (expected = TennisCommandRejected.class)
	public void callScoreCatchesIPersistGamesExceptionsTest() {
		ExceptionTestDouble testLog = new ExceptionTestDouble();
		GameEngine engine = new GameEngine();
		Games testGames = new GameService(engine, testLog, testLog);
		testGames.callScore(1);
		
	}
	
	
	@Test
	public void nextScoreAwardsPointsUsingTestGameEngineTest() throws Exception {
		int p1 = 1, p2 = 2;
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Games gameService = makeTestGameService(testLog);
		int gameId = gameService.startGame(p1, p2);
		gameService.nextScore(gameId, p1);
		GameUpdated secondUpdate = testLog.loadGame(gameId);
		Assert.assertEquals(gameId, secondUpdate.gameId);
		Assert.assertNotEquals(secondUpdate.playerTwoPoints, secondUpdate.playerOnePoints);
	
	}

	@Test
	public void nextScoreStoresGameUpdated() throws Exception {
		int p1 = 1, p2 = 2;
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Games gameService = makeTestGameService(testLog);
		int gameId =gameService.startGame(p1, p2);
		gameService.nextScore(gameId, p1);
		GameUpdated nextUpdate = testLog.loadGame(gameId);
		Assert.assertEquals(nextUpdate.getClass(), GameUpdated.class);
		Assert.assertNotEquals(nextUpdate.playerTwoPoints, nextUpdate.playerOnePoints);
	}
	
	@Test (expected = TennisCommandRejected.class)
	public void nextScoreThrowsExceptionIfGameOverTest(){
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Games gameService = makeTestGameService(testLog);
		int p1 = 1, p2 = 2, gameId = 1;
		testLog.lastStored = new GameUpdated(gameId,p1,p2,4,0,true,false,1);
		gameService.nextScore(gameId, p1);
	}
	
	@Test (expected = TennisCommandRejected.class)
	public void nextScoreThrowsExceptionIfGameNotExistTest(){
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Games gameService = makeTestGameService(testLog);
		int p1 = 1, gameId = 1;
		gameService.nextScore(gameId, p1);
	}
	
	
	private GameUpdated returnStartGameUpdate(int p1, int p2) throws Exception {
		PersistenceTestDouble testLog = new PersistenceTestDouble();
		Games gameService = makeTestGameService(testLog);
		int gameId= gameService.startGame(p1,p2);
		return testLog.loadGame(gameId);
		
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
			lastStored = new GameUpdated(0,0,0,0,0);

		}
		
		@Override
		public int lookupNextGameId() {
			return nextGameId;
		}
		
		@Override
		public Score callScore(int gameId) {
			return null;
		}
		
		@Override
		public boolean alreadyPlaying (int playerId){
			return playerId == lastStored.playerOneId || playerId == lastStored.playerTwoId;
		}
		
		@Override
		public boolean gameOver(int gameId) {
			return false == lastStored.playerWon;
		}
		
		@Override
		public boolean isGame(int gameId) {
			return gameId == lastStored.gameId;
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
		
   }
	
	private class ExceptionTestDouble  implements Games.IQueryGames, Games.IPersistGames{

		@Override
		public void storeUpdate(GameUpdated update) {
			throw new IllegalArgumentException();
			
		}

		@Override
		public GameUpdated loadGame(int gameId) {
			throw new IllegalArgumentException();
		}

		@Override
		public Score callScore(int gameId) {
			throw new IllegalArgumentException();
		}

		@Override
		public int lookupNextGameId() {
			throw new IllegalArgumentException();
		}

		@Override
		public boolean alreadyPlaying(int playerId) {
			throw new IllegalArgumentException();
		}

		@Override
		public boolean gameOver(int gameId) {
			throw new IllegalArgumentException();
		}

		@Override
		public boolean isGame(int gameId) {
			throw new IllegalArgumentException();
		}
		
	}
}
	

