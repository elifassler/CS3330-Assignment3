package com.example.haunted.events;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.BeforeEach;
import com.example.haunted.model.Weapon;
import com.example.haunted.model.Item;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class MoveResultTest {
	
	@Test
	void constructorShouldSetFields() {
		MoveResult moveResult = new MoveResult(true, "Player moved", true, 15);
		
		assertTrue(moveResult.isSuccess());
		assertEquals("Player moved", moveResult.getMessage());
		assertTrue(moveResult.isTrapTriggered());
		assertEquals(15, moveResult.getTrapDamage());
	}
	
	@Test
	void objectShouldHandleTrapNotTriggered() {
		MoveResult moveResult = new MoveResult(true, "Safe", false, 0);
		
		assertTrue(moveResult.isSuccess());
		assertFalse(moveResult.isTrapTriggered());
		assertEquals(0, moveResult.getTrapDamage());
		
	}
	
	@Test
	void objectShouldHandleFailre() {
		MoveResult failedResult = new MoveResult(false, "Can't move", false, 0);
		
		assertFalse(failedResult.isSuccess());
		assertEquals("Can't move", failedResult.getMessage());
		assertEquals(0, failedResult.getTrapDamage());
	}
	
	@Test // null message
	void shouldAllowNullMessage() {
		MoveResult moveResult = new MoveResult(true, null, true, 15);
		
		assertNull(moveResult.getMessage());
	}
}
