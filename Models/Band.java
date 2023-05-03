package Models;

import Reciever.MusicBandCollection;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.lang.Math.max;
import static javax.swing.text.html.HTML.Tag.I;

public class Band implements Comparable<Band> {
    private static Integer nextID = 1;
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private Integer numberOfParticipants;
    private int albumsCount;
    private MusicGenre genre;
    private Studio studio;
    public Band(String bandName, Coordinates coordinates, int numberOfParticipants, int albumsCount, MusicGenre genre, Studio studio) {
        this.name=bandName;
        this.coordinates=coordinates;
        this.numberOfParticipants=numberOfParticipants;
        this.albumsCount=albumsCount;
        this.genre=genre;
        this.studio=studio;
    }

    public Band(int id, String bandName, Coordinates coordinates, int numberOfParticipants, int albumsCount, MusicGenre genre, Studio studio) {
        this.id=id;
        this.name=bandName;
        this.coordinates=coordinates;
        this.numberOfParticipants=numberOfParticipants;
        this.albumsCount=albumsCount;
        this.genre=genre;
        this.studio=studio;
    }

    public static void updateNextId(MusicBandCollection bandCollection) {
        int maxID = bandCollection
                .getBandHashMap().values()
                .stream().filter(Objects::nonNull)
                .map(Band::getId)
                .mapToInt(Integer->nextID.intValue()).max().orElse(0);
        nextID = maxID + 1;
    }
    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
    public Coordinates getCoordinates()
    {
        return coordinates;
    }
    public Integer getNumberOfParticipants()
    {
        return numberOfParticipants;
    }
    public long getAlbumsCount()
    {
        return albumsCount;
    }
    public MusicGenre getGenre()
    {
        return genre;
    }
    public Studio getStudio()
    {
        return studio;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
    public void setAlbumsCount(int albumsCount) {
        this.albumsCount = albumsCount;
    }
    public void setID() {
        this.id = id;
        nextID+=1;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStudio(Studio studio) {
        this.studio = studio;
    }
    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Band band = (Band) o;
        return numberOfParticipants == band.numberOfParticipants && albumsCount==band.albumsCount && id.equals(band.id) && name.equals(band.name) && coordinates.equals(band.coordinates) && Objects.equals(genre, band.genre) && Objects.equals(studio, band.studio);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, numberOfParticipants, albumsCount, genre, studio);
    }
    @Override
    public String toString() {
        return "This band [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", number of participants=" + numberOfParticipants +
                ", albums count=" + albumsCount +
                ", genre=" + genre +
                ", studio=" + studio +
                " ]";
    }
    @Override
    public int compareTo(Band o) {
        if (o == null||o.id == null) return -1;
        else if (this.name.equals(o.name) && this.genre.equals(o.genre))
            return 0;
        else
            return -1;
    }
}