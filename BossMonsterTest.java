package com.example.haunted.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BossMonsterTester {

    @Test
    void shouldReturnBaseAttackWhenHealthIsHigh() {
        // makes sure boss cannot use enraged attack at high health 
        BossMonster boss = new BossMonster("Moon Choi", 100, 10, 5, new ArrayList<>(), 5);
        assertEquals(10, boss.getCurrentAttack());
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 25, 10})
    void shouldReturnEnragedAttackWhenHealthIsLow(int currentHealth) {
        // makes sure bosses health is < 50 for enraged attack 
        BossMonster boss = new BossMonster("Moon Choi", 100, 10, 5, new ArrayList<>(), 5);
        boss.setHealth(currentHealth);
        // base 10 + 5 = 15 
        assertEquals(15, boss.getCurrentAttack()); 
    }

    @Test
    void constructorShouldSetInheritedFields() {
        // makes sure super constructor sets correct name 
        BossMonster boss = new BossMonster("Moon Choi", 100, 10, 5, new ArrayList<>(), 5);
        assertEquals("Moon Choi", boss.getName());
    }
}
