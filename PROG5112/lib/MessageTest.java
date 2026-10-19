import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MessageTest {

    private static final String RECIPIENT = "+27838968976";
    private Message message;

    @Before
    public void setUp() {
        message = new Message(RECIPIENT, "Hi Mike, can you join us for dinner tonight");
    }

    // ---- Getters ----

    @Test
    public void getRecipient_returnsRecipientPassedToConstructor() {
        assertEquals(RECIPIENT, message.getRecipient());
    }

    @Test
    public void getMessage_returnsMessagePassedToConstructor() {
        assertEquals("Hi Mike, can you join us for dinner tonight", message.getMessage());
    }

    // ---- Message ID ----

    @Test
    public void messageID_isNotNull() {
        assertNotNull(message.getMessageID());
    }

    @Test
    public void messageID_isExactlyTenDigits() {
        assertTrue("ID should be 10 digits but was: " + message.getMessageID(),
                message.getMessageID().matches("\\d{10}"));
    }



    // ---- Message hash ----

    @Test
    public void messageHash_hasFormatIdPrefixColonFirstWordLastWord() {
        String expected = message.getMessageID().substring(0, 2) + ":" + "HI" + "TONIGHT";
        assertEquals(expected, message.getMessageHash());
    }

    @Test
    public void messageHash_isUpperCase() {
        Message m = new Message(RECIPIENT, "hello there friend");
        String hash = m.getMessageHash();
        assertEquals(hash.toUpperCase(), hash);
    }

    @Test
    public void messageHash_singleWordUsesSameWordForFirstAndLast() {
        Message m = new Message(RECIPIENT, "Hello");
        String expected = m.getMessageID().substring(0, 2) + ":" + "HELLO" + "HELLO";
        assertEquals(expected, m.getMessageHash());
    }

    @Test
    public void messageHash_ignoresLeadingTrailingAndExtraSpaces() {
        Message m = new Message(RECIPIENT, "   Hi    there   Tom   ");
        String expected = m.getMessageID().substring(0, 2) + ":" + "HI" + "TOM";
        assertEquals(expected, m.getMessageHash());
    }

    @Test
    public void messageHash_handlesTabsAndNewlinesAsWhitespace() {
        Message m = new Message(RECIPIENT, "Hi\tthere\nBob");
        String expected = m.getMessageID().substring(0, 2) + ":" + "HI" + "BOB";
        assertEquals(expected, m.getMessageHash());
    }

    @Test
    public void messageHash_emptyMessageDoesNotCrashAndHasNoWords() {
        Message m = new Message(RECIPIENT, "");
        assertEquals(m.getMessageID().substring(0, 2) + ":", m.getMessageHash());
    }

    @Test
    public void messageHash_whitespaceOnlyMessageDoesNotCrash() {
        Message m = new Message(RECIPIENT, "     ");
        assertEquals(m.getMessageID().substring(0, 2) + ":", m.getMessageHash());
    }

    @Test
    public void messageHash_keepsPunctuationAttachedToWords() {
        Message m = new Message(RECIPIENT, "Hello, world!");
        String expected = m.getMessageID().substring(0, 2) + ":" + "HELLO," + "WORLD!";
        assertEquals(expected, m.getMessageHash());
    }

    // ---- Invalid input ----

    @Test(expected = NullPointerException.class)
    public void constructor_nullMessageThrowsNullPointerException() {
        new Message(RECIPIENT, null);
    }
}