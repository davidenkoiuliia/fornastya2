package Commands;

import Client.ConsoleClient;
import Exceptions.IO.WrongArgumentException;
import Exceptions.Receiver.CollectionKeyException;
import Models.MusicGenre;
import Reciever.Receiver;

import java.time.LocalDateTime;

public class Update extends AbstractCommand {
    private final Integer id;
    private final String name;
    private final Integer x;
    private final Float y;
    private final int albumsCount;
    private final MusicGenre genre;
    private final int numberOfParticipants;
    private final String studioName;
    private final String studioAddress;

    public Update(ConsoleClient client, Receiver receiver, Integer id, String name, Integer x,
                  Float y, int albumsCount, MusicGenre genre, int numberOfParticipants, String studioName, String studioAddress) {
        super("update", client, receiver);
        this.id = id;
        this.name = name;
        this.albumsCount = albumsCount;
        this.genre = genre;
        this.studioAddress = studioAddress;
        this.studioName=studioName;
        this.x = x;
        this.y = y;
        this.numberOfParticipants = numberOfParticipants;
    }

    @Override
    public void execute() throws CollectionKeyException, WrongArgumentException {
        receiver.update(id, name, x, y, albumsCount, genre,
                numberOfParticipants, studioName, studioAddress);
    }

    @Override
    public String toString() {
        return name + " {" +
                "id=" + id +
                ", BandName='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", albumsCount=" + albumsCount +
                ", musicGenre=" + genre +
                ", numberOfParticioants=" + numberOfParticipants +
                ", Studio name='" + studioName + '\'' +
                ", Studio address=" + studioAddress+
                '}';
    }
}