package com.tobadiahstane.tennis.api;


import org.junit.Test;

import com.tobadiahstane.tennis.player.Player;
import com.tobadiahstane.tennis.player.PlayerAdded;
import com.tobadiahstane.tennis.player.Players;

import org.junit.Assert;


public class PlayerControllerTest {

	
	@Test
	public void PlayerControllerCanReturnsPlayerTest() {
		TestDoublePlayers testPlayers = new TestDoublePlayers();
		PlayerController controller = new PlayerController(testPlayers);
		Player player = controller.player("Venus Williams");
		Assert.assertEquals("Venus Williams", player.getName());
		
	}
	
	@Test
	public void PlayerControllerAddsPlayerTest() {
		TestDoublePlayers testPlayers = new TestDoublePlayers();
		PlayerController controller = new PlayerController(testPlayers);
		Player player = controller.player("Venus Williams");
		Assert.assertEquals(testPlayers.player.playerName, player.getName());	
		Assert.assertEquals(testPlayers.player.playerId, player.getId());	
	}	
	
	private class TestDoublePlayers implements Players {

		public PlayerAdded player;
		@Override
		public int addPlayer(String playerName) {
			player = new PlayerAdded(1, playerName);
			return 1;
		}
		
	}

}
