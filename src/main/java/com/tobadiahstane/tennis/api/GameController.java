package com.tobadiahstane.tennis.api;

import com.tobadiahstane.tennis.game.GameUpdated;
import com.tobadiahstane.tennis.game.Games;
import com.tobadiahstane.tennis.game.Score;

public class GameController {

	private final Games games;

	GameController(Games games) {
		this.games = games;
	}

	public Score game(int player1, int player2) {
		int gameId = this.games.startGame(player1, player2);
		GameUpdated game = games.callScore(gameId);
		return new Score(gameId, "Love-Love", game.playerOneId, game.playerTwoId);
	}

	public Score score(int gameId, int playerId) {
		games.nextScore(gameId, playerId);
		GameUpdated game = games.callScore(gameId);
		return new Score(gameId, "15-Love", game.playerOneId, game.playerTwoId);
	}
	
}
