package com.tobadiahstane.tennis.game;

import com.tobadiahstane.tennis.game.Games.IHandleGames;

public class GameEngine implements IHandleGames {

	@Override
	public GameUpdated createGame(int gameId, int playerOneId, int playerTwoId) {

		return new GameUpdated(gameId, playerOneId, playerTwoId, 0, 0);
	}

	@Override
	public GameUpdated awardPoint(GameUpdated game, int playerId) {
		GameUpdated nextUpdate; 
		int newPlayerPoints;
		if (playerId == 1) {
			newPlayerPoints =  game.playerOnePoints+1;
			nextUpdate = updatedGame (game, newPlayerPoints, game.playerTwoPoints, didPlayerPointsWin(newPlayerPoints, game.playerTwoPoints), somePlayerAdvantaged(newPlayerPoints, game.playerTwoPoints));	
		}else {
			newPlayerPoints =  game.playerTwoPoints+1;
			nextUpdate = updatedGame (game, game.playerOnePoints, newPlayerPoints, didPlayerPointsWin(newPlayerPoints, game.playerOnePoints), somePlayerAdvantaged(newPlayerPoints, game.playerOnePoints));
			
		}
		
		return nextUpdate;
	}
	
	private GameUpdated updatedGame (GameUpdated game, int nextPlayerOneScore, int nextPlayerTwoScore,boolean gameWon, boolean advantageGained) {
		
		if (nextPlayerOneScore > nextPlayerTwoScore) {
			return new GameUpdated (game.gameId, game.playerOneId, game.playerTwoId, nextPlayerOneScore, nextPlayerTwoScore, gameWon, advantageGained,  game.playerOneId);
		} else if (nextPlayerTwoScore > nextPlayerOneScore) {
			return new GameUpdated (game.gameId, game.playerOneId, game.playerTwoId, nextPlayerOneScore, nextPlayerTwoScore, gameWon, advantageGained,  game.playerTwoId);
		}else {
			return new GameUpdated (game.gameId, game.playerOneId, game.playerTwoId, nextPlayerOneScore, nextPlayerTwoScore, gameWon, advantageGained,  0);
		}
	}
	
	
	private boolean didPlayerPointsWin (int newPlayerPoints,int opponentPoints) {
		return newPlayerPoints >= 4 && 2 <= newPlayerPoints - opponentPoints;
				
	}
	
	private boolean somePlayerAdvantaged(int newPlayerPoints, int opponentPoints) {
		
		return newPlayerPoints >= 4 && 1 == Math.abs(newPlayerPoints - opponentPoints)
				|| 
				opponentPoints >= 4 && 1 == Math.abs(newPlayerPoints - opponentPoints);
		
	}

}
