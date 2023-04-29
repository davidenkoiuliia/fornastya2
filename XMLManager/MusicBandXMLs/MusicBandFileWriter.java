package XMLManager.MusicBandXMLs;
import Exceptions.IO.FilePermissionException;
import Exceptions.IO.CustomIOException;
import Reciever.MusicBandCollection;

import java.io.FileNotFoundException;

public interface MusicBandFileWriter {
    void write(MusicBandCollection mbCollection) throws FileNotFoundException, FilePermissionException, CustomIOException;
}