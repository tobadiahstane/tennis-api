package com.tobadiahstane.tennis.api;


import org.junit.Test;
import org.junit.Assert;

import com.tobadiahstane.tennis.game.GameUpdated;
import com.tobadiahstane.tennis.game.Games;
import com.tobadiahstane.tennis.game.Score;
public class GameControllerTest {

	
	@Test
	public void GameControllerReturnsScoreForNewGame() {
		TestDoubleGames testGames = new TestDoubleGames();
		GameController testController = new GameController(testGames);
		Score score = testController.game(1,2);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("Love-Love", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
		
	}
	
	@Test
	public void GameControllerReturnsScoreForNewScore() {
		TestDoubleGames testGames = new TestDoubleGames();
		GameController testController = new GameController(testGames);
		testController.game(1,2);
		Score score = testController.score(1,1);
		Assert.assertEquals(1,score.getId());
		Assert.assertEquals("15-Love", score.getScore());
		Assert.assertEquals(1, score.getPlayer1());
		Assert.assertEquals(2, score.getPlayer2());
	}
	
	private class TestDoubleGames implements Games {
		
		private GameUpdated game;
		
		@Override
		public void nextScore(int gameId, int player) {
			this.game = player == 1?
					new GameUpdated (game.gameId, game.playerOneId, game.playerTwoId, game.playerOnePoints+1, game.playerTwoPoints):
	                new GameUpdated (game.gameId, game.playerOneId, game.playerTwoId, game.playerOnePoints, game.playerTwoPoints+1);
			
		}

		@Override
		public int startGame(int p1, int p2) {
			// TODO Auto-generated method stub
			this.game = new GameUpdated(1, p1, p2, 0, 0);
			return game.gameId;
			
		}

		@Override
		public GameUpdated callScore(int gameId) {
			// TODO Auto-generated method stub
			return this.game;
		}

		
	}
}
