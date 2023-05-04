package Commands;

import Client.ConsoleClient;
import Reciever.Receiver;

public abstract class AbstractCommandWithResult<T> extends AbstractCommand implements CommandWithResult<T> {
    public AbstractCommandWithResult(String name, ConsoleClient client, Receiver receiver) {
        super(name, client, receiver);
    }
}