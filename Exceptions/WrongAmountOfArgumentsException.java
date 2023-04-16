package Exceptions;

public class WrongAmountOfArgumentsException extends Exception{
    public WrongAmountOfArgumentsException(Exception reason)
    {
        super(reason);
    }
}
