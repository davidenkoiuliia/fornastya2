package Commands;

import Client.ConsoleClient;
import Exceptions.Receiver.CollectionKeyException;
import Reciever.Receiver;

import java.io.Console;

public class RemoveKey extends AbstractCommand {
    private final Integer key;

    public RemoveKey(ConsoleClient client, Receiver receiver, Integer key) {
        super("remove_key", client, receiver);
        this.key = key;
    }

    @Override
    public void execute() throws CollectionKeyException {
        receiver.removeKey(key);
    }

    @Override
    public String toString() {
        return name + " {" +
                "key=" + key +
                '}';
    }
}