package com.example.haunted.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class KeyTester {

	public KeyTester() {
		@Test
		void constructorSetsKeyProperties() { 
			Key key = new key("Trumans Key", "Opens all buildings on campus");
			assertEquals("Trumans Key", key.getName());
			assertEquals("Opens all buildings on campus", key.getDescription());
			
		}
			
		@Test 
		void keyIsNotEquippable() {
			Key key = new Key("Pickelmans Key", "Opens Pickelmans");
			assertFalse(key instanceof Equippable,"Cannot equip this item.");
			
		}
		
		@Test 
		void toStringReturnsKeyName() {
			Key key = new Key("Dr.Ekin's Key", "Opens Tucker Hall");
			assertEquals("Dr. Ekin's Key", key.toString());
		}
		void SetDefaultNameIfNull() {
			// Sets Default if Name is Null
			Key key = new Key(null, "Unidentified key")
			assertEquals("Unknown Key", key.getName());
		}
		
		void setDefaultDescription() {
			Key key = new Key("Unknown Key", null);
			assertEquals("Unidentified Key", key.getDescription());
			
			
		}
	}

}
