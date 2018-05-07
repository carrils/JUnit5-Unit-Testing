import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    //these variables are used to test evaluateEntropy strength levels
    char[] vw = "Very Weak".toCharArray();
    char[] w = "Weak".toCharArray();
    char[] m = "Moderate".toCharArray();
    char[] s = "Strong".toCharArray();
    char[] vs = "Very Strong".toCharArray();

    //public static final double DELTA = 0.0001;

    @Test
    void countSpecialCharacters() {
        assertEquals(5, Main.countSpecialCharacters("theGodFather#@$%^","!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
        assertEquals(0,Main.countSpecialCharacters("vitoCorleone", "!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
        assertEquals(2, Main.countSpecialCharacters(":)","!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
    }

    @Test
    void hasDigit() {
        assertTrue(Main.hasDigit("cows5"));
        assertTrue(!Main.hasDigit("giraffes"));
        assertTrue(Main.hasDigit("1337h4[]"));
    }

    @Test
    void hasUpperCase() {
        assertTrue(Main.hasUpperCase("JetLi"));
        assertTrue(!Main.hasUpperCase("pantera"));
        assertFalse(!Main.hasUpperCase("Walk"));
    }

    @Test
    void hasLowerCase() {
        assertTrue(Main.hasLowerCase("brick and mortar"));
        assertFalse(Main.hasLowerCase("LOUD NOISES"));
        assertTrue(Main.hasLowerCase("probablyLowercase"));
    }

    @Test
    void trimmedLength() {
        assertEquals(5, Main.trimmedLength("              Senna             "));
        assertEquals(0, Main.trimmedLength(""));
        assertEquals(5, Main.trimmedLength("       Prost        "));
        assertEquals(1, Main.trimmedLength("    N"));
    }

    @Test
    void calculateRange() {
        assertEquals(26, Main.calculateRange("lowercaseonly","!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
        assertEquals(52, Main.calculateRange("LowerAndUpperCase","!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
        assertEquals(62, Main.calculateRange("UpperLowerAndDigits55", "!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
        assertEquals(67, Main.calculateRange("UpperLowerDigitsAnd5SpecChars#$%^&", "!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
        assertEquals(72, Main.calculateRange("UpperLowerDigitsAnd10SpecChars!@#$%^&*()","!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
    }

    @Test
    void truncate() {
        assertEquals(3, Main.truncate("0 length", 0).length());//... = 3 chars
        assertEquals(6, Main.truncate("harmony", 3).length());//har... = 6 chars
        assertEquals(12, Main.truncate("while(1<2)", 9).length());//while(1<2... = 12 chars
    }

    @Test
    void log2() {
        assertEquals(1, Main.log2(2));
        assertEquals(0, Main.log2(1));
        assertEquals(2, Main.log2(4));
    }

    @Test
    void calculateEntropy() {
        assertEquals(32, Main.calculateEntropy("awesome", "!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
        assertEquals(137, Main.calculateEntropy("Fear Of The Dark - 1998", "!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
        assertEquals(0, Main.calculateEntropy("", "!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"));
    }

    @Test
    void evaluateEntropy() {
        assertArrayEquals(vw, Main.evaluateEntropy(63).toCharArray());//very weak
        assertArrayEquals(w, Main.evaluateEntropy(79).toCharArray());//weak
        assertArrayEquals(m, Main.evaluateEntropy(111).toCharArray());//moderate
        assertArrayEquals(s, Main.evaluateEntropy(127).toCharArray());//strong
        assertArrayEquals(vs, Main.evaluateEntropy(129).toCharArray());//very strong

    }
}