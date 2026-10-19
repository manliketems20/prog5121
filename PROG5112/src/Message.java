import java.util.Random;
public class Message {

    private final String messageID;
    private final String recipient;
    private final String messageHash;
    private final String message;

    // Constructor
    public Message(String recipient, String message) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }

    // Generate random 10-digit ID
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }
    // Create hash: first 2 digits of ID + ":" + FIRSTWORD + LASTWORD
    private String createMessageHash() {
        // trim + split on any whitespace so extra spaces / empty messages don't crash
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        return messageID.substring(0, 2) + ":" + firstWord + lastWord;
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getMessageHash() { return messageHash; }
}

