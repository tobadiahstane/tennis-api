package com.tobadiahstane.tennis.persistence;

import java.util.ArrayList;
import java.util.List;

import com.tobadiahstane.tennis.game.GameUpdated;
import com.tobadiahstane.tennis.game.Games.IPersistGames;
import com.tobadiahstane.tennis.game.Games.IQueryGames;
import com.tobadiahstane.tennis.game.Score;
import com.tobadiahstane.tennis.player.PlayerAdded;
import com.tobadiahstane.tennis.player.Players.IPersistPlayers;
import com.tobadiahstane.tennis.player.Players.IQueryPlayers;

public class InMemoryPersistence implements IPersistGames, IQueryGames, IPersistPlayers, IQueryPlayers {

	private List<GameUpdated> games;
	private List<PlayerAdded> players;
	
	public InMemoryPersistence() {
		this.games = new ArrayList<GameUpdated>();
		this.players = new ArrayList<PlayerAdded>();
	}
	
	@Override
	public void storeUpdate(GameUpdated update) {
		this.games.add((update.gameId - 1), update );
	}

	//Load game and callScore seem like duplicated methods because of the simplicity of the in memory persistence. 
	//If all GameUpdated events for a given game were stored as (de)queue, loadGame should be responsible for loading all events in the queue
	
	@Override
	public GameUpdated loadGame(int gameId) {
		return this.games.get(gameId- 1);
	}

	//while callScore would continue to return the last gameUpdated.
	@Override
	public Score callScore(int gameId) {
		GameUpdated game = this.games.get(gameId- 1);
		return new Score (gameId, calculateScore(game) , game.playerOneId, game.playerTwoId);
	}
	
	private String calculateScore(GameUpdated game) {
		String score;
		int compareScores = Math.abs(game.playerOnePoints-game.playerTwoPoints);
		if (game.playerWon) {
			score = "Winner, " + this.lookupPlayerName(game.winningId);
		}else if(game.playerAdvantage) {
			score = "Advantage, " + this.lookupPlayerName(game.winningId);
			
		}else if(game.playerOnePoints >= 3 && compareScores == 0) {
			score = "DEUCE!";
		}else {
		    score = convertPlayerPointsToScore(game.playerOnePoints) + "-" + convertPlayerPointsToScore(game.playerTwoPoints);
		}
		return score;
	}
	
	private String convertPlayerPointsToScore(int playerScore) {
		
		switch(playerScore) {
		case 0:
			return "Love";
		case 1:
			return "15";
		case 2:
			return "30";
		default:
			return "40";
		}
	}


	@Override
	public int lookupNextGameId() {
		return this.games.size() + 1;
	}
	
	@Override
	public boolean alreadyPlaying(int playerId) {
		//TODO implement
		return false;
	}
	
	@Override
	public boolean gameOver(int gameId) {
		//TODO implement tests
		return false;
	}
	
	@Override
	public boolean isGame(int gameId) {
		//TODO implement tests
		return false;
	}

	@Override
	public int lookupNextPlayerId() {
		
		return this.players.size() + 1;
	}

	@Override
	public void storePlayerAdded(PlayerAdded player) {
	   this.players.add((player.playerId -1), player);
		
	}

	public String lookupPlayerName(int firstPlayerId) {
		
		return this.players.get(firstPlayerId - 1).playerName;
	}

}
