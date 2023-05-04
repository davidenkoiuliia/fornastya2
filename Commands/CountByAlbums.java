package Commands;

import Client.ConsoleClient;
import Exceptions.IO.WrongArgumentException;
import Reciever.Receiver;

import java.time.LocalDateTime;

public class CountByAlbums extends AbstractCommand {
    private final int albumsCount;

    public CountByAlbums(ConsoleClient client, Receiver receiver, int albumsCount) {
        super("count_by_albums", client, receiver);
        this.albumsCount = albumsCount;
    }

    @Override
    public void execute() {
        receiver.count_by_albums(albumsCount);
    }

    @Override
    public String toString() {
        return name + " {" +
                "albumsCount=" + albumsCount +
                '}';
    }
}
