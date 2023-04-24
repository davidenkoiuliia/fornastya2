package Exceptions.IO;
public class WrongArgumentException extends Exception {
    public WrongArgumentException(String message) {
        super("! " + message + " !");
    }
}