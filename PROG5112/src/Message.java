import java.util.Random;
public class Message {
private static int totalMessages = 0;

 // Static collections for Part 3
         private static List<Message> storedMessages = new ArrayList<>();
 private static List<Message> sentMessages = new ArrayList<>();
 private static List<Message> disregardedMessages = new ArrayList<>();

 private final String messageID;
 private final String recipient;
 private final String messageHash;
    private final String message;
    private String status;

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

// Create hash: first 2 digits of ID + first + last word
         private String createMessageHash() {
 String[] words = message.split(" ");
 String firstWord = words[0].toUpperCase();
 String lastWord = words[words.length - 1].toUpperCase();
 return messageID.substring(0, 2) + ":" + firstWord + lastWord;
 }

 // Handle sending, storing, or discarding
        public String sendMessage(String option) {
 switch (option.toLowerCase()) {
 case "send":
 status = "Sent";
 totalMessages++;
 sentMessages.add(this);
 return "Message successfully sent";
case "store":
 status = "Stored";
 storedMessages.add(this);
return "Message successfully stored";
 case "discard":
 status = "Disregarded";
 disregardedMessages.add(this);
 return "Message disregarded";
default:
 return "Invalid Option";
 }
}

public static int returnTotalMessages() {
 return totalMessages;
 }

 // Print message details
         public String printMessage() {
 return "Message ID: " + messageID + "\n" +
"Message Hash: " + messageHash + "\n" +
"Recipient: " + recipient + "\n" +
"Message: " + message + "\n" +
"Status: " + status;
 }

// Store message in JSON format
         public JSONObject storeMessageJSON() {
 JSONObject obj = new JSONObject();
 obj.put("MessageID", messageID);
 obj.put("MessageHash", messageHash);
 obj.put("Recipient", recipient);
 obj.put("Message", message);
 obj.put("Status", status);
 return obj;
 }

 // --- Part 3 Stored Message Operations ---
         public static void displayStoredMessages() {
 for (Message msg : storedMessages) {
System.out.println("Recipient: " + msg.recipient);
 }
 }

 public static void displayLongestMessage() {
 Message longest = storedMessages.stream()
 .max(Comparator.comparingInt(m -> m.message.length()))
 .orElse(null);
 if (longest != null) {
 System.out.println("Longest Message: " + longest.message);
 }
 }

 public static void searchByMessageID(String id) {
 for (Message msg : storedMessages) {
 if (msg.messageID.equals(id)) {
 System.out.println("Recipient: " + msg.recipient + ", Message: " + msg.message);
 }
}
 }

 public static void searchByRecipient(String recipient) {
 for (Message msg : storedMessages) {
 if (msg.recipient.equals(recipient)) {
System.out.println(msg.message);
}
 }
 }

public static void deleteByHash(String hash) {
 storedMessages.removeIf(msg -> msg.messageHash.equals(hash));
System.out.println("Message with hash " + hash + " deleted.");
 }

 public static void displayReport() {
 for (Message msg : storedMessages) {
 System.out.println(msg.printMessage());
 }
}

    // Getters
         public String getMessageID() { return messageID; }
 public String getRecipient() { return recipient; }
 public String getMessage() { return message; }
public String getMessageHash() { return messageHash; }
 public String getStatus() { return status; }
}


