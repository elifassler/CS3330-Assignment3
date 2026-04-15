package com.example.haunted.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class QuestItemTest {

    private QuestItem questItem;

    @BeforeEach
    public void setup() {
        questItem = new QuestItem("My Watch", "A Timex");
    }

    @Test
    public void shouldStoreName() {
        assertEquals("My Watch", questItem.getName());
    }

    @Test
    public void shouldStoreDescription() {
        assertEquals("A Timex", questItem.getDescription());
    }
}
