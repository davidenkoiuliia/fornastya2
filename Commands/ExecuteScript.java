package Commands;

import Client.ConsoleClient;
import Exceptions.IO.CustomIOException;
import Reciever.Receiver;

public class ExecuteScript extends AbstractCommand {
    private final String path;

    public ExecuteScript(ConsoleClient client, Receiver receiver, String path) {
        super("execute_script", client, receiver);
        this.path = path;
    }

    @Override
    public void execute() throws CustomIOException {
        client.executeScript(path);
    }

    @Override
    public String toString() {
        return name + " {" +
                "path='" + path + '\'' +
                '}';
    }
}
