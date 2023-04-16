package Exceptions;

public class CommandInterruptException extends Exception{
    public CommandInterruptException(Exception reason)
    {
        super(reason);
    }
}
