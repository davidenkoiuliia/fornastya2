package Commands;

import Client.ConsoleClient;
import Reciever.Receiver;

public class Help extends AbstractCommand {
    public Help(ConsoleClient client, Receiver receiver) {
        super("help", client, receiver);
    }

    @Override
    public void execute() {
        client.help();
    }
}