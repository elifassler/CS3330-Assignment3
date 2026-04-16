
package com.example.haunted.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTester {

    @Test
    void returnTrueWhenNotFull() {
        // makes sure items can be added when capacity is not full 
        Inventory inventory = new Inventory(5);
        Key key = new Key("Moon Choi's Key", "Opens the main office");
        
        assertTrue(inventory.addItem(key));
        assertEquals(1, inventory.getItems().size());
    }

    @Test
    void returnFalseWhenFull() {
        // prevents items from being added when capacity is full
        Inventory inventory = new Inventory(1);
        inventory.addItem(new Key("Key 1", "Description"));
        
        boolean result = inventory.addItem(new Key("Key 2", "Description"));
        
        assertFalse(result);
        assertEquals(1, inventory.getItems().size());
    }

    @ParameterizedTest 
    @ValueSource(strings = {"Moon Choi's Key", "MOON CHOI'S KEY", "moon choi's key"})
    void findItemISCaseInsensitive(String searchName) {
        // makes sure items can be found regardless of case sensitivity 
        Inventory inventory = new Inventory(5);
        inventory.addItem(new Key("Moon Choi's Key", "Description"));
        
        assertTrue(inventory.findItem(searchName).isPresent());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void isFullShouldReturnTrueWhenAtCapacity(int capacity) {
        Inventory inventory = new Inventory(capacity);
        
        // Fill the inventory until capacity is met 
        for (int i = 0; i < capacity; i++) {
            inventory.addItem(new Key("Item " + i, "Description"));
        }
        
        assertTrue(inventory.isFull());
    }

    @Test
    void removeItemReturnsNullIfNotFound() {
        //ensures non existent items are null
        Inventory inventory = new Inventory(5);
        Item removed = inventory.removeItem("NonExistentItem");
        
        assertNull(removed);
    }

    @Test
    void isFullReturnsTrueAtCapacity() {
        // Makes sure Is full returns True when capacity is met 
        Inventory inventory = new Inventory(2);
        inventory.addItem(new Key("Item 1", "Desc"));
        inventory.addItem(new Key("Item 2", "Desc"));
        
        assertTrue(inventory.isFull());
    }
}
