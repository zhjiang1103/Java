package edu.gatech.seclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import java.util.concurrent.TimeUnit;

import static edu.gatech.seclass.MyStringInterface.easterEgg;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Junit test class created for use in Georgia Tech CS6300.
 * <p>
 * This class is provided to interpret your grades via junit tests
 * and as a reminder, should NOT be posted in any public repositories,
 * even after the class has ended.
 */

@Timeout(value = 1, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class MyStringTest {

    private MyStringInterface myString;

    @BeforeEach
    public void setUp() {
        myString = new MyString();
    }

    @AfterEach
    public void tearDown() {
        myString = null;
    }

    @Test
    // Description: First count number example in the interface documentation
    public void testCountAlphabeticWords1() {
        myString.setString("My numbers are 11, 96, and thirteen");
        assertEquals(5, myString.countAlphabeticWords());
    }

    @Test
    // Description: Second count number example in the interface documentation
    public void testCountAlphabeticWords2() {
        myString.setString("i#love 2 pr00gram.");
        assertEquals(4, myString.countAlphabeticWords());
    }

    @Test
    // Description: This test checks whether method countAlphabeticWords counts words when there is no alphabetic words in string
    public void testCountAlphabeticWords3() {
        myString.setString("1234 567890#");
        assertEquals(0, myString.countAlphabeticWords());
    }

    @Test
    // Description: This test checks whether method countAlphabeticWords throws NullPointerException if string is null
    public void testCountAlphabeticWords4() {
        myString.setString(null);
        assertThrows(NullPointerException.class,()->myString.countAlphabeticWords());
    }

    @Test
    // Description: This test checks whether method setString set a value of current string
    public void testSetString1() {
        MyString string = new MyString();
        string.setString(easterEgg);
        assertEquals(easterEgg, string.getString());
    }

    @Test
    // Description: Sample encryption 1
    public void testEncrypt1() {
        myString.setString("Cat & 5 DogS");
        assertEquals("1xU & S 65RJ", myString.encrypt(5, 3));
    }

    @Test
    // Description: It checks whether the method return string "Ja!" encrypted using Affine Cipher
    public void testEncrypt2() {
        myString.setString("Ja!");
        assertEquals("0p!", myString.encrypt(3,5));
    }

    @Test
    // Description: It checks whether the method return string "123Z #" encrypted using Affine Cipher
    public void testEncrypt3() {
        myString.setString("123Z #");
        assertEquals("GNU6 #", myString.encrypt(7,9));
    }

    @Test
    // Description: It checks whether the method throws NullPointerException if string is null
    public void testEncrypt4() {
        myString.setString(null);
        assertThrows(NullPointerException.class, ()->myString.encrypt(3,5));
    }

    @Test
    // Description: It checks whether the method throws IllegalArgumentException if argument 1 is not co prime
    public void testEncrypt5() {
        myString.setString(("Ja!"));
        assertThrows(IllegalArgumentException.class, ()->myString.encrypt(2,3));
    }

    @Test
    // Description: It checks whether the method throws IllegalArgumentException if argument 2 is negative
    public void testEncrypt6() {
        myString.setString(("Ja!"));
        assertThrows(IllegalArgumentException.class, ()->myString.encrypt(3,-5));
    }

    @Test
    // Description: First convert digits example in the interface documentation
    public void testConvertDigitsToNamesInSubstring1() {
        myString.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        myString.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", myString.getString());
    }

    @Test
    // Description: Second convert digits example in the interface documentation
    public void testConvertDigitsToNamesInSubstring2() {
        myString.setString("abc416d");
        myString.convertDigitsToNamesInSubstring(2,7);
        assertEquals("abcFourOneSixd", myString.getString());
    }

    @Test
    // Description: It checks whether the method convert "Java!1" correctly
    public void testConvertDigitsToNamesInSubstring3() {
        myString.setString("Java!1");
        myString.convertDigitsToNamesInSubstring(3,6);
        assertEquals("Java!One", myString.getString());
    }

    @Test
    // Description: It checks whether the method throws IndexOutOfBoundException when finalPosition is out of bound
    public void testConvertDigitsToNamesInSubstring4() {
        myString.setString("Java!1");
        assertThrows(MyIndexOutOfBoundsException.class, () ->myString.convertDigitsToNamesInSubstring(3, 10));
    }

    @Test
    // Description: It checks whether the method throws IllegalArgumentException when finalPosition < firstPosition
    public void testConvertDigitsToNamesInSubstring5() {
        myString.setString("Java!1");
        assertThrows(IllegalArgumentException.class, () ->myString.convertDigitsToNamesInSubstring(3, 2));

    }
}
