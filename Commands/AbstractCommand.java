package Commands;

import Client.ConsoleClient;
import Reciever.Receiver;

public abstract class AbstractCommand implements Command {
    ConsoleClient client;
    final Receiver receiver;
    final String name;

    public AbstractCommand(String name, ConsoleClient client, Receiver receiver) {
        this.name = name;
        this.client = client;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return name;
    }
}