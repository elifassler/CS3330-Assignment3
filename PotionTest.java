package com.example.haunted.model;

// imports
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PotionTest {

    private Player player;
    private Potion potion;
    // have to make a new player and potion for the test
    @BeforeEach
    public void setup() {
        player = new Player("Jack", 100, 10, 5, new Inventory(5));
        potion = new Potion("Coffee Potion", "Restores energy", 25);
    }
    
    @Test
    public void shouldStoreHealingAmount() {
        assertEquals(25, potion.getHealingAmount()); //
    }

    @Test
    public void shouldHealPlayerWhenUsed() {
        player.takeDamage(40);

        potion.use(player);

        assertEquals(85, player.getHealth());
    }

    @Test
    public void shouldNotHealPastMaxHealth() {
        player.takeDamage(10);

        potion.use(player);

        assertEquals(100, player.getHealth());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 25, 100})
    public void shouldHealPlayerForDifferentAmounts(int healingAmount) {
        Potion testPotion = new Potion("Test Potion", "Test Description", healingAmount);
        player.takeDamage(50);

        testPotion.use(player);
        // if statement for parameterized test
        if (50 + healingAmount > 100) {
            assertEquals(100, player.getHealth());
        } else {
            assertEquals(50 + healingAmount, player.getHealth());
        }
    }
}
