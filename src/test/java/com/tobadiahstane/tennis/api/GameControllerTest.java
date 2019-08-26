package com.tobadiahstane.tennis.api;


import org.junit.Test;
import org.junit.Assert;

import com.tobadiahstane.tennis.game.GameUpdated;
import com.tobadiahstane.tennis.game.Games;
public class GameControllerTest {

	
	@Test
	public void GameControllerExists() {
		TestDoubleGames testGames = new TestDoubleGames();
		GameController testController = new GameController(testGames);
	
	}
	
	private class TestDoubleGames implements Games {


		@Override
		public void nextScore(int gameId, int p1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int startGame(int p1, int p2) {
			// TODO Auto-generated method stub
			return 0;
			
		}

		@Override
		public GameUpdated callScore(int gameId) {
			// TODO Auto-generated method stub
			return null;
		}

		
	}
}
