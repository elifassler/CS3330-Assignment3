package com.example.haunted.engine;

import com.example.haunted.events.*;
import com.example.haunted.model.*;
import com.example.haunted.rules.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InteractionEngineTest {
	// Instance vars
	
	private InteractionEngine interactionEngine;
	private Player player;
	private Inventory inventory;
	private Room room;
	private Quest quest;
	
	// Create the interaction
	@BeforeEach
	void makeConstructor() {
		interactionEngine= new InteractionEngine(new QuestTracker());
		
		inventory = new Inventory(50);
		player = new Player("Eli", 100, 10, 5, inventory);
		
		room = new Room("start", "Lobby", "The lobby");
		player.setCurrentRoom(room);
		
		quest = new Quest("Find the teacher", "Destroy him");
	}
	
	@Test
	void pickUpItemShouldFailWhenItemIsNotInThere() {
		InteractionResult result = interactionEngine.pickUpItem(player, quest, "Sword");
		
		assertFalse(result.isSuccess());
		assertEquals("Item not found", result.getMessage());
	}

	
}
