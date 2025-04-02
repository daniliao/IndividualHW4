package application.model;

import application.model.Message;
import application.model.MessageStore;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MessageStoreTest {

    @BeforeEach
    public void setup() {
        MessageStore.clear();
    }

    @Test
    public void testAddAndRetrieveMessage() {
        Message msg = new Message("alice", "bob", "Hello", "Now");
        MessageStore.addMessage(msg);

        List<Message> messages = MessageStore.getMessagesForUser("bob");

        assertEquals(1, messages.size());
        assertEquals("alice", messages.get(0).getSender());
        assertEquals("Hello", messages.get(0).getContent());
    }

    @Test
    public void testNoMessagesForUser() {
        List<Message> messages = MessageStore.getMessagesForUser("charlie");
        assertTrue(messages.isEmpty());
    }
}
