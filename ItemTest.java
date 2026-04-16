package com.example.haunted.model;

import org.junit.jupiter.api.Test;

public class ItemTester {
	
	@Test
	void constructorShouldSetFeildsBySubclass() {
	Armor item = new Armor("Chestplate", "Diamond", 8); 	
	assertEquals("Diamond Chestplate", item.getName());
	assertEquals("Enchanted", item.getDescription());
	
	}
	@Test 
	void ConstructorAssignsDefaultNameIfNull() {
	//Sets default name if name is null
	Weapon item = new Weapon (null, "Strange Sword", 15);
	assertEquals("Unknown Weapon", item.getName());
	}
	
	@Test
	void ConstructorSetsDefaultIfDescriptionNull() {
		//Sets Description to Unidentified if null 
	Key item = new Key("Key", null,);
	assertEquals("Unidentified Key", item.getDescription());
	}
	
	@Test 
	void toStringReturnsItemName() {
	Potion item = new Potion("Light Healing Potion", "A lesser healing potion +10hp", 10);
	assertEquals("Light Healing Potion", item.toString());
