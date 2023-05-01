package Commands;

import Client.ConsoleClient;
import Models.Band;
import Reciever.Receiver;

import java.util.HashMap;

public class Show extends AbstractCommandWithResult<HashMap<Integer, Band>> {
    private HashMap<Integer, Band> result = null;

    public Show(ConsoleClient client, Receiver receiver) {
        super("show", client, receiver);
    }

    @Override
    public void execute() {
        result = receiver.show();
    }

    @Override
    public HashMap<Integer, Band> getResult() {
        return result;
    }
}
