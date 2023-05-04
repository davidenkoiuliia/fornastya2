package Reciever;

import Exceptions.IO.CustomIOException;
import Exceptions.IO.FilePermissionException;
import Exceptions.IO.InvalidFileDataException;
import Exceptions.IO.WrongArgumentException;
import Exceptions.Receiver.CollectionKeyException;
import Models.Band;
import Models.Coordinates;
import Models.MusicGenre;
import Models.Studio;
import XMLManager.MusicBandXMLs.MBXMLReader;
import XMLManager.MusicBandXMLs.MBXMLWriter;
import XMLManager.MusicBandXMLs.MusicBandFileReader;
import XMLManager.MusicBandXMLs.MusicBandFileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Receiver {
    private final MusicBandCollection mbCollection;
    MusicBandFileReader xmlFileReader;
    MusicBandFileWriter xmlFileWriter;
    public Receiver() throws InvalidFileDataException, FileNotFoundException, FilePermissionException {
        String path = System.getenv("LAB5");
        checkFile(path);

        this.xmlFileReader = new MBXMLReader(path);
        this.xmlFileWriter = new MBXMLWriter(path);

        this.mbCollection = xmlFileReader.read();
    }

    public String info() {
        return "*Collection info*\n" +
                "- Type of collection   : Hashmap of Band\n" +
                "- Date of initializing : " + mbCollection.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + "\n" +
                "- Number of elements   : " + mbCollection.length();
    }

    public HashMap<Integer, Band> show() {
        return mbCollection.getBandHashMap();
    }


    public void insert(Integer key, String name, Integer x, Float y, int albumsCount, MusicGenre genre, int numberOfParticipants, String studioName, String StudioAddress) throws CollectionKeyException, WrongArgumentException {
        if (mbCollection.getElementByKey(key) != null)
            throw new CollectionKeyException("key already exists");
        Band band = new Band( key,  name, new Coordinates(x, y), numberOfParticipants, albumsCount, genre,
                new Studio(studioName, StudioAddress));
        band.setID();
        mbCollection.put(key, band);
        System.out.println("*element added successfully*");
    }


    public void update(Integer id, String bandName, Integer x, Float y,  int albumCost, MusicGenre genre, int numberOfParticipants,
                       String name, String address) throws CollectionKeyException, WrongArgumentException {
        Band band = mbCollection.getElementByID(id);
        if (band == null)
            throw new CollectionKeyException("id does not exist");
        band.setName(bandName);
        band.setCoordinates(new Coordinates(x, y));
        band.setNumberOfParticipants(numberOfParticipants);
        band.setGenre(genre);
        band.setStudio(new Studio(name, address));
        System.out.println("*element updated successfully*");
    }

    public void removeKey(Integer key) throws CollectionKeyException {
        if (mbCollection.getElementByKey(key) == null)
            throw new CollectionKeyException("key does not exist");
        mbCollection.remove(key);
        System.out.println("*element removed successfully*");
    }

    public void clear() {
        mbCollection.clear();
        System.out.println("*collection cleared successfully*");
    }

    public void save() {
        try {
            xmlFileWriter.write((MusicBandCollection) mbCollection);
            System.out.println("*collection saved successfully*");
        } catch (FileNotFoundException | FilePermissionException | CustomIOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void removeLower(Integer key, String name, Integer x,
                            Float y, int albumsCount, MusicGenre genre, int numberOfParticipants, String studioName,
                            String studioAddress, LocalDateTime creationDate) throws WrongArgumentException {
        Band band = new Band(name, new Coordinates(x, y), numberOfParticipants, albumsCount, genre,
                new Studio(studioName, studioAddress));
        int count = mbCollection.removeLower(band);
        if (count == 0) {
            System.out.println("*no elements removed*");
        } else {
            System.out.println("* " + count + " elements removed successfully*");
        }
    }
    public void replaceIfGreater(Integer key, String name, Integer x,
                                 Float y, int albumsCount, MusicGenre genre, int numberOfParticipants, String studioName,
                                 String studioAddress) throws CollectionKeyException, WrongArgumentException {
        if (mbCollection.getElementByKey(key) == null)
            throw new CollectionKeyException("key does not exist");
        Band band = new Band(name, new Coordinates(x, y), numberOfParticipants, albumsCount, genre,
                new Studio(studioName, studioAddress));
        boolean replaced = mbCollection.replaceIfGreater(key, band);
        if (replaced) {
            band.setID();
            System.out.println("*element replaced successfully*");
        } else {
            System.out.println("*element was not replaced*");
        }
    }


    public void removeLowerKey(Integer key) {
        int count = mbCollection.removeLowerKey(key);
        if (count == 0) {
            System.out.println("*no elements removed*");
        } else {
            System.out.println("*" + count + " elements removed successfully*");
        }
    }
    public void count_by_albums(int albumsCount) {
        int count = mbCollection.count_by_albums(albumsCount);
        if (count == 0) {
            System.out.println("*there are no elements with albumsCount = " + albumsCount + "*");
        } else {
            System.out.println("*there are " + count + " elements with albumsCount = " + albumsCount + "*");
        }
    }
    public HashMap<Integer, Band> filter_starts_with_name(String name) {
        return mbCollection.filter_starts_with_name(name);
    }

    private void checkFile(String path) throws FileNotFoundException, FilePermissionException {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("! file " + path + " not found !");
        if (!file.canRead())
            throw new FilePermissionException("! no read and/or write permission for file " + path + "  !");
    }
}
