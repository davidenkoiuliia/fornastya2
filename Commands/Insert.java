package Commands;

import Client.ConsoleClient;
import Exceptions.IO.WrongArgumentException;
import Exceptions.Receiver.CollectionKeyException;
import Models.MusicGenre;
import Reciever.Receiver;

public class Insert extends AbstractCommand {
    private final Integer key;
    private final String name;
    private final Integer x;
    private final Float y;
    private final int albumsCount;
    private final MusicGenre genre;
    private final int numberOfParticipants;
    private final String studioName;
    private final String studioAddress;


    public Insert(ConsoleClient client, Receiver receiver, Integer key, String name, Integer x,
                  Float y, int albumsCount, MusicGenre genre, int numberOfParticipants, String studioName, String studioAddress) {
        super("insert", client, receiver);
        this.key = key;
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
        receiver.insert(key, name, x, y, albumsCount, genre,
                numberOfParticipants, studioName, studioAddress);
    }

    @Override
    public String toString() {
        return name + " {" +
                ", BandName='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", albumsCount=" + albumsCount +
                ", musicGenre=" + genre +
                ", numberOfParticipants=" + numberOfParticipants +
                ", Studio name='" + studioName + '\'' +
                ", Studio address=" + studioAddress+
                '}';
    }
}