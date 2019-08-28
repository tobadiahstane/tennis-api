package com.tobadiahstane.tennis.api;


import org.junit.Test;
import org.junit.Assert;

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
		
		private Score score;
		
		@Override
		public void nextScore(int gameId, int player) {
			this.score = new Score (gameId, "15-Love", score.getPlayer1(), score.getPlayer2());
	                
			
		}

		@Override
		public int startGame(int p1, int p2) {
			this.score = new Score(1, "Love-Love", p1, p2);
			return score.getId();
			
		}

		@Override
		public Score callScore(int gameId) {
			return this.score;
		}

		
	}
}
