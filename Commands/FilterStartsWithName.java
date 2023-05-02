package Commands;

import Client.ConsoleClient;
import Exceptions.IO.WrongArgumentException;
import Models.Band;
import Reciever.Receiver;

import java.time.LocalDateTime;
import java.util.HashMap;

public class FilterStartsWithName extends AbstractCommandWithResult<HashMap<Integer, Band>> {
    private final String name;
    private HashMap<Integer, Band> result = null;

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
    @Override
    public HashMap<Integer, Band> getResult() {
        return result;
    }
}
