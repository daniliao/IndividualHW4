package test;

import org.junit.Before;
import org.junit.Test;
import application.model.Message;
import application.model.MessageStore;

import static org.junit.Assert.*;

import java.util.List;

public class MessageStoreTest {

    @Before
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
