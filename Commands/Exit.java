package Commands;

import Client.ConsoleClient;
import Reciever.Receiver;

public class Exit extends AbstractCommand {
    public Exit(ConsoleClient client, Receiver receiver) {
        super("exit", client, receiver);
    }

    @Override
    public void execute() {
        client.exit();
    }
}