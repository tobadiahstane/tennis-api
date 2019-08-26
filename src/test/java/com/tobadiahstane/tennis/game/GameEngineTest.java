package com.tobadiahstane.tennis.game;

import org.junit.Test;

import com.tobadiahstane.tennis.game.GameEngine;

import org.junit.Assert;

public class GameEngineTest {

	@Test
	public void givenGameIDAndPlayerOneIdAndPlayerTwoIdReturnsGameOneTest() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	   
	    isNewGameWithGameAndPlayerIds(newGame, gameId, playerOneId, playerTwoId);
	    
	}
	


	@Test
	public void givenGameIDAndPlayerOneIdAndPlayerTwoIdReturnsGameTwoTest() {
		int gameId = 2, playerOneId = 3, playerTwoId = 4;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    
	    isNewGameWithGameAndPlayerIds(newGame, gameId, playerOneId, playerTwoId);
	    
	    
	}
	@Test
	public void givenGameAndPlayerIdForGameReturnGameUpdatedWithSameGameAndPlayerIdsOneAndTwoTest() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated updatedGame = engine.awardPoint(newGame, playerOneId);
	    isGameUpdated(updatedGame);
	    hasCorrectGameAndPlayerIds(updatedGame, gameId, playerOneId, playerTwoId);
	}

	@Test
	public void givenGameAndPlayerIdForGameReturnGameUpdatedWithSameGameAndPlayerIdsThreeAndFourTest() {
		int gameId = 1, playerOneId = 3, playerTwoId = 4;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated updatedGame = engine.awardPoint(newGame, playerOneId);
	    isGameUpdated(updatedGame);
	    hasCorrectGameAndPlayerIds(updatedGame, gameId, playerOneId, playerTwoId);
	}
	
	@Test
	public void givenGameAndPlayerOneIdAwardPointToPlayerOneTest() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated updatedGame = engine.awardPoint(newGame, playerOneId);
	    Assert.assertEquals(1, updatedGame.playerOnePoints);
	    Assert.assertEquals(0, updatedGame.playerTwoPoints);
	    Assert.assertEquals(0, updatedGame.winnerId);
	    Assert.assertFalse(updatedGame.didPlayerWin);
	}
	
	@Test
	public void givenGameAndPlayerOneIdAwardPointToPlayerTwoTest() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated updatedGame = engine.awardPoint(newGame, playerTwoId);
	    Assert.assertEquals(0, updatedGame.playerOnePoints);
	    Assert.assertEquals(1, updatedGame.playerTwoPoints);
	    Assert.assertEquals(0, updatedGame.winnerId);
	    Assert.assertFalse(updatedGame.didPlayerWin);
	}
	
	@Test
	public void givenGameAndPlayerOneIdAwardTwoPointToPlayerOneTest() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated  updatedGame = (awardNumberOfPoints(engine, newGame, playerOneId, 2));
	    Assert.assertEquals(2, updatedGame.playerOnePoints);
	    Assert.assertEquals(0, updatedGame.playerTwoPoints);
	    Assert.assertEquals(0, updatedGame.winnerId);
	    Assert.assertFalse(updatedGame.didPlayerWin);
	}
	
	@Test
	public void givenGameAndPlayerTwoIdAwardTwoPointToPlayerTwoTest() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated  updatedGame = (awardNumberOfPoints(engine, newGame, playerTwoId, 2));
	    Assert.assertEquals(0, updatedGame.playerOnePoints);
	    Assert.assertEquals(2, updatedGame.playerTwoPoints);
	    Assert.assertEquals(0, updatedGame.winnerId);
	    Assert.assertFalse(updatedGame.didPlayerWin);
	}
	
	@Test
	public void givenAwardFourPointsToPlayerOneAndPlayerTwoZeroPointsPlayerOneWinsTest() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated  updatedGame = (awardNumberOfPoints(engine, newGame, playerOneId, 4));
	    Assert.assertEquals(4, updatedGame.playerOnePoints);
	    Assert.assertEquals(0, updatedGame.playerTwoPoints);
	    Assert.assertEquals(playerOneId, updatedGame.winnerId);
	    Assert.assertTrue(updatedGame.didPlayerWin);
	}
	
	@Test
	public void givenAwardFourPointsToPlayerTwoAndPlayerOneZeroPointsPlayerTwoWinsTest() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated  updatedGame = (awardNumberOfPoints(engine, newGame, playerTwoId, 4));
	    Assert.assertEquals(0, updatedGame.playerOnePoints);
	    Assert.assertEquals(4, updatedGame.playerTwoPoints);
	    Assert.assertEquals(playerTwoId, updatedGame.winnerId);
	    Assert.assertTrue(updatedGame.didPlayerWin);
	}
	
	@Test
	public void playerOneWithFourPointsNotAheadByTwoNotWins() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated  playerTwoAhead = awardNumberOfPoints(engine, newGame, playerTwoId, 3);
	    GameUpdated updatedGame = awardNumberOfPoints(engine, playerTwoAhead, playerOneId, 4);
	    Assert.assertEquals(4, updatedGame.playerOnePoints);
	    Assert.assertEquals(3, updatedGame.playerTwoPoints);
	    Assert.assertEquals(0, updatedGame.winnerId);
	    Assert.assertFalse(updatedGame.didPlayerWin);
	}
	@Test
	public void playerTwoWithFourPointsNotAheadByTwoNotWins() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated  playerOneAhead = awardNumberOfPoints(engine, newGame, playerOneId, 3);
	    GameUpdated updatedGame = awardNumberOfPoints(engine, playerOneAhead, playerTwoId, 4);
	    Assert.assertEquals(3, updatedGame.playerOnePoints);
	    Assert.assertEquals(4, updatedGame.playerTwoPoints);
	    Assert.assertEquals(0, updatedGame.winnerId);
	    Assert.assertFalse(updatedGame.didPlayerWin);
	}
	
	@Test
	public void playerOneWithFivePointsAheadByTwoWins() {
		int gameId = 1, playerOneId = 1, playerTwoId = 2;
	    Games.IHandleGames engine = new GameEngine();
	    GameUpdated newGame = engine.createGame(gameId, playerOneId, playerTwoId);
	    GameUpdated  playerOneAhead = awardNumberOfPoints(engine, newGame, playerOneId, 3);
	    GameUpdated equalized = awardNumberOfPoints(engine, playerOneAhead, playerTwoId, 3);
	    GameUpdated updatedGame = awardNumberOfPoints(engine, equalized, playerOneId, 2);
	    Assert.assertEquals(5, updatedGame.playerOnePoints);
	    Assert.assertEquals(3, updatedGame.playerTwoPoints);
	    Assert.assertEquals(1, updatedGame.winnerId);
	    Assert.assertTrue(updatedGame.didPlayerWin);
	}
	
	private GameUpdated awardNumberOfPoints(Games.IHandleGames gameEngine ,GameUpdated game, int playerId, int pointsToAdd) {
		if(pointsToAdd > 0 ) {
			return awardNumberOfPoints(gameEngine, gameEngine.awardPoint(game, playerId), playerId, pointsToAdd-1);
		}else{
			return game;
		}
	}
	
	private void isNewGameWithGameAndPlayerIds(GameUpdated newGame, int gameId, int playerOneId, int playerTwoId) {
		isGameUpdated(newGame);
		hasCorrectGameAndPlayerIds(newGame, gameId, playerOneId, playerTwoId);
	    isNewGame(newGame);
		
	}
	
	private void hasCorrectGameAndPlayerIds(GameUpdated newGame, int gameId, int playerOneId, int playerTwoId) {
		 hasCorrectGameId(newGame, gameId);
		 hasCorrectPlayerIds(newGame, playerOneId, playerTwoId);
	}
	private void isNewGame(GameUpdated newGame) {
		Assert.assertEquals(0,newGame.playerOnePoints);
	    Assert.assertEquals(0,newGame.playerTwoPoints);
	    Assert.assertEquals(0,newGame.winnerId);
	    Assert.assertFalse(newGame.didPlayerWin);
		
	}
	
	private void hasCorrectPlayerIds (GameUpdated game, int p1, int p2) {
		Assert.assertEquals(p1, game.playerOneId);
	    Assert.assertEquals(p2, game.playerTwoId);
	}
	
	private void hasCorrectGameId(GameUpdated newGame, int gameId) {
		 Assert.assertEquals(gameId, newGame.gameId);
		
	}
	private void isGameUpdated (GameUpdated game) {
		Assert.assertEquals(GameUpdated.class, game.getClass());
	}
	
	
	
}
