package XMLManager.MusicBandXMLs;

import Exceptions.IO.FilePermissionException;
import Exceptions.IO.CustomIOException;
import java.io.FileNotFoundException;

public interface MusicBandFileWriter {
    void write(MusicBandFileWriter movieCollection) throws FileNotFoundException, FilePermissionException, CustomIOException;
}
