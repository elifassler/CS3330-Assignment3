package com.example.haunted.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class KeyTester {

    @Test
    void constructorSetsKeyProperties() { 
        Key key = new Key("Trumans Key", "Opens all buildings on campus");
        assertEquals("Trumans Key", key.getName());
        assertEquals("Opens all buildings on campus", key.getDescription());
    }
        
    @Test //Checks that key cant be equipped 
    void keyIsNotEquippable() {
        Key key = new Key("Pickelmans Key", "Opens Pickelmans");
        assertFalse(key instanceof Equippable);
    }
    
    @Test 
    void toStringReturnsKeyName() {
        Key key = new Key("Dr. Ekin's Key", "Opens Tucker Hall");
        assertEquals("Dr. Ekin's Key", key.toString());
    }

    @ParameterizedTest // Changes all null values to strings 
    @ValueSource(strings = {"Unknown Key"})
    // sets default name 
    void SetDefaultNameIfNull(String expected) {
        Key key = new Key(null, "Unidentified key"); 
        assertEquals(expected, key.getName());
    }

    @Test
    void setDefaultDescriptionIfNull() {
    	// Sets Default description 
        Key key = new Key("Unknown Key", null);
        assertEquals("Unidentified Key", key.getDescription());
    }
}
