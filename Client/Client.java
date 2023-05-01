package Client;

import Exceptions.IO.CustomIOException;

public interface Client {
    void help();

    void exit();

    void history();

    void executeScript(String path) throws CustomIOException;
}
