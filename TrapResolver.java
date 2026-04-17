package com.example.haunted.rules;
// imports
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.haunted.events.InteractionResult;
import com.example.haunted.model.Inventory;
import com.example.haunted.model.Player;
import com.example.haunted.model.Trap;
import com.example.haunted.model.TrapType;

public class TrapResolverTest {

    private TrapResolver trapResolver;
    private Player player;
    // making resolver and player before the tests
    @BeforeEach
    public void setup() {
        trapResolver = new TrapResolver();
        player = new Player("Jack", 100, 10, 5, new Inventory(5));
    }
  
    // resolving with a null trap
    // nothing should happen and player's health should be full
    @Test
    public void shouldReturnNoTrapTriggeredWhenTrapIsNull() {
        InteractionResult result = trapResolver.resolve(player, null);

        assertTrue(result.isSuccess());
        assertEquals("No trap was triggered.", result.getMessage());
        assertEquals(100, player.getHealth());
    }

    // creating trap, disarming it, and making sure the player is not damaged and the trap becomes disarmed
    @Test
    public void shouldReturnNoTrapTriggeredWhenTrapIsDisarmed() {
        Trap trap = new Trap("Broken Wire", TrapType.ELECTRIC, 20, true, true);
        trap.disarm();

        InteractionResult result = trapResolver.resolve(player, trap);

        assertTrue(result.isSuccess());
        assertEquals("No trap was triggered.", result.getMessage());
        assertEquals(100, player.getHealth());
    }

    // using parameterized tests for dif values to make sure the trap affects the players health accordingly then becomes disarmed
    @ParameterizedTest
    @ValueSource(ints = {10, 15, 20})
    public void shouldDamagePlayerWhenTrapIsArmed(int damage) {
        Trap trap = new Trap("Test Trap", TrapType.ELECTRIC, damage, true, true);

        InteractionResult result = trapResolver.resolve(player, trap);

        assertTrue(result.isSuccess());
        // the remaining health is equal to full health - the damage
        assertEquals(100 - damage, player.getHealth());
        assertFalse(trap.isArmed());
    }
  
    // resuable trap, making sure player takes damage and the trap is not disarmed after use
    @Test
    public void shouldKeepReusableTrapArmedAfterTriggering() {
        Trap trap = new Trap("Steam Burst", TrapType.STEAM, 15, true, false);

        trapResolver.resolve(player, trap);

        assertTrue(trap.isArmed());
        assertEquals(85, player.getHealth());
    }
}
