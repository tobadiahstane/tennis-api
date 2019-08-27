package com.tobadiahstane.tennis.api;

import com.tobadiahstane.tennis.game.Games;
import com.tobadiahstane.tennis.game.Score;

public class GameController {

	private final Games games;

	GameController(Games games) {
		this.games = games;
	}

	public Score game(int player1, int player2) {
		int gameId = this.games.startGame(player1, player2);
		return games.callScore(gameId);
	
	}

	public Score score(int gameId, int playerId) {
		games.nextScore(gameId, playerId);
		return games.callScore(gameId);
		
	}
	
}
