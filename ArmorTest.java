package com.example.haunted.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {
	
	@Test
	void constructorShouldSetAllFields() {
		Armor armor = new Armor("Iron Armor", "Medium", 5); // Object declaration
		
		assertEquals("Iron Armor", armor.getName()); // Check if the getName method works correctly
		assertEquals("Medium", armor.getDescription()); // Check if the getDescription method works correctly
		assertEquals(5, armor.getDefenseBonus()); // Check if the getDefenseBonus method works correctly
	}
	
	@Test
	void armorShouldImplementEquippable() {
		Armor armor = new Armor("Boots", "Featherfalling", 2); // Object declaration
		
		assertTrue(armor instanceof Equippable); // Check if armor actually implements Equippable
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 10, -1})
	void getDefenseBonusShouldReturnValue(int defense) {
		Armor armor = new Armor("Test", "Test", defense); // Test object for edge cases
		
		assertEquals(defense, armor.getDefenseBonus()); // Test getDefenseBonus on edge cases
	}
}
