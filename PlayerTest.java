package com.example.haunted.model;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

// I did this whole class, then I realized that I could've used @BeforeEach instead of initializing the objects in every method.

class PlayerTest {
	
	@Test
	void constructorShouldSetAllFields() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object declaration
		
		// Check if constructor set all fields using assertEquals
		assertEquals("Eli", player.getName());
		assertEquals(100, player.getMaxHealth());
		assertEquals(10, player.getBaseAttack());
		assertEquals(5, player.getBaseDefense());
		assertEquals(i, player.getInventory());
	}
	
	@Test
	void setCurrentRoomShouldSetRoom() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		Room testRoom = new Room("Room1", "Test", "Test Room"); // Test room
		player.setCurrentRoom(testRoom); // Assign room
		
		assertEquals(testRoom, player.getCurrentRoom()); // Check if assignment is valid
	}
	
	@Test
	void setCurrentRoomUsesRequireNonNull() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		assertThrows(NullPointerException.class, () -> player.setCurrentRoom(null)); // Had to AI how assertThrows works syntax-wise
	}
	
	// Damage Tests
	@Test
	void takeDamageShouldTakeDamage() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		player.takeDamage(10);
		
		assertEquals(90, player.getHealth());
	}
	
	@Test
	void healthShouldNotBeBelowZero() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		player.takeDamage(150);
		
		assertEquals(0, player.getHealth());
	}
	
	@Test
	void takeDamageWithNegativeNumberShouldNotDoAnything() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		player.takeDamage(-5);
		
		assertEquals(100, player.getHealth());
	}
	
	
	// Heal Test
	@Test
	void healShouldHeal() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		player.takeDamage(50);
		player.heal(10);
		
		assertEquals(60, player.getHealth());
	}
	
	@Test
	void healShouldNotGoPastMaxHealth() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		player.heal(50);
		
		assertEquals(100, player.getHealth());
	}
	
	@Test
	void healWithNegativeNumberShouldNotDoAnything() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		player.takeDamage(10);
		player.heal(-10);
		
		assertEquals(90, player.getHealth());
	}
	
	// Alive test
	
	@Test
	void isAliveShouldReturnTrueWithSatisfiedConditions() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		assertTrue(player.isAlive());
	}
	
	@Test
	void isAliveShouldReturnFalseWithSatisfiedConditions() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		player.takeDamage(100);
		
		assertFalse(player.isAlive());
	}
	
	// Equipment test
	
	@Test
	void getAttackPowerWithoutWeaponShouldReturnBaseAttack() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		Weapon w = new Weapon("Sword", "Iron", 10);
		
		player.equipWeapon(w);
		
		assertEquals(20, player.getAttackPower());
	}
	
	@Test
	void getAttackPowerWithWeaponShouldHaveBonus() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		Weapon w = new Weapon("Sword", "Iron", 5);
		
		player.equipWeapon(w);
		
		assertEquals(15, player.getAttackPower());
	}
	
	@Test
	void getDefensePowerWithArmorShouldHaveBonus() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		Armor a = new Armor("Armor", "Iron", 5);
		
		player.equipArmor(a);
		
		assertEquals(10, player.getDefensePower());
	}
	
	@Test
	void equipWeaponShouldThrow() {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		assertThrows(NullPointerException.class, () -> player.equipArmor(null));
	}
	
	@ParameterizedTest
	@ValueSource (ints = {0, 1, 50, 100})
	void takeDamagePTest(int damage) {
		Inventory i = new Inventory(50);
		Player player = new Player("Eli", 100, 10, 5, i); // Test object
		
		player.takeDamage(damage);
		
		assertTrue(player.getHealth() >= 0);
	}
	
}
