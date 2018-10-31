package ru.unn.agile.caesarcipher.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CaesarCipherTest {

    @Test
    public void canEncodeEmptyString() {
        // Arrange
        CaesarCipher cipher = new CaesarCipher();
        // Act
        String encodedString = cipher.encode("", 1);
        // Assert
        assertEquals(encodedString, "");
    }

    @Test
    public void canShiftStringByZeroOffset() {
        CaesarCipher cipher = new CaesarCipher();
        String encodedString = cipher.encode("abc", 0);
        assertEquals(encodedString, "abc");
    }

    @Test
    public void canShiftStringByPositiveOffset() {
        CaesarCipher cipher = new CaesarCipher();
        String encodedString = cipher.encode("abc", 1);
        assertEquals(encodedString, "bcd");
    }

    @Test
    public void canShiftStringByNegativeOffset() {
        CaesarCipher cipher = new CaesarCipher();
        String encodedString = cipher.encode("bcd", -1);
        assertEquals(encodedString, "abc");
    }

    @Test
    public void canWrapAroundWithNegativeOffset() {
        CaesarCipher cipher = new CaesarCipher();
        String encodedString = cipher.encode("a", -1);
        assertEquals(encodedString, "z");
    }

    @Test
    public void canWrapAroundWithPositiveOffset() {
        CaesarCipher cipher = new CaesarCipher();
        String encodedString = cipher.encode("z", 1);
        assertEquals(encodedString, "a");
    }

    @Test
    public void canShiftCapitalLettersString() {
        CaesarCipher cipher = new CaesarCipher();
        String encodedString = cipher.encode("ABC", 1);
        assertEquals(encodedString, "BCD");
    }

    @Test
    public void canIgnoreOtherSymbols() {
        CaesarCipher cipher = new CaesarCipher();
        String encodedString = cipher.encode("&^#$!.?", 1);
        assertEquals(encodedString, "&^#$!.?");
    }

    @Test
    public void canDecodeString() {
        String testString = "Test 123";
        CaesarCipher cipher = new CaesarCipher();
        String encodedString = cipher.encode(testString, 1);
        String decodedString = cipher.decode(encodedString, 1);
        assertEquals(testString, decodedString);
    }

    @Test
    public void canHandleBigPositiveOffset() {
        CaesarCipher cipher = new CaesarCipher();
        String encodedString = cipher.encode("abc", 6828277);
        assertEquals(encodedString, "bcd");
    }
}
