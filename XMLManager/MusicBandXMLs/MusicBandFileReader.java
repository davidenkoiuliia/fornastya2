package XMLManager.MusicBandXMLs;

import Exceptions.IO.FilePermissionException;
import Exceptions.IO.InvalidFileDataException;
import Reciever.MusicBandCollection;

import java.io.FileNotFoundException;

public interface MusicBandFileReader {
    MusicBandCollection read() throws FileNotFoundException, FilePermissionException, InvalidFileDataException;
}
