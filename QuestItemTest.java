package com.example.haunted.model;
// imports
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class QuestItemTest {
    // creating quest item before test
    private QuestItem questItem;

    @BeforeEach
    public void setup() {
        questItem = new QuestItem("My Watch", "A Timex");
    }
    // testing for storing name and description
    @Test
    public void shouldStoreName() {
        assertEquals("My Watch", questItem.getName());
    }
    
    @Test
    public void shouldStoreDescription() {
        assertEquals("A Timex", questItem.getDescription());
    }
}
