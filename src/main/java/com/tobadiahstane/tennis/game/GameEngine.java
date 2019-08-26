package com.tobadiahstane.tennis.game;

import com.tobadiahstane.tennis.game.Games.IHandleGames;

public class GameEngine implements IHandleGames {

	@Override
	public GameUpdated createGame(int gameId, int playerOneId, int playerTwoId) {

		return new GameUpdated(gameId, playerOneId, playerTwoId, 0, 0);
	}

	private GameUpdated updatedGame (GameUpdated game, int nextPlayerOneScore, int nextPlayerTwoScore, int winnerId) {
		
		return  winnerId == 0?
				new GameUpdated (game.gameId, game.playerOneId, game.playerTwoId, nextPlayerOneScore, nextPlayerTwoScore, false, winnerId):
				new GameUpdated (game.gameId, game.playerOneId, game.playerTwoId, nextPlayerOneScore, nextPlayerTwoScore, true, winnerId);
	}
	
	
	private int didPlayerPointsWin (int playerId, int newPlayerPoints,int opponentPoints) {
		return newPlayerPoints >= 4 && 2 <= newPlayerPoints - opponentPoints? playerId : 0;
				
	}
	@Override
	public GameUpdated awardPoint(GameUpdated game, int playerId) {
		GameUpdated nextUpdate; 
		int newPlayerPoints;
		if (playerId == 1) {
			newPlayerPoints =  game.playerOnePoints+1;
			nextUpdate = updatedGame (game, newPlayerPoints, game.playerTwoPoints, didPlayerPointsWin(game.playerOneId, newPlayerPoints, game.playerTwoPoints));	
		}else {
			newPlayerPoints =  game.playerTwoPoints+1;
			nextUpdate = updatedGame (game, game.playerOnePoints, newPlayerPoints, didPlayerPointsWin(game.playerTwoId, newPlayerPoints, game.playerOnePoints));
			
		}
		
		return nextUpdate;
	}

}
