package Base;

import java.util.Date;
import java.util.Objects;

public class MainBand implements Comparable<MainBand> {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private Integer numberOfParticipants;
    private long albumsCount;
    private MusicGenre genre;
    private Studio studio;
    public Long getId()
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
    public Date getCreationDate()
    {
        return creationDate;
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
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
    public void setAlbumsCount(long albumsCount) {
        this.albumsCount = albumsCount;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainBand band = (MainBand) o;
        return numberOfParticipants == band.numberOfParticipants && albumsCount==band.albumsCount && id.equals(band.id) && name.equals(band.name) && coordinates.equals(band.coordinates) && creationDate.equals(band.creationDate) && Objects.equals(genre, band.genre) && Objects.equals(studio, band.studio);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, numberOfParticipants, albumsCount, genre, studio);
    }
    @Override
    public String toString() {
        return "This band [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", number of participants=" + numberOfParticipants +
                ", albums count=" + albumsCount +
                ", genre=" + genre +
                ", studio=" + studio +
                " ]";
    }
    @Override
    public int compareTo(MainBand o) {
        if (o == null||o.id == null) return -1;
        else if (this.name.equals(o.name) && this.genre.equals(o.genre))
            return 0;
        else
            return -1;
    }
}
