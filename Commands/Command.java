package Commands;

import Exceptions.IO.CustomIOException;
import Exceptions.IO.WrongArgumentException;
import Exceptions.Receiver.CollectionKeyException;

public interface Command {
    void execute() throws CollectionKeyException, WrongArgumentException, CustomIOException;
}