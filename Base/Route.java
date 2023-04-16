package Base;

import java.util.Objects;
import java.util.Date;

public class Route implements Comparable<Route>{
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.util.Date creationDate;
    private MusicGenre genre;
    private Studio studio;
    private int distance;
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
    public java.util.Date getCreationDate()
    {
        return creationDate;
    }
    public MusicGenre genre()
    {
        return genre;
    }
    public Studio studio()
    {
        return studio;
    }
    public int getDistance()
    {
        return distance;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void Studio(Studio studio) {
        this.studio = studio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return distance == route.distance && id.equals(route.id) && name.equals(route.name) && coordinates.equals(route.coordinates) && creationDate.equals(route.creationDate) && Objects.equals(studio, route.studio) && Objects.equals(genre, route.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studio, genre, distance);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", Studio=" + studio +
                ", Genre=" + genre +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(Route o) {
        if (o == null) return 1;
        if (o.id == null) return -1;
        if (this.distance - o.distance < 0)
            return -1;
        else if (this.distance == o.distance)
            return 0;
        else
            return 1;
    }
}