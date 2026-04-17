package com.example.haunted.engine;
// imports
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.haunted.events.CombatResult;
import com.example.haunted.model.Inventory;
import com.example.haunted.model.Item;
import com.example.haunted.model.Monster;
import com.example.haunted.model.Player;
import com.example.haunted.model.Quest;
import com.example.haunted.model.Room;
import com.example.haunted.model.Weapon;
import com.example.haunted.rules.DamageCalculator;
import com.example.haunted.rules.QuestTracker;

public class CombatEngineTest {

    private CombatEngine combatEngine;
    private Player player;
    private Quest quest;
    private Room room;
    // creating items needed to test
    @BeforeEach
    public void setup() {
        combatEngine = new CombatEngine(new DamageCalculator(), new QuestTracker());
        player = new Player("Jack", 100, 10, 5, new Inventory(5));
        quest = new Quest("Escape", "beat the ghost and get the gradebook");
        room = new Room("r1", "Test room", "room for testing");
        player.setCurrentRoom(room);
    }
    // trying to attack a null monster
    @Test
    public void shouldReturnFailureWhenMonsterIsNull() {
        CombatResult result = combatEngine.attack(player, quest, null);
        // attack should fail and nothiing should happen
        assertFalse(result.isSuccess());
        assertEquals("Monster not found.", result.getMessage());
        assertEquals(0, result.getDamageToMonster());
        assertEquals(0, result.getDamageToPlayer());
        assertFalse(result.isMonsterDefeated());
    }
  
    // create monster and kill the player
    @Test
    public void shouldReturnFailureWhenPlayerIsDefeated() {
        Monster monster = new Monster("Ghost", 20, 8, 2, new ArrayList<Item>());
        player.takeDamage(100);

        CombatResult result = combatEngine.attack(player, quest, monster);
        // attack should fail bc player is dead
        assertFalse(result.isSuccess());
        assertEquals("Player is defeated.", result.getMessage());
        assertEquals(0, result.getDamageToMonster());
        assertEquals(0, result.getDamageToPlayer());
        assertFalse(result.isMonsterDefeated());
    }
    
    @Test
    public void shouldAttackMonsterAndMonsterShouldAttackBack() {
        Monster monster = new Monster("Ghost", 20, 8, 2, new ArrayList<Item>());

        CombatResult result = combatEngine.attack(player, quest, monster);
        // making sure both player and monster took damage
        assertTrue(result.isSuccess());
        assertEquals(8, result.getDamageToMonster());
        assertEquals(3, result.getDamageToPlayer());
        assertFalse(result.isMonsterDefeated());
        // making sure health values are as should be
        assertEquals(12, monster.getHealth());
        assertEquals(97, player.getHealth());
    }

    @Test
    public void shouldDefeatMonsterAndDropLoot() {
        ArrayList<Item> loot = new ArrayList<Item>();
        loot.add(new Weapon("Sword", "Dropped weapon", 3));
        // making monster w low health
        Monster monster = new Monster("Ghost", 5, 8, 2, loot);

        CombatResult result = combatEngine.attack(player, quest, monster);
        // asserting that monster was killed and loot dropped and the player doesnt take damage if monster is killed immedietly 
        assertTrue(result.isSuccess());
        assertTrue(result.isMonsterDefeated());
        assertEquals(1, result.getDroppedItems().size());
        assertEquals("Sword", result.getDroppedItems().get(0).getName());
        assertEquals(0, result.getDamageToPlayer());
    }
}
