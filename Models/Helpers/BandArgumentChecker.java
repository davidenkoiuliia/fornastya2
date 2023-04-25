package Models.Helpers;

import Exceptions.IO.WrongArgumentException;
import Models.Coordinates;
import Models.MusicGenre;
import Models.Studio;

import java.time.LocalDateTime;
import java.util.Objects;

public class BandArgumentChecker extends ArgumentChecker {
    public static void checkArguments(String name, Coordinates coordinates, LocalDateTime creationDate, int albumsCount, long numberOfParticipants, Studio studio) throws WrongArgumentException {
        BandArgumentChecker.checkName(name);
        BandArgumentChecker.checkCoordinates(coordinates);
        BandArgumentChecker.checkCreationDate(creationDate);
        BandArgumentChecker.checkNumOfParticipants(numberOfParticipants);
        BandArgumentChecker.checkStudio(studio);
        BandArgumentChecker.checkAlbumsCount(albumsCount);
    }
    public static void checkArguments(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate, int albumsCount, long numberOfParticipants, Studio studio) throws WrongArgumentException {
        BandArgumentChecker.checkID(id);
        BandArgumentChecker.checkName(name);
        BandArgumentChecker.checkCoordinates(coordinates);
        BandArgumentChecker.checkCreationDate(creationDate);
        BandArgumentChecker.checkNumOfParticipants(numberOfParticipants);
        BandArgumentChecker.checkStudio(studio);
        BandArgumentChecker.checkAlbumsCount(albumsCount);
    }

    public static void checkID(Integer id) throws WrongArgumentException {
        checkNull(id, "id");
        checkArgument(id > 0, "argument id cannot be <= 0");
    }

    public static void checkKey(Integer key) throws WrongArgumentException {
        checkNull(key, "key");
        checkArgument(key > 0, "argument key cannot be <= 0");
    }

    public static void checkName(String name) throws WrongArgumentException {
        checkNull(name, "name");
        checkArgument(!Objects.equals(name, ""), "argument name cannot be empty");
    }

    public static void checkCoordinates(Coordinates coordinates) throws WrongArgumentException {
        checkNull(coordinates, "coordinates");
    }

    public static void checkCreationDate(LocalDateTime creationDate) throws WrongArgumentException {
        checkNull(creationDate, "creationDate");
    }
    public static void checkAlbumsCount (int albumsCount) throws WrongArgumentException {
        checkArgument(albumsCount > 0, "the count of albums cannot be <= 0");
    }

    private static void checkStudio(Studio studio) throws WrongArgumentException {
        checkNull(studio, "studio");
    }

    private static void checkNumOfParticipants(long numberOfParticipants) throws WrongArgumentException {
        checkArgument(numberOfParticipants > 0, "number of participants cannot be <= 0");
    }

}