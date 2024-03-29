package Commands;

import Client.ConsoleClient;
import Exceptions.IO.WrongArgumentException;
import Models.MusicGenre;
import Reciever.Receiver;

import java.time.LocalDateTime;

public class RemoveLowerKey extends AbstractCommand {
    private final Integer key;

    public RemoveLowerKey(ConsoleClient client, Receiver receiver, Integer key) {
        super("remove_lower_key", client, receiver);
        this.key = key;
    }

    @Override
    public void execute() {
        receiver.removeLowerKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}