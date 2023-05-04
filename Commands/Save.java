package Commands;

import Client.ConsoleClient;
import Reciever.Receiver;

public class Save extends AbstractCommand {

    public Save(ConsoleClient client, Receiver receiver) {
        super("save", client, receiver);
    }

    @Override
    public void execute() {
        receiver.save();
    }
}