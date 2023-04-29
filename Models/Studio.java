package Models;

import java.util.Objects;

public class Studio {
    private String name;
    private String address;
    public Studio(String name, String address) {
        this.name = name;
        this.address = address;
 }
    public String getName()
    {
        return name;
    }
    public String getAddress()
    {
        return address;
    }
    public void setName (String name) {
        this.name=name;
    }
    public void setAddress (String address) {
        this.address=address;
    }
    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Models.Studio studio = (Models.Studio) o;
        return name.equals(studio.name)&&address.equals(studio.address);
    }
    @Override
    public int hashCode(){
        return Objects.hash(name, address);
    }
    @Override
    public String toString(){
        return "Студия ["+
                "название =" + name +
                "адрес= " + address+
                "]";
    }
}
