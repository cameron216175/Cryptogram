package src.test.java;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import src.main.java.Cryptogram;

class CryptogramTest {

    @Test
    void testExample() {

        String phrase  = Cryptogram.newGram();

        assertNotNull(phrase);

        assertFalse(phrase.isEmpty());



    }
}
