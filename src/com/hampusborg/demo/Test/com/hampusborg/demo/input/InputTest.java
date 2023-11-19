
/*
package com.hampusborg.demo.input;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {
    public void testValidInput() {
        String input = "42/n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int result = Input.getIntInput("Enter a number: ");

        assertEquals(42, result);
    }

    @Test
    public void testFaultyIntInput() {
        String faultyInput = "Faulty\n";
        InputStream in = new ByteArrayInputStream(faultyInput.getBytes());

        assertThrows(NumberFormatException.class, () -> {
            Input.getIntInput("Enter a number: ");
        });
    }

    @Test
    void getIntInput() {
    }
}

*/
