package com.example.haunted.events;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class InteractionResultTest {
	
	@Test
	void constructorShouldSetFields() {
		InteractionResult interactionResult= new InteractionResult(true, "Successful interaction");
		
		assertEquals(true, interactionResult.isSuccess());
		
		assertEquals("Successful interaction", interactionResult.getMessage());
	}
	
	// False success
	@Test
	void objectShouldTakeUnsuccessful() {
		InteractionResult failedResult = new InteractionResult(false, "Failed interaction");
		
		assertFalse(failedResult.isSuccess());
		
		assertEquals("Failed interaction", failedResult.getMessage());
	}
	
	
	@Test
	void nullMessageAllowed() {
		InteractionResult nullMessage = new InteractionResult(true, null);
		
		assertNull(nullMessage.getMessage());
	}
}
