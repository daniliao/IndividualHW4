package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageStore {
    private static final List<Message> messages = new ArrayList<>();

    public static void addMessage(Message msg) {
        messages.add(msg);
    }

    public static List<Message> getMessagesForUser(String username) {
        List<Message> result = new ArrayList<>();
        for (Message m : messages) {
            if (m.getReceiver().equals(username) || m.getSender().equals(username)) {
                result.add(m);
            }
        }
        return result;
    }

    public static void clear() {
        messages.clear();
    }

    public static List<Message> getAll() {
        return Collections.unmodifiableList(messages);
    }
}