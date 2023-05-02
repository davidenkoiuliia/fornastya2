package Commands;

import Client.ConsoleClient;
import Exceptions.IO.WrongArgumentException;
import Reciever.Receiver;

import java.time.LocalDateTime;

public class FilterStartsWithName extends AbstractCommand {
    private final String name;

    public FilterStartsWithName(ConsoleClient client, Receiver receiver, String name) {
        super("filter_starts_with_name", client, receiver);
        this.name = name;
    }

    @Override
    public void execute() {
        receiver.filter_starts_with_name(name);
    }

    @Override
    public String toString() {
        return name + " {" +
                "name=" + name +
                '}';
    }
}

