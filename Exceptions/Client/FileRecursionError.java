package Exceptions.Client;
public class FileRecursionError extends Exception {
    public FileRecursionError(String path) {
        super("! file recursion: " + path + " is called recursively !");
    }
}