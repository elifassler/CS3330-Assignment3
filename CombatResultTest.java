package com.example.haunted.events;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.BeforeEach;
import com.example.haunted.model.Weapon;
import com.example.haunted.model.Item;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class CombatResultTest {
	
	private List<Item> items;
	private CombatResult combatResult;
	
	@BeforeEach
	void makeConstructor() {
		items = new ArrayList<>();
		items.add(new Weapon("Bow", "Higher Damage", 20));
		
		combatResult = new CombatResult(true, "Attack successful", 20, 3, true, items);
	}
	
	@Test
	void constructorShouldSetAllFieldsCorrectly() {
		assertTrue(combatResult.isSuccess());
		assertEquals("Attack successful", combatResult.getMessage());
		assertEquals(20, combatResult.getDamageToMonster());
		assertEquals(10, combatResult.getDamageToPlayer());
		assertTrue(combatResult.isMonsterDefeated());
	}
	
	// Items copy test
	@Test
	void changingListShouldNotChangeDroppedItems() {
		items.add(new Weapon("Sword", "Wood", 4));
		
		assertEquals(1, combatResult.getDroppedItems().size());
	}
	// Had to use AI to see how to check for unmodification and use assertThrows for it
	void getDroppedItemsShouldReturnImmutableList() {
		List<Item> droppedItems = combatResult.getDroppedItems();
		
		// AI here
		assertThrows(UnsupportedOperationException.class, () -> droppedItems.add(new Weapon("Sword", "Wood", 4)));
	}
}
