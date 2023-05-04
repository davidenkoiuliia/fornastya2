package Client;

import Commands.AbstractCommand;
import Commands.AbstractCommandWithResult;
import Exceptions.IO.CustomIOException;
import Exceptions.IO.WrongArgumentException;
import Exceptions.Receiver.CollectionKeyException;

import java.util.Stack;

public class Invoker {
    private final Stack<AbstractCommand> commandHistory = new Stack<>();

    public void execute(AbstractCommand command) throws CollectionKeyException, WrongArgumentException, CustomIOException {
        commandHistory.push(command);
        command.execute();
    }
    public <T> T executeAndReturn(AbstractCommandWithResult<T> command) throws WrongArgumentException, CollectionKeyException, CustomIOException {
        commandHistory.push(command);
        command.execute();
        return command.getResult();
    }
    public Stack<AbstractCommand> getCommandHistory() {
        return commandHistory;
    }
}