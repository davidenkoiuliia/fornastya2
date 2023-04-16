package Manager.AllCommands;
import Exceptions.WrongAmountOfArgumentsException;

public interface Command {
    String getName();
    String getDescr();
    default String getArgs()
    {
        return "";
    }
    void execute(String[] args) throws IllegalArgumentException, WrongAmountOfArgumentsException;
}

