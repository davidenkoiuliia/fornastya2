package Commands;

import Client.ConsoleClient;
import Reciever.Receiver;

import java.io.Console;

public class Info extends AbstractCommandWithResult<String> {
    private String result = null;

    public Info(ConsoleClient client, Receiver receiver) {
        super("info", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.info();
    }

    @Override
    public String getResult() {
        return result;
    }
}
