package com.example.haunted.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; // Required for assertEquals to work

public class ItemTester {
	
	@Test
	void constructorShouldSetFieldsBySubclass() {
		// confirms subclass sets name and description 
		Armor item = new Armor("Chestplate", "Diamond", 8); 	
		assertEquals("Diamond Chestplate", item.getName());
		assertEquals("Enchanted", item.getDescription());
	}

	@Test 
	void ConstructorAssignsDefaultNameIfNull() {
		// sets a default name for null values 
		Weapon item = new Weapon(null, "Strange Sword", 15);
		assertEquals("Unknown Weapon", item.getName());
	}
	
	@Test
	void ConstructorSetsDefaultIfDescriptionNull() {
		// sets Description if null 
		Key item = new Key("Key", null);
		assertEquals("Unidentified Key", item.getDescription());
	}
	
	@Test 
	void toStringReturnsItemName() {
		//verifies that toString returns name 
		Potion item = new Potion("Light Healing Potion", "A lesser healing potion +10hp", 10);
		assertEquals("Light Healing Potion", item.toString());
	}
}
