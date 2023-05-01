package Commands;

import Client.ConsoleClient;
import Reciever.Receiver;

public class Clear extends AbstractCommand {
    public Clear(ConsoleClient client, Receiver receiver) {
        super("clear", client, receiver);
    }

    @Override
    public void execute() {
        receiver.clear();
    }
}
