package Exceptions.Receiver;

    public class CollectionKeyException extends Exception {
        public CollectionKeyException(String message) {
            super("! " + message + " !");
        }
    }

