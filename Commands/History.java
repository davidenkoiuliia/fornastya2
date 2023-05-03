package Commands;

import Client.ConsoleClient;
import Reciever.Receiver;

public class History extends AbstractCommand {
    public History(ConsoleClient client, Receiver receiver) {
        super("history", client, receiver);
    }

    @Override
    public void execute() {
        client.history();
    }
}
